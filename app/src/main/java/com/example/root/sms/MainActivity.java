package com.example.root.sms;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    final private int MY_PERMISSIONS_REQUEST_SMS_SEND=100;
    final private int MY_PERMISSIONS_REQUEST_SMS_RECEIVE=101;
    
    public static final String OTP_REGEX = "[0-9]{1,6}";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permission();

        SmsReceiver.bindListener(new SmsListener() {
            @Override
            public void messageReceived(SmsMessage smsMessage)
            {
                Log.e("Name: ", smsMessage.getDisplayOriginatingAddress());
                Log.e("Message: ", smsMessage.getMessageBody());
            }
        });
    }


    public void send(View view)
    {
        String phoneNumber = "8801786165031";
        String smsBody = "Hello dear!";

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, smsBody, null, null);
    }
    public void permission()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
            {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS))
                {

                }
                else
                {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},
                            MY_PERMISSIONS_REQUEST_SMS_SEND);
                }
            }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SMS_SEND: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                    Log.e("SMS","granted");

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Log.e("SMS","denied");
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}

