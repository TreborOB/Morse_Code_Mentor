package com.example.robert.morseprototype.Tools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import com.example.robert.morseprototype.Hardware.Sound;
import com.example.robert.morseprototype.Options.Options;
import com.example.robert.morseprototype.R;

public class Tools extends AppCompatActivity {


    private Sound playSound = new Sound();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);
    }



    public void openScreenFlash(View view) {

        Intent intent = new Intent(this, ScreenFlash.class);
        startActivity(intent);

        if(Options.getEnabledVoice(this))
            playSound.playSymbol(Tools.this, R.raw.screenflash);
    }


    public void englishMorse(View view) {

        Intent intent = new Intent(this, EnglishToMorse.class);
        startActivity(intent);

        if(Options.getEnabledVoice(this))
            playSound.playSymbol(Tools.this, R.raw.englishtomorse);
    }


    public void convertMorse(View view) {

        Intent intent = new Intent(this, MorseToEnglish.class);
        startActivity(intent);

        if(Options.getEnabledVoice(this))
            playSound.playSymbol(Tools.this, R.raw.morsetoenglish);
    }


    public void morsePad(View view) {

        Intent intent = new Intent(this, MorsePad.class);
        startActivity(intent);

        if(Options.getEnabledVoice(this))
            playSound.playSymbol(Tools.this, R.raw.morsepad);

    }
}