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

import androidx.appcompat.app.AppCompatActivity;

import mauriciofe.github.mymoney.R;
import mauriciofe.github.mymoney.http.conexao.HttpConnectionUsuario;
import mauriciofe.github.mymoney.http.parseJson.ParseUsuario;

public class LoginActivity extends AppCompatActivity {
    Button btnEntrar;
    EditText edtEmail;
    EditText edtSenha;
    TextView txtCadastrese;
    String token = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inicializaElementos();
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
            String conteudo = HttpConnectionUsuario.login(params[0]);
            return conteudo;
        }

        @Override
        protected void onPostExecute(String conteudo) {
            token = ParseUsuario.parseToken(conteudo);
        }
    }


}