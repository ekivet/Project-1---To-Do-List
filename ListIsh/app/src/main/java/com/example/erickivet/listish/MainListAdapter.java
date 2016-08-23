package com.example.erickivet.listish;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by erickivet on 8/20/16.
 */


//set view via layout inflater

public class MainListAdapter extends ArrayAdapter<MainList>{
    private List<MainList> mMainList;

    public MainListAdapter(Context context, List<MainList> mainList){
        super(context, android.R.layout.simple_list_item_1,mainList);

        mMainList = mainList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);
        }


        TextView mainListTextView = (TextView) convertView.findViewById(android.R.id.text1);

        mainListTextView.setText(mMainList.get(position).getListTitle());

        return convertView;
    }
}
