package com.example.mobilprog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class NotActivity extends AppCompatActivity {
    private Button add;
    private EditText notpad;

    public void init(){
        add =(Button) findViewById(R.id.notsvbtn);
        notpad=(EditText)findViewById(R.id.notx);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not);
        init();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent i = getIntent();
                    String fl=(String)  i.getSerializableExtra("dnm");
                    FileOutputStream fileOutputStream=openFileOutput(fl, Context.MODE_PRIVATE |Context.MODE_APPEND);
                    String not=notpad.getText().toString();
                    fileOutputStream.write(not.getBytes());
                    fileOutputStream.close();
                    Toast.makeText(getApplicationContext(), "Kaydedildi...", Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });



    }
}
