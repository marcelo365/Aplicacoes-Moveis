package ao.co.isptec.aplm.fileexplorer;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class InternalStorage extends AppCompatActivity {

    private static final String LINE_SEP = System.getProperty("line.separator");

    private EditText input;
    private TextView output;
    private Button write;
    private Button read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_internal_storage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.input = (EditText) findViewById(R.id.internal_storage_input);
        this.output = (TextView) findViewById(R.id.internal_storage_output);
        this.write = (Button) findViewById(R.id.internal_storage_write_button);

        this.write.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                write();
            }
        });

        this.read = (Button) findViewById(R.id.internal_storage_read_button);
        this.read.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                read();
            }
        });
    }

    private void write() {
        FileOutputStream fos = null;
        try {
            // note that there are many modes you can use
            fos = openFileOutput("test.txt", Context.MODE_PRIVATE);
            fos.write(input.getText().toString().getBytes());
            Toast.makeText(this, "File written", Toast.LENGTH_SHORT).show();
            input.setText("");
            output.setText("");
        } catch (FileNotFoundException e) {
            Log.e(Constants.LOG_TAG, "File not found", e);
        } catch (IOException e) {
            Log.e(Constants.LOG_TAG, "IO problem", e);
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                Log.d("FileExplorer", "Close error.");
            }
        }
    }

    private void read() {
        FileInputStream fis = null;
        Scanner scanner = null;
        StringBuilder sb = new StringBuilder();
        try {
            fis = openFileInput("test.txt");
            // scanner does mean one more object, but it's easier to work with
            scanner = new Scanner(fis);
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine() + LINE_SEP);
            }
            Toast.makeText(this, "File read", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            Log.e(Constants.LOG_TAG, "File not found", e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    Log.d("FileExplorer", "Close error.");
                }
            }
            if (scanner != null) {
                scanner.close();
            }
        }
        output.setText(sb.toString());
    }
}