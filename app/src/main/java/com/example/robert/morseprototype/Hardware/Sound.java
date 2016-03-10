package com.example.robert.morseprototype.Hardware;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import com.example.robert.morseprototype.R;


public class Sound {

    private static final int DEFAULT_SOUND = R.raw.morse;

    private SoundPool soundPool;

    private boolean loaded;

    private int loadedSoundPool;
    private int sound;

    private Context mContext;
    private MediaPlayer mMediaPlayer;

    public Sound(Context context) {
        this.mContext = context;
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        sound = soundPool.load(context, DEFAULT_SOUND, 1);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });
    }

    public Sound() {

    }

    public void turnOn() {
        if (loaded) {
            loadedSoundPool = soundPool.play(sound, 0.5f, 0.5f, 1, 0, 1f);
        }
    }

    public void turnOff() {
        soundPool.stop(loadedSoundPool);
    }

     public void release() {
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public void playSymbol(Context context, int audioResID) {
        mMediaPlayer = MediaPlayer.create(context, audioResID);
        mMediaPlayer.start();
    }
}