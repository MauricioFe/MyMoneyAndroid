package mauriciofe.github.mymoney.ui.activities.fragments.movimentacoes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MovimentacaoViewModel  extends ViewModel {

    private MutableLiveData<String> mText;

    public MovimentacaoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
