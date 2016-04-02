package com.example.robert.morseprototype.Training;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.robert.morseprototype.Misc.BaseActivity;
import com.example.robert.morseprototype.Hardware.Sound;
import com.example.robert.morseprototype.Misc.Logger;
import com.example.robert.morseprototype.Misc.ShowCaseViewArrays;
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
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;


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

    private ShowcaseConfig config = new ShowcaseConfig();

    private ArrayList<MorseTutorial> mSteps;
    private int                      mCurrentStep;
    private int                      tickProgress;
    private String                   data;

    private String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_trail);
        ButterKnife.bind(this);

        data = getIntent().getExtras().getString("keyName");


        //Gets the current language
        language = Options.getLanguage(TrainingTrail.this);


        Logger.log(language);


        switch (language) {
            case "English":
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


                break;
            case "Spanish":
                switch (data) {
                    case "Introduction":
                        Logger.log("Introduction");

                        break;
                    case "A-F":
                        mSteps = TutorialSteps.getListAtoFSpanish();

                        break;
                    case "G-K":
                        mSteps = TutorialSteps.getListGtoKSpanish();

                        break;
                    case "L-P":
                        mSteps = TutorialSteps.getListLtoPSpanish();

                        break;
                    case "Q-U":
                        mSteps = TutorialSteps.getListQtoUSpanish();

                        break;
                    case "V-Z":
                        mSteps = TutorialSteps.getListVtoZSpanish();

                        break;
                    case "Numbers":
                        mSteps = TutorialSteps.getListNumbersSpanish();

                        break;
                }

                //Chinese
                break;
            default:
                switch (data) {
                    case "Introduction":
                        Logger.log("Introduction");

                        break;
                    case "A-F":
                        mSteps = TutorialSteps.getListAtoFChinese();

                        break;
                    case "G-K":
                        mSteps = TutorialSteps.getListGtoKChinese();

                        break;
                    case "L-P":
                        mSteps = TutorialSteps.getListLtoPChinese();

                        break;
                    case "Q-U":
                        mSteps = TutorialSteps.getListQtoUChinese();

                        break;
                    case "V-Z":
                        mSteps = TutorialSteps.getListVtoZChinese();

                        break;
                    case "Numbers":
                        mSteps = TutorialSteps.getListNumbersChinese();

                        break;
                }


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
        resetText();
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



    private void showCaseMainActivity(){


        config.setDelay(300);

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this);

        String array[];

        switch (language) {
            case "English":
                array = ShowCaseViewArrays.trainingEnglish();
                break;
            case "Spanish":
                array = ShowCaseViewArrays.trainingSpanish();
                break;
            default:
                array = ShowCaseViewArrays.trainingChinese();
                break;
        }



        sequence.setConfig(config);

        sequence.addSequenceItem(trainingObjective,
                array[0], array[2]);

        sequence.addSequenceItem(tickOne,
                array[1], array[2]);


        sequence.start();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_favorite:

                switch(language) {
                    case "English":
                        if(Options.getEnabledVoice(this))
                        playSound.playSymbol(TrainingTrail.this, R.raw.help);
                        break;

                    case "Spanish":
                        if(Options.getEnabledVoice(this))
                        playSound.playSymbol(TrainingTrail.this, R.raw.helpspanish);
                        break;

                    default:
                        if(Options.getEnabledVoice(this))
                        playSound.playSymbol(TrainingTrail.this, R.raw.helpchinese);
                        break;
                }

                showCaseMainActivity();
        }
        return true;

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
