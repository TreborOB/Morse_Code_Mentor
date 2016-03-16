package com.example.robert.morseprototype.Training;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.robert.morseprototype.Database.SnappyDB;
import com.example.robert.morseprototype.Misc.Logger;
import com.example.robert.morseprototype.R;
import com.google.common.base.Strings;


public class ResultsAdapter extends ArrayAdapter <String> {
    private final LayoutInflater mInflater;


    private String results [];
    private String question [];
    private String testLetters;



    public ResultsAdapter(Activity context, String [] array, String testLetters, String [] question) {
        super(context, R.layout.activity_results_item, array);
        mInflater = LayoutInflater.from(context);
        this.results = array;
        this.question = question;
        this.testLetters = testLetters;


        SnappyDB.initSnappy();

    }



    public View getView(int position, View convertView, ViewGroup parent) {

        View view;


        if(position == 0){

            view = mInflater.inflate(R.layout.result_calculation_item, parent, false);

            TextView score      = (TextView) view.findViewById(R.id.score);
            TextView percentage = (TextView) view.findViewById(R.id.passFail);


            int i = calculateResult()*10;


            score.setText("" + i + "%");

            if(i >0){
                percentage.setText("Pass");
                updateProgress(testLetters);

            }else{
                percentage.setText("Fail");
            }


        }else {

            view = mInflater.inflate(R.layout.activity_results_item, parent, false);


            String textToDisplay = getItem(position);

            TextView questionNumber = (TextView) view.findViewById(R.id.questionNumber);
            TextView answer         = (TextView) view.findViewById(R.id.answer);

            if(Strings.isNullOrEmpty(textToDisplay)){
                textToDisplay = "Not answered";
                questionNumber.setText("");

            }else{
                questionNumber.setText("Question Number " + position + ": " + question[position]);
            }
                answer.setText(textToDisplay);


        }

            return view;


    }



    private int calculateResult() {

        int score = 0;

        for (int i = 1; i < results.length-1; i++) {

             if (results[i].equals("Correct")) {
                score++;

            } else {

                if (score > 0) {
                 score--;

                }
            }
        }
        return score;
    }



    private void updateProgress(String lettersProgress){

        SnappyDB.insertElement(lettersProgress);

        Logger.log("ResultsAdapter: " + lettersProgress);


        //Opens up the next stage of the trail
        switch (lettersProgress) {
            case "A-F":
                SnappyDB.insertElement("G-K");
                break;
            case "G-K":
                SnappyDB.insertElement("L-P");
                break;
            case "L-P":
                SnappyDB.insertElement("Q-U");
                break;
            case "Q-U":
                SnappyDB.insertElement("V-Z");
                break;
            case "V-Z":
                SnappyDB.insertElement("Numbers");
                break;
        }



    }



}
