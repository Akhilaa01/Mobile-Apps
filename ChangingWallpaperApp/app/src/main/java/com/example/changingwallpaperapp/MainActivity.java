package com.example.changingwallpaperapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button button;
    View screenView;
    int[] back_images;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Store all the images in an array (0 index = img1, 1 index = img2,....)
        back_images = new int[]{R.drawable.w1, R.drawable.w2, R.drawable.w3, R.drawable.w4, R.drawable.w5, R.drawable.w6, R.drawable.w7, R.drawable.w8, R.drawable.w9, R.drawable.w10};
        button = findViewById(R.id.button);
        screenView = findViewById(R.id.constraint_layout);

        //https://medium.com/@ankit.sinhal/handler-in-android-d138c1f4980e#:~:text=A%20Handler%20allows%20communicating%20back,communicate%20directly%20with%20UI%20thread.&text=A%20Handler%20allows%20you%20to,associated%20with%20a%20thread's%20MessageQueue.
        handler = new Handler() ;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.postDelayed(runnable, 0); //Start without any delay.
                button.setVisibility(View.GONE); //Hide the button after click.
            }
        });
    }

    //https://developer.android.com/reference/java/lang/Thread
    //The Runnable interface should be implemented by any class whose instances are intended to be executed by a thread.
    //The class must define a method of no arguments called run.
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {

            //Fetching length of array
            int array_length = back_images.length;

            //Object creation of random class
            Random random = new Random();

            //Generation of random number
            int random_number = random.nextInt(array_length);

            //Set background images on screenView using setBackground() method.
            screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(), back_images[random_number]));

            handler.postDelayed(this, 30000);
            //Causes the runnable to be added to the message queue, to be run after the specified amount of time elapses.
            //30000-indicates give 30 second delay.
        }
    };
}