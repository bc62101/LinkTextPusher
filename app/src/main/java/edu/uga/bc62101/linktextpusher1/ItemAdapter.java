package edu.uga.bc62101.linktextpusher1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        if( v == null ){
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.list_item, null);
        }

        Item item = items.get(position);

        if( item != null ){
            TextView content = (TextView) v.findViewById(R.id.content);
            TextView username = (TextView) v.findViewById(R.id.username);
            TextView source = (TextView) v.findViewById(R.id.source);

            if( username != null ){
                username.setText(item.getUserID());
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
