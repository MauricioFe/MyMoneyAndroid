package mauriciofe.github.mymoney.tasks.repeticao;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import mauriciofe.github.mymoney.http.conexao.HttpConnectionRepeticao;
import mauriciofe.github.mymoney.http.parseJson.ParseRepeticao;
import mauriciofe.github.mymoney.models.Movimentacoes;
import mauriciofe.github.mymoney.models.Repeticao;

public class PostRepeticao extends AsyncTask<String, String, String> {
    Context context;
    Repeticao repeticao;
    Movimentacoes movimentacoes;

    public PostRepeticao(Context context, Repeticao repeticao, Movimentacoes movimentacoes) {
        this.context = context;
        this.repeticao = repeticao;
        this.movimentacoes = movimentacoes;
    }

    @Override
    protected String doInBackground(String... params) {
        String content = HttpConnectionRepeticao.postDados(params[0], params[1], repeticao);
        return content;
    }

    @Override
    protected void onPostExecute(String content) {
        repeticao = ParseRepeticao.converToObject(content);
        movimentacoes.setRepeticao_id(repeticao.getId());
    }
}
