package mauriciofe.github.mymoney.http.parseJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mauriciofe.github.mymoney.models.Categoria;

public class ParseCategoria {
    public static List<Categoria> getCategoriasJson(String conteudo) {
        List<Categoria> categorias = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(conteudo);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Categoria categoria = new Categoria();
                categoria.setId(jsonObject.getInt("Id"));
                categoria.setDescricao(jsonObject.getString("Descricao"));
                categorias.add(categoria);
            }
            return categorias;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
