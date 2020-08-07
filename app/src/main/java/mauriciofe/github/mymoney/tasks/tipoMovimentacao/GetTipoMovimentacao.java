package mauriciofe.github.mymoney.tasks.tipoMovimentacao;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Spinner;

import java.util.List;

import mauriciofe.github.mymoney.http.conexao.HttpConnectionTipoMovimentacao;
import mauriciofe.github.mymoney.http.parseJson.ParseTipoMovimentacao;
import mauriciofe.github.mymoney.models.TipoMovimentacao;
import mauriciofe.github.mymoney.ui.adapters.TipoMovimentacaoAdapter;

public class GetTipoMovimentacao extends AsyncTask<String, String, String> {
    Context context;
    List<TipoMovimentacao> tipoMovimentacaoList;
    Spinner spnTipoMovimentacao;

    public GetTipoMovimentacao(Context context, Spinner spnTipoMovimentaca) {
        this.context = context;
        this.spnTipoMovimentacao = spnTipoMovimentaca;
    }

    @Override
    protected String doInBackground(String... params) {
        String content = HttpConnectionTipoMovimentacao.getDados(params[0], params[1]);
        return content;
    }

    @Override
    protected void onPostExecute(String content) {
        tipoMovimentacaoList = ParseTipoMovimentacao.parseDados(content);
        if (tipoMovimentacaoList != null) {
            TipoMovimentacaoAdapter adapter = new TipoMovimentacaoAdapter(context, tipoMovimentacaoList);
            spnTipoMovimentacao.setAdapter(adapter);
        }
    }
}
