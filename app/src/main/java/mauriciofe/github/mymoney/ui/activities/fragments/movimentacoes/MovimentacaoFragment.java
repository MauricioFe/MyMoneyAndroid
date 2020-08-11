package mauriciofe.github.mymoney.ui.activities.fragments.movimentacoes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import mauriciofe.github.mymoney.R;
import mauriciofe.github.mymoney.models.Movimentacoes;
import mauriciofe.github.mymoney.tasks.movimentacao.GetMovimentacoes;


public class MovimentacaoFragment extends Fragment {

    private MovimentacaoViewModel movimentacaoViewModel;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        movimentacaoViewModel =
                ViewModelProviders.of(this).get(MovimentacaoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_movimentacao, container, false);

//        ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, movimentacoesList);
//        movimentacaoList = root.findViewById(R.id.fragment_movimentacao_lista_movimentacao);
//        movimentacaoList.setAdapter(adapter);
        return root;
    }

}