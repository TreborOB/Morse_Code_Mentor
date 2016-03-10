package com.example.robert.morseprototype.Training;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.robert.morseprototype.Misc.BaseActivity;
import com.example.robert.morseprototype.Hardware.Sound;
import com.example.robert.morseprototype.Misc.Logger;
import com.example.robert.morseprototype.Options.Options;
import com.example.robert.morseprototype.R;
import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.widgets.Dialog;
import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;
import com.github.siyamed.shapeimageview.CircularImageView;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;



@SuppressWarnings("ResourceType")
public class Test extends BaseActivity implements MorseInput.OnOnEndOfInput {

    private MorseInput morseInput;
    private Sound playSound = new Sound();

    @Bind(R.id.padTest) CircularImageView   testPad;
    @Bind(R.id.testTextChar) TextView       testTextViewTextChar;
    @Bind(R.id.testMorseChar) TextView      testTextViewMorseChar;
    @Bind(R.id.testTextObjective) TextView  testObjective;
    @Bind(R.id.timer) TextView              timer;
    @Bind(R.id.testProgressBar) ProgressBar testProgressBar;
    @Bind(R.id.testStart) ButtonFlat        startTest;
    @Bind(R.id.questionNumber) TextView     questionNumber;


    private ArrayList<MorseTutorial> mSteps;
    private int                      mCurrentStep;
    private CountDownTimer           countDownTimer;
    private boolean                  isTimerRunning;
    private int                      noOfQuestions;
    private String                   data;


    private String scores[];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);



        progressBarInvisible();


        data = getIntent().getExtras().getString("keyName");



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



        morseInput = new MorseInput(this, testTextViewMorseChar, testTextViewTextChar, testPad, testProgressBar, null, null, null);
        morseInput.setOnEndOfInputCallback(this);


        progressBarInvisible();

        updateCurrentStep();

        noOfQuestions = mSteps.size()-1;

        scores = new String [mSteps.size()];
    }


    @SuppressWarnings("WrongConstant")
    private void updateCurrentStep() {
        resetText();


        if(mCurrentStep == mSteps.size()){

            Intent intent = new Intent(this, Results.class);


            Bundle extras = new Bundle();
            extras.putStringArray("scores", scores);
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
                questionNumber.setText("Q " + mCurrentStep + "/" + noOfQuestions);
                countDown();

            }

        }


        progressBarInvisible();
    }


    public void startTest(View view) {

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


    @Override
    public void onInputEnded() {

        String answer = mSteps.get(mCurrentStep).getAnswer();
        String inputSentence = morseInput.getWord();


        if (inputSentence.equals(answer)) {

            scores[mCurrentStep] = "Correct";

            displayToast("Correct!");


            mCurrentStep++;

            cancelCountDown();
            updateCurrentStep();
            playSuccessSound();

        } else {

            scores[mCurrentStep] = "Incorrect";

          displayToast("Wrong Answer");

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

        countDownTimer = new CountDownTimer(9000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("seconds remaining: " + millisUntilFinished / 1000);
                isTimerRunning = true;
            }

            public void onFinish() {
                timer.setText("Time up" );
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
        dialog.show();

        
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


                Intent intent = new Intent(Test.this, Results.class);
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
        }
    }



    private void cancelCountDown(){
        countDownTimer.cancel();
    }



}
