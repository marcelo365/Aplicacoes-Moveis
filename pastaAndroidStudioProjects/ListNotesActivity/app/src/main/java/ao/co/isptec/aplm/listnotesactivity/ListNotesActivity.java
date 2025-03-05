package ao.co.isptec.aplm.listnotesactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListNotesActivity extends AppCompatActivity {


    private ActivityResultLauncher<Intent> launcher;
    private ArrayList<Nota> listaNotas;
    private ListView listaNotasView;

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

        listaNotas = new ArrayList<>();
        listaNotasView = findViewById(R.id.listaNotas);
        ArrayAdapter<Nota> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaNotas);
        listaNotasView.setAdapter(adapter);


        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                        if (result.getResultCode() == ListNotesActivity.RESULT_OK) {
                            Intent data = result.getData();

                            if (data != null) {
                                Nota notaCriada = (Nota) data.getSerializableExtra("novaNota");
                                listaNotas.add(notaCriada);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                }
        );

        listaNotasView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListNotesActivity.this, ReadNotesActivity.class);
                intent.putExtra("visualizarNota", listaNotas.get(position));
                startActivity(intent);
            }
        });

    }


    public void irParaNovaNota(View view) {
        Intent intent = new Intent(this, CreateNoteActivity.class);
        launcher.launch(intent);
    }


}