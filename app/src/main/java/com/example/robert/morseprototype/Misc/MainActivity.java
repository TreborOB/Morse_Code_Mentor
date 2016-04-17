package com.example.robert.morseprototype.Misc;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
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



import com.mixpanel.android.mpmetrics.MixpanelAPI;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;



public class MainActivity extends BaseActivity {
    private GestureDetectorCompat mDetector;
    private final Sound playSound = new Sound();

    private String language;

    @Bind(R.id.swipeTop) TextView    topSwipe;
    @Bind(R.id.swipeRight) TextView  rightSwipe;
    @Bind(R.id.swipeBottom) TextView bottomSwipe;
    @Bind(R.id.swipeLeft) TextView   leftSwipe;

    private final ShowcaseConfig config = new ShowcaseConfig();

    MixpanelAPI mixPanel;
    MixpanelAPI mixPanelTraining;
    MixpanelAPI mixPanelSOS;
    MixpanelAPI mixPanelTools;
    MixpanelAPI mixPanelOptions;
    String      projectToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mDetector = new GestureDetectorCompat(this, new mainMorseGestureDetector());

        projectToken     = "a030a77c9710298b9cee4e20d7c2b814";
        mixPanel         = MixpanelAPI.getInstance(this, projectToken);
        mixPanelTraining = MixpanelAPI.getInstance(this, projectToken);
        mixPanelSOS      = MixpanelAPI.getInstance(this, projectToken);
        mixPanelTools    = MixpanelAPI.getInstance(this, projectToken);
        mixPanelOptions  = MixpanelAPI.getInstance(this, projectToken);


        if(Options.getDataAnalytics(this)) {
            try {
                JSONObject props = new JSONObject();
                props.put("App Started", "True");
                mixPanel.track("MainActivity", props);

            } catch (JSONException e) {

                Log.e("App started error", "ERROR", e);
            }
        }

        language = Options.getLanguage(MainActivity.this);

        //Loads preferences (sets default language on first launch)
        PreferenceManager.setDefaultValues(this, R.xml.pref_general, false);

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


        if(Options.getDataAnalytics(this)) {
        try{
            JSONObject training = new JSONObject();
            training.put("Open Training", "True");
            mixPanelTraining.track("Training", training);

        }catch(JSONException e) {

            Log.e("Open training error", "ERROR", e);
        }
        }



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

        if(Options.getDataAnalytics(this)) {
        try{
            JSONObject tools = new JSONObject();
            tools.put("Open Tool", "True");
            mixPanelTools.track("Tools", tools);

        }catch(JSONException e) {

            Log.e("Open tools error", "Error", e);
        }
        }

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

        if(Options.getDataAnalytics(this)) {
            try {
                JSONObject options = new JSONObject();
                options.put("Open Options", "True");
                mixPanelOptions.track("Options", options);

            } catch (JSONException e) {

                Log.e("Open options error", "Error", e);
            }
        }

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

        if(Options.getDataAnalytics(this)) {
            try {
                JSONObject sos = new JSONObject();
                sos.put("Open SOS", "True");
                mixPanelSOS.track("SOS", sos);

            } catch (JSONException e) {

                Log.e("Open SOS error", "Error", e);
            }
        }

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

                case "language":
                    if (Options.getEnabledVoice(MainActivity.this)) {
                        playSound.playSymbol(MainActivity.this, R.raw.morseletters);
                    }
                    LettersDialog.showLetters(MainActivity.this);

                    break;

                case "English":
                    if (Options.getEnabledVoice(MainActivity.this)) {
                        playSound.playSymbol(MainActivity.this, R.raw.morseletters);
                    }
                    LettersDialog.showLetters(MainActivity.this);

                    break;

                case "Spanish":
                    if (Options.getEnabledVoice(MainActivity.this)) {
                        playSound.playSymbol(MainActivity.this, R.raw.morselettersspanish);
                    }
                    LettersDialog.showLettersSpanish(MainActivity.this);

                    break;

                case "Chinese":
                    if (Options.getEnabledVoice(MainActivity.this)) {
                        playSound.playSymbol(MainActivity.this, R.raw.morseletterschinese);
                    }
                    LettersDialog.showLettersChinese(MainActivity.this);
            }
        }


        public void onSwipeLeft() {
            switch(language) {


                case "language":
                    if (Options.getEnabledVoice(MainActivity.this)) {
                        playSound.playSymbol(MainActivity.this, R.raw.numbers);
                    }
                    LettersDialog.showNumbers(MainActivity.this);

                    break;

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
                    LettersDialog.showNumbersSpanish(MainActivity.this);

                    break;

                case "Chinese":
                    if (Options.getEnabledVoice(MainActivity.this)) {
                        playSound.playSymbol(MainActivity.this, R.raw.numberschinese);
                    }
                    LettersDialog.showNumbersChinese(MainActivity.this);
            }
            }

        public void onSwipeTop() {


            switch(language){

                case "language":
                    if(Options.getEnabledVoice(MainActivity.this)) {
                        playSound.playSymbol(MainActivity.this, R.raw.zcodes);
                    }
                    LettersDialog.showZCodes(MainActivity.this);
                    break;

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
                    }
                    LettersDialog.showQCodesChinese(MainActivity.this);
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
            case "Chinese":
                array = ShowCaseViewArrays.mainActivityChinese();
                break;
            default:
                array = ShowCaseViewArrays.mainActivity();
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
                                " the help icon - pressing this will help guide you on how to use features on each screen, try it out!")
                .withoutShape()
                .setDelay(200).singleUse(SHOWCASE_ID) // provide a unique ID used to ensure it is only shown once
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


//  Flushes any unsent events to Mix Panel
    @Override
    protected void onDestroy() {
        mixPanel.flush();
        mixPanelOptions.flush();
        mixPanelSOS.flush();
        mixPanelTools.flush();
        mixPanelTraining.flush();
        super.onDestroy();
    }

    @Override
    public void onResume(){
        super.onResume();
    }
}