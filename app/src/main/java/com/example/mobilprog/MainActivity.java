package com.example.mobilprog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button login;
    private EditText user,pass;
    private String username,password;
    private ArrayList<Person> userList=new ArrayList<Person>();
    private int again=0;
    private int find=0;


    public void init(){
        login =(Button) findViewById(R.id.loginbtn);
        user=(EditText)findViewById(R.id.log);
        pass=(EditText) findViewById(R.id.pas);
    }

    public int userpas(String username,String password){
        int in = -1;
        int i=0;

        while (in == -1 && i < userList.size()){
            if (userList.get(i).getUsername().equals(username) && userList.get(i).getPassword().equals(password))
                in = i;
            else
                i++;
        }
        return in;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person user1=new Person("Furkan","oguz","furkan.oguz.60@gmail.com","admin","1234");
                Person user2=new Person("Deneme","dene","dene@deneme.com","deneme","dene");
                Person user3=new Person("Username","dene","dene@deneme.com","Username","123");
                userList.add(user1);
                userList.add(user2);
                userList.add(user3);

                username = user.getText().toString();
                password = pass.getText().toString();
                find = userpas(username, password);

                if ( find != -1 ) {
                    again = 0;
                    Intent menuIntent = new Intent(MainActivity.this, MenuActivity.class);
                    menuIntent.putExtra("objPerson",userList);
                    menuIntent.putExtra("objid",find);
                    startActivity(menuIntent);
                } else {
                    if( again == 2){
                        Toast.makeText(getApplicationContext(), "Uygulama kapandı...", Toast.LENGTH_LONG).show();
                        finish();
                    }else{
                        again++;
                        user.setText("Again");
                        pass.setText("");
                    }
                }
            }
        });

    }
}
