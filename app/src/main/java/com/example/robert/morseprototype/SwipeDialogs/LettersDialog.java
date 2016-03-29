package com.example.robert.morseprototype.SwipeDialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.example.robert.morseprototype.Hardware.Sound;
import com.example.robert.morseprototype.Options.Options;
import com.example.robert.morseprototype.R;

import java.util.ArrayList;


public class LettersDialog extends Activity{


    public static void showLetters(Activity activity) {
        new CustomListDialog("Morse Letters", activity, SwipeArrays.getLetters(), true);
    }

    public static void showNumbers(Activity activity) {
        new CustomListDialog("Morse Numbers", activity, SwipeArrays.getNumbers(), true);
    }

    public static void showQCodes(Activity activity) {
        new CustomListDialog("Q Codes", activity, SwipeArrays.getQcodes(), false);
    }

    public static void showQCodesSpanish(Activity activity) {
        new CustomListDialog("Códigos Q", activity, SwipeArrays.getQcodesSpanish(), false);
    }

    public static void showQCodesChinese(Activity activity) {
        new CustomListDialog("Q码", activity, SwipeArrays.getQcodesChinese(), false);
    }


    public static void showZCodes(Activity activity) {
        new CustomListDialog("Z Codes", activity, SwipeArrays.getZcodes(), false);
    }

    public static void showZCodesSpanish(Activity activity) {
        new CustomListDialog("Códigos Z", activity, SwipeArrays.getZcodesSpanish(), false);
    }

    public static void showZCodesChinese(Activity activity) {
        new CustomListDialog("ž代码", activity, SwipeArrays.getZcodesChinese(), false);
    }

    private static class CustomListDialog {
        Sound sound = null;

        public CustomListDialog(String title, final Activity activity, final ArrayList<MorseSymbols> morseSymbols, boolean isGrid) {
            final Dialog dialog = new Dialog(activity);
            dialog.setTitle(title);



            AbsListView listView;

            if (isGrid) {
                dialog.setContentView(R.layout.dialog_grid);
                listView = (GridView) dialog.findViewById(R.id.gridView1);
            } else {
                dialog.setContentView(R.layout.dialog_list);
                listView = (ListView) dialog.findViewById(R.id.listView1);
            }

            listView.setAdapter(new DialogAdapter(activity, morseSymbols));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MorseSymbols selectedSymbol = morseSymbols.get(position);

                        if (sound == null)
                            sound = new Sound();
                    sound.playSymbol(activity, selectedSymbol.getAudioResID());

                }
            });




            dialog.setCancelable(true);
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(final DialogInterface arg0) {
                    //if the user opens the dialog but doesn't click on an item then audio.release is not called (otherwise it will crash)
                    if (sound != null) {
                        sound.release();
                    }
                }
            });

            dialog.show();
        }
    }
}

