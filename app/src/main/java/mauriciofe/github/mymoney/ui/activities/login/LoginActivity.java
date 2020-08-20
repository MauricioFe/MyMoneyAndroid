package mauriciofe.github.mymoney.ui.activities.login;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import mauriciofe.github.mymoney.R;

import mauriciofe.github.mymoney.http.ssl.ConferirSsl;
import mauriciofe.github.mymoney.models.Usuario;
import mauriciofe.github.mymoney.tasks.usuario.Login;

public class LoginActivity extends AppCompatActivity {
    Button btnEntrar;
    EditText edtEmail;
    EditText edtSenha;
    TextView txtCadastrese;
    String token = null;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inicializaElementos();
        ConferirSsl.trustEveryone();

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarLogin();
                if (usuario != null) {
                    enviarLogin("https://192.168.0.14:44303/api/usuarios/login");
                }
            }
        });

    }

    public void realizarLogin() {
        if (!(edtSenha.getText().length() == 0 && edtEmail.getText().length() == 0)) {

            usuario = new Usuario();
            usuario.setEmail(edtEmail.getText().toString());
            usuario.setSenha(edtSenha.getText().toString());
        } else {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
        }
    }

    public void enviarLogin(String uri) {
        if (isOnline()) {
            Login task = new Login(this, usuario);
            task.execute(uri);
            this.finish();
        }else {
            new AlertDialog.Builder(this).setTitle("Erro de conexão")
                    .setMessage("Erro ao conectar a internet. verifique sua conexão")
                    .setNeutralButton("OK", null).show();
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }

    private void inicializaElementos() {
        btnEntrar = findViewById(R.id.login_btn_entrar);
        edtEmail = findViewById(R.id.login_editText_email);
        edtSenha = findViewById(R.id.login_editText_senha);
        txtCadastrese = findViewById(R.id.login_txtCadastrar);
        txtCadastrese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ResgisterActivity.class);
                startActivity(intent);
            }
        });
    }


}