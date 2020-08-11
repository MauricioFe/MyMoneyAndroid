package mauriciofe.github.mymoney.tasks.movimentacao;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import java.util.List;

import mauriciofe.github.mymoney.http.conexao.HttpConnectionMovimentacoes;
import mauriciofe.github.mymoney.http.parseJson.ParseMovimentacao;
import mauriciofe.github.mymoney.models.Movimentacoes;
import mauriciofe.github.mymoney.ui.adapters.MovimentacoesAdapter;

public class GetMovimentacoes extends AsyncTask<String, String, String> {
    Context context;
    ListView movimentacaoList;

    public GetMovimentacoes(Context context, ListView movimentacoesList) {
        this.context = context;
        this.movimentacaoList = movimentacoesList;
    }

    @Override
    protected String doInBackground(String... params) {
        return HttpConnectionMovimentacoes.getDados(params[0], params[1]);
    }

    @Override
    protected void onPostExecute(String content) {
        List<Movimentacoes> movimentacoesList = ParseMovimentacao.parseForList(content);
        MovimentacoesAdapter adapter = new MovimentacoesAdapter(movimentacoesList, context);
        movimentacaoList.setAdapter(adapter);
    }
}
