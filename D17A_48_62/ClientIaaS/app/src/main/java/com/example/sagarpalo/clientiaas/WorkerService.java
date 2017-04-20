package com.example.sagarpalo.clientiaas;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import servercloudhandler.*;
import static android.app.AlarmManager.ELAPSED_REALTIME;
import static android.os.SystemClock.elapsedRealtime;

public class WorkerService extends Service {

    @Override
    public int onStartCommand(final Intent intent, final int flags,
                              final int startId) {
        android.util.Log.d("Echo","Sterted");
        doAction(intent);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    String status = "Initiating as mini cloud...";
    int x[];

    public final class Constants {

        // Defines a custom Intent action
        public static final String BROADCAST_ACTION = "com.example.sagarpalo.clientiaas.BROADCAST";

        // Defines the key for the status "extra" in an Intent
        public static final String EXTENDED_DATA_STATUS = "com.example.sagarpalo.clientiaas.STATUS";
        public static final String EXTENDED_DATA_STATUS2 = "com.example.sagarpalo.clientiaas.STATUS2";

    }

    protected void doAction(Intent intent) {
        android.util.Log.d("Echo","Sterted2");
        if (intent != null) {
            final String action = intent.getAction();
            Thread worker = new Thread(new Runnable(){

                private int[] getCpuUsageStatistic() {

                    String tempString = executeTop();

                    tempString = tempString.replaceAll(",", "");
                    tempString = tempString.replaceAll("User", "");
                    tempString = tempString.replaceAll("System", "");
                    tempString = tempString.replaceAll("IOW", "");
                    tempString = tempString.replaceAll("IRQ", "");
                    tempString = tempString.replaceAll("%", "");
                    for (int i = 0; i < 10; i++) {
                        tempString = tempString.replaceAll("  ", " ");
                    }
                    tempString = tempString.trim();
                    String[] myString = tempString.split(" ");
                    int[] cpuUsageAsInt = new int[myString.length];
                    for (int i = 0; i < myString.length; i++) {
                        myString[i] = myString[i].trim();
                        cpuUsageAsInt[i] = Integer.parseInt(myString[i]);
                    }
                    return cpuUsageAsInt;
                }

                private String executeTop() {
                    java.lang.Process p = null;
                    BufferedReader in = null;
                    String returnString = null;
                    try {
                        p = Runtime.getRuntime().exec("top -n 1");
                        in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                        while (returnString == null || returnString.contentEquals("")) {
                            returnString = in.readLine();
                        }
                    } catch (IOException e) {
                        Log.e("executeTop", "error in getting first line of top");
                        e.printStackTrace();
                    } finally {
                        try {
                            in.close();
                            p.destroy();
                        } catch (IOException e) {
                            Log.e("executeTop",
                                    "error in closing and destroying top process");
                            e.printStackTrace();
                        }
                    }
                    return returnString;
                }

                @Override
                public void run()
                {
                    Socket socket = new Socket();
                            try {
                                String host = Common.IP;
                                int port = 5001;
                                InetAddress address = InetAddress.getByName(host);
                                socket = new Socket(address, port);

                                InputStream is = socket.getInputStream();
                                InputStreamReader isr = new InputStreamReader(is);
                                BufferedReader br = new BufferedReader(isr);
                                String message = br.readLine();
                                System.out.println("Message received from the server : " + message);

                                port = Integer.parseInt(message);
                                socket.close();

                                socket = new Socket(address, port);
                                is = socket.getInputStream();
                                isr = new InputStreamReader(is);
                                br = new BufferedReader(isr);
                                message = br.readLine();
                                System.out.println("Message received from the server : " + message);
                                android.util.Log.i("Message", "Message received from the server : " + message);

                                if (message.equals("sending job")) {
                                    System.out.println("compute he");
                                    status = "Computing Job";
                                }
                                if (message.equals("done")) {
                                    System.out.println("done he");
                                    status = "Job Completed";
                                }

                                ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
                                MatrixOperation mat = (MatrixOperation) inStream.readObject();

                                mat.multiply();

                                x = getCpuUsageStatistic();

                                Intent localIntent = new Intent(Constants.BROADCAST_ACTION);
                                localIntent.putExtra(Constants.EXTENDED_DATA_STATUS, status);
                                localIntent.putExtra(Constants.EXTENDED_DATA_STATUS2,x);
                                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(localIntent);

                                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                                outputStream.writeObject(mat);
                                outputStream.flush();

                            }
                            catch (Exception exception)
                            {
                                x = getCpuUsageStatistic();
                                android.util.Log.i("MessageE", exception.toString());
                                Intent localIntent = new Intent(Constants.BROADCAST_ACTION);
                                localIntent.putExtra(Constants.EXTENDED_DATA_STATUS, status);
                                localIntent.putExtra(Constants.EXTENDED_DATA_STATUS2,x);
                                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(localIntent);
                            }
                            finally {
                                try {
                                    socket.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                }

            });
            worker.start();

        }
    }

    @Override
    public void onTaskRemoved(Intent rootIntent){
        Intent restartServiceIntent = new Intent(getApplicationContext(), this.getClass());

        PendingIntent restartServicePendingIntent = PendingIntent.getService(
                getApplicationContext(), 1, restartServiceIntent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmService = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmService.set(ELAPSED_REALTIME, elapsedRealtime() + 1000,
                restartServicePendingIntent);

        super.onTaskRemoved(rootIntent);
    }



}

