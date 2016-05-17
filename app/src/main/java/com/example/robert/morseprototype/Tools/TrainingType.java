package com.example.robert.morseprototype.Tools;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.robert.morseprototype.Hardware.Sound;
import com.example.robert.morseprototype.Options.Options;
import com.example.robert.morseprototype.R;
import com.example.robert.morseprototype.Training.Receive;
import com.example.robert.morseprototype.Training.Training;



public class TrainingType extends AppCompatActivity {



    private Sound playSound = new Sound();

    String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_type);

        language = Options.getLanguage(TrainingType.this);

    }



    public void openTransmit(View view){
        Intent intent = new Intent(this, Training.class);
        startActivity(intent);

        if(Options.getEnabledVoice(this))

            switch(language) {
                case "English":
                    playSound.playSymbol(TrainingType.this, R.raw.transmit);
                    break;

                case "Spanish":
                    playSound.playSymbol(TrainingType.this, R.raw.transmitspanish);
                    break;

                default:
                    playSound.playSymbol(TrainingType.this, R.raw.transmitchinese);
                    break;
            }

    }

    public void openReceive(View view){
        Intent intent = new Intent(this, Receive.class);
        startActivity(intent);


        if(Options.getEnabledVoice(this))

            switch(language) {
                case "English":
                    playSound.playSymbol(TrainingType.this, R.raw.receive);
                    break;

                case "Spanish":
                    playSound.playSymbol(TrainingType.this, R.raw.receivespanish);
                    break;

                default:
                    playSound.playSymbol(TrainingType.this, R.raw.receivechinese);
                    break;
            }
    }


}
