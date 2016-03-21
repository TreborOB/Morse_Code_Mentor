package com.example.robert.morseprototype.Tools;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.robert.morseprototype.R;
import com.example.robert.morseprototype.Training.Receive;
import com.example.robert.morseprototype.Training.Training;

public class TrainingType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_type);


    }


    public void openTransmit(View view){
        Intent intent = new Intent(this, Training.class);
        startActivity(intent);

    }

    public void openReceive(View view){
        Intent intent = new Intent(this, Receive.class);
        startActivity(intent);
    }


}
