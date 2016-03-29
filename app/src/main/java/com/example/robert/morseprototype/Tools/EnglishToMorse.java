package com.example.robert.morseprototype.Tools;


import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.robert.morseprototype.Hardware.Sound;
import com.example.robert.morseprototype.Misc.BaseActivity;
import com.example.robert.morseprototype.Misc.MainActivity;
import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.Switch;
import com.example.robert.morseprototype.Hardware.Output;
import com.example.robert.morseprototype.Misc.MorseTranslations;
import com.example.robert.morseprototype.Options.Options;
import com.example.robert.morseprototype.R;
import com.example.robert.morseprototype.SwipeDialogs.LettersDialog;
import com.example.robert.morseprototype.SwipeDialogs.MorseGestureDetector;
import com.google.common.base.Strings;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class EnglishToMorse extends BaseActivity {

    private GestureDetectorCompat         mDetector;
    private MorseTranslations             morseTranslations;
    private Output                        mOutput;

    @Bind(R.id.editText) EditText    textViewSource;
    @Bind(R.id.textView2) TextView   textViewResult;
    @Bind(R.id.switch1) Switch       switchLight;
    @Bind(R.id.translate) ButtonFlat translate;
    @Bind(R.id.reset) ButtonFlat     reset;

    private final ShowcaseConfig config = new ShowcaseConfig();

    private final Sound playSound = new Sound();

    private String language;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_to_morse);
        ButterKnife.bind(this);

        language = Options.getLanguage(EnglishToMorse.this);

        mDetector = new GestureDetectorCompat(this, new mainMorseGestureDetector());

        morseTranslations = new MorseTranslations();


        mOutput = new Output(this, false, false, false, false);

        //Allows the user to turn on the camera flash if the device has one
        switchLight.setClickable(mOutput.getHasCamera());

        switchLight.setOncheckListener(new Switch.OnCheckListener() {
            @Override
            public void onCheck(Switch buttonView, boolean isChecked) {
                mOutput.setLightEnabled(isChecked);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(Options.getEnabledDialogs(this))
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }




    public void translate(View view) {

        String str = textViewSource.getText().toString().trim();

        if (Strings.isNullOrEmpty(str)) {
            YoYo.with(Techniques.Shake).duration(700).playOn(findViewById(R.id.editText));
        } else {
            String translatedText = morseTranslations.translatedText(str);
            textViewResult.setText(translatedText);

            if(switchLight.isCheck())
            mOutput.outputString(translatedText);
        }
    }

    public void reset(View view) {
        textViewSource.setText("");
        textViewResult.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mOutput.release();
    }


    private class mainMorseGestureDetector extends MorseGestureDetector {

        public void onSwipeRight() {
            LettersDialog.showLetters(EnglishToMorse.this);
        }

        public void onSwipeLeft() {
            LettersDialog.showNumbers(EnglishToMorse.this);
        }

        public void onSwipeBottom() {

            switch(language){

                case "English":
                    LettersDialog.showQCodes(EnglishToMorse.this);

                    break;

                case "Spanish":
                    LettersDialog.showQCodesSpanish(EnglishToMorse.this);

                    break;

                case "Chinese":
                    LettersDialog.showQCodesChinese(EnglishToMorse.this);

                    break;
            }


        }

        public void onSwipeTop() {


            switch(language){

                case "English":
                    LettersDialog.showZCodes(EnglishToMorse.this);
                    break;

                case "Spanish":
                    LettersDialog.showZCodesSpanish(EnglishToMorse.this);

                    break;

                case "Chinese":
                    LettersDialog.showZCodesChinese(EnglishToMorse.this);

                    break;
            }
        }
    }


    private void showCaseMainActivity(){


        config.setDelay(300);

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this);

        sequence.setConfig(config);

        sequence.addSequenceItem(textViewSource,
                "Enter the text you would like translated here", "GOT IT");

        sequence.addSequenceItem(translate,
                "This button then translates the text you entered", "GOT IT");

        sequence.addSequenceItem(reset,
                "Use reset to clear the text so you can enter a new word", "GOT IT");

        sequence.addSequenceItem(switchLight,
                "This switch toggles the flash on or off, easy!", "GOT IT");


        sequence.start();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_favorite:

                if(Options.getEnabledVoice(this))

                    switch(language) {
                        case "English":
                            playSound.playSymbol(EnglishToMorse.this, R.raw.help);
                            break;

                        case "Spanish":
                            playSound.playSymbol(EnglishToMorse.this, R.raw.help);
                            break;

                        default:
                            playSound.playSymbol(EnglishToMorse.this, R.raw.help);
                            break;
                    }

                showCaseMainActivity();
        }
        return true;

    }





}



