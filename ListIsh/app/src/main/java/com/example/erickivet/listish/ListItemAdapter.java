package com.example.erickivet.listish;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;


import java.util.List;

/**
 * Created by erickivet on 8/21/16.
 */

public class ListItemAdapter extends ArrayAdapter<ListItems> {

    List<ListItems> mItems;

    public ListItemAdapter(Context context, List <ListItems> items){
       super(context,android.R.layout.simple_list_item_multiple_choice,items);
       mItems = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ListItems item = mItems.get(position);
        ListItems checked = mItems.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(android.R.layout.simple_list_item_multiple_choice,parent,false);
        }

        CheckedTextView itemName = (CheckedTextView) convertView.findViewById(android.R.id.text1);

        itemName.setText(item.getItem());
        itemName.setChecked(checked.isChecked());


        return convertView;
    }
}
