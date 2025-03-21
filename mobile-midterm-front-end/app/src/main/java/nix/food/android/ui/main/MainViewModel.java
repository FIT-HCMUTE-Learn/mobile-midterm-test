package nix.food.android.ui.main;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import nix.food.android.MVVMApplication;
import nix.food.android.data.Repository;
import nix.food.android.data.model.api.request.login.LoginRequest;
import nix.food.android.data.model.api.response.category.CategoryResponse;
import nix.food.android.ui.base.activity.BaseViewModel;
import nix.food.android.utils.NetworkUtils;
import retrofit2.HttpException;
import timber.log.Timber;

public class MainViewModel extends BaseViewModel {

    public MainViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }
    public void doLogin(){
        LoginRequest request = new LoginRequest();
        request.setPosId(deviceId);
        showLoading();
        compositeDisposable.add(repository.getApiService().login(request)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .retryWhen(throwable ->
                                                           throwable.flatMap((Function<Throwable, ObservableSource<?>>) throwable1 -> {
                                                               if (NetworkUtils.checkNetworkError(throwable1)) {
                                                                   hideLoading();
                                                                   return application.showDialogNoInternetAccess();
                                                               }else{
                                                                   return Observable.error(throwable1);
                                                               }
                                                           })
                                        )
                                        .subscribe(
                                                response -> {
                                                    hideLoading();
                                                    repository.getSharedPreferences().setToken(response.getData().getAccess_token());
                                                    showSuccessMessage("Login success");
                                                }, throwable -> {
                                                    hideLoading();
                                                    Timber.e(throwable);
                                                    if (throwable instanceof HttpException && ((HttpException) throwable).code() == 400){
                                                        HttpException httpException = (HttpException) throwable;
                                                        if (httpException.code() == 400) {
                                                        }
                                                        showErrorMessage("Login failed");
                                                    } else{
                                                        showErrorMessage("Login failed");
                                                    }
                                                }));
    }

    public void getAllCategories(MainCalback<List<CategoryResponse>> callback){
        showLoading();
        compositeDisposable.add(repository.getApiService().getAllCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            if (response != null) {
                                callback.doSuccess(response);
                            } else {
                                hideLoading();
                                callback.doFail();
                            }
                        }, throwable -> {
                            Timber.e(throwable);
                            callback.doError(throwable);
                        }
                )
        );
    }
}
