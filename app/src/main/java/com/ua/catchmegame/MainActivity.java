package com.ua.catchmegame;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView scoreText;
    TextView timerText;
    ImageView pika1;
    ImageView pika2;
    ImageView pika3;
    ImageView pika4;
    ImageView pika5;
    ImageView pika6;
    ImageView pika7;
    ImageView pika8;
    ImageView pika9;

    int score;

    ImageView[] imageArray ;

    Runnable runnable;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score = 0;
        initView();

        hideImage();

        new CountDownTimer(30000,10000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText("Time: "+ millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                timerText.setText("Time's off");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray ){
                    image.setVisibility(View.INVISIBLE);
                }
            }
        }.start();


    }

    public void initView(){
        scoreText =  findViewById(R.id.tvScore);
        timerText = findViewById(R.id.tvTimer);
        pika1 = findViewById(R.id.imageView1);
        pika2 = findViewById(R.id.imageView2);
        pika3 = findViewById(R.id.imageView3);
        pika4 = findViewById(R.id.imageView4);
        pika5 = findViewById(R.id.imageView5);
        pika6 = findViewById(R.id.imageView6);
        pika7 = findViewById(R.id.imageView7);
        pika8 = findViewById(R.id.imageView8);
        pika9 = findViewById(R.id.imageView9);
        imageArray = new ImageView[]{pika1,pika2,pika3,pika4,pika5,pika6,pika7,pika8,pika9};
    }

    public void increaseScore(View view){

        score++;
        scoreText.setText("Score: " +score);
    }

    private void hideImage(){


        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                Random rnd = new Random();
                int pos = rnd.nextInt(8-0);
                for (ImageView image : imageArray ){
                    image.setVisibility(View.INVISIBLE);
                }

                imageArray[pos].setVisibility(View.VISIBLE);

                handler.postDelayed(this,500);
            }
        };
        handler.post(runnable);
    }
}
