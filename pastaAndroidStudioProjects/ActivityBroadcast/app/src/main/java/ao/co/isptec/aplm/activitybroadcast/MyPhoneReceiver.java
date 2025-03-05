package ao.co.isptec.aplm.activitybroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MyPhoneReceiver extends BroadcastReceiver {

    private static final String TAG = "TAG_MyPhoneReceiver";


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            Log.i(TAG, "State: " + state + ", Number: " + number);
            Toast.makeText(context, "State: " + state + ", Number: " + number , Toast.LENGTH_SHORT).show();
        }
    }
}