package com.gibelatrip;

import android.app.Application;
import android.graphics.Typeface;

import com.digits.sdk.android.Digits;
import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import io.fabric.sdk.android.Fabric;

public class AppController extends Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "RUksmaWdc0yKvRFnl3Vru6kZb";
    private static final String TWITTER_SECRET = "PPeBrKWgCtmyxoTHWEEtRgue8tS8jysBipeLak7I5wTfZc03EI";
    private static AppController singleton;
    private Typeface avenirFont;

    public static AppController getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MapboxAccountManager.start(this, Constants.MAPBOX_ACCESS_TOKEN);
        /*Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(Car.class);
        Parse.initialize(this, "", "");
        */
        //TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        //Fabric.with(this, new TwitterCore(authConfig), new Digits());

    }

    private void extractAvenir() {
        avenirFont = Typeface.createFromAsset(getAssets(), "fonts/Avenir.ttc");
    }

    public Typeface getTypeface() {
        if (avenirFont == null) {
            extractAvenir();
        }
        return avenirFont;
    }
}
