package ao.co.isptec.aplm.appserver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ao.co.isptec.aplm.appserver.model.Utilizador;
import ao.co.isptec.aplm.appserver.retrofit.RetrofitService;
import ao.co.isptec.aplm.appserver.retrofit.UtilizadorApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private EditText senha;
    private EditText userName;
    private RetrofitService retrofitService;
    private UtilizadorApi utilizadorApi;

    private ListView listaUtilizadoresView;
    private ArrayList<String> listaUtilizadores;

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

        senha = findViewById(R.id.senhaLogin);
        userName = findViewById(R.id.usernameLogin);
        retrofitService = new RetrofitService();
        utilizadorApi = retrofitService.getRetrofit().create(UtilizadorApi.class);
        listaUtilizadores = new ArrayList<>();
        listaUtilizadoresView = findViewById(R.id.listUtilizadoresView);

    }


    public void fazerLogin(View view) {
        String userNameLogin = userName.getText().toString();
        String senhaLogin = senha.getText().toString();

        if (userNameLogin.isEmpty()) {
            Toast.makeText(this, "Campo UserName vazio", Toast.LENGTH_SHORT).show();
            return;
        }

        if (senhaLogin.isEmpty()) {
            Toast.makeText(this, "Campo senha vazio", Toast.LENGTH_SHORT).show();
            return;
        }

        utilizadorApi.getUtilizadorByUsernameAndSenha(userNameLogin, senhaLogin).enqueue(new Callback<Utilizador>() {
            @Override
            public void onResponse(Call<Utilizador> call, Response<Utilizador> response) {


                if (response.isSuccessful() && (response.body() != null)) {
                    Toast.makeText(Login.this, "Usuário Encontrado " + response.body().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Login.this, "Usuário Não Encontrado ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Utilizador> call, Throwable throwable) {
                Toast.makeText(Login.this, "Erro de rede verificar usuario", Toast.LENGTH_SHORT).show();
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, "Error ocurred", throwable);
            }
        });
    }

    public void listarUtilizadores(View view) {

        utilizadorApi.getAllUtilizadores().enqueue(new Callback<List<Utilizador>>() {
            @Override
            public void onResponse(Call<List<Utilizador>> call, Response<List<Utilizador>> response) {

                if (response.isSuccessful() && response.body() != null) {
                    listaUtilizadores.clear();

                    for(Utilizador utilizador : response.body()){
                        listaUtilizadores.add(utilizador.getNomecompleto());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(Login.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item ,listaUtilizadores);
                    listaUtilizadoresView.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<List<Utilizador>> call, Throwable throwable) {
                Toast.makeText(Login.this, "Erro de rede listar Usuários", Toast.LENGTH_SHORT).show();
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, "Error ocurred", throwable);

            }
        });

    }

    public void irRegistrar(View view) {
        Intent intent = new Intent(this, Registrar.class);
        startActivity(intent);
    }
}