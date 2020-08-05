package mauriciofe.github.mymoney.ui.activities.fragments.relatorios;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RelatoriosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RelatoriosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}