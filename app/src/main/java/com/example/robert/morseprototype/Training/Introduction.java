package com.example.robert.morseprototype.Training;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.robert.morseprototype.Misc.BaseActivity;
import com.example.robert.morseprototype.Hardware.Sound;
import com.example.robert.morseprototype.Options.Options;
import com.example.robert.morseprototype.R;
import com.example.robert.morseprototype.Training.MorseInput.OnEndOfInput;
import com.gc.materialdesign.views.ButtonFlat;
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
public class Introduction extends BaseActivity implements OnEndOfInput {


    private Sound playSound = new Sound();

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        ButterKnife.bind(this);

        progressBarInvisible();
        morseInput = new MorseInput(this, introTextViewMorseChar, introTextViewTextChar, introPad, introProgressBar, tickOne, tickTwo, tickThree);
        morseInput.setOnEndOfInputCallback(this);

        mSteps = TutorialSteps.getIntro();
        mCurrentStep = 0;

        updateCurrentStep();

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

        sequence.setConfig(config);

        sequence.addSequenceItem(introObjective,
                "Each step of the introduction will have an objective", "GOT IT");

        sequence.addSequenceItem(introTextViewMorseChar,
                "When you key in the Morse your Morse code will be displayed here - you must get the objective correct 3 " +
                 "times in order to move onto the next stage", "GOT IT");


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
