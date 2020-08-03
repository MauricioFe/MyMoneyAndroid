package mauriciofe.github.mymoney.tasks.categoria;

import android.content.Context;
import android.os.AsyncTask;

import mauriciofe.github.mymoney.http.conexao.HttpConnectionCategoria;

public class PutCategoria extends AsyncTask<String,String,String> {
    Context context;

    public PutCategoria(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String content = HttpConnectionCategoria.putDados(params[0]);
        return null;
    }

}
