package mauriciofe.github.mymoney.ui.activities.login;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import mauriciofe.github.mymoney.R;
import mauriciofe.github.mymoney.http.conexao.HttpConnectionUsuario;
import mauriciofe.github.mymoney.http.parseJson.ParseUsuario;
import mauriciofe.github.mymoney.http.ssl.ConferirSsl;
import mauriciofe.github.mymoney.models.Usuario;
import mauriciofe.github.mymoney.ui.activities.MainActivity;

import static mauriciofe.github.mymoney.http.ssl.ConferirSsl.trustEveryone;

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
                if (!(edtSenha.getText().length() == 0 && edtEmail.getText().length() == 0)) {

                    usuario = new Usuario();
                    usuario.setEmail(edtEmail.getText().toString());
                    usuario.setSenha(edtSenha.getText().toString());

                    enviarLogin("https://192.168.0.14:44325/api/usuarios/login");
                } else {
                    Toast.makeText(LoginActivity.this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }

    private void enviarLogin(String uri) {
        if (isOnline()) {
            Login task = new Login(this);
            task.execute(uri);
            token = task.enviaToken();
        }
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

    public class Login extends AsyncTask<String, String, String> {
        Context context;
        private String token;

        public Login(Context context) {
            this.context = context;
        }

        public String enviaToken() {
            if (token != null)
                return token;
            else
                return null;
        }

        @Override
        protected String doInBackground(String... params) {
            String conteudo = HttpConnectionUsuario.login(params[0], usuario);
            return conteudo;
        }

        @Override
        protected void onPostExecute(String conteudo) {
            token = ParseUsuario.parseToken(conteudo);
            if (token != null){
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    }




}