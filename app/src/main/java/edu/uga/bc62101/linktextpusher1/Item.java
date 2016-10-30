package edu.uga.bc62101.linktextpusher1;

import android.content.Intent;
import android.net.Uri;

/**
 * Item.java
 * ---------
 * Defines the template for messages and link items sent by the user.
 * @author: Benson Chau
 */

import com.google.firebase.auth.FirebaseUser;

public class Item {

    private String content;
    private String source;
    private FirebaseUser user;

    //public Item() {}

    public Item(String content, FirebaseUser user){
        this.content = content;
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public String getUserID(){
        return this.user.getDisplayName();
    }

    public String getSource(){
        return "Source here";
    }



}
