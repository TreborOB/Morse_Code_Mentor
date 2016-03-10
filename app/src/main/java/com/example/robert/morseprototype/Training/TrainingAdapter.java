package com.example.robert.morseprototype.Training;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.example.robert.morseprototype.Database.SnappyDB;
import com.example.robert.morseprototype.Hardware.Sound;
import com.example.robert.morseprototype.Misc.Logger;
import com.example.robert.morseprototype.R;
import com.example.robert.morseprototype.SwipeDialogs.MorseSymbols;
import com.gc.materialdesign.views.ButtonFlat;

import java.util.HashMap;
import java.util.List;


public class TrainingAdapter extends ArrayAdapter<MorseSymbols> {
    private final LayoutInflater mInflater;
    private Context context;


    private Sound playSound = new Sound();

    private SnappyDB snappy;

    HashMap<String, Boolean> map;



    public TrainingAdapter(Activity context, List list) {
        super(context, R.layout.training_list_item, list);
        mInflater = LayoutInflater.from(context);
        this.context = context;

        snappy = new SnappyDB(context);
        SnappyDB.initSnappy();


           map = SnappyDB.getSnappy();


        Logger.log("Training Adapter: map.size() = " + map.size());




    }

    public View getView(int position, View convertView, ViewGroup parent) {


        View view;


        MorseSymbols textToDisplay = getItem(position);




        if(!map.containsKey(textToDisplay.getLetter()) && !textToDisplay.getLetter().equals("A-F") && !textToDisplay.getLetter().equals("Introduction") ){
           view = mInflater.inflate(R.layout.training_list_blank, parent, false);


           TextView blackLetters  = (TextView) view.findViewById(R.id.textView14);
           blackLetters.setText(textToDisplay.getLetter());

            view.setBackgroundColor(Color.parseColor("#f2f2f2"));


            view.setEnabled(false);
           view.setOnClickListener(null);


        }else{
            view = mInflater.inflate(R.layout.training_list_item, parent, false);



            final TextView txtTitle = (TextView) view.findViewById(R.id.item);
            TextView extraTxt       = (TextView) view.findViewById(R.id.textView1);
            ButtonFlat test         = (ButtonFlat) view.findViewById(R.id.textView4);

            //Sets the introduction list items test button to invisible as it has no test function
            if(position == 0){
              test.setVisibility(View.INVISIBLE);
            }


            txtTitle.setText(textToDisplay.getLetter());
            extraTxt.setText(textToDisplay.getMorseSymbol());


            test.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {




                    Intent intent = new Intent(context, Test.class);
                    String selectedItem = txtTitle.getText().toString();
                    intent.putExtra("keyName", selectedItem);
                    context.startActivity(intent);

                }
            });
        }




        return view;

    }





}