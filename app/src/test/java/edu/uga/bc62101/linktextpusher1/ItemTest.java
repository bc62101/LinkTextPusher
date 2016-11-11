package edu.uga.bc62101.linktextpusher1;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {

    private Item item;
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();

    @Test
    public void isLink(){
        item = new Item("http://www.google.com", mFirebaseUser);

        assertTrue(item.isLink());
    }



}
