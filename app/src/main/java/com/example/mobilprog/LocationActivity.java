package com.example.mobilprog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import java.util.List;

public class LocationActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION =1 ;
    private Button al, gon;
    private TextView yaz;
    private FusedLocationProviderClient mFusedLocationClient;
    Double latittude=null;
    Double longitude=null;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        al = (Button) findViewById(R.id.albtn);
        gon = (Button) findViewById(R.id.gonbtn);
        yaz = (TextView) findViewById(R.id.textView11);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        al.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yaz.setText("Bekleyiniz...");
                al();
            }
        });

        gon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gonder();
            }
        });

    }




    private void al() {
        mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
        @Override
        public void onSuccess(Location location) {
            if (location != null) {
                // Logic to handle location object
                latittude = location.getLatitude();
                longitude = location.getLongitude();

                yaz.setText("Latitude = "+latittude + "\nLongitude = " + longitude);


            }
            else
                yaz.setText("TEKRAR DENEYİNİZ");
        }
    });
    }


    private void gonder() {
        if(latittude != null && longitude !=null){
            Uri location = Uri.parse("geo:"+latittude+","+longitude+"?z=14"); // z parametresi yaklaşma seviyesini belirler
           // Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
            String whatsAppMessage = "http://maps.google.com/maps?saddr=" + latittude + "," + longitude;
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, whatsAppMessage);
            sendIntent.setType("text/plain");
            //sendIntent.setPackage("com.whatsapp");
         //   startActivity(sendIntent);

            Intent chooser=Intent.createChooser(sendIntent,"Başlık");

            startActivity(chooser);
        }


    }


}
