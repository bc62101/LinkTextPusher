/**
 * MainActivity.java
 * -----------------
 * @author: Benson Chau
 * Purpose: The core of the app; handles authentication, posts to database and removals.
 */

package edu.uga.bc62101.linktextpusher1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");

        // TODO: Add auto-login/remember me checkbox. If checked, login automatically. If not, log out on pause.

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        if( mFirebaseUser == null ){
            loadLogInView();
        } else {
            mUserId = mFirebaseUser.getUid();

            final ListView listView = (ListView) findViewById(R.id.listView);

            final ItemAdapter adapter = new ItemAdapter(this,
                    R.layout.list_item);

            listView.setAdapter(adapter);

            final EditText text = (EditText) findViewById(R.id.itemText);
            final Button button = (Button) findViewById(R.id.sendButton);

            button.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick( View v ){
                    Item item = new Item(text.getText().toString(), mFirebaseUser);
                    mDatabase.child("users").child(mUserId).child("items")
                            .push().child("content").setValue(item.getContent());
                    text.getText().clear();

                    // TODO: If there is a link in content, open it with an intent.

                    // TODO: Add listview animation when more items are added.
                }
            });

            // TODO: Connect users with each other in the database

            mDatabase.child("users").child(mUserId).child("items")
                    .addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            String itemContent = (String) dataSnapshot.child("content").getValue();
                            Item item = new Item(itemContent, mFirebaseUser);

                            adapter.add(item);
                            Log.i(TAG, "Database child added");
                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {
                            /*
                            String itemContent = (String) dataSnapshot.child("content").getValue();
                            Item item = new Item(itemContent, mFirebaseUser);
                            adapter.remove(item);
                            */
                            Log.i(TAG, "Database child removed");
                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


            listView.setOnItemClickListener( new AdapterView.OnItemClickListener(){
                public void onItemClick( AdapterView<?> parent, View view, int position, long id ){
                    mDatabase.child("users").child(mUserId).child("items")
                            .orderByChild("content")
                            //.equalTo((String) listView.getItemAtPosition(position))
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    // TODO: Allow users to edit data by clicking on it.

                                    /*
                                    if( dataSnapshot.hasChildren() ){
                                        DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
                                        firstChild.getRef().removeValue();
                                    }
                                    */

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                }
            });


        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            mFirebaseAuth.signOut();
            loadLogInView();
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Starts the login screen intent.
     */
    private void loadLogInView() {
        Intent intent = new Intent(this, LogInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

        Log.d(TAG, "Loaded login view");
    }

}
