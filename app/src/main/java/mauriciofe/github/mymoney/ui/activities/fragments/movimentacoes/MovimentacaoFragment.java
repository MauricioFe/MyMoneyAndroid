package mauriciofe.github.mymoney.ui.activities.fragments.movimentacoes;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import mauriciofe.github.mymoney.R;
import mauriciofe.github.mymoney.tasks.movimentacao.GetMovimentacoes;


public class MovimentacaoFragment extends Fragment {

    private MovimentacaoViewModel movimentacaoViewModel;
    ListView movimentacaoList;
    String token;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        movimentacaoViewModel =
                ViewModelProviders.of(this).get(MovimentacaoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_movimentacao, container, false);

        SharedPreferences preferences = getContext().getSharedPreferences("users-preferences", Context.MODE_PRIVATE);
        token = preferences.getString("token", "");
        movimentacaoList = root.findViewById(R.id.fragment_movimentacao_lista_movimentacao);
        buscarMovimentacoes("https://192.168.0.14:44303/api/movimentacoes");


        return root;
    }

    private void buscarMovimentacoes(String uri) {
        GetMovimentacoes task = new GetMovimentacoes(getContext(), movimentacaoList);
        task.execute(uri, token);
    }
}