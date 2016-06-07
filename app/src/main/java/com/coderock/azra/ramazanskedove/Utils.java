package com.coderock.azra.ramazanskedove;

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
     * @return
     * returns today's Ramadan day: 0 - 28 if it is Ramadan
     * returns -1 if it is not Month Ramadan
     */
    public static int getDayInRamadan() {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String start_date ="06/06/2016";
        String end_date ="04/07/2016";
        Date today = new Date();

        Date  startDate = null;
        Date  endDate = null;
        try {
            startDate = (Date)formatter.parse(start_date);
            endDate = (Date)formatter.parse(end_date);
            today = (Date) formatter.parse(formatter.format(today));
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }finally {

        }
        // generate list of days in Ramadan in Date format
        List<Date> dates = new ArrayList<Date>();
        Date iDate = startDate;
        if (iDate != null) {
            while (!iDate.after(endDate)) {
                dates.add(iDate);
                iDate = addDayToDate(iDate);
            }
        }
        // generate list of days in Ramadan in Date format
        if(!today.before(startDate) && !today.after(endDate)){
            for (int i = 0; i < dates.size(); i++){
                Date dateItem = dates.get(i);
                Log.d("TAG", " i " +dateItem);
                if (dateItem.equals(today)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * @return returns String for today's Date in format "20. Juni"
     */

    public static String getTodayDayAndMonthAsString(){
        Date today = new Date();

        String intMonth = (String) android.text.format.DateFormat.format("MM", today); //example "06"
        String day = (String) android.text.format.DateFormat.format("dd", today); //example "20"

        int m = Integer.parseInt(intMonth); //convert month to int

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

        return day + ". " + months.get(m-1); //example "20. Juni"
    }


    /**
     * @return
     * returns String for today's Date in format "20. Juni / 05. Ramazan" if it is Ramazan
     * returns String for today's Date in format "20. Juli" if it is not Ramazan
     */

    public static String getTodaysDateAsTitle(){
        if(getDayInRamadan() < 0){
            return getTodayDayAndMonthAsString();
        }else {
            int day = getDayInRamadan() + 1;
            String ramadanDate = day + ". Ramazan";
            return getTodayDayAndMonthAsString() + " / " + ramadanDate;
        }
    }

    /**
     * @param date - Date object
     * @return Date which is one day after parametar date
     */

    public static Date addDayToDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    /**
     * @param date - Date
     * @return returns same Date with time set to midnight
     */

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



