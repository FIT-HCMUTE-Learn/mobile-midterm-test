package nix.food.android.ui.main.login;
//Ngyuen Thi Hong Nhung - 22110391 - Le Nhut Anh - 22110279
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import nix.food.android.MVVMApplication;
import nix.food.android.data.Repository;
import nix.food.android.data.model.api.request.login.LoginRequest;
import nix.food.android.data.model.api.response.login.LoginResponse;
import nix.food.android.ui.base.activity.BaseViewModel;
import nix.food.android.ui.main.MainCalback;
import nix.food.android.utils.NetworkUtils;
import timber.log.Timber;

public class LoginViewModel extends BaseViewModel {
    public LoginViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }

    public void doLogin(MainCalback<LoginResponse> callback, LoginRequest request){
        showLoading();
        compositeDisposable.add(repository.getApiService().login(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            if (response != null) {
                                repository.getSharedPreferences().setToken(response.getAccessToken());
                                repository.getSharedPreferences().setUserName(request.getUsername());
                                callback.doSuccess(response);
                            } else {
                                hideLoading();
                                callback.doFail();
                            }
                        }, throwable -> {
                            Timber.e(throwable);
                            callback.doError(throwable);
                        }));
    }
}
