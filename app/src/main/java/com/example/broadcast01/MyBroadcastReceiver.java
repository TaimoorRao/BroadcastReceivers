package com.example.broadcast01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        /**
         * Static broadcast receiver
         */
//        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction()))
//            Toast.makeText(context,"Boot Completed", Toast.LENGTH_SHORT).show();
//        else if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
//            Toast.makeText(context,"Connectivity Status Change",Toast.LENGTH_SHORT).show();
//        }

        /**
         * Dynamic Broadcast Receiver
         */
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            boolean booleanExtra = intent.getBooleanExtra
                    (ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
            if (!booleanExtra){
                Toast.makeText(context,"Connected",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context,"Disconnected",Toast.LENGTH_SHORT).show();
            }
        }
        else if (Intent.ACTION_TIME_TICK.equals(intent.getAction())){
            Toast.makeText(context,"Time Incremented",Toast.LENGTH_SHORT).show();
        }
    }
}
