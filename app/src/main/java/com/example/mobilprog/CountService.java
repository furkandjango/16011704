package com.example.mobilprog;

import android.app.IntentService;
import android.content.Intent;


import androidx.annotation.Nullable;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.ArrayList;

public class CountService extends IntentService {
    protected static final String TAG=DetectService.class.getSimpleName();
    CountActivity count;

     /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public CountService(String name) {
        super(name);
    }
    public CountService() {
        super(TAG);
    }

    @Override
    public void onCreate(){super.onCreate();}

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        ActivityRecognitionResult result=ActivityRecognitionResult.extractResult(intent);
        ArrayList<DetectedActivity> detectServices=(ArrayList) result.getProbableActivities();
        for (DetectedActivity activity:detectServices){
            switch(activity.getType()){
                case DetectedActivity.STILL:{
                    count.setCounter(0);
                    break;
                }
                case DetectedActivity.WALKING:{
                    count.setCounter(1);
                    break;
                }
            }
        }
    }


}
