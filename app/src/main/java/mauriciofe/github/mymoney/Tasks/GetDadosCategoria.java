package mauriciofe.github.mymoney.Tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import mauriciofe.github.mymoney.http.conexao.HttpConnection;
import mauriciofe.github.mymoney.http.parseJson.ParseCategoria;
import mauriciofe.github.mymoney.models.Categoria;

public class GetDadosCategoria extends AsyncTask<String, String, String> {

    private List<Categoria> categoriaList;
    private Context context;
    public GetDadosCategoria(Context context, List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String content = HttpConnection.getDados(params[0]);
        return content;
    }

    @Override
    protected void onPostExecute(String conteudo) {
         categoriaList = ParseCategoria.getCategoriasJson(conteudo);
        if (categoriaList != null) {
            for (Categoria item : categoriaList) {
                Log.i("Return API", "Id da categoria " + item.getId() + "\n" + "Descrição " + item.getDescricao() + "\n");
            }
        }
    }
}
