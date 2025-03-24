package nix.food.android.ui.main.login;
// Le Nhut Anh - 22110279
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import nix.food.android.MVVMApplication;
import nix.food.android.data.Repository;
import nix.food.android.data.model.api.response.category.CategoryResponse;
import nix.food.android.data.model.api.response.category.ProductResponse;
import nix.food.android.ui.base.activity.BaseViewModel;
import nix.food.android.ui.main.MainCalback;
import timber.log.Timber;

public class IntroViewModel extends BaseViewModel {
    public IntroViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }
}
