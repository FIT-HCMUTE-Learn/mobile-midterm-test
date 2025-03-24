package nix.food.android.ui.main.login;
//Ngyuen Thi Hong Nhung - 22110391 - Le Nhut Anh - 22110279
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import eu.davidea.flexibleadapter.databinding.BR;
import nix.food.android.R;
import nix.food.android.data.model.api.request.login.LoginRequest;
import nix.food.android.data.model.api.response.login.LoginResponse;
import nix.food.android.databinding.ActivityLoginBinding;
import nix.food.android.di.component.ActivityComponent;
import nix.food.android.ui.base.activity.BaseActivity;
import nix.food.android.ui.main.MainActivity;
import nix.food.android.ui.main.MainCalback;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding.setA(this);
        viewBinding.setVm(viewModel);
    }
    public void onLoginClick() {
        LoginRequest request = new LoginRequest();
        request.setUsername("super_admin");
        request.setPassword("base@123");
        viewModel.doLogin(new MainCalback<LoginResponse>() {
            @Override
            public void doError(Throwable error) {
                viewModel.showLoading();
            }

            @Override
            public void doSuccess() {

            }
            @Override
            public void doSuccess(LoginResponse objects) {
                navigateHome();
            }

            @Override
            public void doFail() {

            }
        }, request);
    }
    public void navigateHome() {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }
    public void onSignUpClick(){
        viewModel.showLoading();
        Intent it = new Intent(this, SignUpActivity.class);
        startActivity(it);
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }
}
