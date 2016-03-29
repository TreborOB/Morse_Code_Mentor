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
import com.example.robert.morseprototype.Tools.TrainingType;



import butterknife.Bind;
import butterknife.ButterKnife;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;


public class MainActivity extends BaseActivity {
    private GestureDetectorCompat mDetector;
    private final Sound playSound = new Sound();

    private String language;

    @Bind(R.id.swipeTop) TextView    topSwipe;
    @Bind(R.id.swipeRight) TextView  rightSwipe;
    @Bind(R.id.swipeBottom) TextView bottomSwipe;
    @Bind(R.id.swipeLeft) TextView   leftSwipe;


    private final ShowcaseConfig config = new ShowcaseConfig();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDetector = new GestureDetectorCompat(this, new mainMorseGestureDetector());
        ButterKnife.bind(this);


        language = Options.getLanguage(MainActivity.this);

        Logger.log(language);

        showStartUpTutorial();

        //sets the action bar text to the chosen language
        //noinspection ConstantConditions,ConstantConditions,ConstantConditions,ConstantConditions
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(Options.getEnabledDialogs(this))
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


    public void openTraining(View view) {
        Intent intent = new Intent(this, TrainingType.class);
        startActivity(intent);


        if(Options.getEnabledVoice(this)) {
            switch (language) {
                case "English":
                    playSound.playSymbol(MainActivity.this, R.raw.training);
                    break;

                case "Spanish":
                    playSound.playSymbol(MainActivity.this, R.raw.trainingspanish);
                    break;

                default:
                    playSound.playSymbol(MainActivity.this, R.raw.trainingchinese);
                    break;
            }
        }
    }


    public void openTools(View view) {
        Intent intent = new Intent(this, Tools.class);
        startActivity(intent);

        if(Options.getEnabledVoice(this)) {
        switch(language){
            case "English":
                playSound.playSymbol(MainActivity.this, R.raw.tools);
                break;

            case "Spanish":
                playSound.playSymbol(MainActivity.this, R.raw.toolspanish);
                break;

            default:
                playSound.playSymbol(MainActivity.this, R.raw.toolschinese);
                break;
        }
        }

    }


    public void openOptions(View view) {
        Intent intent = new Intent(this, Options.class);
        startActivity(intent);

        if(Options.getEnabledVoice(this)) {
        switch(language){
            case "English":
                playSound.playSymbol(MainActivity.this, R.raw.options);
                break;

            case "Spanish":
                playSound.playSymbol(MainActivity.this, R.raw.oprionsspanish);
                break;

            default:
                playSound.playSymbol(MainActivity.this, R.raw.optionschinese);
                break;
        }}

    }


    public void openSOS(View view) {
        Intent intent = new Intent(this, Sos.class);
        startActivity(intent);

        if(Options.getEnabledVoice(this)) {
        switch(language) {
            case "English":
                playSound.playSymbol(MainActivity.this, R.raw.sos);
                break;

            case "Spanish":
                playSound.playSymbol(MainActivity.this, R.raw.sosspanish);
                break;

            case "Chinese":
                playSound.playSymbol(MainActivity.this, R.raw.soschinese);
                break;
        }
        }

    }


    private class mainMorseGestureDetector extends MorseGestureDetector {

        public void onSwipeRight() {
            switch (language) {

                case "English":
                    if (Options.getEnabledVoice(MainActivity.this)) {
                        playSound.playSymbol(MainActivity.this, R.raw.morseletters);
                    }
                    LettersDialog.showLetters(MainActivity.this);

                    break;

                case "Spanish":
                    if (Options.getEnabledVoice(MainActivity.this)) {
                        playSound.playSymbol(MainActivity.this, R.raw.morseletterspanish);
                    }
                    LettersDialog.showLetters(MainActivity.this);

                    break;

                case "Chinese":
                    if (Options.getEnabledVoice(MainActivity.this)) {
                        playSound.playSymbol(MainActivity.this, R.raw.morseletterschinese);
                    }
                    LettersDialog.showLetters(MainActivity.this);

            }
        }




        public void onSwipeLeft() {
            switch(language) {

                case "English":
                    if (Options.getEnabledVoice(MainActivity.this)) {
                        playSound.playSymbol(MainActivity.this, R.raw.numbers);
                    }
                    LettersDialog.showNumbers(MainActivity.this);

                    break;

                case "Spanish":
                    if (Options.getEnabledVoice(MainActivity.this)) {
                        playSound.playSymbol(MainActivity.this, R.raw.numbersspanish);
                    }
                    LettersDialog.showNumbers(MainActivity.this);

                    break;

                case "Chinese":
                    if (Options.getEnabledVoice(MainActivity.this)) {
                        playSound.playSymbol(MainActivity.this, R.raw.numberschinese);
                    }
                    LettersDialog.showNumbers(MainActivity.this);
            }
            }

        public void onSwipeTop() {


            switch(language){

                case "English":
                    if(Options.getEnabledVoice(MainActivity.this)) {
                        playSound.playSymbol(MainActivity.this, R.raw.zcodes);
                    }
                    LettersDialog.showZCodes(MainActivity.this);
                    break;

                case "Spanish":
                    if(Options.getEnabledVoice(MainActivity.this)) {
                        playSound.playSymbol(MainActivity.this, R.raw.zcodespanish);
                    }
                    LettersDialog.showZCodesSpanish(MainActivity.this);

                    break;

                case "Chinese":
                    if(Options.getEnabledVoice(MainActivity.this)) {
                        playSound.playSymbol(MainActivity.this, R.raw.zcodeschinese);
                    }
                    LettersDialog.showZCodesChinese(MainActivity.this);

                    break;
            }
        }

        public void onSwipeBottom() {

            switch(language){

                case "English":
                    if(Options.getEnabledVoice(MainActivity.this)) {
                        playSound.playSymbol(MainActivity.this, R.raw.qcodes);
                    }
                    LettersDialog.showQCodes(MainActivity.this);

                break;

                case "Spanish":
                    if(Options.getEnabledVoice(MainActivity.this)) {
                        playSound.playSymbol(MainActivity.this, R.raw.qcodesspanish);
                    }
                    LettersDialog.showQCodesSpanish(MainActivity.this);

                break;

                case "Chinese":
                    if(Options.getEnabledVoice(MainActivity.this)) {
                        playSound.playSymbol(MainActivity.this, R.raw.qcodeschinese);
                        LettersDialog.showQCodesChinese(MainActivity.this);
                    }
                        break;
            }
        }
        }







    private void showCaseMainActivity(){


        String array[];

        switch (language) {
            case "language":
                //First time the app starts the language parameter will be 'language' (English)
                array = ShowCaseViewArrays.mainActivity();
                break;
            case "English":
                array = ShowCaseViewArrays.mainActivity();
                break;
            case "Spanish":
                array = ShowCaseViewArrays.mainActivitySpanish();
                break;
            default:
                array = ShowCaseViewArrays.mainActivityChinese();
                break;
        }





        config.setDelay(300);

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this);

        sequence.setConfig(config);

        sequence.addSequenceItem(topSwipe,
                array[0], array[4]);

        sequence.addSequenceItem(rightSwipe,
                array[1], array[4]);

        sequence.addSequenceItem(bottomSwipe,
                array[2], array[4]);

        sequence.addSequenceItem(leftSwipe,
                array[3], array[4]);


        sequence.start();

    }


    public void showStartUpTutorial(){

        String SHOWCASE_ID = "showcase_id";
        new MaterialShowcaseView.Builder(this)
                .setTarget(topSwipe)
                .setDismissText("GOT IT")
                .setContentText("Welcome to Morse code mentor! In the top right hand corner of the screen is" +
                                " a question mark - pressing this will help you guide you on how to use some of" +
                                " the features of app!")
                .withoutShape()
                .setDelay(200) // optional but starting animations immediately in onCreate can make them choppy
                .singleUse(SHOWCASE_ID) // provide a unique ID used to ensure it is only shown once
                .show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:


                switch(language) {

                    case "English":
                        if(Options.getEnabledVoice(this))
                        playSound.playSymbol(MainActivity.this, R.raw.help);
                        break;

                    case "Spanish":
                        if(Options.getEnabledVoice(this))
                        playSound.playSymbol(MainActivity.this, R.raw.helpspanish);
                        break;

                    case "Chinese":
                        if(Options.getEnabledVoice(this))
                        playSound.playSymbol(MainActivity.this, R.raw.helpchinese);
                        break;
                }


                showCaseMainActivity();
        }
        return true;

    }
}