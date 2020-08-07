package mauriciofe.github.mymoney.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import mauriciofe.github.mymoney.R;
import mauriciofe.github.mymoney.models.TipoMovimentacao;

public class TipoMovimentacaoAdapter extends ArrayAdapter<TipoMovimentacao> {
    public TipoMovimentacaoAdapter(@NonNull Context context, List<TipoMovimentacao> tipoMovimentacaoList) {
        super(context, 0, tipoMovimentacaoList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item, parent, false);
        }
        TextView txtTipo = convertView.findViewById(R.id.spinner_list_descricao);
        TipoMovimentacao tipoMovimentacao = getItem(position);
        if (tipoMovimentacao != null) {
            txtTipo.setText(tipoMovimentacao.getDescricao());
        }
        return convertView;
    }
}
