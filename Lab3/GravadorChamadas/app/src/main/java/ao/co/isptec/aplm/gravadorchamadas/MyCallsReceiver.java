package ao.co.isptec.aplm.gravadorchamadas;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyCallsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction() == TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

            if ((state == null) || (number == null)) {
                Toast.makeText(context, "State: " + state + ", Number: " + number, Toast.LENGTH_SHORT).show();
                return;
            }

            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                saveCallDetails(context, "Recebida de: " + number + "\n\n");
            } else if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                saveCallDetails(context, "Atendida de: " + number + "\n\n");
            }

            Toast.makeText(context, "State: " + state + ", Number: " + number, Toast.LENGTH_SHORT).show();
        }
    }


    private void saveCallDetails(Context context, String logDetails) {
        try {
            // Usa o armazenamento interno privado do aplicativo
            FileOutputStream fos = context.openFileOutput("call_logs.txt", Context.MODE_APPEND);
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            fos.write((timeStamp + " - " + logDetails).getBytes());
            fos.close();

            //Toast.makeText(context, "Detalhes da chamada salvos", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Log.e("Erro", "Erro ao salvar detalhes da chamada: " + e.getMessage());
        }
    }



}