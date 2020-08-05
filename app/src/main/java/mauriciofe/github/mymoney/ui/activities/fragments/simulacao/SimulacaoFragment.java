package mauriciofe.github.mymoney.ui.activities.fragments.simulacao;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import mauriciofe.github.mymoney.R;

public class SimulacaoFragment extends Fragment {

    private SimulacaoViewModel simulacaoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        simulacaoViewModel =
                ViewModelProviders.of(this).get(SimulacaoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_simulacao, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        simulacaoViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}