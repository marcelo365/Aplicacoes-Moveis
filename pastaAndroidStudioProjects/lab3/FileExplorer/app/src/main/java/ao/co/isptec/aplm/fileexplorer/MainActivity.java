package ao.co.isptec.aplm.fileexplorer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button internalStorage;
    private Button externalStorage;

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

        this.internalStorage = (Button) findViewById(R.id.main_internal_storage_button);
        this.internalStorage.setOnClickListener(this);

        this.externalStorage = (Button) findViewById(R.id.main_external_storage_button);
        this.externalStorage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(internalStorage)) {
            startActivity(new Intent(this, InternalStorage.class));
        } else if (v.equals(externalStorage)) {
            startActivity(new Intent(this, ExternalStorage.class));
        }
    }
}