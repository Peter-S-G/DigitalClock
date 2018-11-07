package com.petergeras.digitalclock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;




public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    Button buttonBlack;
    Button buttonGreen;
    Button buttonBlue;
    Button buttonRed;
    ListView lView;

    ArrayAdapter<String> adapter;
    ArrayList<String> data;

    Switch switchButton;

    private ImageButton imageButton;

    boolean is24HoursChecked;
    String selectedTimeZone;
    int selectedColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);

        //getting the values that you have saved before in SharedPreferences (Storage)
        SharedPreferences pref = getSharedPreferences("MyPref", 0);
        is24HoursChecked = pref.getBoolean("is24HoursChecked", false);
        selectedTimeZone = pref.getString("selectedTimeZone", null);
        selectedColor = pref.getInt("selectedColor", Color.BLACK);


        switchButton = (Switch) findViewById(R.id.switch1);
        switchButton.setChecked(is24HoursChecked);


        buttonBlack = (Button) findViewById(R.id.push_button1);
        GradientDrawable bgShape1 = (GradientDrawable) buttonBlack.getBackground();
        bgShape1.setColor(Color.BLACK);
        bgShape1.setStroke(5, Color.WHITE);
        buttonBlack.setOnClickListener(this);

        buttonGreen = (Button) findViewById(R.id.push_button2);
        GradientDrawable bgShape2 = (GradientDrawable) buttonGreen.getBackground();
        bgShape2.setColor(Color.GREEN);
        bgShape2.setStroke(5, Color.WHITE);
        buttonGreen.setOnClickListener(this);

        buttonBlue = (Button) findViewById(R.id.push_button3);
        GradientDrawable bgShape3 = (GradientDrawable) buttonBlue.getBackground();
        bgShape3.setColor(Color.BLUE);
        bgShape3.setStroke(5, Color.WHITE);
        buttonBlue.setOnClickListener(this);

        buttonRed = (Button) findViewById(R.id.push_button4);
        GradientDrawable bgShape4 = (GradientDrawable) buttonRed.getBackground();
        bgShape4.setColor(Color.RED);
        bgShape4.setStroke(5, Color.WHITE);
        buttonRed.setOnClickListener(this);

        imageButton = (ImageButton) findViewById(R.id.imgBack);
        imageButton.setOnClickListener(new MyClickListener());


        lView = (ListView) findViewById(R.id.listZone);
        data = new ArrayList<String>();


        //linkedHashMap keeps the Map in order. Key were typed and values were using Android Studios
        final Map<String, String> linkedHashMap = new LinkedHashMap<String, String>();

        linkedHashMap.put("Greenwich Mean Time", "Greenwich");
        linkedHashMap.put("European Central Time", "ECT");
        linkedHashMap.put("Eastern European Time", "EET");
        linkedHashMap.put("(Arabic) Egypt Standard Time", "Africa/Cairo");
        linkedHashMap.put("Eastern African Time", "Asia/Baghdad");
        linkedHashMap.put("Middle East Time", "Asia/Baghdad");
        linkedHashMap.put("Eastern African Time", "Iran");
        linkedHashMap.put("Near East Time", "Europe/Moscow");
        linkedHashMap.put("Pakistan Lahore Time", "Indian/Maldives");
        linkedHashMap.put("India Standard Time", "Asia/Colombo");
        linkedHashMap.put("Bangladesh Standard Time", "Asia/Dacca");
        linkedHashMap.put("Vietnam Standard Time", "Asia/Bangkok");
        linkedHashMap.put("China Taiwan Time", "Hongkong");
        linkedHashMap.put("Japan Standard Time", "Japan");
        linkedHashMap.put("Australia Central Time", "Australia/Darwin");
        linkedHashMap.put("Australia Eastern Time", "Australia/Melbourne");
        linkedHashMap.put("Solomon Standard Time", "Pacific/Kosrae");
        linkedHashMap.put("New Zealand Standard Time", "Pacific/Fiji");
        linkedHashMap.put("Midway Islands Time", "Pacific/Apia");
        linkedHashMap.put("Hawaii Standard Time", "Pacific/Honolulu");
        linkedHashMap.put("Alaska Standard Time", "America/Nome");
        linkedHashMap.put("Pacific Standard Time", "America/Los_Angeles");
        linkedHashMap.put("Phoenix Standard Time", "US/Arizona");
        linkedHashMap.put("Mountain Standard Time", "America/Denver");
        linkedHashMap.put("Central Standard Time", "America/Chicago");
        linkedHashMap.put("Eastern Standard Time", "America/New_York");
        linkedHashMap.put("Puerto Rico and US Virgin Islands Time", "America/Puerto_Rico");
        linkedHashMap.put("Canada Newfoundland Time", "Canada/Newfoundland");
        linkedHashMap.put("Argentina Standard Time", "America/Buenos_Aires");
        linkedHashMap.put("Brazil Eastern Time", "Brazil/East");
        linkedHashMap.put("Central African Time", "Atlantic/Cape_Verde");


        Set<String> zones = linkedHashMap.keySet();

        data.addAll(zones);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);

        lView.setAdapter(adapter);

        // every time we click on specific item, a toast will appear. It gets the value from
        // linkedHashMap based on key and sets it to selectedTimeZone
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
                String key = ((TextView) view).getText().toString();
                selectedTimeZone = (String)linkedHashMap.get(key);
            }
        });
    }


    // return button
    class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            openMainActivity();
        }
        public void openMainActivity() {
            Intent myIntent = new Intent(SettingActivity.this, MainActivity.class);


            is24HoursChecked = switchButton.isChecked();



            // saves key and variable ( settings ) to SharedPreferences (Storage)
            SharedPreferences pref = getSharedPreferences("MyPref", 0);
            SharedPreferences.Editor editor = pref.edit();

            editor.putBoolean("is24HoursChecked", is24HoursChecked);
            editor.putString("selectedTimeZone", selectedTimeZone);
            editor.putInt("selectedColor", selectedColor);
            editor.apply();
            editor.commit();

            startActivity(myIntent);
        }
    }


    // changes colors when pressing color button
    public void onClick(View view){

        switch (view.getId()){

            case R.id.push_button1:
                selectedColor = Color.BLACK;
                break;
            case R.id.push_button2:
                selectedColor = Color.GREEN;
                break;
            case R.id.push_button3:
                selectedColor = Color.BLUE;
                break;
            case R.id.push_button4:
                selectedColor = Color.RED;
                break;

            default:
                break;
        }
    }
}

