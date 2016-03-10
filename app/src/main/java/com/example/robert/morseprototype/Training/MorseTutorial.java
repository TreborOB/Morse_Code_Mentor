package com.example.robert.morseprototype.Training;

import android.view.View;



public class MorseTutorial {

    private String  objective;
    private String  answer;
    private boolean nextEnabled;
    private boolean prevEnabled;
    private boolean padEnabled;
    private String  nextTitle;
    private boolean tickOneEnabled;
    private boolean tickTwoEnabled;
    private boolean tickThreeEnabled;

    public MorseTutorial(String objective, String answer, boolean padEnabled, boolean nextEnabled, boolean prevEnabled, String nextTitle,
    boolean tickOneEnabled, boolean tickTwoEnabled, boolean tickThreeEnabled){
        this.objective        = objective;
        this.answer           = answer;
        this.nextEnabled      = nextEnabled;
        this.prevEnabled      = prevEnabled;
        this.padEnabled       = padEnabled;
        this.nextTitle        = nextTitle;
        this.tickOneEnabled   = tickOneEnabled;
        this.tickTwoEnabled   = tickTwoEnabled;
        this.tickThreeEnabled = tickThreeEnabled;
    }

    public String getObjective() {
        return objective;
    }


    public String getAnswer() {
        return answer;
    }


    public boolean getNextEnabled(){
        return nextEnabled;
    }

    public int getPadVisibility(){
        if (padEnabled) return View.VISIBLE;
        return View.INVISIBLE;
    }

    public boolean getPrevEnabled(){
        return prevEnabled;
    }

    public String getNextTitle(){
        return nextTitle;
    }

    public int isTickThreeEnabled() {
        if (tickThreeEnabled) return View.VISIBLE;
        return View.INVISIBLE;
    }

    public int isTickTwoEnabled() {
        if (tickTwoEnabled) return View.VISIBLE;
        return View.INVISIBLE;
    }

    public int isTickOneEnabled() {
        if (tickOneEnabled) return View.VISIBLE;
        return View.INVISIBLE;
    }
}
