package com.example.mobilprog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AlarmActivity extends AppCompatActivity implements View.OnClickListener {
    private Button alarm,ipt;
    private TextView saat;
    Handler handler=null;
    Runnable runnable=null;
    String time;
    TextView alarmtime;
    private TimePickerDialog timePickerDialog;
    final static int islem_kodu = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        alarm=(Button) findViewById(R.id.alarmbtn);
        saat=(TextView)findViewById(R.id.saattx);
        ipt=(Button)findViewById(R.id.iptalbtn);
        ipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(AlarmActivity.this, MyAlarm.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 1, intent, 0);

                alarmManager.cancel(pendingIntent);
                Toast.makeText(getApplicationContext(),"ALARM İPTAL!",Toast.LENGTH_SHORT).show();
                alarmtime.setText("\n\n\nALARM YOK");
            }
        });
        alarm.setOnClickListener(this);
        alarmtime=(TextView)findViewById(R.id.times);

        final SimpleDateFormat shape=new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
        time = shape.format(new Date());
        saat.setText(time);

        handler = new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                time=shape.format(new Date());
                saat.setText(time);
                handler.postDelayed(runnable,1000);
            }
        };
        runnable.run();

    }


    @Override
    public void onClick(View v) {
        Calendar calendar = Calendar.getInstance();

        timePickerDialog = new TimePickerDialog(AlarmActivity.this, onTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.setTitle("Ayarla");
        timePickerDialog.show();
       // openPickerDialog(true);
    }
//    private void openPickerDialog(boolean tumgunsaat) {


//    }
    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener(){
        @Override
        public void onTimeSet(TimePicker view, int saat, int dakika) {

            Calendar calNow = Calendar.getInstance();
            Calendar calSet = (Calendar) calNow.clone();

            calSet.set(Calendar.HOUR_OF_DAY, saat);
            calSet.set(Calendar.MINUTE, dakika);
            calSet.set(Calendar.SECOND, 0);
            calSet.set(Calendar.MILLISECOND, 0);

            if(calSet.compareTo(calNow) <= 0){

                calSet.add(Calendar.DATE, 1);
            }

            setAlarm(calSet);
        }};

    private void setAlarm(Calendar alarmCalender){
        Toast.makeText(getApplicationContext(),"ALARM ETKİN",Toast.LENGTH_SHORT).show();
        String timess="\n \n \nAlarm : ";
        timess +=DateFormat.getTimeInstance(DateFormat.SHORT).format(alarmCalender.getTime());
        alarmtime.setText(timess);
        Intent intent = new Intent(getBaseContext(), MyAlarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), islem_kodu, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, alarmCalender.getTimeInMillis(), pendingIntent);

    }
}
