package com.example.broadcast01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    /**
     * Registering Broadcast Receiver Dynamically
     * If we register in onCreate Method that unregister in onDestroy Method
     * If we register in onStart Method that unregister in onStop Method
     * or else vise...
     */
    MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
    private TextView mTextView;
    private int counter = 1;

    /**
     * Anonymous Inner Class for broadcast receiver within app...
     */
    private BroadcastReceiver mInnerReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if("com.example.custombroadcast02.ACTION_SEND".equals(intent.getAction())){
                String intentExtra = intent.getStringExtra("com.example.custombroadcast02.EXTRA_DATA");
                mTextView.setText("Inner Broadcast Received"+intentExtra+counter);
                counter++;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView);

        // Dynamically registered
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        intentFilter.addAction(Intent.ACTION_TIME_TICK);
        // We have register the broadcast receiver
        registerReceiver(myBroadcastReceiver,intentFilter);
    }
    public void sendBroadcast(View view){
        Intent intent = new Intent("com.example.custombroadcast02.ACTION_SEND");
        intent.putExtra("com.example.custombroadcast02.EXTRA_DATA", "From Sender App");
        sendBroadcast(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter("com.example.custombroadcast02.ACTION_SEND");
        registerReceiver(mInnerReceiver,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mInnerReceiver);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        // We must unregistered it otherwise an error will occur shows that a MainActivity has Leaked an Intent
        unregisterReceiver(myBroadcastReceiver);
    }
}