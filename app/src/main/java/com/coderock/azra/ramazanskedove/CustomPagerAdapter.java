package com.coderock.azra.ramazanskedove;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Azra on 30.5.2016.
 */
public class CustomPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    public CustomPagerAdapter(FragmentManager fragmentManager, Context c) {
        super(fragmentManager);
        mContext = c;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentPage.newInstance(position);
    }

    @Override
    public int getCount() {
        return FragmentPage.CustomPagerEnum.values().length;
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        FragmentPage.CustomPagerEnum customPagerEnum = FragmentPage.CustomPagerEnum.values()[position];
        return mContext.getString(customPagerEnum.getmTitleResId());
    }





}
