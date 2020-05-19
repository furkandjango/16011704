package com.example.mobilprog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    private Button mail,list,not,set,sens,tsk,alarm,lks,count;
    ArrayList<Person> userlist=new ArrayList<Person>();
    int id=0;

    public void init(){
        mail =(Button) findViewById(R.id.mailbtn);
        list=(Button) findViewById(R.id.listbtn);
        not=(Button)findViewById(R.id.notbtn);
        set=(Button) findViewById(R.id.setbtn);
        sens=(Button) findViewById(R.id.sensbtn);
        tsk=(Button)findViewById(R.id.atask);
        alarm=(Button)findViewById(R.id.alarmbtn);
        lks=(Button) findViewById(R.id.lksbtn);
        count=(Button) findViewById(R.id.countbtn);
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

        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentalarm=new Intent(MenuActivity.this,AlarmActivity.class);
                startActivity(intentalarm);
            }
        });

        tsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenttsk=new Intent(MenuActivity.this,AtaskActivity.class);
                startActivity(intenttsk);
            }
        });

        lks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentlks=new Intent(MenuActivity.this,LocationActivity.class);
                startActivity(intentlks);
            }
        });

        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentc=new Intent(MenuActivity.this,CountActivity.class);
                startActivity(intentc);
            }
        });


    }
}
