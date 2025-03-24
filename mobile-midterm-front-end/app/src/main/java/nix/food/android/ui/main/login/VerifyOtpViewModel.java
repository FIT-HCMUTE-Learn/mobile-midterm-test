package nix.food.android.ui.main.login;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import nix.food.android.MVVMApplication;
import nix.food.android.data.Repository;
import nix.food.android.data.model.api.request.login.SignUpRequest;
import nix.food.android.data.model.api.request.login.VerifyOtpRequest;
import nix.food.android.ui.base.activity.BaseViewModel;
import nix.food.android.ui.main.MainCalback;
import timber.log.Timber;
// TRANG KIM LOI - 22110371 - Le Tan Tru - 22110447
public class VerifyOtpViewModel extends BaseViewModel {
    public VerifyOtpViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }
    public void VerifyOtp(MainCalback<String> callback, VerifyOtpRequest request) {
        showLoading();
        compositeDisposable.add(repository.getApiService().verifyOtp(request)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if(response != null) {
                        callback.doSuccess(response);
                    }
                },err-> {
                    hideLoading();
                    Timber.d(err);
                }));
    }
}
