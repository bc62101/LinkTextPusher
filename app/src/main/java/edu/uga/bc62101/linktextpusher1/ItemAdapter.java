/**
 * ItemAdapter.java
 * ----------------
 * Defines and adds to the ListView the contents of Items.
 * @author: Benson Chau
 */

package edu.uga.bc62101.linktextpusher1;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<Item> {

    private LayoutInflater inflater;
    private ArrayList<Item> items = new ArrayList<>();

    /**
     * Adapter constructor without specifying the list of items to add to the new view.
     * @param context Current state of the application.
     * @param textViewResourceId ID of the particular layout to be used
     */
    public ItemAdapter( Context context, int textViewResourceId ){
        super(context, textViewResourceId);
        this.inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * Adapter constructor specifying the list of items to add to the new view.
     * @param context Current state of the application.
     * @param textViewResourceId ID of the particular layout to be used.
     * @param items List of items to populate the new view with.
     */
    public ItemAdapter( Context context, int textViewResourceId, ArrayList<Item> items ){
        super(context, textViewResourceId, items);
        this.inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void add( Item item ){
        Log.w("ItemAdapter", "add");
        items.add(item);
        notifyDataSetChanged();
    }

    public void remove( Item item ){
        Log.w("ItemAdapter", "remove");
        items.remove(item);
        notifyDataSetChanged();
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent ){
        Log.w("ItemAdapter", "getView");
        View v = convertView;

        if( v == null ){
            v = inflater.inflate(R.layout.list_item, null);
        }

        Item item = items.get(position);

        if( item != null ){
            TextView content = (TextView) v.findViewById(R.id.content);
            TextView username = (TextView) v.findViewById(R.id.username);
            TextView source = (TextView) v.findViewById(R.id.source);

            if( username != null ){
                username.setText(item.getUsername());
            }

            if( content != null ){
                content.setText(item.getContent());
            }

            if( source != null ){
                source.setText(item.getSource());
            }
        }

        return v;
    }

}
