package com.example.robert.morseprototype.Training;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.robert.morseprototype.Misc.MorseTranslations;
import com.example.robert.morseprototype.Options.Options;
import com.example.robert.morseprototype.R;
import com.github.siyamed.shapeimageview.CircularImageView;

public class MorseInput {

    private static final String CHAR_DOT   = ".";
    private static final String CHAR_DASH  = "-";
    private static final String CHAR_SPACE = " ";

    private int TYPE_END_DELAY_MILLIS;
    private int DOT_MAX_PRESS_LENGTH;
    private int DASH_MAX_PRESS_LENGTH;
    private int NEW_WORD;
    private int INPUT_END;
    private int PROGRESS_BAR;
    private int PROGRESS_BAR_INCREASE;



    public interface OnCharacterDecoded {
        void onCharacterDecoded(String character);
    }

    public interface OnPadStateChanged {
        void onPadPressed();

        void onPadReleased();
    }

    public interface OnEndOfInput {
        void onInputEnded();
    }

    private MorseTranslations mTranslator;


    private CircularImageView introPad;
    private TextView          textViewMorseLetter;
    private TextView          textViewTextLetter;
    private ProgressBar       progressBar;
    private ImageView         tickOne;
    private ImageView         tickTwo;
    private ImageView         tickThree;


    private StringBuilder mMorseStringBuilder = new StringBuilder();
    private StringBuilder mWords              = new StringBuilder();


    private long padPressedAtTime;
    private long padPressDuration;
    private int  progressBarValue;

    private long padReleased;
    private long timeSincePadReleased;

    OnEndOfInput               mOnEndOfInputCallback;
    private OnCharacterDecoded mOnCharacterDecodedCallback;
    private OnPadStateChanged  mOnStateChangedBack;


    public MorseInput(Activity activity, TextView textViewTextLetter, TextView textViewMorseLetter, CircularImageView introPad, ProgressBar progressBar, ImageView tickOne, ImageView tickTwo, ImageView tickThree) {
        this.textViewTextLetter     = textViewTextLetter;
        this.textViewMorseLetter    = textViewMorseLetter;
        this.introPad               = introPad;
        this.progressBar            = progressBar;
        this.tickOne                = tickOne;
        this.tickTwo                = tickTwo;
        this.tickThree              = tickThree;

        Options.InputSpeeds inputSpeeds = Options.getInputSpeeds(activity);

        TYPE_END_DELAY_MILLIS       = inputSpeeds.TYPE_END_DELAY_MILLIS;
        DOT_MAX_PRESS_LENGTH        = inputSpeeds.DOT_MAX_PRESS_LENGTH;
        DASH_MAX_PRESS_LENGTH       = inputSpeeds.DASH_MAX_PRESS_LENGTH;
        NEW_WORD                    = inputSpeeds.NEW_WORD;
        INPUT_END                   = inputSpeeds.INPUT_END;
        PROGRESS_BAR                = inputSpeeds.PROGRESS_BAR;
        PROGRESS_BAR_INCREASE       = inputSpeeds.PROGRESS_BAR_INCREASE;


        init();
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            //Increments the progress bar
            progressBarValue += PROGRESS_BAR_INCREASE;

            if (progressBarValue > PROGRESS_BAR) {
                //Changes the progress bar to blue once it reaches over 200ms (dash)
                progressBar.getProgressDrawable().setColorFilter(Color.rgb(30,136,229), PorterDuff.Mode.SRC_IN);
            }

            progressBar.setProgress(progressBarValue);
            handler.sendEmptyMessageDelayed(0, 50);
        }
    };

    public void setOnCharacterDecodedCallback(Activity activity) {
        mOnCharacterDecodedCallback = (OnCharacterDecoded) activity;
    }

    public void setOnPadChangeChangedCallback(Activity activity) {
        mOnStateChangedBack = (OnPadStateChanged) activity;
    }

    public void setOnEndOfInputCallback(Activity activity) {
        mOnEndOfInputCallback = (OnEndOfInput) activity;
    }

    public void init() {

        mTranslator = new MorseTranslations();

        progressBar.setScaleY(3f);
        progressBar.getProgressDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);

        introPad.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //Pad pressed
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    onPadPressedStart();
                }
                //Pad released
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    onPadPressedEnd();
                }
                return true;
            }

        });

        padReleased = 0;

    }

    public void reset() {
        mMorseStringBuilder.setLength(0);
        mWords.setLength(0);
        padReleased = 0;
    }

    private void onPadPressedStart() {
        padPressedAtTime = System.currentTimeMillis();
        timeSincePadReleased = System.currentTimeMillis() - padReleased;

        introPad.setImageResource(R.drawable.pressed_blue);

        //This code adds the space to the word
        if (padReleased > 0 && timeSincePadReleased > NEW_WORD) {
            mWords.append(CHAR_SPACE);
        }

        progressBarValue = 0;
        handler.sendEmptyMessage(0);



        if (mOnStateChangedBack != null) mOnStateChangedBack.onPadPressed();

        handler.removeCallbacks(endOfInputTask);

    }

    private void onPadPressedEnd() {

        padPressDuration = System.currentTimeMillis() - padPressedAtTime;
        padReleased = System.currentTimeMillis();

        introPad.setImageResource(R.drawable.blue);

        handler.removeMessages(0);
        progressBar.setProgress(0);
        progressBar.getProgressDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);



        if (padPressDuration < DOT_MAX_PRESS_LENGTH) {
            mMorseStringBuilder.append(CHAR_DOT);
        } else if (padPressDuration > DOT_MAX_PRESS_LENGTH && padPressDuration < DASH_MAX_PRESS_LENGTH) {
            mMorseStringBuilder.append(CHAR_DASH);
        }


        handler.removeCallbacks(translateMorseSymbolTask);
        handler.postDelayed(translateMorseSymbolTask, TYPE_END_DELAY_MILLIS);


        String resultString = mMorseStringBuilder.toString();
        textViewMorseLetter.setText(resultString);


        if (mOnStateChangedBack != null) mOnStateChangedBack.onPadReleased();
    }

    Runnable translateMorseSymbolTask = new Runnable() {
        @Override
        public void run() {


            String mCharacterToAppend = mTranslator.translateMorse(mMorseStringBuilder);


            mWords.append(mCharacterToAppend);

            //Morse letter that is appended!
            textViewTextLetter.setText(mWords);

            mMorseStringBuilder.setLength(0);
            textViewMorseLetter.setText("");


            //Checks to see if it is correct
            //fire on every symbol
            if (mOnCharacterDecodedCallback != null) mOnCharacterDecodedCallback.onCharacterDecoded(mCharacterToAppend);

            //will fire on end of input
            handler.removeCallbacks(endOfInputTask);
            handler.postDelayed(endOfInputTask, INPUT_END);


        }
    };

    Runnable endOfInputTask = new Runnable() {
        @Override
        public void run() {
            if (mOnEndOfInputCallback != null) mOnEndOfInputCallback.onInputEnded();
        }
    };

    public String getWord() {
        return mWords.toString();
    }

}
