package com.example.mobilprog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    private Button mail,list,not,set,sens;
    ArrayList<Person> userlist=new ArrayList<Person>();
    int id=0;

    public void init(){
        mail =(Button) findViewById(R.id.mailbtn);
        list=(Button) findViewById(R.id.listbtn);
        not=(Button)findViewById(R.id.notbtn);
        set=(Button) findViewById(R.id.setbtn);
        sens=(Button) findViewById(R.id.sensbtn);
        Intent i = getIntent();
        userlist=(ArrayList<Person>) i.getSerializableExtra("objPerson");
        id=(int) i.getSerializableExtra("objid");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        init();


        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMail=new Intent(MenuActivity.this,MailActivity.class);
                startActivity(intentMail);
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentList=new Intent(MenuActivity.this,ListActivity.class);
                intentList.putExtra("objP",userlist);
                startActivity(intentList);
            }
        });

        not.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNot=new Intent(MenuActivity.this,ChnotActivity.class);
                startActivity(intentNot);
            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSetting=new Intent(MenuActivity.this,SettingActivity.class);
                intentSetting.putExtra("objPP",userlist);
                intentSetting.putExtra("ob",id);
                startActivity(intentSetting);
            }
        });

        sens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSensor=new Intent(MenuActivity.this,SensorActivity.class);
                startActivity(intentSensor);
            }
        });


    }
}
