package com.example.erickivet.listish;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erickivet on 8/20/16.
 */

//List Singleton
public class MainListSingleton {
    private static MainListSingleton mMainListSingleton = null;
    private static List <MainList> mMainList;

    private MainListSingleton(){
        mMainList = new ArrayList<>();
    }

    public static MainListSingleton getInstance() {

        if(mMainListSingleton == null)
            mMainListSingleton = new MainListSingleton();

        return mMainListSingleton;
    }

    public void setMainList(MainList mainList) {
        mMainList.add(mainList);
    }

    public List<MainList> getMainList(){
        return mMainList;
    }
}
