package com.example.robert.morseprototype.Training;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.robert.morseprototype.Misc.BaseActivity;
import com.example.robert.morseprototype.Hardware.Sound;
import com.example.robert.morseprototype.Misc.Logger;
import com.example.robert.morseprototype.Options.Options;
import com.gc.materialdesign.views.ButtonFlat;
import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.example.robert.morseprototype.R;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


@SuppressWarnings("ResourceType")
public class TrainingTrail extends BaseActivity implements MorseInput.OnEndOfInput {

    private Sound playSound = new Sound();

    private MorseInput morseInput;



    @Bind(R.id.padTraining) CircularImageView           trainingPad;
    @Bind(R.id.trainingTextChar) TextView               trainingTextViewTextChar;
    @Bind(R.id.trainingMorseChar) TextView              trainingTextViewMorseChar;
    @Bind(R.id.trainingTextObjective) JustifiedTextView trainingObjective;
    @Bind(R.id.tickOneTraining) ImageView               tickOne;
    @Bind(R.id.tickTwoTraining) ImageView               tickTwo;
    @Bind(R.id.tickThreeTraining) ImageView             tickThree;
    @Bind(R.id.trainingButtonNext)ButtonFlat            trainingNext;
    @Bind(R.id.trainingButtonPrev) ButtonFlat           trainingPrevious;
    @Bind(R.id.trainingProgressBar) ProgressBar         trainingProgressBar;



    private ArrayList<MorseTutorial> mSteps;
    private int                      mCurrentStep;
    private int                      tickProgress;
    private String                   data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_trail);
        ButterKnife.bind(this);

        data = getIntent().getExtras().getString("keyName");


        assert data != null;
        switch (data) {
            case "Introduction":
                Logger.log("Introduction");

                break;
            case "A-F":
                mSteps = TutorialSteps.getListAtoF();

                break;
            case "G-K":
                mSteps = TutorialSteps.getListGtoK();

                break;
            case "L-P":
                mSteps = TutorialSteps.getListLtoP();

                break;
            case "Q-U":
                mSteps = TutorialSteps.getListQtoU();

                break;
            case "V-Z":
                mSteps = TutorialSteps.getListVtoZ();

                break;
            case "Numbers":
                mSteps = TutorialSteps.getListNumbers();

                break;
        }


        morseInput = new MorseInput(this, trainingTextViewMorseChar, trainingTextViewTextChar, trainingPad, trainingProgressBar, tickOne, tickTwo, tickThree);
        morseInput.setOnEndOfInputCallback(this);

        mCurrentStep = 0;

        updateCurrentStep();


    }



    @SuppressWarnings("WrongConstant")
    private void updateCurrentStep() {
        resetText();

        MorseTutorial step = mSteps.get(mCurrentStep);

        trainingObjective.setText(step.getObjective());
        trainingPrevious.setEnabled(step.getPrevEnabled());
        trainingNext.setEnabled(step.getNextEnabled());
        trainingNext.setText(step.getNextTitle());
        trainingProgressBar.setVisibility(step.getPadVisibility());
        trainingPad.setVisibility(step.getPadVisibility());
        tickOne.setVisibility(step.isTickOneEnabled());
        tickTwo.setVisibility(step.isTickTwoEnabled());
        tickThree.setVisibility(step.isTickThreeEnabled());


        progressBarInvisible();
    }



    public void onNextClicked(View view) {


        if (mCurrentStep >= mSteps.size() - 1) {

           Intent intent = new Intent(this, FinishedTrailSection.class);
           intent.putExtra("trailSection", data);

            //finishes the activity when the results page is opened
            finish();

            startActivity(intent);

        }else {

            mCurrentStep++;
            updateCurrentStep();
            resetTicks();
            setNextButtonInvisible();
        }
    }


    public void onPreviousClicked(View view) {
        if (mCurrentStep <= 0) return;

        mCurrentStep--;
        updateCurrentStep();
        setNextButtonVisible();
    }

    private void resetText() {
        trainingTextViewTextChar.setText("");
        trainingTextViewMorseChar.setText("");
    }



    @Override
    public void onInputEnded() {
        String answer = mSteps.get(mCurrentStep).getAnswer();
        String inputSentence = morseInput.getWord();

        if (inputSentence.equals(answer)) {

            displayToast("Correct Answer");

             tickProgress++;
             updateTicks();
             playSuccessSound();

         } else {

            displayToast("Wrong Answer");

            playFailureSound();
        }

        morseInput.reset();
    }



    private void updateTicks() {

        if(tickProgress == 0){
            tickOne.setBackgroundResource(R.drawable.blank_tick);
            tickTwo.setBackgroundResource(R.drawable.blank_tick);
            tickThree.setBackgroundResource(R.drawable.blank_tick);
        }
        else if(tickProgress == 1){
            tickOne.setBackgroundResource(R.drawable.completed_tick);
        }else if(tickProgress == 2){
            tickTwo.setBackgroundResource(R.drawable.completed_tick);
        }else if(tickProgress == 3){
            tickThree.setBackgroundResource(R.drawable.completed_tick);
            setNextButtonVisible();
        }
    }



    private void displayToast(String textToDisplay){
        SuperToast.create(this, textToDisplay, SuperToast.Duration.VERY_SHORT,
        Style.getStyle(Style.BLUE, SuperToast.Animations.FLYIN)).show();
    }

    private void resetTicks() {
        tickProgress = 0;
        updateTicks();
    }



    private void progressBarInvisible() {
        if(Options.getEnabledProgressBar(TrainingTrail.this)){
            trainingProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void setNextButtonInvisible(){
        trainingNext.setVisibility(4);
    }

    private void setNextButtonVisible() {
        trainingNext.setVisibility(1);
    }

    private void playSuccessSound(){
        playSound.playSymbol(this, R.raw.success);
    }

    private void playFailureSound() {
        playSound.playSymbol(this, R.raw.failure);
    }

}
