package nix.food.android.ui.main.login;

import android.content.Intent;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import nix.food.android.MVVMApplication;
import nix.food.android.data.Repository;
import nix.food.android.data.model.api.request.login.SignUpRequest;
import nix.food.android.ui.base.activity.BaseViewModel;
import nix.food.android.ui.main.MainCalback;
import timber.log.Timber;
// TRANG KIM LOI - 22110371 - Le Tan Tru - 22110447

public class SignUpViewModel extends BaseViewModel {
    public SignUpViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }

    public void SignUp(MainCalback<List<String>> callback, SignUpRequest request) {
        showLoading();
        compositeDisposable.add(repository.getApiService().signUp(request)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if(response.isResult()) {
                        callback.doSuccess(response.getData().getContent());
                    }
                },err-> {
                    hideLoading();
                    Timber.d(err);
                }));
    }
}
