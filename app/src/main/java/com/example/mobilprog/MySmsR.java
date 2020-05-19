package com.example.mobilprog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.io.FileOutputStream;

public class MySmsR extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            Object [] pdus= (Object[]) bundle.get("pdus");
            for (int i=0;i<pdus.length;i++){
                SmsMessage sms=SmsMessage.createFromPdu((byte[]) pdus[i], bundle.getString("format"));
                String body=sms.getDisplayMessageBody();
                String adr=sms.getDisplayOriginatingAddress();
               // Toast.makeText(context,sms.getDisplayOriginatingAddress()+"----"+sms.getDisplayMessageBody(),Toast.LENGTH_LONG).show();
                try{

                    // FileOutputStream fileOutputStream=openFileOutput("a.txt", Context.MODE_PRIVATE |Context.MODE_APPEND);
                    FileOutputStream fileOutputStream=context.openFileOutput("a.txt",Context.MODE_PRIVATE | Context.MODE_APPEND);
                    body+=" ";
                    body+=adr;
                    body+="\n";
                    fileOutputStream.write(body.getBytes());
                    fileOutputStream.close();
                    Toast.makeText(context, "Kaydedildi...\n"+body, Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }
    }
}
