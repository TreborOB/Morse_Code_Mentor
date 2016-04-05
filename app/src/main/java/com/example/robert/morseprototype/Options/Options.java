package com.example.robert.morseprototype.Options;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.example.robert.morseprototype.Misc.MorseApplication;
import com.example.robert.morseprototype.R;
import java.util.Locale;


public class Options extends AppCompatPreferenceActivity {

    private static final String ENABLE_VOICE        = "switch_voice";
    private static final String ENABLE_DIALOGS      = "switch_dialogs";
    private static final String ENABLE_PROGRESS_BAR = "switch_progress_bar";
    private static final String SWITCH_SOS_SPEED    = "switch_sos_speed";
    private static final String MORSE_LEVEL         = "morse_speed";
    private static final String MORSE_LANGUAGE      = "language";

    public static int DOT_LENGTH             = 100;
    public static int DASH_LENGTH            = 500;
    public static int INTER_DOT_LENGTH       = 100;
    public static int INTER_CHARACTER_LENGTH = 200;

    public static int TIMER                  = 10000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Options");
        getFragmentManager().beginTransaction().replace(android.R.id.content, new GeneralPreferenceFragment()).commit();
    }

    public static class InputSpeeds {
        public int TYPE_END_DELAY_MILLIS;
        public int DOT_MAX_PRESS_LENGTH;
        public int DASH_MAX_PRESS_LENGTH;
        public int NEW_WORD;
        public int INPUT_END;
        public int PROGRESS_BAR;
        public int PROGRESS_BAR_INCREASE;



        public InputSpeeds(int DASH_MAX_PRESS_LENGTH, int DOT_MAX_PRESS_LENGTH, int INPUT_END, int NEW_WORD, int TYPE_END_DELAY_MILLIS, int PROGRESS_BAR, int PROGRESS_BAR_INCREASE) {

            this.DASH_MAX_PRESS_LENGTH = DASH_MAX_PRESS_LENGTH;
            this.DOT_MAX_PRESS_LENGTH  = DOT_MAX_PRESS_LENGTH;
            this.INPUT_END             = INPUT_END;
            this.NEW_WORD              = NEW_WORD;
            this.TYPE_END_DELAY_MILLIS = TYPE_END_DELAY_MILLIS;
            this.PROGRESS_BAR          = PROGRESS_BAR;
            this.PROGRESS_BAR_INCREASE = PROGRESS_BAR_INCREASE;

        }
    }

    public static InputSpeeds getInputSpeeds(Context context) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String typeSpeed = sharedPreferences.getString(MORSE_LEVEL, MORSE_LEVEL);

        switch (typeSpeed) {
            case "Standard":
                TIMER = 12000;
                return new InputSpeeds(700, 200, 800, 1400, 800, 20, 10);
            case "Expert":
                TIMER = 9000;
                return new InputSpeeds(350, 100, 500, 700, 450, 10, 20);
            default: //"Beginner"
                TIMER = 15000;
                return new InputSpeeds(1050, 300, 1200, 2100, 1250, 30, 8);
        }

    }


    public static class GeneralPreferenceFragment extends PreferenceFragment {

        ListPreference listPreferenceSpeed;
        ListPreference listPreferenceLanguage;


        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_general);


            listPreferenceSpeed    = (ListPreference) findPreference("morse_speed");
            listPreferenceLanguage = (ListPreference) findPreference("language");



            listPreferenceSpeed.setSummary(listPreferenceSpeed.getEntry());
            listPreferenceLanguage.setSummary(listPreferenceLanguage.getEntry());

            if (listPreferenceSpeed.getValue() == null) {
                // to ensure we don't get a null value
                // set first value by default
                listPreferenceSpeed.setDefaultValue("Standard");
            }

            if (listPreferenceLanguage.getValue() == null) {
                // to ensure we don't get a null value
                // set first value by default
                listPreferenceLanguage.setDefaultValue(Resources.getSystem().getConfiguration().locale);
            }


            listPreferenceSpeed.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    preference.setSummary(newValue.toString());
                    return true;
                }
            });


            listPreferenceLanguage.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {

                    preference.setSummary(newValue.toString());
                    //setLanguage(newValue.toString());

                    //Recreates the activity
                    MorseApplication app = (MorseApplication)getActivity().getApplication();
                    app.setLocale(getLocale(newValue.toString()));
                    getActivity().recreate();
                    return true;
                }
            });
        }
    }

    public static boolean getEnabledVoice(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(ENABLE_VOICE, false);

    }

    public static boolean getEnabledDialogs(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(ENABLE_DIALOGS, true);
    }

    public static boolean getEnabledProgressBar(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return !sharedPreferences.getBoolean(ENABLE_PROGRESS_BAR, true);
    }


    public static boolean getScreenFlashSpeed(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(SWITCH_SOS_SPEED, false);
    }



    public static String getLanguage(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(MORSE_LANGUAGE, MORSE_LANGUAGE);
    }


   public static String getLevel(Context context){

       SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
       return sharedPreferences.getString(MORSE_LEVEL, MORSE_LEVEL);
   }



    private static Locale getLocale(String lang){
        switch (lang) {
            case "English":
                lang = "en";
                break;
            case "Spanish":
                lang = "es";
                break;
            case "Chinese":
                lang = "zh";
                break;
        }
        return new Locale(lang);
    }




    public static Locale getLocale(Context context){
        return getLocale(getLanguage(context));
    }



    public static void setSpeedSlow() {

        DOT_LENGTH = 200;
        DASH_LENGTH = 1000;
        INTER_DOT_LENGTH = 200;
        INTER_CHARACTER_LENGTH = 400;

    }

    public static void setSpeedFast() {

        DOT_LENGTH = 100;
        DASH_LENGTH = 500;
        INTER_DOT_LENGTH = 100;
        INTER_CHARACTER_LENGTH = 200;

    }




}








