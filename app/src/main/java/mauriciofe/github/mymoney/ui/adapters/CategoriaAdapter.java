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
import mauriciofe.github.mymoney.models.Categoria;

public class CategoriaAdapter extends ArrayAdapter<Categoria> {
    public CategoriaAdapter(@NonNull Context context, List<Categoria> categoriaList) {
        super(context, 0, categoriaList);
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item_categoria, parent, false);
        }
        TextView textView = convertView.findViewById(R.id.spinner_list_descricao);

        Categoria categoria = getItem(position);
        textView.setText(categoria.getId()+" "+categoria.getDescricao());

        return convertView;
    }
}
