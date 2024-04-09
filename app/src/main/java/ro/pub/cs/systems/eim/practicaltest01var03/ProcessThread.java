package ro.pub.cs.systems.eim.practicaltest01var03;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

public class ProcessThread extends Thread{

    private Context context= null;
    private boolean isRunning = true;
    private int num1;
    private int num2;

    public ProcessThread(Context context, int input1, int input2) {
        this.context = context;
        this.num1 = input1;
        this.num2 = input2;
    }

    @Override
    public void run() {
        while (isRunning) {
            sleep();
            sendMessageSum();
            sleep();
            sendMessageDiff();
            stopThread();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(Constants.SLEEP_TIME);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    private void sendMessageSum() {
        Intent intent = new Intent();
        intent.setAction(Constants.ACTION_STRING);

        int sum = num1 + num2;

        String sumStr = String.valueOf("SUM: " + sum);

        //String broadcast = "Time: " + new Date(System.currentTimeMillis()) + ", sum: " + sum + ", dif: " + dif;
        intent.putExtra(Constants.BROADCAST_RECEIVER_EXTRA, sumStr);
//        Log.d(Constants.SERVICE, broadcast);

        context.sendBroadcast(intent);
    }

    private void sendMessageDiff() {
        Intent intent = new Intent();
        intent.setAction(Constants.ACTION_STRING);

        int dif = num1 - num2;

        String difStr = String.valueOf("DIFF: " + dif);

        //String broadcast = "Time: " + new Date(System.currentTimeMillis()) + ", sum: " + sum + ", dif: " + dif;
        intent.putExtra(Constants.BROADCAST_RECEIVER_EXTRA, difStr);
//        Log.d(Constants.SERVICE, broadcast);

        context.sendBroadcast(intent);
    }

    public void stopThread() {
        isRunning = false;
    }
}
