package mauriciofe.github.mymoney.ui.activities.fragments.simulacao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SimulacaoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SimulacaoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}