package mauriciofe.github.mymoney.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

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
        return convertView;
    }
}
