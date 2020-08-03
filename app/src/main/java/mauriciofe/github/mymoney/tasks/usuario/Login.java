package mauriciofe.github.mymoney.tasks.usuario;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import mauriciofe.github.mymoney.http.conexao.HttpConnectionUsuario;
import mauriciofe.github.mymoney.http.parseJson.ParseUsuario;
import mauriciofe.github.mymoney.models.Usuario;
import mauriciofe.github.mymoney.ui.activities.MainActivity;
import mauriciofe.github.mymoney.ui.activities.login.LoginActivity;

public class Login extends AsyncTask<String, String, String> {
    Context context;
    Usuario usuario;
    private String token;

    public Login(Context context, Usuario usuario) {
        this.context = context;
        this.usuario = usuario;
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
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        }
    }
}
