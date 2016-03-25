package com.example.robert.morseprototype.SwipeDialogs;

import android.view.GestureDetector;
import android.view.MotionEvent;


//Class took from Stack overflow - i did not write any of the code in this class

//http://stackoverflow.com/questions/4139288/android-how-to-handle-right-to-left-swipe-gestures

public class MorseGestureDetector extends GestureDetector.SimpleOnGestureListener {
    private static final float SWIPE_DISTANCE_THRESHOLD = 100;
    private static final float SWIPE_VELOCITY_THRESHOLD = 100;

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float diffY = e2.getY() - e1.getY();
        float diffX = e2.getX() - e1.getX();
        if (Math.abs(diffX) > Math.abs(diffY)) {
            if (Math.abs(diffX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffX > 0) onSwipeRight();
                else onSwipeLeft();

            }
        } else if (Math.abs(diffY) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
            if (diffY > 0) onSwipeBottom();
            else onSwipeTop();

        }

        return true;
    }

    public void onSwipeRight() {
    }

    public void onSwipeLeft() {
    }

    public void onSwipeTop() {
    }

    public void onSwipeBottom() {
    }
}
