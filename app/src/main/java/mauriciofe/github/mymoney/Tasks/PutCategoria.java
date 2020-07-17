package mauriciofe.github.mymoney.Tasks;

import android.content.Context;
import android.os.AsyncTask;

import java.net.HttpURLConnection;

import mauriciofe.github.mymoney.http.conexao.HttpConnection;

public class PutCategoria extends AsyncTask<String,String,String> {
    Context context;

    public PutCategoria(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String content = HttpConnection.putDados(params[0]);
        return null;
    }

}
