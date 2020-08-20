package mauriciofe.github.mymoney.tasks.usuario;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import mauriciofe.github.mymoney.http.conexao.HttpConnectionUsuario;
import mauriciofe.github.mymoney.http.parseJson.ParseUsuario;
import mauriciofe.github.mymoney.models.Usuario;
import mauriciofe.github.mymoney.ui.activities.MenuActivity;

public class Login extends AsyncTask<String, String, String> {
    Context context;
    Usuario usuario;
    private String token;
    private String _usuario;

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
        usuario = ParseUsuario.getUsuarioLogado(conteudo);
        String usuarioJson = ParseUsuario.converterParaJSONcomId(usuario);
        if (token != null){
            SharedPreferences preferences = context.getSharedPreferences("users-preferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("usuario-logado", usuarioJson);
            editor.putString("token", token);
            editor.apply();
            Intent intent = new Intent(context, MenuActivity.class);
            context.startActivity(intent);
        }
    }
}
