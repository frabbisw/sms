package com.example.root.sms;

import android.telephony.SmsMessage;

/**
 * Created by root on 7/16/17.
 */

public interface SmsListener {
    public void messageReceived(SmsMessage smsMessage);
}
