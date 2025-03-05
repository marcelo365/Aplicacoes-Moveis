package ao.co.isptec.aplm.servicesimple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //private int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    // Start the service
    public void startService(View view) {
        //contador++;
        Intent intent = new Intent(this , MyService.class);
        //intent.putExtra("contador" , contador);

        //-----------------------------------------------------------------------

        Context context = getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyServicePrefs" , MODE_PRIVATE);

        int currentCount = sharedPreferences.getInt("contador" , 0);
        currentCount++;

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("contador", currentCount);
        editor.apply();

        //----------------------------------------------------------------------------------

        startService(intent);
    }

    // Stop the service
    public void stopService(View view) {
        stopService(new Intent(this, MyService.class));
    }
}