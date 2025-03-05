package ao.co.isptec.aplm.producerconsumerwithlooper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Handler handler;

    private class Consumer extends Thread {

        @Override
        public void run() {

            Looper.prepare();

            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    int number = msg.what;
                    if (number % 2 == 0) {
                        Log.d("Consumer", number + " is divisible by 2");
                    } else {
                        Log.d("Consumer", number + " is not divisible by 2");
                    }
                }
            };

            Looper.loop();
        }
    }

    private class Producer extends Thread {

        public Producer(String name) {
            super(name);
        }

        @Override
        public void run() {

            Random random = new Random();
            while (true) {
                int number = random.nextInt(100);
                Log.d("Producer " + getName(), Integer.toString(number));
                handler.sendEmptyMessage(number);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }

        }
    }


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

        // launch the consumer
        new Consumer().start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        // launch the producers
        new Producer("A").start();
        new Producer("B").start();
    }
}