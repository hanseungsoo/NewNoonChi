package com.example.han.newnewnoon;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by han on 2015-10-12.
 */
public class registerAlarm {
    Context context;

    public registerAlarm(Context context) {
        super();
        this.context = context;
    }

    public void testAM(String index,int hour,int minute) {

        try {
            long atime = System.currentTimeMillis();

            Calendar curTime = Calendar.getInstance();
            curTime.set(Calendar.HOUR_OF_DAY, hour);
            curTime.set(Calendar.MINUTE, minute);
            curTime.set(Calendar.SECOND, 0);
            curTime.set(Calendar.MILLISECOND, 0);
            long btime = curTime.getTimeInMillis();
            long triggerTime = btime;
            if (atime > btime)
                triggerTime += 1000 * 60 * 60 * 24;
            Intent intentMyService;
            intentMyService = new Intent(index);
            AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
            PendingIntent sender = PendingIntent.getBroadcast(context, 0, intentMyService, 0);

            // 서비스 시작
            am.set(AlarmManager.RTC, triggerTime, sender);
        } catch (Exception e) {
            Log.d("MpMainActivity", e.getMessage() + "");

            e.printStackTrace();
        }
    }
    public void registerAM(String idIndex,String index) {
        SharedInit SI = new SharedInit(context);
        try {

            Intent intentMyService;
            intentMyService = new Intent(idIndex);

            AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
            PendingIntent sender = PendingIntent.getBroadcast(context, 0, intentMyService, 0);

            // 서비스 시작
            am.set(AlarmManager.RTC, SI.getSharedTime(index), sender);
        } catch (Exception e) {
            Log.d("MpMainActivity", e.getMessage() + "");

            e.printStackTrace();
        }
    }
    public void registerWT(String idIndex){
        try {
           /* long now = System.currentTimeMillis();
            Date data = new Date(now);
            SimpleDateFormat sdfNow = new SimpleDateFormat("HHmm");
            String strNow = sdfNow.format(data);*/
            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) + 10);
            long oneHour = 60 * 60 * 1000;
            Intent intentMyService;
            intentMyService = new Intent(idIndex);
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            intentMyService.putExtra("lati",location.getLatitude());
            intentMyService.putExtra("lon",location.getLongitude());

            AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
            PendingIntent sender = PendingIntent.getBroadcast(context, 0, intentMyService, 0);

            // 서비스 시작
            am.setRepeating(AlarmManager.RTC, cal.getTimeInMillis(), oneHour, sender);
        } catch (Exception e) {
            Log.d("MpMainActivity", e.getMessage() + "");

            e.printStackTrace();
        }
    }
    public void registerInit(){
        SharedInit SI = new SharedInit(context);
        AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);

        Intent intentMyService;
        PendingIntent sender;
        intentMyService = new Intent("ACTION.GET.ONE");
        sender = PendingIntent.getBroadcast(context, 0, intentMyService, 0);
        am.set(AlarmManager.RTC, SI.getSharedTime("0"), sender);

        intentMyService = new Intent("ACTION.GET.TWO");
        sender = PendingIntent.getBroadcast(context, 0, intentMyService, 0);
        am.set(AlarmManager.RTC, SI.getSharedTime("1"), sender);

        intentMyService = new Intent("ACTION.GET.THREE");
        sender = PendingIntent.getBroadcast(context, 0, intentMyService, 0);
        am.set(AlarmManager.RTC, SI.getSharedTime("2"), sender);

        intentMyService = new Intent("ACTION.GET.FOUR");
        sender = PendingIntent.getBroadcast(context, 0, intentMyService, 0);
        am.set(AlarmManager.RTC, SI.getSharedTime("3"), sender);

        intentMyService = new Intent("ACTION.GET.FIVE");
        sender = PendingIntent.getBroadcast(context, 0, intentMyService, 0);
        am.set(AlarmManager.RTC, SI.getSharedTime("4"), sender);

        intentMyService = new Intent("ACTION.GET.SIX");
        sender = PendingIntent.getBroadcast(context, 0, intentMyService, 0);
        am.set(AlarmManager.RTC, SI.getSharedTime("5"), sender);

        intentMyService = new Intent("ACTION.GET.SEVEN");
        sender = PendingIntent.getBroadcast(context, 0, intentMyService, 0);
        am.set(AlarmManager.RTC, SI.getSharedTime("6"), sender);

    }
    public void AlarmCancel(String index){
        AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);

        Intent intentMyService;
        PendingIntent sender;
        intentMyService = new Intent(index);
        sender = PendingIntent.getBroadcast(context, 0, intentMyService, 0);
        am.cancel(sender);
        sender.cancel();
    }
}
