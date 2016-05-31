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

        final TextView tvContent = (TextView) layout.findViewById(R.id.tvContent);
        tvContent.setText(getPageContent(position));

        final Button btnShowArabic = (Button) layout.findViewById(R.id.btnShowArabic);

        btnShowArabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isBS = btnShowArabic.getText().toString().contentEquals("PRIKAŽI NA ARAPSKOM");
                if(isBS){
                    tvContent.setText(getPageContentArabic(position));
                    btnShowArabic.setText("PRIKAŽI NA BOSANSKOM");
                }
                else{
                    tvContent.setText(getPageContent(position));
                    btnShowArabic.setText("PRIKAŽI NA ARAPSKOM");
                }



            }
        });

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
        return mContext.getString(customPagerEnum.getTitleResId());
    }


    public CharSequence getPageContent(int position) {
        CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
        return mContext.getString(customPagerEnum.getContentResId());
    }

    public CharSequence getPageContentArabic(int position) {
        CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
        return mContext.getString(customPagerEnum.getContentArabicResId());
    }

    public enum CustomPagerEnum {

        DAY1(R.string.day1_title,R.string.day1, R.string.day1_arabic),
        DAY2(R.string.day2_title, R.string.day2, R.string.day2_arabic),
        DAY3(R.string.day3_title, R.string.day3, R.string.day3_arabic);


        private int mTitleResId;
        private int mContentResId;
        private int mContentArabicResId;

        CustomPagerEnum(int titleResId, int contentResId, int contentArabicResId) {
            mContentResId = contentResId;
            mTitleResId = titleResId;
            mContentArabicResId = contentArabicResId;
        }

        public int getContentResId() {
            return mContentResId;
        }

        public int getTitleResId() {
            return mTitleResId;
        }

        public int getContentArabicResId() {
            return mContentArabicResId;
        }
    }



}
