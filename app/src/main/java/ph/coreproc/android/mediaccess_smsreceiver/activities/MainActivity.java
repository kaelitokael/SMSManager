package ph.coreproc.android.mediaccess_smsreceiver.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import ph.coreproc.android.mediaccess_smsreceiver.R;
import ph.coreproc.android.mediaccess_smsreceiver.libs.UiUtils;

public class MainActivity extends AppCompatActivity {

    static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";
    private Context mContext;
    private String mSender;
    private String mMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        mSender = getIntent().getStringExtra("sender");
        mMessage = getIntent().getStringExtra("message");

        if(mSender != null && mMessage != null)
            showAlert(mSender, mMessage);

        /*IntentFilter filter = new IntentFilter(ACTION);
        this.registerReceiver(mReceivedSMSReceiver, filter);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showAlert(String from, String message) {

        UiUtils.showAlert(mContext, "SENDER: " + from, "Message:\n\n<b>" + message + "</b>");

    }

    //if you want the activity to be the receiver

    /*private final BroadcastReceiver mReceivedSMSReceiver = new BroadcastReceiver() {
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

                            showAlert(msgFrom, msgBody);
                        }


                    } catch (Exception e) {
                        Log.e("SmsReceiver", "Exception caught: " + e.getMessage());
                        //Log.d("Exception caught", e.getMessage());
                    }
                }

            }
        }
    };*/

}
