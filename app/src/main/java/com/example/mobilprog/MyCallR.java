package com.example.mobilprog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.io.FileOutputStream;

public class MyCallR extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String state=intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
            String number=intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            try{

               // FileOutputStream fileOutputStream=openFileOutput("a.txt", Context.MODE_PRIVATE |Context.MODE_APPEND);
                FileOutputStream fileOutputStream=context.openFileOutput("a.txt",Context.MODE_PRIVATE | Context.MODE_APPEND);
                number+="\n";
                fileOutputStream.write(number.getBytes());
                fileOutputStream.close();
                Toast.makeText(context, "Kaydedildi...\n"+number, Toast.LENGTH_LONG).show();
            }catch (Exception e){
                e.printStackTrace();
            }

            //Toast.makeText(context,number,Toast.LENGTH_LONG).show();
        }
    }
}
