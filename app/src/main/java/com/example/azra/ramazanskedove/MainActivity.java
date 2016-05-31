package com.example.azra.ramazanskedove;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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
        viewPager.setCurrentItem(5);

        Date today = new Date();
        today = setTimeToMidnight(today);

        String intMonth = (String) android.text.format.DateFormat.format("MM", today); //06
        String day = (String) android.text.format.DateFormat.format("dd", today); //20

        List<Date> dates = new ArrayList<Date>();

//        String str_date ="06/06/2016";
  //      String end_date ="05/07/2016";

        String str_date ="27/05/2016";
        String end_date ="06/06/2016";


        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date  startDate = null;
        Date  endDate = null;
        try {
            startDate = (Date)formatter.parse(str_date);
            endDate = (Date)formatter.parse(end_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }finally {

        }

        Date iDate = startDate;
        if (iDate != null) {
            Log.d("TAG", " not null" );
            while (!iDate.after(endDate)) {
                dates.add(iDate);
                iDate = addDay (iDate);
            }
        }

        Log.d("TAG", " dates " + dates.size() );

        Log.d("TAG", " stat " + startDate );
        Log.d("TAG", " end " + endDate );
        Log.d("TAG", " today " + today );


        if(today.after(startDate) && !today.after(endDate)){

            Log.d("TAG", " tu smo ");
            for (int i = 0; i < dates.size(); i++){
                Date dateItem = dates.get(i);
                Log.d("TAG", " ITEM " + dateItem );
                if (dateItem.equals(today)) {
                    Log.d("TAG", " dobnitnik" + i);
                }
                }
            }
        }







    public static Date addDay(Date d){
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.DATE, 1);
            return cal.getTime();
        }



    public static Date setTimeToMidnight(Date date) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime( date );
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }








}
