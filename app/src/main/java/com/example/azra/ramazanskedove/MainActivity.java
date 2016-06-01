package com.example.azra.ramazanskedove;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CustomPagerAdapter(this));
        viewPager.setCurrentItem(Utils.getDayInRamadan() < 0 ? 0: Utils.getDayInRamadan());
        Log.d("TAG", Utils.getTodayAsStringDayAndMonth());

        TextView tvToday = (TextView) findViewById(R.id.tvToday);

        if (tvToday != null) {
            tvToday.setText(Utils.getTodaysDateAsTitle());
        }

    }


}
