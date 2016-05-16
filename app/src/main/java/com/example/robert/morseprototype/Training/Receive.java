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
import com.example.robert.morseprototype.Misc.MorseTranslations;
import com.example.robert.morseprototype.Options.Options;
import com.example.robert.morseprototype.R;
import com.gc.materialdesign.views.Button;
import com.gc.materialdesign.views.ButtonFlat;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class Receive extends BaseActivity {


    @Bind(R.id.imageViewReceive)
    ImageView imageViewReceive;
    @Bind(R.id.startReceive)
    ButtonFlat startReceive;
    @Bind(R.id.wordFlashed)
    TextView wordFlashed;

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

    //Creates arrays to hold the words to be flashed
    ArrayList<String> list = new ArrayList<>();

    List<String> listEnglish = new ArrayList<>();
    List<String> listSpanish = new ArrayList<>();
    List<String> listChinese = new ArrayList<>();

    String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        ButterKnife.bind(this);

        language = Options.getLanguage(this);


        //Sets screen flash speed
        if (Options.getScreenFlashSpeed(this)) {
            Options.setSpeedFast();
        } else {
            Options.setSpeedSlow();
        }

        init();


        SnappyStrings.SnappyStringsInit(this);
        SnappyStrings.initSnappy();

        list = SnappyStrings.getSnappy();

        //Creates sub-lists from the retrieved Snappy list each containing language specific words
        listEnglish = list.subList(0, 10);
        listSpanish = list.subList(11, 20);
        listChinese = list.subList(21, 30);


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


    private void startScreenFlash() {

        release();

        init();

        final MorseTranslations morseTranslations = new MorseTranslations();

        Random rand = new Random();

        switch (language) {
            case "English":
            case "language":

                int random = rand.nextInt(listEnglish.size());
                randomWord = listEnglish.get(random);
                break;
            case "Spanish":
                int randomSpanish = rand.nextInt(listSpanish.size());
                randomWord = listSpanish.get(randomSpanish);
                break;
            case "Chinese":
                int randomChinese = rand.nextInt(listChinese.size());
                randomWord = listChinese.get(randomChinese);
                break;
        }

        final String st = morseTranslations.translatedText(randomWord);
        mOutput.outputString(st);

        setHint();
    }


    public void setWordFlashed() {
        wordFlashed.setText(randomWord);

        mOutput.cancel();
    }

    private void showCaseMainActivity() {


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

                switch (language) {
                    case "English":
                        if (Options.getEnabledVoice(this))
                            playSound.playSymbol(Receive.this, R.raw.help);
                        break;

                    case "Spanish":
                        if (Options.getEnabledVoice(this))
                            playSound.playSymbol(Receive.this, R.raw.helpspanish);
                        break;

                    case "Chinese":
                        if (Options.getEnabledVoice(this))
                            playSound.playSymbol(Receive.this, R.raw.helpchinese);
                        break;
                }

                showCaseMainActivity();
        }
        return true;
    }


    public void setHint() {

        switch (language) {
            case "English":
                wordFlashed.setText(R.string.press_to_see_answer);
                break;
            case "Spanish":
                wordFlashed.setText(R.string.press_to_see_answer);
                break;
            case "Chinese":
                wordFlashed.setText(R.string.press_to_see_answer);
                break;
        }
    }


    public void init() {

        mOutput = new Output(this, false, false, false, false);

        bindInterface();

        mOutput.setScreenViewHandler(mImageHandler);

        startReceive.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                startScreenFlash();

            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SnappyStrings.closeSnappyStrings();
        mOutput.release();
    }

    public void release() {
        mOutput.release();
    }


    public void onPause() {
        super.onPause();
        SnappyStrings.closeSnappyStrings();
    }


}