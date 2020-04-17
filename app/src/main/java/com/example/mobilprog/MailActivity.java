package com.example.mobilprog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MailActivity extends AppCompatActivity {
    private EditText fro,to,sbj,con;
    private Button sendbtn,attcbtn;
    public void init(){
        fro=(EditText) findViewById(R.id.from);
        to=(EditText) findViewById(R.id.too);
        sbj=(EditText) findViewById(R.id.subject);
        sendbtn=(Button) findViewById(R.id.send);
        con=(EditText) findViewById(R.id.cont);
        attcbtn=(Button) findViewById(R.id.attch);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        init();
        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentmail=new Intent(Intent.ACTION_SEND);
                intentmail.setData(Uri.parse("mailto:"));
                String[] To={fro.getText().toString(),to.getText().toString()};
                intentmail.putExtra(Intent.EXTRA_EMAIL,To);
                intentmail.putExtra(Intent.EXTRA_SUBJECT,sbj.getText().toString());
                intentmail.putExtra(Intent.EXTRA_TEXT,con.getText().toString());
                intentmail.setType("message/rfc822");
                startActivity(intentmail);
            }
        });

        attcbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageIntent = new Intent();
                imageIntent.setType("image/*");
                imageIntent.setAction(Intent.ACTION_GET_CONTENT);
                imageIntent.putExtra("return-data", true);
                startActivity(imageIntent);
            }
        });



    }
}
