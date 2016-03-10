package com.example.robert.morseprototype.Tools;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.example.robert.morseprototype.Misc.BaseActivity;
import com.github.siyamed.shapeimageview.CircularImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.robert.morseprototype.Hardware.Output;
import com.example.robert.morseprototype.Options.Options;
import com.example.robert.morseprototype.R;
import com.example.robert.morseprototype.SwipeDialogs.LettersDialog;
import com.example.robert.morseprototype.SwipeDialogs.MorseGestureDetector;
import com.example.robert.morseprototype.Training.MorseInput;
import com.example.robert.morseprototype.Training.MorseInput.OnCharacterDecoded;
import com.example.robert.morseprototype.Training.MorseInput.OnPadStateChanged;
import com.gc.materialdesign.views.Switch;


import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;


public class MorsePad extends BaseActivity implements OnCharacterDecoded, OnPadStateChanged {

    private GestureDetectorCompat mDetector;

    private MorseInput morseInput;
    private Output mOutput;

    private ShowcaseConfig config = new ShowcaseConfig();


    @Bind(R.id.imgView) CircularImageView    introPad;
    @Bind(R.id.textViewMorseChar) TextView   textViewMorseChar;
    @Bind(R.id.textViewTextChar) TextView    textViewTextChar;
    @Bind(R.id.textViewResult) TextView      sentence;
    @Bind(R.id.progressBar) ProgressBar      progressBar;
    @Bind(R.id.audioSwitch) Switch           switchAudio;
    @Bind(R.id.lightSwitch) Switch           switchLight;
    @Bind(R.id.vibrateSwitch) Switch         switchVibrate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morse_pad);
        ButterKnife.bind(this);


        mDetector = new GestureDetectorCompat(this, new mainMorseGestureDetector());

        mOutput = new Output(this, false, false, false, false);

        bindInterface();

        morseInput = new MorseInput(this, textViewMorseChar, textViewTextChar, introPad, progressBar, null, null, null);
        morseInput.setOnPadChangeChangedCallback(this);
        morseInput.setOnCharacterDecodedCallback(this);

        progressBarInvisible();

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(Options.getEnabledDialogs(this))
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private void bindInterface() {


        switchLight.setClickable(mOutput.getHasCamera());



        switchLight.setOncheckListener(new Switch.OnCheckListener() {

            @Override
            public void onCheck(Switch View, boolean isChecked) {
                mOutput.setVibratorEnabled(isChecked);
            }
        });

        switchAudio.setOncheckListener(new Switch.OnCheckListener() {

            @Override
            public void onCheck(Switch buttonView, boolean isChecked) {
                mOutput.setSoundEnabled(isChecked);
            }
        });

       switchVibrate.setOncheckListener(new Switch.OnCheckListener() {

           @Override
           public void onCheck(Switch View, boolean isChecked) {
               mOutput.setLightEnabled(isChecked);
           }
       });

    }

    @Override
    public void onCharacterDecoded(String character) {
        sentence.setText(morseInput.getWord());
    }

    @Override
    public void onPadPressed() {
        mOutput.turnOn();
    }

    @Override
    public void onPadReleased() {
        mOutput.turnOff();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mOutput.release();
    }




    private class mainMorseGestureDetector extends MorseGestureDetector {

        public void onSwipeRight() {
            LettersDialog.showLetters(MorsePad.this);
        }

        public void onSwipeLeft() {
            LettersDialog.showNumbers(MorsePad.this);
        }

        public void onSwipeBottom() {
            LettersDialog.showQCodes(MorsePad.this);
        }

        public void onSwipeTop() {
            LettersDialog.showZCodes(MorsePad.this);
        }
    }





    private  void progressBarInvisible() {
        if(Options.getEnabledProgressBar(MorsePad.this)){
            progressBar.setVisibility(View.INVISIBLE);
        }
    }


    private void showCaseMainActivity(){


        config.setDelay(300);

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this);

        sequence.setConfig(config);

        sequence.addSequenceItem(introPad,
                "Here you can use your newly learnt Morse skills anyway you choose", "GOT IT");



        sequence.start();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_favorite:

                showCaseMainActivity();
        }
        return true;

    }



}