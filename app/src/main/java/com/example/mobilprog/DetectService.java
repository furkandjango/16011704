package com.example.mobilprog;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.location.ActivityRecognitionClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class DetectService extends Service {
    private Intent intent;
    private PendingIntent pendingIntent;
    private ActivityRecognitionClient activityRecognitionClient;

    IBinder binder=new DetectService.LocalBinder();

    public class LocalBinder extends Binder{
        public DetectService getServerIntance(){
            return DetectService.this;
        }
    }
    public DetectService(){

    }

    @Override
    public void onCreate() {
        super.onCreate();
        activityRecognitionClient=new ActivityRecognitionClient(this);
        intent=new Intent(this,CountService.class);
        pendingIntent=PendingIntent.getService(this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        super.onStartCommand(intent,flags,startId);
        return START_STICKY;
    }
    public void requestActivityUpdates(){
        Task<Void> task=activityRecognitionClient.requestActivityUpdates(1*500,pendingIntent);
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "BAŞARILI", Toast.LENGTH_LONG).show();

            }
        });

        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "BAŞARISIZ !", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void removeActivityUpdates(){
        Task<Void> task = activityRecognitionClient.removeActivityUpdates(pendingIntent);
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "BAŞARILI sİLME !", Toast.LENGTH_LONG).show();
            }
        });
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "BAŞARISIZ SİLME !", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        removeActivityUpdates();
    }

}
