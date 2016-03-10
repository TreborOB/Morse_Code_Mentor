package com.example.robert.morseprototype.Hardware;


import android.os.Handler;

import com.example.robert.morseprototype.Misc.Logger;


public class ScreenFlash {

    private Handler mImageHandler;

    public ScreenFlash(Handler imageHandler) {
        this.mImageHandler = imageHandler;
    }

    public ScreenFlash() {

    }

    public void turnOn() {
        mImageHandler.sendEmptyMessage(1);
    }

    public void turnOff() {
        mImageHandler.sendEmptyMessage(0);
    }

    public void finished(){
        mImageHandler.sendEmptyMessage(4);
        Logger.log("Finished ScreenFlash");
    }

    public void release() {

    }

    public void setScreenViewHandler(Handler imageHandler) {
        this.mImageHandler = imageHandler;
    }
}