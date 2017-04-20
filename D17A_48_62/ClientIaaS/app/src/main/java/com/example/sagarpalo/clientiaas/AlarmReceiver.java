package com.example.sagarpalo.clientiaas;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Sagar Palo on 2017-03-28.
 */

public class AlarmReceiver extends BroadcastReceiver {
    public static final int REQUEST_CODE = 12345;
    public static final String ACTION = "com.example.sagarpalo.cliantiaas.alarm";

    @Override
    public void onReceive(Context context, Intent intent) {

        context.startService(new Intent(context, WorkerService.class));

    }
}
