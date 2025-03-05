package ao.co.isptec.aplm.thread;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Thread thread;

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

        Button startBtn = findViewById(R.id.startBtn);
        Button stopBtn = findViewById(R.id.stopBtn);


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarContagem();
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (thread != null) {
                    thread.interrupt();
                    thread = null;
                }

            }
        });

    }


    public void iniciarContagem(){

        if (thread != null && thread.isAlive()) {
            Log.d("Thread", "A contagem já está em execução.");
            return; // Sai se a thread já estiver em execução
        }

        Runnable run = new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 10; i++) {

                    if (Thread.currentThread().isInterrupted()) {
                        Log.d("Thread", "Thread foi interrompida");
                        return; // Sai do método run se a thread foi interrompida
                    }

                    Log.d("Thread", "Contador = " + i);

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        Log.d("Thread", "Thread foi interrompida em sleep");
                        return;
                    }
                }

            }
        };

        thread = new Thread(run);
        thread.start();
    }

}
