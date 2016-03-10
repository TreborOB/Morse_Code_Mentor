package com.example.robert.morseprototype.Misc;

import android.util.Log;

public class Logger {
    private static final String DEBUG_TAG = "Morse debug";

    public static void log(String message) {
        Log.d(Logger.DEBUG_TAG, message);
    }
}
