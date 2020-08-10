package mauriciofe.github.mymoney.http.parseJson;

import org.json.JSONException;
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
}
