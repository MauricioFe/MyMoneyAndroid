package mauriciofe.github.mymoney.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import mauriciofe.github.mymoney.R;
import mauriciofe.github.mymoney.models.Movimentacoes;

public class MovimentacoesAdapter extends BaseAdapter {
    private List<Movimentacoes> movimentacoesList;
    private Context context;

    public MovimentacoesAdapter(List<Movimentacoes> movimentacoesList, Context context) {
        this.movimentacoesList = movimentacoesList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return movimentacoesList.size();
    }

    @Override
    public Object getItem(int position) {
        return movimentacoesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.movimentacoes_item, parent, false);
        Movimentacoes movimentacoes = movimentacoesList.get(position);
        TextView txtDescricao = convertView.findViewById(R.id.movimentacao_item_descricao);
        TextView txtData = convertView.findViewById(R.id.movimentacao_item_data);
        TextView txtValor = convertView.findViewById(R.id.movimentacao_item_valor);
        txtDescricao.setText(movimentacoes.getDescricao());
        txtData.setText(movimentacoes.getData());
        String valor = String.valueOf(movimentacoes.getValor());
        txtValor.setText(valor);
        return convertView;
    }
}
