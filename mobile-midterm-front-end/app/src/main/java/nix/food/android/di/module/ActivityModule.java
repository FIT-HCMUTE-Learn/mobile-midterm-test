package nix.food.android.di.module;

import android.content.Context;

import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;

import nix.food.android.MVVMApplication;
import nix.food.android.ViewModelProviderFactory;
import nix.food.android.data.Repository;
import nix.food.android.di.scope.ActivityScope;
import nix.food.android.ui.base.activity.BaseActivity;
import nix.food.android.ui.main.MainViewModel;
import nix.food.android.ui.main.login.IntroViewModel;
import nix.food.android.ui.main.login.LoginViewModel;
import nix.food.android.ui.main.login.SignUpViewModel;
import nix.food.android.ui.main.login.VerifyOtpViewModel;
import nix.food.android.utils.GetInfo;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private BaseActivity<?, ?> activity;

    public ActivityModule(BaseActivity<?, ?> activity) {
        this.activity = activity;
    }

    @Named("access_token")
    @Provides
    @ActivityScope
    String provideToken(Repository repository){
        return repository.getToken();
    }

    @Named("device_id")
    @Provides
    @ActivityScope
    String provideDeviceId( Context applicationContext){
        return GetInfo.getAll(applicationContext);
    }


    @Provides
    @ActivityScope
    MainViewModel provideMainViewModel(Repository repository, Context application) {
        Supplier<MainViewModel> supplier = () -> new MainViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<MainViewModel> factory = new ViewModelProviderFactory<>(MainViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(MainViewModel.class);
    }

    @Provides
    @ActivityScope
    LoginViewModel provideLoginViewModel(Repository repository, Context application) {
        Supplier<LoginViewModel> supplier = () -> new LoginViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<LoginViewModel> factory = new ViewModelProviderFactory<>(LoginViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(LoginViewModel.class);
    }

    @Provides
    @ActivityScope
    SignUpViewModel provideSignUpViewModel(Repository repository, Context application) {
        Supplier<SignUpViewModel> supplier = () -> new SignUpViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<SignUpViewModel> factory = new ViewModelProviderFactory<>(SignUpViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(SignUpViewModel.class);
    }

    @Provides
    @ActivityScope
    IntroViewModel provideIntroViewModel(Repository repository, Context application) {
        Supplier<IntroViewModel> supplier = () -> new IntroViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<IntroViewModel> factory = new ViewModelProviderFactory<>(IntroViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(IntroViewModel.class);
    }
    @Provides
    @ActivityScope
    VerifyOtpViewModel provideVerifyOtpViewModel(Repository repository, Context application) {
        Supplier<VerifyOtpViewModel> supplier = () -> new VerifyOtpViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<VerifyOtpViewModel> factory = new ViewModelProviderFactory<>(VerifyOtpViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(VerifyOtpViewModel.class);
    }
}
