package mauriciofe.github.mymoney.http.parseJson;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import mauriciofe.github.mymoney.models.Repeticao;

public class ParseRepeticao {
    public static  Repeticao converToObject(String content){
        Repeticao repeticao;
        try {
            JSONObject jsonObject = new JSONObject(content);
            repeticao = new Repeticao();
            repeticao.setId(jsonObject.getInt("id"));
            return repeticao;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    public static String convertForJson(Repeticao repeticao) {
        JSONStringer js = new JSONStringer();
        try {
            js.object();
            js.key("descricao").value(repeticao.getDescricao());
            js.key("periodo").value(repeticao.getPeriodo());
            js.key("numOcorrencias").value(repeticao.getNumOcorrencias());
            js.key("numParcelas").value(repeticao.getNumParcelas());
            js.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return js.toString();
    }
}
