package com.example.robert.morseprototype.Misc;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.robert.morseprototype.Options.Options;
import com.example.robert.morseprototype.R;

import java.util.Locale;

public class BaseActivity extends AppCompatActivity {

    private Locale mCurrentLocale;

    @Override
    protected void onStart() {
        super.onStart();

        mCurrentLocale = getResources().getConfiguration().locale;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }



    @Override
    protected void onRestart() {
        super.onRestart();
        Locale locale = Options.getLocale(this);

        if (!locale.equals(mCurrentLocale)) {

            mCurrentLocale = locale;
            recreate();
        }
    }

}
