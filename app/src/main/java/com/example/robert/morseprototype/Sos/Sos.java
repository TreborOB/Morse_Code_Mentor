package com.example.robert.morseprototype.Sos;


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


    private Sound playSound = new Sound();
    Output mOutput;

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


        if(Options.getSosSpeed(this)) {
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
                    if (isChecked)
                        playSound.playSymbol(Sos.this, R.raw.audioon);
                    else playSound.playSymbol(Sos.this, R.raw.audiooff);
            }
        });

        switchVibrate.setOncheckListener(new Switch.OnCheckListener() {

            @Override
            public void onCheck(Switch View, boolean isChecked) {
                if(!isChecked)
                    mOutput.cancel();
                mOutput.setVibratorEnabled(isChecked);

                if (Options.getEnabledVoice(Sos.this))
                    if (isChecked)
                        playSound.playSymbol(Sos.this, R.raw.vibrateon);
                    else playSound.playSymbol(Sos.this, R.raw.vibrateoff);
            }
        });

        switchLight.setOncheckListener(new Switch.OnCheckListener() {

            @Override
            public void onCheck(Switch View, boolean isChecked) {
                if(!isChecked)
                   mOutput.cancel();


                mOutput.setLightEnabled(isChecked);

                if (Options.getEnabledVoice(Sos.this))
                    if (isChecked)
                        playSound.playSymbol(Sos.this, R.raw.flashon);
                    else playSound.playSymbol(Sos.this, R.raw.flashoff);
            }
        });

        switchFlash.setOncheckListener(new Switch.OnCheckListener() {

            @Override
            public void onCheck(Switch View, boolean isChecked) {
                if(!isChecked)
                    mOutput.cancel();

                mOutput.setScreenEnabled(isChecked, mImageHandler);

                if (Options.getEnabledVoice(Sos.this))
                    if (isChecked)
                        playSound.playSymbol(Sos.this, R.raw.screenflashon);
                    else playSound.playSymbol(Sos.this, R.raw.screenflashoff);
            }
        });

    }

    public void startSOS(View v) {

        final MorseTranslations morseTranslations = new MorseTranslations();
        final String st = morseTranslations.translatedText("SOS");

        mOutput.outputString(st);
    }


    private void showCaseMainActivity(){


        config.setDelay(300);

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this);

        sequence.setConfig(config);

        sequence.addSequenceItem(sosButton,
                "Select how you would like to send the SOS Morse sequence and hit this button to start", "GOT IT");



        sequence.start();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_favorite:

                if(Options.getEnabledVoice(this))
                    playSound.playSymbol(Sos.this, R.raw.help);

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