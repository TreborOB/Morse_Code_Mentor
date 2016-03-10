package com.example.robert.morseprototype.SwipeDialogs;




public class MorseSymbols {

    private String letter;
    private String morseSymbol;
    private int    audioResID;


    public MorseSymbols(String letter, String morseSymbol, int audioResID) {
        this.letter = letter;
        this.morseSymbol = morseSymbol;
        this.audioResID = audioResID;
    }

    public String getLetter() {
        return letter;
    }

    public String getMorseSymbol() {
        return morseSymbol;
    }

    public int getAudioResID() {
        return audioResID;
    }



}
