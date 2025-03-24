package nix.food.android.ui.main;
// TRANG KIM LOI - 22110371
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import nix.food.android.MVVMApplication;
import nix.food.android.data.Repository;
import nix.food.android.data.model.api.ResponseWrapper;
import nix.food.android.data.model.api.request.login.LoginRequest;
import nix.food.android.data.model.api.response.category.CategoryResponse;
import nix.food.android.data.model.api.response.category.ProductResponse;
import nix.food.android.ui.base.activity.BaseViewModel;
import nix.food.android.utils.NetworkUtils;
import retrofit2.HttpException;
import timber.log.Timber;

public class MainViewModel extends BaseViewModel {
    public MainViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }
    public void getAllCategories(MainCalback<List<CategoryResponse>> callback) {
        compositeDisposable.add(repository.getApiService().getAllCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            if (response != null && response.getData() != null) {
                                callback.doSuccess(response.getData().getContent());
                            } else {
                                hideLoading();
                                callback.doFail();
                            }
                        },
                        throwable -> {
                            Timber.e(throwable);
                            callback.doError(throwable);
                        }
                )
        );
    }
    public void getAllProducts(MainCalback<List<ProductResponse>> callback) {
        compositeDisposable.add(repository.getApiService().getAllProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            if (response != null && response.getData() != null) {
                                callback.doSuccess(response.getData().getContent());
                            } else {
                                hideLoading();
                                callback.doFail();
                            }
                        },
                        throwable -> {
                            Timber.e(throwable);
                            callback.doError(throwable);
                        }
                )
        );
    }

}
