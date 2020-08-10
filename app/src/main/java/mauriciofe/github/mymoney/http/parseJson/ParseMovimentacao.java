package mauriciofe.github.mymoney.http.parseJson;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import mauriciofe.github.mymoney.models.Movimentacoes;

public class ParseMovimentacao {
    public static String parseForJson(Movimentacoes movimentacoes) {
        JSONStringer js = new JSONStringer();
        try {
            js.object();
            js.key("descricao").value(movimentacoes.getDescricao());
            js.key("valor").value(movimentacoes.getValor());
            js.key("date").value(movimentacoes.getData());
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
            movimentacoes.setData(jsonObject.getString("date"));
            movimentacoes.setObservacoes(jsonObject.getString("observacoes"));
            movimentacoes.setCategoria_id(jsonObject.getInt("categoria_id"));
            movimentacoes.setTipoMovimentacao_id(jsonObject.getInt("tipoMovimentacao_id"));
            movimentacoes.setRepeticao_id(jsonObject.getInt("repeticao_id"));
            movimentacoes.setUsuario_id(jsonObject.getInt("usuario_id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movimentacoes;
    }
}
