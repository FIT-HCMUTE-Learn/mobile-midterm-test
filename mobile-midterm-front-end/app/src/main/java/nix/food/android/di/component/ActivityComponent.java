package nix.food.android.di.component;

import com.facebook.login.Login;

import nix.food.android.di.module.ActivityModule;
import nix.food.android.di.scope.ActivityScope;
import nix.food.android.ui.main.MainActivity;

import dagger.Component;
import nix.food.android.ui.main.login.IntroActivity;
import nix.food.android.ui.main.login.LoginActivity;
import nix.food.android.ui.main.login.SignUpActivity;
import nix.food.android.ui.main.login.VerifyOtpActivity;

@ActivityScope
@Component(modules = {ActivityModule.class}, dependencies = AppComponent.class)
public interface ActivityComponent {
    void inject(MainActivity activity);
    void inject(LoginActivity activity);
    void inject(SignUpActivity activity);
    void inject(IntroActivity activity);
    void inject(VerifyOtpActivity activity);
}

