package mauriciofe.github.mymoney.http.parseJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;

import mauriciofe.github.mymoney.models.Movimentacoes;

public class ParseMovimentacao {
    public static String parseForJson(Movimentacoes movimentacoes) {
        JSONStringer js = new JSONStringer();
        try {
            js.object();
            js.key("descricao").value(movimentacoes.getDescricao());
            js.key("valor").value(movimentacoes.getValor());
            js.key("data").value(movimentacoes.getData());
            js.key("observacoes").value(movimentacoes.getObservacoes());
            js.key("categoria_id").value(movimentacoes.getCategoria_id());
            js.key("tipoMovimentacao_id").value(movimentacoes.getTipoMovimentacao_id());
            js.key("repeticao_id").value(movimentacoes.getRepeticao_id());
            js.key("usuario_id").value(movimentacoes.getUsuario_id());
            js.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return js.toString();
    }

    public static Movimentacoes parseJsonForObject(String content) {
        Movimentacoes movimentacoes = new Movimentacoes();
        try {
            JSONObject jsonObject = new JSONObject(content);
            movimentacoes.setId(jsonObject.getInt("id"));
            movimentacoes.setDescricao(jsonObject.getString("descricao"));
            movimentacoes.setValor(jsonObject.getDouble("valor"));
            movimentacoes.setData(jsonObject.getString("data"));
            movimentacoes.setObservacoes(jsonObject.getString("observacoes"));
            movimentacoes.setCategoria_id(jsonObject.getInt("categoria_id"));
            movimentacoes.setTipoMovimentacao_id(jsonObject.getInt("tipoMovimentacao_id"));
            movimentacoes.setRepeticao_id(jsonObject.getInt("repeticao_id"));
            movimentacoes.setUsuario_id(jsonObject.getInt("usuario_id"));
            return movimentacoes;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Movimentacoes> parseForList(String content) {
        List<Movimentacoes> movimentacoesList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(content);
            for (int i = 0; i < jsonArray.length(); i++) {
                Movimentacoes movimentacoes = new Movimentacoes();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                movimentacoes.setId(jsonObject.getInt("id"));
                movimentacoes.setDescricao(jsonObject.getString("descricao"));
                movimentacoes.setValor(jsonObject.getDouble("valor"));
                movimentacoes.setData(jsonObject.getString("data"));
                movimentacoes.setObservacoes(jsonObject.getString("observacoes"));
                movimentacoes.setCategoria_id(jsonObject.getInt("categoria_id"));
                movimentacoes.setTipoMovimentacao_id(jsonObject.getInt("tipoMovimentacao_id"));
                movimentacoes.setRepeticao_id(jsonObject.getInt("repeticao_id"));
                movimentacoes.setUsuario_id(jsonObject.getInt("usuario_id"));
                movimentacoesList.add(movimentacoes);
            }
            return movimentacoesList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
