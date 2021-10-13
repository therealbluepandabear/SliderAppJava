package com.example.sliderappjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.SeekBar;

import com.google.android.material.snackbar.Snackbar;

import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {
    SeekBar slider;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slider = findViewById(R.id.mySeekBar);
        sharedPreferences = getSharedPreferences("MyPrefs",
                Context.MODE_PRIVATE);

        slider.setProgress(sharedPreferences.getInt("lastValue", 0));

        slider.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        ConstraintLayout contextView = findViewById(R.id.rootLayout);

                        Snackbar.make(contextView, "Val is " + progress, Snackbar.LENGTH_SHORT)
                                        .show();

                        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
                        prefEditor.putInt("lastValue", progress);
                        prefEditor.apply();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) { }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) { }
                }
        );
    }
}