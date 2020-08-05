package mauriciofe.github.mymoney.ui.activities.fragments.movimentacoes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import mauriciofe.github.mymoney.R;


public class MovimentacaoFragment extends Fragment {

    private MovimentacaoViewModel movimentacaoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        movimentacaoViewModel =
                ViewModelProviders.of(this).get(MovimentacaoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_movimentacao, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        movimentacaoViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}