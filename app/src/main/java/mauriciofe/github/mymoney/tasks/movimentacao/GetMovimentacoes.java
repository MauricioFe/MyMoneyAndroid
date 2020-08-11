package mauriciofe.github.mymoney.tasks.movimentacao;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import mauriciofe.github.mymoney.http.conexao.HttpConnectionMovimentacoes;

public class GetMovimentacoes extends AsyncTask<String, String, String> {
    Context context;
    ListView movimentacoesList;

    public GetMovimentacoes(Context context, ListView movimentacoesList) {
        this.context = context;
        this.movimentacoesList = movimentacoesList;
    }

    @Override
    protected String doInBackground(String... params) {
        return HttpConnectionMovimentacoes.getDados(params[0], params[1]);
    }
}
