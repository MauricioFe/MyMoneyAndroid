package mauriciofe.github.mymoney.ui.activities.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import mauriciofe.github.mymoney.R;
import mauriciofe.github.mymoney.models.Usuario;
import mauriciofe.github.mymoney.tasks.usuario.PostUsuario;

public class ResgisterActivity extends AppCompatActivity {
    Button btnCadastrar;
    EditText edtEmail;
    EditText edtNome;
    EditText edtSenha;
    TextView txtPossuoLogin;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgister);
        inicializarComponentes();

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(edtNome.getText().length() == 0 &&edtEmail.getText().length() == 0 && edtSenha.getText().length() == 0)) {
                    usuario = new Usuario();
                    usuario.setNome(edtNome.getText().toString());
                    usuario.setEmail(edtEmail.getText().toString());
                    usuario.setSenha(edtSenha.getText().toString());
                    cadastrarUsuario("https://192.168.0.14:44325/api/usuarios/");
                }else{
                    Toast.makeText(ResgisterActivity.this, "Preecha todos os campos", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void cadastrarUsuario(String uri) {
        if(isOnline()){
            PostUsuario task = new PostUsuario(this, usuario);
            task.execute(uri);
        }
    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }

    private void inicializarComponentes() {
        btnCadastrar = findViewById(R.id.register_btn_entrar);
        edtEmail = findViewById(R.id.register_editText_email);
        edtNome = findViewById(R.id.register_editText_nome);
        edtSenha = findViewById(R.id.register_editText_senha);
        txtPossuoLogin = findViewById(R.id.register_txtPossuo_cadastro);

        txtPossuoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResgisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}