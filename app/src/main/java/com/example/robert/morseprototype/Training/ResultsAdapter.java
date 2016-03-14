package com.example.robert.morseprototype.Training;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.robert.morseprototype.Database.SnappyDB;
import com.example.robert.morseprototype.R;
import com.google.common.base.Strings;


public class ResultsAdapter extends ArrayAdapter <String> {
    private final LayoutInflater mInflater;


    private String results [];
    private String question [];
    private String testLetters;
    private SnappyDB snappy;



    public ResultsAdapter(Activity context, String [] array, String testLetters, String [] question) {
        super(context, R.layout.activity_results_item, array);
        mInflater = LayoutInflater.from(context);
        this.results = array;
        this.question = question;
        this.testLetters = testLetters;


        snappy = new SnappyDB(context);
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

        if(snappy.snappyDBCount() == 1){
            SnappyDB.insertElement("G-K");
        }
        else if(snappy.snappyDBCount() == 2)
        {
            SnappyDB.insertElement("L-P");
        }
        else if(snappy.snappyDBCount() == 3)
        {
            SnappyDB.insertElement("Q-U");
        }
        else if(snappy.snappyDBCount() == 4){
            SnappyDB.insertElement("V-Z");
        }
        else{
            SnappyDB.insertElement("Numbers");
        }



    }



}
