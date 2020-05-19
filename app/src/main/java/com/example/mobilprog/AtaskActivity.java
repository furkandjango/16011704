package com.example.mobilprog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class AtaskActivity extends AppCompatActivity {

    private Button dbtn;
    ProgressBar progressBar;
    TextView textView;
    private ImageView image;
    MediaPlayer mediaPlayer;
    private int sayac=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atask);
        textView=(TextView)findViewById(R.id.gotx);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        image=(ImageView) findViewById(R.id.imageView);
        mediaPlayer=MediaPlayer.create(this,R.raw.ok);

        progressBar.setIndeterminate(false);
        progressBar.setProgress(0);
        //p.setMax(1);
        dbtn=(Button) findViewById(R.id.downbtn);

        dbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AsyncTaskRun().execute();
               // if(sayac>=100)
                  //  image.setImageResource(R.drawable.ok);
            }

        });


    }
    class AsyncTaskRun extends AsyncTask<String,Integer,String>{

        public AsyncTaskRun() {
            super();
        }


        @SuppressLint("WrongThread")
        @Override
        protected String doInBackground(String... strings) {
            Random random=new Random();
            try {

                while(sayac<100) {
                    Thread.sleep(500);
                    sayac+=random.nextInt(10-1);
                    publishProgress(sayac); // updateprogress metoduna değer yolluyoruz
                    if(sayac>=100) {
                        mediaPlayer.start();
                        image.setImageResource(R.drawable.ok);

                        //mediaPlayer.stop();
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            return "Process finished Successfully !";

            // Arkaplanda yapılacak işlemler ne ise burada onlar yazılır
            // bu sınıfın baş rol oyuncusu bu metod denilebilir.
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setMax(100);
            progressBar.setMin(0);
            progressBar.setProgress(0,true);
            textView.setText("İşlem Başlıyor...");
            // Asenkron işlemin başlanğıcında olan işlemler buraya yazılabilir
            // örneğin progress bar burada sıfırlanabilir.
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(s);
            // doInBackground( )işlemi bittikten sonraki işlemler
            // return ile gelen string türündeki ifade burada kullanılabilir.
            // Success! gibi bir ifade mesela
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
            textView.setText(" İşlemin durumu % "+String.valueOf(values[0]));

            // doInBackground() metodu işletildiği esnada yapılacak işlemler buraya yazılır
            // mesela. Progress bar'ın ilerleme değerleri burada kullancıya aktarılabilii.
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);

            textView.setText("İşlem iptal edildi");
            // İşlem iptal edildiğinde yapılacak işlemler buraya yazılır
        }
    }




}

