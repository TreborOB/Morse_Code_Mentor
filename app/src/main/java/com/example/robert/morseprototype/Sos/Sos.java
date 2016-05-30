package com.example.robert.morseprototype.Sos;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import com.example.robert.morseprototype.Misc.BaseActivity;
import com.example.robert.morseprototype.Hardware.Output;
import com.example.robert.morseprototype.Hardware.Sound;
import com.example.robert.morseprototype.Misc.MorseTranslations;
import com.example.robert.morseprototype.Misc.ShowCaseViewArrays;
import com.example.robert.morseprototype.Options.Options;
import com.example.robert.morseprototype.R;
import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.Switch;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class Sos extends BaseActivity {



    @Bind(R.id.switchLight) Switch       switchLight;
    @Bind(R.id.switchFlash) Switch       switchFlash;
    @Bind(R.id.switchVibrate) Switch     switchVibrate;
    @Bind(R.id.switchAudio) Switch       switchAudio;
    @Bind(R.id.imageViewFlash) ImageView flashImageView;
    @Bind(R.id.button9) ButtonFlat       sosButton;


    private ShowcaseConfig config = new ShowcaseConfig();

    String language;

    private Sound playSound = new Sound();
    Output mOutput;

    @SuppressLint("HandlerLeak")
    Handler mImageHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            if (msg.what == 1)
                flashImageView.setBackgroundColor(Color.WHITE);
            else
                flashImageView.setBackgroundColor(Color.rgb(30,136,229));
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);
        ButterKnife.bind(this);

        language = Options.getLanguage(Sos.this);

        if(Options.getScreenFlashSpeed(this)) {
            Options.setSpeedFast();
        }else{

            Options.setSpeedSlow();
        }

        mOutput = new Output(this, false, false, false, false);

        bindInterface();

        mOutput.setScreenViewHandler(mImageHandler);
    }

    private void bindInterface() {

        switchLight.setClickable(mOutput.getHasCamera());

        switchAudio.setOncheckListener(new Switch.OnCheckListener() {

            @Override
            public void onCheck(Switch View, boolean isChecked) {
                if(!isChecked)
                    mOutput.cancel();
                mOutput.setSoundEnabled(isChecked);

                if (Options.getEnabledVoice(Sos.this))
                    if (isChecked){

                        switch(language) {
                            case "English":
                                playSound.playSymbol(Sos.this, R.raw.audioon);
                                break;

                            case "Spanish":
                                playSound.playSymbol(Sos.this, R.raw.audioonspanish);
                                break;

                            default:
                                playSound.playSymbol(Sos.this, R.raw.audioonchinese);
                                break;
                        }
                    }
                    else {


                        switch(language) {
                            case "English":
                                playSound.playSymbol(Sos.this, R.raw.audiooff);
                                break;

                            case "Spanish":
                                playSound.playSymbol(Sos.this, R.raw.audiooffspanish);
                                break;

                            default:
                                playSound.playSymbol(Sos.this, R.raw.audiooffchinese);
                                break;
                        }
                    }
            }
        });

        switchVibrate.setOncheckListener(new Switch.OnCheckListener() {

            @Override
            public void onCheck(Switch View, boolean isChecked) {
                if(!isChecked)
                    mOutput.cancel();
                mOutput.setVibratorEnabled(isChecked);

                if (Options.getEnabledVoice(Sos.this))
                    if (isChecked) {

                        switch(language) {
                            case "English":
                                playSound.playSymbol(Sos.this, R.raw.vibrateon);
                                break;

                            case "Spanish":
                                playSound.playSymbol(Sos.this, R.raw.vibrateonspanish);
                                break;

                            default:
                                playSound.playSymbol(Sos.this, R.raw.vibrateonchinese);
                                break;
                        }
                    }
                    else {
                        switch(language) {
                            case "English":
                                playSound.playSymbol(Sos.this, R.raw.vibrateoff);
                                break;

                            case "Spanish":
                                playSound.playSymbol(Sos.this, R.raw.virbrateoffspanish);
                                break;

                            default:
                                playSound.playSymbol(Sos.this, R.raw.vibrateoffchinese);
                                break;
                        }
                    }
            }
        });

        switchLight.setOncheckListener(new Switch.OnCheckListener() {

            @Override
            public void onCheck(Switch View, boolean isChecked) {
                if(!isChecked)
                    mOutput.cancel();


                mOutput.setLightEnabled(isChecked);

                if (Options.getEnabledVoice(Sos.this))
                    if (isChecked) {
                        switch(language) {
                            case "English":
                                playSound.playSymbol(Sos.this, R.raw.flashon);
                                break;

                            case "Spanish":
                                playSound.playSymbol(Sos.this, R.raw.flashonspanish);
                                break;

                            default:
                                playSound.playSymbol(Sos.this, R.raw.flashonchinese);
                                break;
                        }
                    }
                    else{
                        switch(language) {
                            case "English":
                                playSound.playSymbol(Sos.this, R.raw.flashoff);
                                break;

                            case "Spanish":
                                playSound.playSymbol(Sos.this, R.raw.flashoffspanish);
                                break;

                            case "Chinese":
                                playSound.playSymbol(Sos.this, R.raw.flashoffchinese);
                                break;
                        }
                    }
            }
        });

        switchFlash.setOncheckListener(new Switch.OnCheckListener() {

            @Override
            public void onCheck(Switch View, boolean isChecked) {
                if(!isChecked)
                    mOutput.cancel();

                mOutput.setScreenEnabled(isChecked, mImageHandler);

                if (Options.getEnabledVoice(Sos.this))
                    if (isChecked) {
                        switch(language) {
                            case "English":
                                playSound.playSymbol(Sos.this, R.raw.screenflashon);
                                break;

                            case "Spanish":
                                playSound.playSymbol(Sos.this, R.raw.screenflashonspanish);
                                break;

                            case "Chinese":
                                playSound.playSymbol(Sos.this, R.raw.screenflashonchinese);
                                break;
                        }
                    }
                    else {
                        switch(language) {
                            case "English":
                                playSound.playSymbol(Sos.this, R.raw.screenflashoff);
                                break;

                            case "Spanish":
                                playSound.playSymbol(Sos.this, R.raw.screenflashoffspanish);
                                break;

                            case "Chinese":
                                playSound.playSymbol(Sos.this, R.raw.screenflashoffchinese);
                                break;
                        }
                    }
            }
        });

    }

    public void startSOS(View view) {

        final MorseTranslations morseTranslations = new MorseTranslations();
        final String st = morseTranslations.translatedText("SOS");

        mOutput.outputString(st);
    }


    private void showCaseMainActivity(){


        config.setDelay(300);


        String array[];

        switch (language) {
            case "English":
                array = ShowCaseViewArrays.sosEnglish();
                break;
            case "Spanish":
                array = ShowCaseViewArrays.sosSpanish();
                break;
            default:
                array = ShowCaseViewArrays.sosChinese();
                break;
        }



        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this);

        sequence.setConfig(config);

        sequence.addSequenceItem(sosButton, array[0], array[1]);



        sequence.start();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_favorite:

                switch(language) {
                    case "English":
                        if(Options.getEnabledVoice(this))
                        playSound.playSymbol(Sos.this, R.raw.help);
                        break;

                    case "Spanish":
                        if(Options.getEnabledVoice(this))
                        playSound.playSymbol(Sos.this, R.raw.helpspanish);
                        break;

                    default:
                        if(Options.getEnabledVoice(this))
                        playSound.playSymbol(Sos.this, R.raw.helpchinese);
                        break;
                }

                showCaseMainActivity();
        }
        return true;

    }




    @Override
    protected void onDestroy() {
        super.onDestroy();

        mOutput.release();
    }
}