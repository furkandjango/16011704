package com.example.mobilprog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.location.DetectedActivity;


public class CountActivity extends AppCompatActivity {
    private TextView say;
    private int counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);
        say=(TextView) findViewById(R.id.saytx);



    }

    public void setCounter(int c) {
        this.counter += c;
        String tmp=String.valueOf(counter);
        say.setText(tmp);
    }


    public int getCounter(){
        return counter;
    }

}
