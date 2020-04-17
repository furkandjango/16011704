package com.example.mobilprog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private TextView name,sex,mod;
    private EditText age,height,weight;
    private Button sav;
    Spinner spinner;

    ArrayList<Person> userlist=new ArrayList<Person>();
    int id=0;

    public void init(){
        name=(TextView)findViewById(R.id.nametx);
        sex=(TextView)findViewById(R.id.cntx);
        age=(EditText) findViewById(R.id.editText3);
        height=(EditText)findViewById(R.id.editText);
        weight=(EditText)findViewById(R.id.editText2);
        mod=(TextView)findViewById(R.id.textView8);
        sav=(Button)findViewById(R.id.savebtn);

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        editor=sharedPreferences.edit();
        String nn=userlist.get(id).getName();
        String ss=userlist.get(id).getGender();
        name.setText(nn);
        sex.setText(ss);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Intent i = getIntent();
        userlist=(ArrayList<Person>)  i.getSerializableExtra("objPP");
        id=(int)i.getSerializableExtra("ob");
        init();
        spinner=(Spinner)findViewById(R.id.spinner);
        String [] mods={"Dark","Light"};
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,mods);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               /* switch (position){
                    case 0 : mod.setText("DARK");
                    break;
                    case 1: mod.setText("LIGHT");
                    break;
                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sharedPr();
        sav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aa=age.getText().toString();
                editor.putString(getString(R.string.age),aa);
                editor.commit();
                String ww=weight.getText().toString();
                editor.putString(getString(R.string.weight),ww);
                editor.commit();
                String hh=height.getText().toString();
                editor.putString(getString(R.string.height),hh);
                editor.commit();

                String mm=spinner.getSelectedItem().toString();
                //Toast.makeText(getApplicationContext(), mm, Toast.LENGTH_LONG).show();
                editor.putString(getString(R.string.mod),mm);
                editor.commit();
            }
        });

    }
    private void sharedPr() {
        //Toast.makeText(getApplicationContext(), userlist.get(id).getName(), Toast.LENGTH_LONG).show();
      //  String n = sharedPreferences.getString(getString(R.string.name),userlist.get(id).getName());
      //  String s = sharedPreferences.getString(getString(R.string.sex), "Male");
        String a = sharedPreferences.getString(getString(R.string.age), "");
        String w = sharedPreferences.getString(getString(R.string.weight), "");
        String h = sharedPreferences.getString(getString(R.string.height), "");
        //String m = sharedPreferences.getString(getString(R.string.mod), "");
        String mmm=sharedPreferences.getString(getString(R.string.mod), "DARK");


        age.setText(a);
        weight.setText(w);
        height.setText(h);
       // mod.setText(spinner.getSelectedItem().toString());

        mod.setText(mmm);
    }
}
