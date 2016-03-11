package com.example.robert.morseprototype.Training;


import android.os.Bundle;
import android.widget.ListView;
import com.example.robert.morseprototype.Misc.BaseActivity;
import com.example.robert.morseprototype.R;

public class Results extends BaseActivity {


    private ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_list);


        Bundle extras = getIntent().getExtras();
        String[] scoresArray = extras.getStringArray("scores");
        String[] questionArray = extras.getStringArray("question");
        String testLetters   = extras.getString("testLetters");



        ResultsAdapter adapter = new ResultsAdapter(this, scoresArray, testLetters, questionArray);
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);


    }




}

