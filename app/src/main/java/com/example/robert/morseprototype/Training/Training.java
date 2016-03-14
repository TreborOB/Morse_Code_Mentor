package com.example.robert.morseprototype.Training;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.robert.morseprototype.Hardware.Sound;
import com.example.robert.morseprototype.Misc.Logger;
import com.example.robert.morseprototype.Options.Options;
import com.example.robert.morseprototype.R;
import com.example.robert.morseprototype.SwipeDialogs.MorseSymbols;
import java.util.ArrayList;

import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;


public class Training extends AppCompatActivity {


    private ListView listView;

    private static String[] items = {"Introduction", "A-F", "G-K", "L-P", "Q-U", "V-Z", "Numbers"};

    private Sound playSound = new Sound();

    private TrainingAdapter adapter;

    private ShowcaseConfig config = new ShowcaseConfig();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_list);



        final ArrayList<MorseSymbols> trail = new ArrayList<>();
        MorseSymbols m1 = new MorseSymbols("Introduction", "Morse Introduction", R.raw.morseintroduction);
        MorseSymbols m2 = new MorseSymbols("A-F", "Letters A-F", R.raw.lettersatof);
        MorseSymbols m3 = new MorseSymbols("G-K", "Letters G-K", R.raw.lettersgtok);
        MorseSymbols m4 = new MorseSymbols("L-P", "Letters L-P", R.raw.lettersltop);
        MorseSymbols m5 = new MorseSymbols("Q-U", "Letters Q-U", R.raw.lettersqtou);
        MorseSymbols m6 = new MorseSymbols("V-Z", "Letters V-Z", R.raw.lettersvtoz);
        MorseSymbols m7 = new MorseSymbols("Numbers", "Numbers 1-9", R.raw.numbers);


        trail.add(m1);
        trail.add(m2);
        trail.add(m3);
        trail.add(m4);
        trail.add(m5);
        trail.add(m6);
        trail.add(m7);




        adapter = new TrainingAdapter(this, trail);
        listView = (ListView) findViewById(R.id.list);




        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                String selectedItem = items[position];


                if (Options.getEnabledVoice(Training.this))
                    playSound.playSymbol(Training.this, trail.get(position).getAudioResID());

                if (selectedItem.equals("Introduction")) {
                    Intent intent = new Intent(Training.this, Introduction.class);
                    intent.putExtra("keyName", selectedItem);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(Training.this, TrainingTrail.class);
                    intent.putExtra("keyName", selectedItem);
                    startActivity(intent);
                }

            }

        });

    }





    public void onResume(){
        super.onResume();

        Logger.log("Training: Recreate()");

    }
}