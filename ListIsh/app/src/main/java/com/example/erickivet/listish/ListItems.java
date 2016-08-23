package com.example.erickivet.listish;

/**
 * Created by erickivet on 8/20/16.
 */
public class ListItems {
    private String item;
    private boolean isChecked;


    public ListItems(String item, boolean isChecked) {
        this.item = item;
        this.isChecked = false;
    }



    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean checked) {
        isChecked = checked;
    }


}
