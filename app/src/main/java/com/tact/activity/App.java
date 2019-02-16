package com.tact.activity;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import com.tact.utils.Support;

public class App extends MultiDexApplication
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        // ACRA.init(this);
        Support.con = getApplicationContext();
    }

    @Override
    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}

