package mauriciofe.github.mymoney.tasks.usuario;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;

import mauriciofe.github.mymoney.http.conexao.HttpConnectionUsuario;
import mauriciofe.github.mymoney.models.Usuario;

public class PostUsuario extends AsyncTask<String, String, String> {
    Context context;
    Usuario usuario;

    public PostUsuario(Context context, Usuario usuario) {
        this.context = context;
        this.usuario = usuario;
    }

    @Override
    protected String doInBackground(String... params) {
        String conteudo = HttpConnectionUsuario.postUsuario(params[0], usuario);
        return conteudo;
    }

    @Override
    protected void onPostExecute(String conteudo) {
        Login task = new Login(context, usuario);
        task.execute("https://192.168.0.14:44325/api/usuarios/login");
    }
}
