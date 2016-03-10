package com.example.robert.morseprototype.Hardware;

import android.content.Context;
import android.os.Handler;

import com.example.robert.morseprototype.Misc.Logger;
import com.example.robert.morseprototype.Options.Options;

public class Output{

    private Context mContext;

    private boolean mHasCamera;
    private boolean threadRunning;

    Light       mLight       = null;
    Vibrator    mVibrator    = null;
    Sound       mSound       = null;
    ScreenFlash mScreenFlash = null;

    boolean useSound;
    boolean useLight;
    boolean useVibrator;
    boolean useScreen;

    boolean mCancel;


    Thread outputThread;

    public Output(Context context, boolean useSound, boolean useLight, boolean useVibrator, boolean useScreen) {

        this.mContext = context;

        this.useSound    = useSound;
        this.useLight    = useLight;
        this.useVibrator = useVibrator;
        this.useScreen   = useScreen;

        initHardware(context);


        mCancel = false;

    }

    private void initHardware(Context context) {
        mHasCamera = Light.hasCamera(context);


        if (!mHasCamera) useSound   = false;

        if (useLight) mLight        = new Light(context);
        if (useVibrator) mVibrator  = new Vibrator(context);
        if (useSound) mSound        = new Sound(context);
        if (useScreen) mScreenFlash = new ScreenFlash();
    }

    public boolean getHasCamera() {
        return mHasCamera;
    }

     public void release() {

        threadRunning = true;
        if (mLight != null) mLight.release();
        if (mSound != null) mSound.release();
        if (mVibrator != null) mVibrator.release();
        if (mScreenFlash != null) mScreenFlash.release();
    }

    public void outputString(final String outputString) {


        cancel();

        mCancel = false;
        outputThread = new Thread(new Runnable() {
            public void run() {

                for (char c : outputString.toCharArray()) {
                    if (mCancel) return;

                    switch (c) {
                        case '.':

                            if(!threadRunning)
                            turnOn();

                            sleep(Options.DOT_LENGTH);

                            if(!threadRunning)
                            turnOff();

                            sleep(Options.INTER_DOT_LENGTH);

                            break;
                        case ' ':
                            sleep(Options.INTER_CHARACTER_LENGTH);
                            break;
                        default:

                            if(!threadRunning)
                            turnOn();

                            sleep(Options.DASH_LENGTH);

                            if(!threadRunning)
                            turnOff();

                            sleep(Options.INTER_DOT_LENGTH);
                    }
                }

                //Checks to see if the screen flash has finished as part of the 'quiz'
                if(mScreenFlash!=null) {
                    finished();
                }
            }


        });

        outputThread.start();
    }


    private void finished(){
        Logger.log("Finished");
        mScreenFlash.finished();
    }


    private void sleep(int pauseLength) {
        try {

            Thread.sleep(pauseLength);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void turnOn() {
        if (useScreen) mScreenFlash.turnOn();
        if (useLight) mLight.turnOn();
        if (useSound) mSound.turnOn();
        if (useVibrator) mVibrator.turnOn();
    }

    public void turnOff() {
        if (useScreen) mScreenFlash.turnOff();
        if (useLight) mLight.turnOff();
        if (useSound) mSound.turnOff();
        if (useVibrator) mVibrator.turnOff();
    }

    public void setSoundEnabled(boolean enabled) {
        useSound = enabled;

        if (enabled) {
            mSound = new Sound(mContext);

        } else {
            if (mSound != null) mSound.release();

        }
    }

    public void setVibratorEnabled(boolean enabled) {
        useVibrator = enabled;

        if (enabled) {
            mVibrator = new Vibrator(mContext);

        } else {
            if (mVibrator != null) mVibrator.release();

        }
    }

    public void setLightEnabled(boolean enabled) {
        if (!mHasCamera) return;
        useLight = enabled;

        if (enabled) {
            mLight = new Light(mContext);

        } else {
            if (mLight != null) mLight.release();

        }
    }

    public void setScreenEnabled(boolean enabled, Handler imageHandler) {
        useScreen = enabled;

        if (enabled) {
            mScreenFlash = new ScreenFlash(imageHandler);

        } else {
            if (mScreenFlash != null) mScreenFlash.release();

        }
    }

    public void setScreenViewHandler(Handler imageHandler) {
        if (useScreen) {
            mScreenFlash.setScreenViewHandler(imageHandler);
        }
    }

    public void cancel() {
        mCancel = true;
        if (outputThread != null && outputThread.isAlive()) outputThread.interrupt();
    }


}
