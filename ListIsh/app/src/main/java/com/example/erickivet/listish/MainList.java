package com.example.erickivet.listish;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erickivet on 8/20/16.
 */
public class MainList {
    private String mListTitle;
    private List<ListItems> mListOfItems;

    public MainList (String listTitle){
        mListTitle = listTitle;
        mListOfItems = new ArrayList<>();
    }

    public String getListTitle(){
        return mListTitle;
    }

    public List<ListItems> getListItems(){
        return mListOfItems;
    }

    public void setmListTitle(String mListTitle) {
        this.mListTitle = mListTitle;
    }

    public void setmListOfItems(List<ListItems> mListOfItems) {
        this.mListOfItems = mListOfItems;
    }
}
