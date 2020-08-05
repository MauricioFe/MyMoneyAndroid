package mauriciofe.github.mymoney.ui.activities.fragments.relatorios;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import mauriciofe.github.mymoney.R;


public class RelatoriosFragment extends Fragment {

    private RelatoriosViewModel relatoriosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        relatoriosViewModel =
                ViewModelProviders.of(this).get(RelatoriosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_relatorios, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        relatoriosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}