package com.app.dueday.maya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class OwnCalendar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_calendar);

        Intent intent;
        intent = new Intent(getApplicationContext(), Calendar.class);
        startActivity(intent);
    }
}
