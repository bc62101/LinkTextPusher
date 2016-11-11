/**
 * Item.java
 * ---------
 * Defines the template for messages and link items sent by the user.
 * @author: Benson Chau
 */
package edu.uga.bc62101.linktextpusher1;

import android.content.Intent;
import android.net.Uri;

import com.google.firebase.auth.FirebaseUser;

public class Item {

    // TODO: Replace with something better here.

    private String content;
    private String source;
    private FirebaseUser user;

    //public Item() {}

    /**
     * Sole constructor of the Item class. Model for holding the content, user, location data.
     * @param content is the message sent.
     * @param user is the user that sent the message.
     */
    public Item(String content, FirebaseUser user) {
        this.content = content;
        this.user = user;
    }

    /**
     * Get the content string.
     * @return the message.
     */
    public String getContent() {
        return content;
    }

    /**
     * Return true if the content is a link, false if the content is not a link.
     * @return a boolean indicating if the message is a link.
     */
    // TODO: Parse content to find if it contains a link.
    public boolean isLink() {
        return true;
    }

    /**
     * Get the username as a String.
     * @return a string with the user's displayname.
     */
    public String getUsername() {
        return this.user.getDisplayName();
    }

    /**
     * Get the source of the message- i.e if I send it from my android, the source is my android phone.
     * @return a string containing the source of the message.
     */
    public String getSource() {
        return "Source here";
    }

    /**
     * Return a browser intent that opens the link in the message (if there is one).
     * @return an Intent that opens the content.
     */
    public Intent openLink() {
        // TODO: Replace with a better way to parse content string


        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(this.content));
        //startActivity(browserIntent);

        return browserIntent;
    }



}
