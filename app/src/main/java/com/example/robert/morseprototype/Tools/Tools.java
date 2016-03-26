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

    String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);

        language = Options.getLanguage(Tools.this);

    }



    public void openScreenFlash(View view) {

        Intent intent = new Intent(this, ScreenFlash.class);
        startActivity(intent);

        if(Options.getEnabledVoice(this))

            switch(language) {
                case "English":
                    playSound.playSymbol(Tools.this, R.raw.screenflash);
                    break;

                case "Spanish":
                    playSound.playSymbol(Tools.this, R.raw.screenflashspanish);
                    break;

                default:
                    playSound.playSymbol(Tools.this, R.raw.screenflashchinese);
                    break;
            }
    }


    public void englishMorse(View view) {

        Intent intent = new Intent(this, EnglishToMorse.class);
        startActivity(intent);

        if(Options.getEnabledVoice(this))

            switch(language) {
                case "English":
                    playSound.playSymbol(Tools.this, R.raw.englishtomorse);
                    break;

                case "Spanish":
                    playSound.playSymbol(Tools.this, R.raw.englishtomorsespanish);
                    break;

                case "Chinese":
                    playSound.playSymbol(Tools.this, R.raw.englishtomorsechinese);
                    break;
            }
    }


    public void convertMorse(View view) {

        Intent intent = new Intent(this, MorseToEnglish.class);
        startActivity(intent);

        if(Options.getEnabledVoice(this))

            switch(language) {
                case "English":
                    playSound.playSymbol(Tools.this, R.raw.morsetoenglish);
                    break;

                case "Spanish":
                    playSound.playSymbol(Tools.this, R.raw.morsetoenglishspanish);
                    break;

                case "Chinese":
                    playSound.playSymbol(Tools.this, R.raw.morsetoenglishchinese);
                    break;
            }
    }


    public void morsePad(View view) {

        Intent intent = new Intent(this, MorsePad.class);
        startActivity(intent);

        if(Options.getEnabledVoice(this))

            switch(language) {
                case "English":
                    playSound.playSymbol(Tools.this, R.raw.morsepad);
                    break;

                case "Spanish":
                    playSound.playSymbol(Tools.this, R.raw.morsepadspanish);
                    break;

                case "Chinese":
                    playSound.playSymbol(Tools.this, R.raw.morsepadchinese);
                    break;
            }

    }
}