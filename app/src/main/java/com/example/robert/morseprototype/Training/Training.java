package com.example.robert.morseprototype.Training;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.robert.morseprototype.Database.SnappyDB;
import com.example.robert.morseprototype.Hardware.Sound;
import com.example.robert.morseprototype.Options.Options;
import com.example.robert.morseprototype.R;
import com.example.robert.morseprototype.SwipeDialogs.MorseSymbols;
import java.util.ArrayList;



public class Training extends AppCompatActivity {


    private static String[] items = {"Introduction", "A-F", "G-K", "L-P", "Q-U", "V-Z", "Numbers"};

    private Sound playSound = new Sound();

    private TrainingAdapter adapter;

    final ArrayList<MorseSymbols> trail = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_list);


        String language = Options.getLanguage(Training.this);


        switch (language) {
            case "English": {
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

                break;
            }
            case "Spanish": {

                MorseSymbols m1 = new MorseSymbols("Introducción", "Morse Introducción", R.raw.morseintroductionspanish);
                MorseSymbols m2 = new MorseSymbols("A-F", "Letras A-F", R.raw.lettersatofspanish);
                MorseSymbols m3 = new MorseSymbols("G-K", "Letras G-K", R.raw.lettersgtokspanish);
                MorseSymbols m4 = new MorseSymbols("L-P", "Letras L-P", R.raw.lettersltopspanish);
                MorseSymbols m5 = new MorseSymbols("Q-U", "Letras Q-U", R.raw.lettersqtouspanish);
                MorseSymbols m6 = new MorseSymbols("V-Z", "Letras V-K", R.raw.lettersvtozspanish);
                MorseSymbols m7 = new MorseSymbols("Números", "Números de Morse", R.raw.numbersspanish);

                trail.add(m1);
                trail.add(m2);
                trail.add(m3);
                trail.add(m4);
                trail.add(m5);
                trail.add(m6);
                trail.add(m7);

                break;
            }
            default: {

                MorseSymbols m1 = new MorseSymbols("介绍", "莫尔斯介绍", R.raw.morseintroductionchinese);
                MorseSymbols m2 = new MorseSymbols("A-F", "字母A-F", R.raw.lettersatofchinese);
                MorseSymbols m3 = new MorseSymbols("G-K", "字母g的-K", R.raw.lettersgtokchinese);
                MorseSymbols m4 = new MorseSymbols("L-P", "字母L-P", R.raw.lettersltopchinese);
                MorseSymbols m5 = new MorseSymbols("Q-U", "字母Q-ù", R.raw.lettersqtouchinese);
                MorseSymbols m6 = new MorseSymbols("V-Z", "字母V-ž", R.raw.lettersvtozchinese);
                MorseSymbols m7 = new MorseSymbols("数字", "莫尔斯号", R.raw.numberschinese);

                trail.add(m1);
                trail.add(m2);
                trail.add(m3);
                trail.add(m4);
                trail.add(m5);
                trail.add(m6);
                trail.add(m7);


                break;
            }
        }

        adapter = new TrainingAdapter(this, trail);
        ListView listView = (ListView) findViewById(R.id.list);

        assert listView != null;
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String selectedItem = items[position];

                if(Options.getEnabledVoice(Training.this))
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


    public void onPause(){
        super.onPause();
        SnappyDB.closeSnappy();
    }

    public void onResume(){
        super.onResume();

        adapter = new TrainingAdapter(this, trail);
        ListView listView = (ListView) findViewById(R.id.list);

        assert listView != null;
        listView.setAdapter(adapter);

    }
}