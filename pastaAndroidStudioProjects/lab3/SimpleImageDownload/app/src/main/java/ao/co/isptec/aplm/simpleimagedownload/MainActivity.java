package ao.co.isptec.aplm.simpleimagedownload;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements Handler.Callback {

    public static final String KEY_HANDLER_MSG = "status";
    private static final String IMAGE_SOURCE = "https://w7.pngwing.com/pngs/605/905/png-transparent-free-pic-web-design-label-text-thumbnail.png";
    private Button btnDownloadFile;
    private Button btnDownloadFileAsync;
    private TextView statusTextView;
    private ImageView imageView;
    // declare handler

    private Runnable imageDownloader;

    private Handler handler;

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

        btnDownloadFile = (Button) findViewById(R.id.btnDownloadFile);
        btnDownloadFileAsync = (Button) findViewById(R.id.btnDownloadFileAsync);
        imageView = (ImageView) findViewById(R.id.image_view);
        statusTextView = (TextView) findViewById(R.id.status);
        handler = new Handler(this);

        // exercise 1 - step 1
        btnDownloadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(imageDownloader, "Download thread").start();
                //statusTextView.setText(getString(R.string.download_started));
            }
        });

        // exercise 1 - step 1
        imageDownloader = new Runnable() {
            public void run() {
                // exercise 1 - step 1
                downloadImage(IMAGE_SOURCE);
            }
        };

        // exercise 1 - step 2 to implement
        btnDownloadFileAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DownloadTask().execute(IMAGE_SOURCE);
            }
        });

        // initialize handler


    }

    private void downloadImage(String urlStr) {
        try {
            URL imageUrl = new URL(urlStr);
            Bitmap image = BitmapFactory.decodeStream(imageUrl.openStream());
            if (image != null) {
                //Log.i("DL", getString(R.string.download_success))
                sendMessage(getString(R.string.download_success));
                /*runOnUiThread(new Runnable() {                         ou então handler.post
                    @Override
                    public void run() {
                        statusTextView.setText(getString(R.string.download_success));
                    }
                });*/

            } else {
                //Log.i("DL", getString(R.string.download_failed_stream));
                sendMessage(getString(R.string.download_failed_stream));
                /*runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        statusTextView.setText(getString(R.string.download_failed_stream));
                    }
                });*/

            }
        } catch (Exception e) {
            //Log.i("DL", getString(R.string.download_failed));
            sendMessage(getString(R.string.download_failed));
            /*runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    statusTextView.setText(getString(R.string.download_failed));
                }
            });*/

            e.printStackTrace();
        }
    }


    @Override
    public boolean handleMessage(@NonNull Message msg) {
        String text = msg.getData().getString("status");
        TextView statusTextView = (TextView) findViewById(R.id.status);
        statusTextView.setText(text);
        return true;
    }

    private void sendMessage(String what) {
        Bundle bundle = new Bundle();
        bundle.putString("status", what);
        Message message = new Message();
        message.setData(bundle);
        handler.sendMessage(message);
    }

    private class DownloadTask extends AsyncTask<String , String , Bitmap>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {

            try {
                URL imageUrl = new URL(strings[0]);
                Bitmap image = BitmapFactory.decodeStream(imageUrl.openStream());
                if (image != null) {
                    return image;
                } else {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap != null){
                imageView.setImageBitmap(bitmap);
            }

        }
    }

}
