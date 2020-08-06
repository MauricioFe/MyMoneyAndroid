package mauriciofe.github.mymoney.http.parseJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mauriciofe.github.mymoney.models.TipoMovimentacao;

public class ParseTipoMovimentacao {
    public static List<TipoMovimentacao> parseDados(String content){
        List<TipoMovimentacao> tipoMovimentacaoList = new ArrayList<>();
        try {
            if (content != null) {
                JSONArray jsonArray = new JSONArray(content);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    TipoMovimentacao tipoMovimentacao = new TipoMovimentacao();
                    tipoMovimentacao.setId(object.getInt("id"));
                    tipoMovimentacao.setDescricao(object.getString("descricao"));
                    tipoMovimentacaoList.add(tipoMovimentacao);
                }
                return tipoMovimentacaoList;
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }
}
