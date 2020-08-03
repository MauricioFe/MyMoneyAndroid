package mauriciofe.github.mymoney.tasks.categoria;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import mauriciofe.github.mymoney.http.conexao.HttpConnectionCategoria;

public class DeleteCategoria extends AsyncTask<String, String, String> {
    Context context;

    public DeleteCategoria(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        HttpConnectionCategoria.deleteDados(params[0]);
        Log.i("testeDelete", "Será que foi excluido?");
        return null;
    }
}
