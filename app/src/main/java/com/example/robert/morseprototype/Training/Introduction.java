package com.example.robert.morseprototype.Training;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.robert.morseprototype.Hardware.Output;
import com.example.robert.morseprototype.Misc.BaseActivity;
import com.example.robert.morseprototype.Hardware.Sound;
import com.example.robert.morseprototype.Misc.ShowCaseViewArrays;
import com.example.robert.morseprototype.Options.Options;
import com.example.robert.morseprototype.R;
import com.example.robert.morseprototype.SwipeDialogs.LettersDialog;
import com.example.robert.morseprototype.SwipeDialogs.MorseGestureDetector;
import com.example.robert.morseprototype.Training.MorseInput.OnEndOfInput;
import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.Switch;
import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;


@SuppressWarnings("ResourceType")
public class Introduction extends BaseActivity implements OnEndOfInput, MorseInput.OnPadStateChanged {


    private GestureDetectorCompat mDetector;

    private Sound   playSound = new Sound();
    private Output  mOutput;

    MorseInput morseInput;

    private ShowcaseConfig config = new ShowcaseConfig();

    private ArrayList<MorseTutorial> mSteps;
    private int                      mCurrentStep;
    private int                      tickProgress;



    @Bind(R.id.padIntro)           CircularImageView    introPad;
    @Bind(R.id.introMorseChar)     TextView             introTextViewMorseChar;
    @Bind(R.id.introTextChar)      TextView             introTextViewTextChar;
    @Bind(R.id.introProgressBar)   ProgressBar          introProgressBar;
    @Bind(R.id.introTextObjective) JustifiedTextView    introObjective;
    @Bind(R.id.introButtonNext)    ButtonFlat           introNext;
    @Bind(R.id.introButtonPrev)    ButtonFlat           introPrevious;
    @Bind(R.id.tickOne)            ImageView            tickOne;
    @Bind(R.id.tickTwo)            ImageView            tickTwo;
    @Bind(R.id.tickThree)          ImageView            tickThree;
    @Bind(R.id.introSound)         Switch               introSound;

    String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        ButterKnife.bind(this);


        mDetector = new GestureDetectorCompat(this, new mainMorseGestureDetector());

        progressBarInvisible();
        morseInput = new MorseInput(this, introTextViewMorseChar, introTextViewTextChar, introPad, introProgressBar, tickOne, tickTwo, tickThree);
        morseInput.setOnEndOfInputCallback(this);


        language = Options.getLanguage(Introduction.this);


        switch (language) {
            case "English":
                mSteps = TutorialSteps.getIntro();
                break;
            case "Spanish":
                mSteps = TutorialSteps.getIntroSpanish();
                break;
            default:
                mSteps = TutorialSteps.getIntroChinese();
                break;
        }



        mCurrentStep = 0;

        updateCurrentStep();


        mOutput = new Output(this, false, false, false, false);


        introSound.setOncheckListener(new Switch.OnCheckListener() {
            @Override
            public void onCheck(Switch buttonView, boolean isChecked) {
                mOutput.setSoundEnabled(isChecked);
            }
        });


        morseInput.setOnPadChangeChangedCallback(this);
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



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(Options.getEnabledDialogs(this))
            this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }




    @SuppressWarnings("WrongConstant")
    private void updateCurrentStep() {
        resetText();


        MorseTutorial step = mSteps.get(mCurrentStep);

        introObjective.setText(step.getObjective());
        introPrevious.setEnabled(step.getPrevEnabled());
        introNext.setEnabled(step.getNextEnabled());
        introNext.setText(step.getNextTitle());
        introProgressBar.setVisibility(step.getPadVisibility());
        introPad.setVisibility(step.getPadVisibility());
        tickOne.setVisibility(step.isTickOneEnabled());
        tickTwo.setVisibility(step.isTickTwoEnabled());
        tickThree.setVisibility(step.isTickThreeEnabled());

        progressBarInvisible();

    }



    public void onNextClicked(View view) {

        if (mCurrentStep == 1) morseInput.reset();

        if (mCurrentStep >= mSteps.size() - 1) return;

        mCurrentStep++;
        updateCurrentStep();
        resetTicks();
        setNextButtonInvisible();
    }



    public void onPreviousClicked(View view) {
        if (mCurrentStep <= 0) return;

        mCurrentStep--;
        updateCurrentStep();
        resetText();
        setNextButtonVisible();
    }



    private void resetText() {
        introTextViewTextChar.setText("");
        introTextViewMorseChar.setText("");
    }



    private void updateTicks() {

        if (tickProgress == 0) {
            tickOne.setBackgroundResource(R.drawable.blank_tick);
            tickTwo.setBackgroundResource(R.drawable.blank_tick);
            tickThree.setBackgroundResource(R.drawable.blank_tick);
        } else if (tickProgress == 1) {
            tickOne.setBackgroundResource(R.drawable.completed_tick);
        } else if (tickProgress == 2) {
            tickTwo.setBackgroundResource(R.drawable.completed_tick);
        } else if (tickProgress == 3) {
            tickThree.setBackgroundResource(R.drawable.completed_tick);
            setNextButtonVisible();
        }
    }


    private void resetTicks() {
        tickProgress = 0;
        updateTicks();
    }


    private void progressBarInvisible() {
        if (Options.getEnabledProgressBar(Introduction.this)) {
            introProgressBar.setVisibility(View.INVISIBLE);
        }
    }


    private void setNextButtonInvisible() {
        introNext.setVisibility(4);
    }

    private void setNextButtonVisible() {
        introNext.setVisibility(1);
    }

    private void playSuccessSound() {
        playSound.playSymbol(this, R.raw.success);
    }

    private void playFailureSound() {
        playSound.playSymbol(this, R.raw.failure);
    }



    @Override
    public void onInputEnded() {
        String answer = mSteps.get(mCurrentStep).getAnswer();
        String inputSentence = morseInput.getWord();

        if (inputSentence.equals(answer)) {

            SuperToast.create(this, "Right Answer!", SuperToast.Duration.VERY_SHORT,
            Style.getStyle(Style.BLUE, SuperToast.Animations.FLYIN)).show();

            playSuccessSound();
            tickProgress++;
            updateTicks();
        }
        else {

            SuperToast.create(this, "Incorrect!", SuperToast.Duration.VERY_SHORT,
            Style.getStyle(Style.BLUE, SuperToast.Animations.FLYIN)).show();

            playFailureSound();
        }

        morseInput.reset();
        resetText();
    }




    private void showCaseMainActivity(){


        config.setDelay(300);

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this);

        String array[];

        switch (language) {
            case "English":
                array = ShowCaseViewArrays.introductionEnglish();
                break;
            case "Spanish":
                array = ShowCaseViewArrays.introductionSpanish();
                break;
            default:
                array = ShowCaseViewArrays.introductionChinese();
                break;
        }



        sequence.setConfig(config);

        sequence.addSequenceItem(introObjective,
                array[0], array[2]);

        sequence.addSequenceItem(introTextViewMorseChar,
                array[1], array[2]);


        sequence.start();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_favorite:

                switch(language) {
                    case "English":
                        playSound.playSymbol(Introduction.this, R.raw.help);
                        break;

                    case "Spanish":
                        playSound.playSymbol(Introduction.this, R.raw.helpspanish);
                        break;

                    default:
                        playSound.playSymbol(Introduction.this, R.raw.helpchinese);
                        break;
                }

                showCaseMainActivity();
        }
        return true;

    }





    private class mainMorseGestureDetector extends MorseGestureDetector {

        public void onSwipeRight() {
            LettersDialog.showLetters(Introduction.this);
        }

        public void onSwipeLeft() {
            LettersDialog.showNumbers(Introduction.this);
        }

        public void onSwipeBottom() {
            LettersDialog.showQCodes(Introduction.this);
        }

        public void onSwipeTop() {
            LettersDialog.showZCodes(Introduction.this);
        }
    }

}
