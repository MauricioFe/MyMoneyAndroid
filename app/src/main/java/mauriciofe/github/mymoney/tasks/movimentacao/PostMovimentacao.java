package mauriciofe.github.mymoney.tasks.movimentacao;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

import mauriciofe.github.mymoney.http.conexao.HttpConnectionMovimentacoes;
import mauriciofe.github.mymoney.http.parseJson.ParseMovimentacao;
import mauriciofe.github.mymoney.models.Movimentacoes;

public class PostMovimentacao extends AsyncTask<String, String, String> {
    Context context;
    Movimentacoes movimentacoes;

    public PostMovimentacao(Context context, Movimentacoes movimentacoes) {
        this.context = context;
        this.movimentacoes = movimentacoes;
    }

    @Override
    protected String doInBackground(String... params) {
        String content = HttpConnectionMovimentacoes.postDados(params[0], params[1], movimentacoes);
        return content;
    }

    @Override
    protected void onPostExecute(String content) {
        Movimentacoes movimentacoes = ParseMovimentacao.parseJsonForObject(content);

    }
}
