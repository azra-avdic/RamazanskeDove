package com.example.azra.ramazanskedove;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Azra on 30.5.2016.
 */
public class CustomPagerAdapter extends PagerAdapter {

    private Context mContext;

    public CustomPagerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, final int position) {
        CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.custompager_item, collection, false);

        TextView tvTitle = (TextView) layout.findViewById(R.id.tvTitle);
        TextView tvDoaAbrab = (TextView) layout.findViewById(R.id.tvDoaArab);
        TextView tvDoa = (TextView) layout.findViewById(R.id.tvDoa);
        TextView tvHadis = (TextView) layout.findViewById(R.id.tvHadis);

        tvTitle.setText("Dova za " + getPageTitle(position));
        tvDoaAbrab.setText(getDoaArab(position));
        tvDoa.setText(getDoa(position));
        tvHadis.setText(getHadis(position));

        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return CustomPagerEnum.values().length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
        return mContext.getString(customPagerEnum.getmTitleResId());
    }


    public CharSequence getDoaArab(int position) {
        CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
        return mContext.getString(customPagerEnum.getmDoaArabResId());
    }

    public CharSequence getDoa(int position) {
        CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
        return mContext.getString(customPagerEnum.getmDoaResId());
    }

    public CharSequence getHadis(int position) {
        CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
        return mContext.getString(customPagerEnum.getmHadisResId());
    }

    public enum CustomPagerEnum {
        DAY1(R.string.day1_title, R.string.daily_doa1_arabic, R.string.daily_doa1, R.string.daily_hadis1),
        DAY2(R.string.day2_title, R.string.daily_doa2_arabic, R.string.daily_doa2, R.string.daily_hadis2),
        DAY3(R.string.day3_title, R.string.daily_doa3_arabic, R.string.daily_doa3, R.string.daily_hadis3),
        DAY4(R.string.day4_title, R.string.daily_doa4_arabic, R.string.daily_doa4, R.string.daily_hadis4),
        DAY5(R.string.day5_title, R.string.daily_doa5_arabic, R.string.daily_doa5, R.string.daily_hadis5),
        DAY6(R.string.day6_title, R.string.daily_doa6_arabic, R.string.daily_doa6, R.string.daily_hadis6),
        DAY7(R.string.day7_title, R.string.daily_doa7_arabic, R.string.daily_doa7, R.string.daily_hadis7),
        DAY8(R.string.day8_title, R.string.daily_doa8_arabic, R.string.daily_doa8, R.string.daily_hadis8),
        DAY9(R.string.day9_title, R.string.daily_doa9_arabic, R.string.daily_doa9, R.string.daily_hadis9),
        DAY10(R.string.day10_title, R.string.daily_doa10_arabic, R.string.daily_doa10, R.string.daily_hadis10),
        DAY11(R.string.day11_title, R.string.daily_doa11_arabic, R.string.daily_doa11, R.string.daily_hadis11),
        DAY12(R.string.day12_title, R.string.daily_doa12_arabic, R.string.daily_doa12, R.string.daily_hadis12),
        DAY13(R.string.day13_title, R.string.daily_doa13_arabic, R.string.daily_doa13, R.string.daily_hadis13),
        DAY14(R.string.day14_title, R.string.daily_doa14_arabic, R.string.daily_doa14, R.string.daily_hadis14),
        DAY15(R.string.day15_title, R.string.daily_doa15_arabic, R.string.daily_doa15, R.string.daily_hadis15),
        DAY16(R.string.day16_title, R.string.daily_doa16_arabic, R.string.daily_doa16, R.string.daily_hadis16),
        DAY17(R.string.day17_title, R.string.daily_doa17_arabic, R.string.daily_doa17, R.string.daily_hadis17),
        DAY18(R.string.day18_title, R.string.daily_doa18_arabic, R.string.daily_doa18, R.string.daily_hadis18),
        DAY19(R.string.day19_title, R.string.daily_doa19_arabic, R.string.daily_doa19, R.string.daily_hadis19),
        DAY20(R.string.day20_title, R.string.daily_doa20_arabic, R.string.daily_doa20, R.string.daily_hadis20),
        DAY21(R.string.day21_title, R.string.daily_doa21_arabic, R.string.daily_doa21, R.string.daily_hadis21),
        DAY22(R.string.day22_title, R.string.daily_doa22_arabic, R.string.daily_doa22, R.string.daily_hadis22),
        DAY23(R.string.day23_title, R.string.daily_doa23_arabic, R.string.daily_doa23, R.string.daily_hadis23),
        DAY24(R.string.day24_title, R.string.daily_doa24_arabic, R.string.daily_doa24, R.string.daily_hadis24),
        DAY25(R.string.day25_title, R.string.daily_doa25_arabic, R.string.daily_doa25, R.string.daily_hadis25),
        DAY26(R.string.day26_title, R.string.daily_doa26_arabic, R.string.daily_doa26, R.string.daily_hadis26),
        DAY27(R.string.day27_title, R.string.daily_doa27_arabic, R.string.daily_doa27, R.string.daily_hadis27),
        DAY28(R.string.day28_title, R.string.daily_doa28_arabic, R.string.daily_doa28, R.string.daily_hadis28),
        DAY29(R.string.day29_title, R.string.daily_doa29_arabic, R.string.daily_doa29, R.string.daily_hadis29),
        DAY30(R.string.day30_title, R.string.daily_doa30_arabic, R.string.daily_doa30, R.string.daily_hadis30);


        private int mTitleResId;
        private int mDoaArabResId;
        private int mDoaResId;
        private int mHadisResId;


    CustomPagerEnum(int titleResId, int doaArabResId, int doaResId, int hadisResId) {
            mTitleResId = titleResId;
            mDoaArabResId = doaArabResId;
            mDoaResId = doaResId;
            mHadisResId = hadisResId;
        }

        public int getmTitleResId() {
            return mTitleResId;
        }

        public int getmDoaArabResId() {
            return mDoaArabResId;
        }

        public int getmDoaResId() {
            return mDoaResId;
        }

        public int getmHadisResId() {
            return mHadisResId;
        }
    }



}
