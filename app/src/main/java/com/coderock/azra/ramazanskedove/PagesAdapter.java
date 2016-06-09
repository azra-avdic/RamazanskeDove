package com.coderock.azra.ramazanskedove;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagesAdapter extends FragmentPagerAdapter {

    private Context mContext;
    public PagesAdapter(FragmentManager fragmentManager, Context c) {
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
