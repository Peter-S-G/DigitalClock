package com.petergeras.digitalclock;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {


    Time mTime;

    Handler handler;
    Runnable runUpdateClock;

    DigitView digitHour1;
    DigitView digitHour2;
    DigitView digitMinute1;
    DigitView digitMinute2;
    DigitView digitSecond1;
    DigitView digitSecond2;
    TextView blinker;
    TextView tAM;
    TextView tPM;
    TextView tDate;

    boolean is24HoursChecked;
    String selectedTimeZone = null;
    int selectedColor;

    private ImageButton buttonSettings;

    // Timezones, using android studios internal timezones to get IDs
    public static void main(String[] args) {

        String[] ids = TimeZone.getAvailableIDs();
        for (String id : ids) {
            System.out.println(displayTimeZone(TimeZone.getTimeZone(id)));
        }


    }

    private static String displayTimeZone(TimeZone tz) {

        long hours = TimeUnit.MILLISECONDS.toHours(tz.getRawOffset());
        long minutes = TimeUnit.MILLISECONDS.toMinutes(tz.getRawOffset())
                - TimeUnit.HOURS.toMinutes(hours);
        // avoid -4:-30 issue
        minutes = Math.abs(minutes);

        String result = "";
        if (hours > 0) {
            result = String.format("(GMT+%d:%02d) %s", hours, minutes, tz.getID());
        } else {
            result = String.format("(GMT%d:%02d) %s", hours, minutes, tz.getID());
        }

        return result;
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Getting varribles from SharedPreferences (Storage)
        SharedPreferences pref = getSharedPreferences(Constants.MyPref, 0);
        is24HoursChecked = pref.getBoolean(Constants.Is24HoursChecked, false);
        selectedTimeZone = pref.getString("selectedTimeZone", null);
        selectedColor = pref.getInt("selectedColor", Color.BLACK);


        digitHour1 = (DigitView) findViewById(R.id.first_digit1);
        digitHour2 = (DigitView) findViewById(R.id.first_digit2);
        digitMinute1 = (DigitView) findViewById(R.id.first_digit3);
        digitMinute2 = (DigitView) findViewById(R.id.first_digit4);
        digitSecond1 = (DigitView) findViewById(R.id.first_digit5);
        digitSecond2 = (DigitView) findViewById(R.id.first_digit6);
        tAM = (TextView) findViewById(R.id.textAM);
        tPM = (TextView) findViewById(R.id.textPM);
        tDate = (TextView) findViewById(R.id.textDate);
        blinker = (TextView) findViewById(R.id.textView);

        mTime = new Time();
        handler = new Handler();

        //clock code
        runUpdateClock = new Runnable() {
            @Override
            public void run() {

                mTime.setToNow();
                updateClock(mTime);
                handler.postDelayed(runUpdateClock, 500);

            }
        };
        runUpdateClock.run();


        //click settings button
        buttonSettings = (ImageButton) findViewById(R.id.imageButtonSetting);
        buttonSettings.setOnClickListener(new SettingButtonClickListener());


    }

    //open settings
    public void openSetting() {

        handler.removeCallbacksAndMessages(null);
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }

    private void updateClock(Time time) {

        //":" blinking code
        if (blinker.getVisibility() == View.VISIBLE) {
            blinker.setVisibility(View.INVISIBLE);
        } else {
            blinker.setVisibility(View.VISIBLE);
        }


        int cur_hours = time.hour;

        if (is24HoursChecked == true) {
            //24 hour clock, sets am and pm to invisible
            tAM.setAlpha(0f);
            tPM.setAlpha(0f);

        } else {
            //12 hour clock, sets am and pm faded depending on time
            if (cur_hours < 12) {
                tAM.setAlpha(1);
                tPM.setAlpha(0.07f);
            }
            if (cur_hours == 12) {
                tAM.setAlpha(0.07f);
                tPM.setAlpha(1);
            }
            if (cur_hours > 12) {
                cur_hours = cur_hours - 12;
                tAM.setAlpha(0.07f);
                tPM.setAlpha(1);
            }
        }


        //assigns digit to time
        int hour1 = cur_hours / 10;
        int hour2 = cur_hours % 10;
        digitHour1.makeNumber(hour1);
        digitHour2.makeNumber(hour2);

        int minutes1 = time.minute / 10;
        int minutes2 = time.minute % 10;
        digitMinute1.makeNumber(minutes1);
        digitMinute2.makeNumber(minutes2);

        int seconds1 = time.second / 10;
        int seconds2 = time.second % 10;
        digitSecond1.makeNumber(seconds1);
        digitSecond2.makeNumber(seconds2);

        //changes timezone and date from settings class
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMMM dd");

        if (selectedTimeZone != null) {
            time.switchTimezone(selectedTimeZone);
            TimeZone tz = TimeZone.getTimeZone(selectedTimeZone);
            sdf.setTimeZone(tz);
        }

        long date = System.currentTimeMillis();
        String dateString = sdf.format(date);
        tDate.setText(dateString);


        // Change color from settings class
        tAM.setTextColor(selectedColor);
        tPM.setTextColor(selectedColor);
        tDate.setTextColor(selectedColor);
        blinker.setTextColor(selectedColor);
    }

    class SettingButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            openSetting();
        }
    }

}

