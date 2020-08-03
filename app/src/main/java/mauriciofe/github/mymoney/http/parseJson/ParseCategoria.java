package mauriciofe.github.mymoney.http.parseJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;

import mauriciofe.github.mymoney.models.Categoria;

public class ParseCategoria {
    public static List<Categoria> getCategoriasJson(String conteudo) {
        List<Categoria> categorias = new ArrayList<>();
        if (conteudo != null) {
            try {

                JSONArray jsonArray = new JSONArray(conteudo);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Categoria categoria = new Categoria();
                    categoria.setId(jsonObject.getInt("id"));
                    categoria.setDescricao(jsonObject.getString("descricao"));
                    categorias.add(categoria);
                }
                return categorias;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        } else
            return null;
    }

    public static String converterParaJSON(Categoria categoria) {
        JSONStringer js = new JSONStringer();
        try {
            js.object();
            js.key("descricao").value(categoria.getDescricao());
            js.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return js.toString();
    }
}
