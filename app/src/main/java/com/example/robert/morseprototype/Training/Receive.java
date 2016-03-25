package com.example.robert.morseprototype.Training;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.robert.morseprototype.Database.SnappyStrings;
import com.example.robert.morseprototype.Hardware.Sound;
import com.example.robert.morseprototype.Misc.BaseActivity;
import com.example.robert.morseprototype.Hardware.Output;
import com.example.robert.morseprototype.Misc.Logger;
import com.example.robert.morseprototype.Misc.MorseTranslations;
import com.example.robert.morseprototype.Options.Options;
import com.example.robert.morseprototype.R;
import com.gc.materialdesign.views.Button;
import com.gc.materialdesign.views.ButtonFlat;


import java.util.ArrayList;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class Receive extends BaseActivity {



    @Bind(R.id.imageViewReceive) ImageView imageViewReceive;
    @Bind(R.id.startReceive) ButtonFlat    startReceive;
    @Bind(R.id.wordFlashed) TextView       wordFlashed;

    private ShowcaseConfig config = new ShowcaseConfig();

    private Sound playSound = new Sound();

    private Output mOutput;

    String randomWord;

    @SuppressLint("HandlerLeak")
    Handler mImageHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            if (msg.what == 1)
                imageViewReceive.setBackgroundColor(Color.WHITE);
            else
                imageViewReceive.setBackgroundColor(Color.rgb(30, 136, 229));
        }


    };


    ArrayList<String> list = new ArrayList<>();

    String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        ButterKnife.bind(this);

        language = Options.getLanguage(Receive.this);

        if(Options.getSosSpeed(this)) {
            Options.setSpeedFast();
        }else{
            Options.setSpeedSlow();
        }

        init();

        String difficulty = Options.getLevel(Receive.this);

        switch(difficulty){
            case "Beginner":

            break;

            case "Standard":

            break;

            case "Expert":

            break;
        }



        SnappyStrings snappy = new SnappyStrings(Receive.this);
        SnappyStrings.initSnappy();

        list = SnappyStrings.getSnappy();



        wordFlashed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                setWordFlashed();
            }
        });
    }



    private void bindInterface() {

        mOutput.setScreenEnabled(true, mImageHandler);

    }



    private void startScreenFlash(){


        release();

        init();

        final MorseTranslations morseTranslations = new MorseTranslations();


        Random rand = new Random();
        randomWord = list.get(rand.nextInt(list.size()));

        Logger.log(randomWord);


        final String st = morseTranslations.translatedText(randomWord);
        mOutput.outputString(st);


        setHint();



    }


    public void setWordFlashed(){
        wordFlashed.setText(randomWord);
    }


    private void showCaseMainActivity(){


        config.setDelay(300);

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this);

        sequence.setConfig(config);

        sequence.addSequenceItem(startReceive,
                "The button starts the flash process", "GOT IT");

        sequence.addSequenceItem(wordFlashed,
                "press here to see reveal the word that was flashed", "GOT IT");

        sequence.start();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_favorite:

                if(Options.getEnabledVoice(this))


                    switch(language) {
                        case "English":
                            playSound.playSymbol(Receive.this, R.raw.help);
                            break;

                        case "Spanish":
                            playSound.playSymbol(Receive.this, R.raw.helpspanish);
                            break;

                        default:
                            playSound.playSymbol(Receive.this, R.raw.helpchinese);
                            break;
                    }

                showCaseMainActivity();
        }
        return true;

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SnappyStrings.closeSnappyStrings();
        mOutput.release();
    }

    public void release(){
        mOutput.release();
    }


    public void onPause(){
        super.onPause();
        SnappyStrings.closeSnappyStrings();
    }



    public void setHint(){
        wordFlashed.setText("Press here to see the answer");
    }


    public void init(){

        mOutput = new Output(this, false, false, false, false);

        bindInterface();

        mOutput.setScreenViewHandler(mImageHandler);

        startReceive.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                startScreenFlash();

            }
        });
    }


}