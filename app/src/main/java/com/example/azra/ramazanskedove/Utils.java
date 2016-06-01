package com.example.azra.ramazanskedove;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Azra on 1.6.2016.
 */
public class Utils {
     /**
     * @param stringId - string id
     * @return string from resources
     */
    public static String getString(Context context, int stringId) {
        if (context != null) {
            String mString = context.getResources().getString(stringId);
            return TextUtils.isEmpty(mString) ? "" : mString;
        }

        return "";
    }

    /**
     * @param colorId color id
     */
    public static int getColor(Context context, int colorId){
        int color = android.R.color.holo_red_light;
        if (context != null) {
            color = ContextCompat.getColor(context, colorId);
        }

        return color;
    }

    /**
     * @param message - message for toast
     * message length is SHORT
     */
    public static void showToast(Context context, String message){
        if (context != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * @return return current time
     */
    public static String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy h:mm a");
        return sdf.format(calendar.getTime());
    }


    /**
     * @return return current time
     */
    public static int getDayInRamadan() {
        Date today = new Date();
        today = setTimeToMidnight(today);

        List<Date> dates = new ArrayList<Date>();

         //String str_date ="06/06/2016";
         //String end_date ="05/07/2016";

        String str_date ="06/05/2016";
        String end_date ="05/06/2016";


        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date  startDate = null;
        Date  endDate = null;
        try {
            startDate = (Date)formatter.parse(str_date);
            endDate = (Date)formatter.parse(end_date);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
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

        Log.d("TAG", " start " + startDate );
        Log.d("TAG", " end " + endDate );
        Log.d("TAG", " today " + today );

        if(today.after(startDate) && !today.after(endDate)){

            Log.d("TAG", " tu smo ");
            for (int i = 0; i < dates.size(); i++){
                Date dateItem = dates.get(i);
                if (dateItem.equals(today)) {
                    Log.d("TAG", " danas je.. item u  Ramazanu.." + i);
                    return i;
                }
            }
        }
        return -1;
    }


    public static String getTodayAsStringDayAndMonth(){
        Date today = new Date();

        String intMonth = (String) android.text.format.DateFormat.format("MM", today); //06
        String day = (String) android.text.format.DateFormat.format("dd", today); //20

        int m = Integer.parseInt(intMonth);

        ArrayList<String> months = new ArrayList<String>();
        months.add("Januar");
        months.add("Februar");
        months.add("Mart");
        months.add("April");
        months.add("Maj");
        months.add("Juni");
        months.add("Juli");
        months.add("August");
        months.add("Septembar");
        months.add("Oktobar");
        months.add("Novembar");
        months.add("Decembar");

        return day + ". " + months.get(m-1);
    }


    public static String getTodaysDateAsTitle(){
        if(getDayInRamadan() < 0){
            return getTodayAsStringDayAndMonth();
        }else {
            int day = getDayInRamadan() + 1;
            String ramadanDate = day + ". Ramazan";
            return getTodayAsStringDayAndMonth() + " / " + ramadanDate;
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



