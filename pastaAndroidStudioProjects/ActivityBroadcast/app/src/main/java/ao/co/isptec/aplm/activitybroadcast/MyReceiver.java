package ao.co.isptec.aplm.activitybroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String message = "";

        if(intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
            message = "Power is Connected";
        } else if(intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)){
            message = "Power is Disconnected";
        }

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

    }
}