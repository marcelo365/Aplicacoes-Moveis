package com.example.simpleactivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        Log.i("MainActivity", "onCreate");
        // Código a ser executado quando a Activity é criada
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity", "onStart");
        // Código a ser executado quando a Activity se torna visível
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity", "onResume");
        // Código a ser executado quando a Activity começa a interagir com o usuário
        Toast.makeText(this , "onResume" , Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity", "onPause");
        // Código a ser executado quando a Activity perde o foco
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity", "onStop");
        // Código a ser executado quando a Activity não está mais visível
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity", "onDestroy");
        // Código a ser executado quando a Activity é destruída
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MainActivity", "onRestart");
        //chamado quando uma atividade que estava parada (no estado de onStop()) está prestes a se tornar visível novamente para o usuário
    }

    public void finishButtonPressed(View view) {
        finish();
    }




}