package com.example.robert.morseprototype.Training;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.robert.morseprototype.Misc.BaseActivity;
import com.example.robert.morseprototype.Hardware.Sound;
import com.example.robert.morseprototype.Misc.Logger;
import com.example.robert.morseprototype.Options.Options;
import com.example.robert.morseprototype.R;
import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.ProgressBarDeterminate;
import com.gc.materialdesign.widgets.Dialog;
import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;
import com.github.siyamed.shapeimageview.CircularImageView;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;


@SuppressWarnings("ResourceType")
public class Test extends BaseActivity implements MorseInput.OnEndOfInput {

    private MorseInput morseInput;
    private Sound playSound = new Sound();


    @Bind(R.id.padTest) CircularImageView   testPad;
    @Bind(R.id.testTextChar) TextView       testTextViewTextChar;
    @Bind(R.id.testMorseChar) TextView      testTextViewMorseChar;
    @Bind(R.id.testTextObjective) TextView  testObjective;
    @Bind(R.id.timer) TextView              timer;
    @Bind(R.id.testProgressBar)
    ProgressBarDeterminate testProgressBar;
    @Bind(R.id.testStart) ButtonFlat        startTest;
    @Bind(R.id.questionNumber) TextView     questionNumber;


    private ArrayList<MorseTutorial> mSteps;
    private int                      mCurrentStep;
    private CountDownTimer           countDownTimer;
    private boolean                  isTimerRunning;
    private int                      noOfQuestions;
    private String                   data;


    private String scores[];
    private String question[];

    private ShowcaseConfig config = new ShowcaseConfig();

    String language;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        language = Options.getLanguage(Test.this);

        progressBarInvisible();

        data = getIntent().getExtras().getString("keyName");

        String language = Options.getLanguage(Test.this);

        switch (language) {
            case "English":

                assert (data != null ? data : null) != null;
                switch (data) {
                    case "A-F":
                        mSteps = TestSteps.getAtoFSteps();

                        break;
                    case "G-K":
                        mSteps = TestSteps.getGtoKSteps();

                        break;
                    case "L-P":
                        mSteps = TestSteps.getLtoPSteps();

                        break;
                    case "Q-U":
                        mSteps = TestSteps.getQtoUSteps();

                        break;
                    case "V-Z":
                        mSteps = TestSteps.getVtoZSteps();
                        break;
                    case "Numbers":
                        mSteps = TestSteps.numberSteps();

                        break;
                }


                break;
            case "Spanish":

                assert (data != null ? data : null) != null;
                switch (data) {
                    case "A-F":
                        mSteps = TestSteps.getAtoFStepsSpanish();

                        break;
                    case "G-K":
                        mSteps = TestSteps.getGtoKStepsSpanish();

                        break;
                    case "L-P":
                        mSteps = TestSteps.getLtoPStepsSpanish();

                        break;
                    case "Q-U":
                        mSteps = TestSteps.getQtoUStepsSpanish();

                        break;
                    case "V-Z":
                        mSteps = TestSteps.getVtoZStepsSpanish();
                        break;
                    case "Numbers":
                        mSteps = TestSteps.numberStepsSpanish();

                        break;
                }

                break;
            default:

                assert (data != null ? data : null) != null;
                switch (data) {
                    case "A-F":
                        mSteps = TestSteps.getAtoFStepsChinese();

                        break;
                    case "G-K":
                        mSteps = TestSteps.getGtoKStepsChinese();

                        break;
                    case "L-P":
                        mSteps = TestSteps.getLtoPStepsChinese();

                        break;
                    case "Q-U":
                        mSteps = TestSteps.getQtoUStepsChinese();

                        break;
                    case "V-Z":
                        mSteps = TestSteps.getVtoZStepsChinese();
                        break;
                    case "Numbers":
                        mSteps = TestSteps.numberStepsChinese();

                        break;
                }
                break;
        }


        morseInput = new MorseInput(this, testTextViewMorseChar, testTextViewTextChar, testPad, testProgressBar, null, null, null);
        morseInput.setOnEndOfInputCallback(this);


        progressBarInvisible();

        updateCurrentStep();

        noOfQuestions = mSteps.size()-1;

        scores = new String [mSteps.size()];

        question = new String [mSteps.size()];
    }


    @SuppressWarnings("WrongConstant")
    private void updateCurrentStep() {
        resetText();

        if(mCurrentStep == 0){
            testObjective.setTextSize(20.0f);
        }else{
            testObjective.setTextSize(40.0f);
        }


        if(mCurrentStep == mSteps.size()){

            Logger.log("Test: " + scores.length);

            Intent intent = new Intent(this, Results.class);

            Bundle extras = new Bundle();
            extras.putStringArray("scores", scores);
            extras.putStringArray("question", question);
            extras.putString("testLetters", data);
            intent.putExtras(extras);


            //finishes the activity when the results page is opened
            finish();

            startActivity(intent);

        }else {

            MorseTutorial step = mSteps.get(mCurrentStep);


            testObjective.setText(step.getObjective());
            startTest.setEnabled(step.getNextEnabled());
            testProgressBar.setVisibility(step.getPadVisibility());
            testPad.setVisibility(step.getPadVisibility());

            if (mCurrentStep != 0) {
                questionNumber.setText(getResources().getText(R.string.q)+ " " + mCurrentStep + "/" + noOfQuestions);
                countDown();

            }

        }
        progressBarInvisible();
    }

    //Begins the test
    public void startTest(View view) {

        scores[mCurrentStep]="First Element";
        mCurrentStep++;
        updateCurrentStep();
        setNextButtonInvisible();

    }


    private  void progressBarInvisible() {
        if (Options.getEnabledProgressBar(Test.this)) {
            testProgressBar.setVisibility(View.INVISIBLE);
        }
    }


    private  void setNextButtonInvisible() {
        startTest.setVisibility(4);
    }

    //InputEnded checks for a correct otr incorrect answer and progresses the test to the next question
    @Override
    public void onInputEnded() {

        String answer = mSteps.get(mCurrentStep).getAnswer();
        String inputSentence = morseInput.getWord();


        if (inputSentence.equals(answer)) {

            scores[mCurrentStep]   = "Correct";
            question[mCurrentStep] = mSteps.get(mCurrentStep).getAnswer();

            //displayToast("Correct!");


            mCurrentStep++;

            cancelCountDown();
            updateCurrentStep();
            playSuccessSound();

        } else {

            //displayToast("Wrong Answer");

            scores[mCurrentStep] = "Incorrect";
            question[mCurrentStep] = mSteps.get(mCurrentStep).getAnswer();

            mCurrentStep++;
            cancelCountDown();
            updateCurrentStep();
            playFailureSound();
        }

        morseInput.reset();

    }

    private  void resetText() {
        testTextViewTextChar.setText("");
        testTextViewMorseChar.setText("");
    }

    private void displayToast(String textToDisplay){
        SuperToast.create(this, textToDisplay, SuperToast.Duration.VERY_SHORT,
        Style.getStyle(Style.BLUE, SuperToast.Animations.FLYIN)).show();
    }



    private void countDown() {

        countDownTimer = new CountDownTimer(Options.TIMER, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText(getResources().getText(R.string.seconds_remaining) + " " + millisUntilFinished / 1000);
                isTimerRunning = true;
            }

            public void onFinish() {

                timer.setText(getResources().getText(R.string.time_up));
                isTimerRunning = false;
                showDialog();


            }
        }.start();
    }


    private void playSuccessSound() {
        playSound.playSymbol(this, R.raw.success);
    }

    private void playFailureSound() {
        playSound.playSymbol(this, R.raw.failure);
    }




    public void showDialog() {

        final Dialog dialog = new Dialog(this, "You ran out of time", "");

        dialog.addCancelButton("Try Again");

        if(isTimerRunning) {
            dialog.show();
        }


        ButtonFlat results = dialog.getButtonAccept();


        if(mCurrentStep == 1)
        {
            results.setVisibility(4);
        }
        else{
            results.setText("Results");
        }


        dialog.setOnAcceptButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                scores[mCurrentStep] = "Incorrect";

                Intent intent = new Intent(Test.this, Results.class);

                Bundle extras = new Bundle();
                extras.putStringArray("scores", scores);
                extras.putStringArray("question", question);
                extras.putString("testLetters", data);
                intent.putExtras(extras);

                //finishes the activity when the results page is opened
                finish();

                startActivity(intent);

            }
        });

        dialog.setOnCancelButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

                dialog.dismiss();
                recreate();
            }
        });
    }


    public void onPause(){
        super.onPause();

        if(isTimerRunning) {
            countDownTimer.cancel();
            isTimerRunning = false;
        }
    }


    public void onStop(){
        super.onStop();

        if(isTimerRunning) {
            countDownTimer.cancel();
            isTimerRunning = false;
        }
    }


    public void onDestroy(){
        super.onDestroy();

        if(isTimerRunning) {
            countDownTimer.cancel();
            isTimerRunning = false;
        }
    }


    private void cancelCountDown(){
        countDownTimer.cancel();
    }



    private void showCaseMainActivity(){


        config.setDelay(300);

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this);

        sequence.setConfig(config);

        sequence.addSequenceItem(testObjective,
                "The objective will for each question will be displayed in the center of the screen. In order to correctly" +
                "answer the question you must key in the Morse code for the objective", "GOT IT");

        sequence.addSequenceItem(timer,
                "You must answer the question before the timer counts down!", "GOT IT");


        sequence.addSequenceItem(questionNumber,
                "The current question number and total number of questions will be displayed here", "GOT IT");


        sequence.start();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_favorite:



                switch(language) {
                    case "English":
                        if(Options.getEnabledVoice(this))
                        playSound.playSymbol(Test.this, R.raw.help);
                        break;

                    case "Spanish":
                        if(Options.getEnabledVoice(this))
                        playSound.playSymbol(Test.this, R.raw.helpspanish);
                        break;

                    case "Chinese":
                        if(Options.getEnabledVoice(this))
                        playSound.playSymbol(Test.this, R.raw.helpchinese);
                        break;
                }

                showCaseMainActivity();
        }
        return true;

    }


}
