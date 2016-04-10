package com.example.robert.morseprototype.Training;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.robert.morseprototype.Database.SnappyDB;
import com.example.robert.morseprototype.Options.Options;
import com.example.robert.morseprototype.R;
import com.google.common.base.Strings;
import com.mixpanel.android.mpmetrics.MixpanelAPI;

import org.json.JSONException;
import org.json.JSONObject;


public class ResultsAdapter extends ArrayAdapter <String> {
    private final LayoutInflater mInflater;


    private String results [];
    private String question [];
    private String testLetters;
    private Context context;

    String projectToken;
    MixpanelAPI mixPanelResultsAdapter;


    public ResultsAdapter(Activity context, String [] array, String testLetters, String [] question) {
        super(context, R.layout.activity_results_item, array);
        mInflater        = LayoutInflater.from(context);
        this.results     = array;
        this.question    = question;
        this.testLetters = testLetters;
        this.context     = context;


        projectToken = "a030a77c9710298b9cee4e20d7c2b814";
        mixPanelResultsAdapter = MixpanelAPI.getInstance(context, projectToken);

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

            if(i >=0){
                percentage.setText(context.getResources().getText(R.string.pass));
                updateProgress(testLetters);

            }else{
                percentage.setText(context.getResources().getText(R.string.fail));
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
                questionNumber.setText(context.getResources().getText(R.string.question_number) + " " + position + ": " + question[position]);
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


        if (Options.getDataAnalytics(context)) {
            try {
                JSONObject itemSelected = new JSONObject();
                itemSelected.put("Stage Completed", testLetters);
                mixPanelResultsAdapter.track("Results", itemSelected);

            } catch (JSONException e) {

                Log.e("App started error", "ERROR", e);
            }

        }


        SnappyDB.insertElement(lettersProgress);

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
                SnappyDB.insertElement("Números");
                SnappyDB.insertElement("数字");
                break;
        }

    }
}