package com.fixpocket.Adapter;

/**
 * Created by lakum on 17-04-2017.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fixpocket.Fragment.ExtrasForBeatDeatail;
import com.fixpocket.Fragment.discriptionForBeatDetail;

public class ViewPaggerAdapter extends FragmentPagerAdapter {

    // An array of tab titles(labels)
    String titles[];
    // Should be initialized with number of tabs
    int numOfTabs;
    // Use support fragment for your app to work on earlier devics
    // fragments were introduced in android 3.0, api 11
    Fragment fragment;

    // Constructor, initializes passed arguments with class values
    public ViewPaggerAdapter(FragmentManager fm, String titles[], int numOfTabs) {
        super(fm);
        this.titles = titles;
        this.numOfTabs = numOfTabs;
    }

    // This method returns a fragment object, of the selected tab
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                fragment = new discriptionForBeatDetail();
                return fragment;
            case 1:
                fragment = new ExtrasForBeatDeatail();
                return fragment;
            case 2:
                fragment = new ExtrasForBeatDeatail();
                return fragment;
            default:
                return null;
        }
    }

    // Returns the tab's title
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    // Returns the number of tabs
    @Override
    public int getCount() {
        return numOfTabs;
    }


}
