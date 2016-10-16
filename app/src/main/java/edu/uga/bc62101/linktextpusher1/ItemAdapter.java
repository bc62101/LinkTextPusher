package edu.uga.bc62101.linktextpusher1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<Item> {

    private ArrayList<Item> items;

    public ItemAdapter(Context context, int textViewResourceId, ArrayList<Item> items ){
        super(context, textViewResourceId, items);
        this.items = items;
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent ){
        View v = convertView;

    }

}
