package com.example.mobilprog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class ChnotActivity extends AppCompatActivity {
    private Button gor,ek,sil;
    private EditText name;

    public void init(){
        gor =(Button) findViewById(R.id.gorbtn);
        sil=(Button) findViewById(R.id.delbtn);
        ek=(Button)findViewById(R.id.ekbtn);
        name=(EditText) findViewById(R.id.dnamem);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chnot);
        init();
        ek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEk=new Intent(ChnotActivity.this,NotActivity.class);
                intentEk.putExtra("dnm",name.getText().toString());
                startActivity(intentEk);
            }
        });
        gor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGor=new Intent(ChnotActivity.this,NotgActivity.class);
                intentGor.putExtra("dname",name.getText().toString());
                startActivity(intentGor);
            }
        });
        sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file= new File(getFilesDir(),name.getText().toString());
                if(file.exists()){
                    deleteFile(name.getText().toString());
                    Toast.makeText(getApplicationContext(), "Dosya Silindi....", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Dosya bulunamadı !", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
