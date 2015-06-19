package ph.coreproc.android.mediaccess_smsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import ph.coreproc.android.mediaccess_smsreceiver.activities.MainActivity;

/**
 * Created by Kaelito on 6/19/15.
 */

public class IncomingSms extends BroadcastReceiver {

    static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        //your SMS processing code
        Log.d("SmsReceiver", "Intent recieved: " + intent.getAction());
        if (action.equals(ACTION)) {
            Log.d("SmsReceiver", "SMS received");
            Bundle bundle = intent.getExtras();
            SmsMessage[] msgs = null;
            String msgFrom;
            if (bundle != null) {
                //---retrieve the SMS message received---
                try {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for (int i = 0; i < msgs.length; i++) {
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        msgFrom = msgs[i].getOriginatingAddress();
                        String msgBody = msgs[i].getMessageBody();

                        Log.d("SmsReceiver", "Received message from: " + msgFrom);

                        Intent intent1 = new Intent(context, MainActivity.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent1.putExtra("sender", msgFrom);
                        intent1.putExtra("message", msgBody);
                        context.startActivity(intent1);

                    }


                } catch (Exception e) {
                    Log.e("SmsReceiver", "Exception caught: " + e.getMessage());
                    //Log.d("Exception caught", e.getMessage());
                }
            }

        }
    }
}