package ao.co.isptec.aplm.servicesimple;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onCreate() {
        Toast.makeText(this, "Service was Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Toast.makeText(this, "Contador = " + intent.getIntExtra("contador" , 900) , Toast.LENGTH_SHORT).show();


        Context context = getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyServicePrefs" , MODE_PRIVATE);
        int currentCount = sharedPreferences.getInt("contador" , 0);
        Toast.makeText(this, "Contador = " + currentCount , Toast.LENGTH_SHORT).show();



        return START_STICKY; // read more on: http://developer.android.com/reference/android/app/Service.html
    }


    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_SHORT).show();
    }
}