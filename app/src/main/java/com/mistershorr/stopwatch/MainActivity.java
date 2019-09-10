package com.mistershorr.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

    public class MainActivity extends AppCompatActivity {
        private static final long START_TIME_IN_MILLIS = 0;

        private TextView mTextViewCountDown;
        private Button mButtonStartPause;
        private Button mButtonReset;

        private CountDownTimer mCountDownTimer;

        private boolean mTimerRunning;
        private int stoppedTime;
        private boolean stopped;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            mTextViewCountDown = findViewById(R.id.chronometer_main_time);

            mButtonStartPause = findViewById(R.id.button_main_start_stop);
            mButtonReset = findViewById(R.id.button_main_reset);

            mButtonStartPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTimerRunning) {
                       Log.d(TAG, "Time on click:" + stoppedTime);
                       stopped = true;
                    } else {
                        startTimer();
                    }
                }
            });

            mButtonReset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetTimer();
                }
            });

            updateCountDownText();
        }

        private void startTimer() {
            mCountDownTimer = new CountDownTimer() {
                @Override
                public void onTick(long millisUntilFinished) {
                    mTimeLeftInMillis = millisUntilFinished;
                    updateCountDownText();
                }

                @Override
                public void onFinish() {
                    mTimerRunning = false;
                    mButtonStartPause.setText("Start");
                }
            }.start();

            mTimerRunning = true;
            mButtonStartPause.setText("pause");
        }

        private void pauseTimer() {
            mCountDownTimer.cancel();
            stopped = true;
            mButtonStartPause.setText("Start");
        }

        private void resetTimer() {
            mTimeLeftInMillis = START_TIME_IN_MILLIS;
            updateCountDownText();
        }

        private void updateCountDownText() {
            int minutes = (int) (mTimeLeftInMillis / 1000) + 60;
            int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

            String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

            mTextViewCountDown.setText(timeLeftFormatted);
        }
    }
