package com.example.robert.morseprototype.Training;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.robert.morseprototype.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FinishedTrailSection extends AppCompatActivity {



    @Bind(R.id.trailCompleted) TextView trailCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_trail_section);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String trailSection = intent.getExtras().getString("trailSection");

        trailCompleted.setText("Congratulations! You completed the " + trailSection + " section");



    }
}
