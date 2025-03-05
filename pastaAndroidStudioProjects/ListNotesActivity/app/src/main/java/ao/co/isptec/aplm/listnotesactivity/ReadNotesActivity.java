package ao.co.isptec.aplm.listnotesactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ReadNotesActivity extends AppCompatActivity {

    private TextView titulo;
    private TextView descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_read_notes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        titulo = findViewById(R.id.tituloVisualizar);
        descricao = findViewById(R.id.descricaoVisualizar);

        Intent intent = getIntent();
        Nota nota = (Nota) intent.getSerializableExtra("visualizarNota");

        titulo.setText(nota.getTitulo());
        descricao.setText(nota.getDescricao());

    }

    public void voltarListaNotas(View view) {
        finish();
    }
}