package mauriciofe.github.mymoney.tasks.categoria;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import mauriciofe.github.mymoney.http.conexao.HttpConnectionCategoria;

public class PostCategoria extends AsyncTask<String, String, String> {
    Context context;

    public PostCategoria(Context context) {
        this.context = context;
    }
    @Override
    protected String doInBackground(String... params) {
        String teste = HttpConnectionCategoria.postDados(params[0]);
        Log.i("TAG", "doInBackground: Deu certo? deu sim hahahahahah" + teste);
        return null;
    }
}
