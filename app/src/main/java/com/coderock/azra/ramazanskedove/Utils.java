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
import java.util.Locale;

public class Utils {

    public static final String ramadan_start_date ="06/06/2016";
    public static final String ramadan_end_date ="04/07/2016";

    public static String getString(Context context, int stringId) {
        if (context != null) {
            String mString = context.getResources().getString(stringId);
            return TextUtils.isEmpty(mString) ? "" : mString;
        }
        return "";
    }

    public static int getColor(Context context, int colorId){
        int color = android.R.color.holo_red_light;
        if (context != null) {
            color = ContextCompat.getColor(context, colorId);
        }
        return color;
    }

    public static void showToast(Context context, String message){
        if (context != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    public static int getDayInRamadan() {
        Date currentDate, ramadanStartDate, ramadanEndDate;
        try {
            ramadanStartDate = createDateObjectFromString(ramadan_start_date);
            ramadanEndDate =  createDateObjectFromString(ramadan_end_date);
            currentDate = getDateObjectForToday();
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
        List<Date> datesInRamadan = getListOfDatesBetweenTwoDatesInclusive(ramadanStartDate, ramadanEndDate);
        return datesInRamadan.indexOf(currentDate);
    }

    public static Date createDateObjectFromString(String string) throws ParseException {

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date = null;
        try {
            date = (Date)formatter.parse(string);
        } catch (ParseException e) {
            throw(e);
        }

        return date;
    }

    public static Date getDateObjectForToday() throws ParseException{

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String today= formatter.format(new Date());
        Date date = null;
        try {
            date = (Date)formatter.parse(today);
        } catch (ParseException e) {
            throw(e);
        }
        return date;
    }

    public static List<Date> getListOfDatesBetweenTwoDatesInclusive(Date startDate, Date endDate){
        List<Date> dates = new ArrayList<Date>();
        if (startDate != null) {
            while (!startDate.after(endDate)) {
                dates.add(startDate);
                startDate = addDayToDate(startDate);
            }
        }
        return dates;
    }

    public static String getTodayDayAndMonthAsString(){
        Date today = new Date();
        String day = (String) android.text.format.DateFormat.format("dd", today); //example "20"
        String month = getCurrentMonthAsString(); // example "Juni"

        return day + ". " + month; //example "20. Juni"
    }

    public static String getCurrentMonthAsString(){
        Date today = new Date();
        String intMonth = (String) android.text.format.DateFormat.format("MM", today); //example "06"
        return convertIntMonthToBosnianMonthName(intMonth);
    }

    public static String convertIntMonthToBosnianMonthName(String intMonth){
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
        return months.get(m-1);
    }

    public static Date addDayToDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
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



