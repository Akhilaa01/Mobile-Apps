package com.example.counterapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //https://docs.huihoo.com/android/2.3/resources/articles/timed-ui-updates.html
    
    TextView output ;
    Button start, stop;
    int cvalue = 0;
    Handler handler;
    //A handler is basically a message queue.
    //You post a message to it, and it will eventually process it by calling its run method and passing the message to it.
    //Since these run calls will always occur in the order of messages received on the same thread, it allows you to serialize events.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = (TextView)findViewById(R.id.cvalue);  //Finds a view that was identified by the android:id XML attribute that was processed in onCreate.
        start = (Button)findViewById(R.id.start);
        stop = (Button)findViewById(R.id.stop);

        handler = new Handler();
        //Default constructor associates this handler with the Looper for the current thread.
        //If this thread does not have a looper, this handler won't be able to receive messages so an exception is thrown.

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                handler.postDelayed(runnable, 0);
                //Causes the runnable to be added to the message queue, to be run after the specified amount of time elapses.
                //0-indicates start immediately without any delay.
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacks(runnable);
                //When you create a new Handler, it is bound to the thread / message queue of the thread that is creating it.
                // From that point on, it will deliver messages and runnables to that message queue and execute them as they come out of the message queue.
                // removeCallbacks simply removes those runnables who have not yet begun processing from the queue.
            }
        });
    }

    //The Runnable interface should be implemented by any class whose instances are intended to be executed by a thread.
    // The class must define a method of no arguments called run.
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            cvalue++;// Increment the counter value.

            output.setText(String.format("%d", cvalue));//Display the cvalue on the textbox.

            handler.postDelayed(this, 1000);
            //Causes the runnable to be added to the message queue, to be run after the specified amount of time elapses.
            //1000-indicates give 1 second delay.
        }
    };
}