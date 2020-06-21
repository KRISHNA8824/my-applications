package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    TextView time;
    int ap1 = 0;
    int ap2 = 0;
    int i = 0;
    //int secondleft;
    int current;
    int set;
    CountDownTimer countdown;
    Button button;
    boolean counterIsActive = false;
    SeekBar seekBar;
    public void updatetime(int secondleft){
        int minute = (int)secondleft/60;
        int second = secondleft-minute*60;
        if(second == 0){
            time.setText(minute + ":00");
        }
        else if(second <= 9){time.setText(minute + ":0" + second);}
        else{time.setText(minute + ":" + second);}
    }
    public void start(View view){
        //i++;
            /*new CountDownTimer(seekBar.getProgress() * 1000, 1000) {
                public void onTick(long milisecondsfinished) {
                    int remainsecond = (int) milisecondsfinished / 1000 + 1;
                    int minute = (int) remainsecond / 60;
                    int second = remainsecond - minute * 60;

                        time.setText(minute + ":" + second);
                }

                public void onFinish() {
                    Log.i("timer", "timer has completed");
                    time.setText( "0:0");
                }
            }.start();*/
            if(counterIsActive == false) {
                counterIsActive = true;
                seekBar.setEnabled(false);
                button.setText("Stop");
                countdown = new CountDownTimer(seekBar.getProgress() * 1000, 1000) {
                    @Override
                    public void onTick(long remain) {
                        updatetime((int) remain / 1000);
                    }

                    @Override
                    public void onFinish() {
                        Log.i("timer", "finished");
                        counterIsActive = false;
                        seekBar.setEnabled(true);

                    }
                }.start();
            }
            else{
                time.setText("0:30");
                seekBar.setProgress(30);
                countdown.cancel();
                seekBar.setEnabled(true);
                button.setText("start");
                counterIsActive = false;

            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*timer 1st method*/
        /*final Handler handler = new Handler();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                Log.i("Timer","1 second complete");
            }
        };
        handler.post(run);*/
        //timer 2nd method
        /*new CountDownTimer(15000, 1000){
            public void onTick(long milisecondsfinished){
                Log.i("left time",String.valueOf(milisecondsfinished/1000+1));
            }
            public void onFinish(){
                Log.i("timer","timer has completed");
            }
        }.start();*/
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        time = (TextView) findViewById(R.id.time);
        button = (Button) findViewById(R.id.button);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                updatetime((progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
