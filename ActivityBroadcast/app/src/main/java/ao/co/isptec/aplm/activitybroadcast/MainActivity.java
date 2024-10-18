package ao.co.isptec.aplm.activitybroadcast;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.Manifest;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver;

    private static final String TAG = "TAG_MainActivity";
    private static final int PHONE_STATUS_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        broadcastReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        registerReceiver(broadcastReceiver, intentFilter);

    }


    @Override
    protected void onStart() {
        super.onStart();
        askPhonePermission();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    private void askPhonePermission(){
        int hasPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if(hasPhonePermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_PHONE_STATE}, PHONE_STATUS_REQUEST_CODE);
        };
        hasPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG);
        if(hasPhonePermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_CALL_LOG}, PHONE_STATUS_REQUEST_CODE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PHONE_STATUS_REQUEST_CODE:

                // Se o pedido está cancelado, os vectores resultantes
                // são vazios

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, "permissão de estado do telefone concedida");
                    Toast.makeText(this, "permissão de estado do telefone concedida", Toast.LENGTH_SHORT).show();

                    // a permissão foi concedida! Faça a
                    // tarefa relacionada a contactos que precisa fazer.

                } else {
                    Log.i(TAG, "permissão de estado do telefone não concedida");
                    Toast.makeText(this, "permissão de estado do telefone não concedida", Toast.LENGTH_SHORT).show();

                    // permissão negada! Desabilite a
                    // funcionalidade que depende dessa permissão.
                }
                break;


            // outras linhas 'case' para verificar outras
            // permissões que esta aplicação pode solicitar
        }
    }


}