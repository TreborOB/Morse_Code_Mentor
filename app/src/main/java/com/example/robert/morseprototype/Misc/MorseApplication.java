package com.example.robert.morseprototype.Misc;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;
import com.example.robert.morseprototype.Options.Options;


import java.util.Locale;

public class MorseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setLocale(Options.getLocale(this));


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setLocale(Options.getLocale(this));
    }

    public void setLocale(Locale locale) {

        final Resources resources = getResources();
        final Configuration configuration = new Configuration(resources.getConfiguration());
        if (!configuration.locale.equals(locale)) {
            configuration.setLocale(locale);
            resources.updateConfiguration(configuration, null);
        }
    }
}
