package com.example.root.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.example.root.sms.SmsListener;

public class SmsReceiver extends BroadcastReceiver {

    //interface
    private static SmsListener smsListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data  = intent.getExtras();

        Object[] pdus = (Object[]) data.get("pdus");

        for(int i=0;i<pdus.length;i++)
        {
            SmsMessage smsMessage;
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                String format = data.getString("format");
                smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i], format);
            }
            else
            {
                smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
            }

            smsListener.messageReceived(smsMessage);
        }

    }

    public static void bindListener(SmsListener listener) {
        smsListener = listener;
    }
}