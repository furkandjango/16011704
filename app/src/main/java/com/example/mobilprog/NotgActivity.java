package com.example.mobilprog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NotgActivity extends AppCompatActivity {
    private TextView notpad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notg);
      //  notpad=(TextView) findViewById(R.id.notx);
        Intent i = getIntent();
        String fl=(String)  i.getSerializableExtra("dname");

        try{
            FileInputStream fileInputStream=openFileInput(fl);
            InputStreamReader isr=new InputStreamReader(fileInputStream);
            BufferedReader br= new BufferedReader(isr);
            notpad=(TextView) findViewById(R.id.notx);
            notpad.setText(br.readLine());
            fileInputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
