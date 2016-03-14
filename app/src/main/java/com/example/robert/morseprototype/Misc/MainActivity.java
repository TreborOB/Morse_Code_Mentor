package com.example.robert.morseprototype.Misc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.robert.morseprototype.Hardware.Sound;
import com.example.robert.morseprototype.Options.Options;
import com.example.robert.morseprototype.R;
import com.example.robert.morseprototype.Sos.Sos;
import com.example.robert.morseprototype.SwipeDialogs.LettersDialog;
import com.example.robert.morseprototype.SwipeDialogs.MorseGestureDetector;
import com.example.robert.morseprototype.Tools.Tools;
import com.example.robert.morseprototype.Training.Training;


import butterknife.Bind;
import butterknife.ButterKnife;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;


public class MainActivity extends BaseActivity {
    private GestureDetectorCompat mDetector;
    private Sound playSound = new Sound();



    @Bind(R.id.swipeTop) TextView    topSwipe;
    @Bind(R.id.swipeRight) TextView  rightSwipe;
    @Bind(R.id.swipeBottom) TextView bottomSwipe;
    @Bind(R.id.swipeLeft) TextView   leftSwipe;


    private ShowcaseConfig config = new ShowcaseConfig();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDetector = new GestureDetectorCompat(this, new mainMorseGestureDetector());
        ButterKnife.bind(this);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(Options.getEnabledDialogs(this))
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


    public void openTraining(View view) {
        Intent intent = new Intent(this, Training.class);
        startActivity(intent);


        if(Options.getEnabledVoice(this))
        playSound.playSymbol(MainActivity.this, R.raw.training);


    }


    public void openTools(View view) {
        Intent intent = new Intent(this, Tools.class);
        startActivity(intent);

        if(Options.getEnabledVoice(this))
            playSound.playSymbol(MainActivity.this, R.raw.tools);

    }


    public void openOptions(View view) {
        Intent intent = new Intent(this, Options.class);
        startActivity(intent);


        if(Options.getEnabledVoice(this))
            playSound.playSymbol(MainActivity.this, R.raw.options);

    }


    public void openSOS(View view) {
        Intent intent = new Intent(this, Sos.class);
        startActivity(intent);


        if(Options.getEnabledVoice(this))
            playSound.playSymbol(MainActivity.this, R.raw.sos);
    }


    private class mainMorseGestureDetector extends MorseGestureDetector {

        public void onSwipeRight() {
            LettersDialog.showLetters(MainActivity.this);
        }

        public void onSwipeLeft() {
            LettersDialog.showNumbers(MainActivity.this);
        }

        public void onSwipeBottom() {
            LettersDialog.showQCodes(MainActivity.this);
        }

        public void onSwipeTop() {
            LettersDialog.showZCodes(MainActivity.this);
        }
    }



    private void showCaseMainActivity(){


        config.setDelay(300);

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this);

        sequence.setConfig(config);

        sequence.addSequenceItem(topSwipe,
                "Swiping from the top of the activity will show the Q codes", "GOT IT");

        sequence.addSequenceItem(rightSwipe,
                "Swiping from the right of the activity will show Morse numbers", "GOT IT");

        sequence.addSequenceItem(bottomSwipe,
                "Swiping from the bottom of the activity will show Z codes", "GOT IT");

        sequence.addSequenceItem(leftSwipe,
                "Swiping from the left of the activity will show Morse letters", "GOT IT");


        sequence.start();


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:

                if(Options.getEnabledVoice(this))
                    playSound.playSymbol(MainActivity.this, R.raw.help);

                showCaseMainActivity();
        }
        return true;

    }


}