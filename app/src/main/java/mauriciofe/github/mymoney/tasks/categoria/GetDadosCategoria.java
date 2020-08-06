package mauriciofe.github.mymoney.tasks.categoria;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import mauriciofe.github.mymoney.http.conexao.HttpConnectionCategoria;
import mauriciofe.github.mymoney.http.parseJson.ParseCategoria;
import mauriciofe.github.mymoney.models.Categoria;
import mauriciofe.github.mymoney.ui.activities.CadastrarMovimentacaoActivity;
import mauriciofe.github.mymoney.ui.adapters.CategoriaAdapter;

public class GetDadosCategoria extends AsyncTask<String, String, String> {

    private List<Categoria> categoriaList;
    private Context context;
    Spinner spinnerCategoria;

    public GetDadosCategoria(Context context, Spinner spinnerCategoria) {
        this.context = context;
        this.spinnerCategoria = spinnerCategoria;
    }

    @Override
    protected String doInBackground(String... params) {
        String content = HttpConnectionCategoria.getDados(params[0], params[1]);
        return content;
    }

    @Override
    protected void onPostExecute(String conteudo) {
        categoriaList = ParseCategoria.getCategoriasJson(conteudo);
        if (categoriaList != null) {
            CategoriaAdapter adapter = new CategoriaAdapter(context, categoriaList);
            spinnerCategoria.setAdapter(adapter);
        }
    }
}
