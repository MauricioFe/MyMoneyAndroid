package mauriciofe.github.mymoney.tasks.movimentacao;

import android.content.Context;
import android.os.AsyncTask;

import mauriciofe.github.mymoney.models.Movimentacoes;

public class PostMovimentacao extends AsyncTask<String, String, String> {
    Context context;
    Movimentacoes movimentacoes;

    public PostMovimentacao(Context context, Movimentacoes movimentacoes) {
        this.context = context;
        this.movimentacoes = movimentacoes;
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }
}
