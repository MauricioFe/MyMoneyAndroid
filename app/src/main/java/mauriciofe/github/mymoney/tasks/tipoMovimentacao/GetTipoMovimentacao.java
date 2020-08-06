package mauriciofe.github.mymoney.tasks.tipoMovimentacao;

import android.os.AsyncTask;

import mauriciofe.github.mymoney.http.conexao.HttpConnectionTipoMovimentacao;
import mauriciofe.github.mymoney.http.parseJson.ParseTipoMovimentacao;

public class GetTipoMovimentacao extends AsyncTask<String, String, String> {
    @Override
    protected String doInBackground(String... params) {
        String content = HttpConnectionTipoMovimentacao.getDados(params[0], params[1]);
        return content;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
