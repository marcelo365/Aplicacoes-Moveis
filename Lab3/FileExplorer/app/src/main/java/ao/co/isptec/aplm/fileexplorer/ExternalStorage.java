package ao.co.isptec.aplm.fileexplorer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.util.ArrayList;

public class ExternalStorage extends AppCompatActivity {

    // wrap some operations that are likely to be needed in more than one place in FileUtil

    private EditText input;
    private TextView output;
    private Button write;
    private Button read;
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_external_storage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        this.input = (EditText) findViewById(R.id.external_storage_input);
        this.output = (TextView) findViewById(R.id.external_storage_output);

        this.write = (Button) findViewById(R.id.external_storage_write_button);
        this.write.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                write();
            }
        });

        this.read = (Button) findViewById(R.id.external_storage_read_button);
        this.read.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                read();
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        checkPermissions();

    }

    private void checkPermissions() {
        int hasWriteExternalStoragePermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int hasReadExternalStoragePermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE);
        ArrayList<String> permissions = new ArrayList<>();

        if (hasWriteExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (hasReadExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (!permissions.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissions.toArray(new String[0]), REQUEST_CODE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE) {

            boolean allPermissionsGranted = true;

            // Verifica se todas as permissões foram concedidas
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allPermissionsGranted = false;
                    break;
                }
            }

            if (allPermissionsGranted) {
                Toast.makeText(this, "Todas as permissões foram concedidas", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permissões não concedidas!", Toast.LENGTH_SHORT).show();
            }

        }

    }

    private void write() {
        if (FileUtil.isExternalStorageWritable()) {
            File dir = FileUtil.getExternalFilesDirAllApiLevels(this.getPackageName());
            Log.d("ExternalStorage", "File directory: " + dir.getAbsolutePath());

//

            File file = new File(dir, "test.txt");
            FileUtil.writeStringAsFile(input.getText().toString(), file);
            Toast.makeText(this, "File written", Toast.LENGTH_SHORT).show();
            input.setText("");
            output.setText("");
        } else {
            Toast.makeText(this, "External storage not writable", Toast.LENGTH_SHORT).show();
        }
    }

    private void read() {
        if (FileUtil.isExternalStorageReadable()) {
            File dir = FileUtil.getExternalFilesDirAllApiLevels(this.getPackageName());

            File file = new File(dir, "test.txt");
            if (file.exists() && file.canRead()) {
                output.setText(FileUtil.readFileAsString(file));
                Toast.makeText(this, "File read", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Unable to read file: " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "External storage not readable", Toast.LENGTH_SHORT).show();
        }
    }


}