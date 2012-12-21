package com.ecs.sdcard;

import com.ecs.sdcard.R;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
	private BroadcastReceiver receiver;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        registerSDReceiver();
    }
    
    private void registerSDReceiver() {

        IntentFilter filter = new IntentFilter();
        // burada hangi aksiyonları dinleyeceğimizi belirtiyoruz (sd kart takma ve çıkarma)
        filter.addAction(Intent.ACTION_MEDIA_MOUNTED);
        filter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
        filter.addDataScheme("file"); // bunun eklenmemesi durumunda uygulamamız çalışmaz

        receiver = new BroadcastReceiver() {
            /** Dinlediğimiz aksiyon gerçekleştiğinde çağrılır */
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if(action.equals(Intent.ACTION_MEDIA_MOUNTED))
                    Log.d("registerSDReceiver", "SD Kart takıldı");
                else if(action.equals(Intent.ACTION_MEDIA_UNMOUNTED))
                    Log.d("registerSDReceiver", "SD Kart çıkarıldı");
            }
        };
        
        registerReceiver(receiver, filter);

    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
    
}