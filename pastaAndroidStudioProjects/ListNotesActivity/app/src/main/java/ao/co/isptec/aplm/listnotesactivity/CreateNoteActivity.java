package ao.co.isptec.aplm.listnotesactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateNoteActivity extends AppCompatActivity {
    private EditText titulo;
    private EditText descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        titulo = findViewById(R.id.inputTitulo);
        descricao = findViewById(R.id.inputDescricao);
    }

    public void voltarListaNotas(View view) {
        finish();
    }
    public void CriarNota(View view) {

        if (titulo.getText().toString().isEmpty()) {
            Toast.makeText(this, "Campo Título está vazio", Toast.LENGTH_SHORT).show();
            return;
        }

        Nota notaCriada = new Nota(titulo.getText().toString() , descricao.getText().toString());
        Toast.makeText(this, "Nota Criada", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra("novaNota" , notaCriada);
        setResult(CreateNoteActivity.RESULT_OK , intent);
        finish();
    }
}