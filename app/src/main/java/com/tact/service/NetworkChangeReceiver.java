package com.tact.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tact.utils.Support;

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        if (Support.isNetworkOnline(context)) {
            System.out.println("internet has been connected = " + context);
            startService(context);

        }
    }


    private void startService(Context context) {


        Intent intent = new Intent(context, SyncingService.class);

        context.startService(intent);
    }
}
