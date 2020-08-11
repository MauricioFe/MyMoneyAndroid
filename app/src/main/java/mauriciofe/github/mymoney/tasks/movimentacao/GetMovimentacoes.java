package mauriciofe.github.mymoney.tasks.movimentacao;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

public class GetMovimentacoes extends AsyncTask<String, String, String> {
    Context context;
    ListView movimentacoesList;

    public GetMovimentacoes(Context context, ListView movimentacoesList) {
        this.context = context;
        this.movimentacoesList = movimentacoesList;
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }
}
