package com.example.robert.morseprototype.Training;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.robert.morseprototype.Database.SnappyDB;
import com.example.robert.morseprototype.Misc.Logger;
import com.example.robert.morseprototype.Options.Options;
import com.example.robert.morseprototype.R;
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

        //The first element is used to display either a pass or fail
        if(position == 0){

            view = mInflater.inflate(R.layout.result_calculation_item, parent, false);

            TextView score      = (TextView) view.findViewById(R.id.score);
            TextView percentage = (TextView) view.findViewById(R.id.passFail);


            int i = calculateResult()*10;

            score.setText("" + i + "%");

            //Sets the pass rate for each test
            if(i >=70){

                percentage.setText(context.getResources().getText(R.string.pass));
                percentage.setTextColor(Color.GREEN);

                score.setText("" + i + "%");
                score.setTextColor(Color.GREEN);

                updateProgress(testLetters);

            }else{

                percentage.setText(context.getResources().getText(R.string.fail));
                percentage.setTextColor(Color.RED);

                score.setText("" + i + "%");
                score.setTextColor(Color.RED);
            }

        }else {

            view = mInflater.inflate(R.layout.activity_results_item, parent, false);

            String textToDisplay = getItem(position);

            TextView questionNumber = (TextView) view.findViewById(R.id.questionNumber);
            TextView answer         = (TextView) view.findViewById(R.id.answer);

            questionNumber.setText(context.getResources().getText(R.string.question_number) + " " + position + ": " + question[position]);

            answer.setText(textToDisplay);
        }

            return view;
    }

    
    private int calculateResult() {

        int score = 0;

        for (String result : results) {


            Logger.log("Loop: " + result);

            try {
                if (result.equals("Correct")) {
                    score++;
                }
            } catch (NullPointerException e) {
                Logger.log("Null Pointer!");
            }

        }

        Logger.log("" + score);
        return score;
    }


    //Updates the progress to the DB is test successfully passed
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