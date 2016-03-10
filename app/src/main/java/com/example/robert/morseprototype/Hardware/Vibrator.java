package com.example.robert.morseprototype.Hardware;


import android.content.Context;


public class Vibrator {

    private static android.os.Vibrator vibrate;

    public Vibrator(Context context) {
        vibrate = (android.os.Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void turnOn() {
        vibrate.vibrate(10000);
    }

    public void turnOff() {
        vibrate.cancel();
    }

    public void release() {
        turnOff();
    }
}