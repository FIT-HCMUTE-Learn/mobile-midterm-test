package nix.food.android.ui.main.login;
//Ngyuen Thi Hong Nhung - 22110391
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.List;

import eu.davidea.flexibleadapter.databinding.BR;
import nix.food.android.R;
import nix.food.android.data.model.api.request.login.SignUpRequest;
import nix.food.android.databinding.ActivitySignupBinding;
import nix.food.android.di.component.ActivityComponent;
import nix.food.android.ui.base.activity.BaseActivity;
import nix.food.android.ui.main.MainActivity;
import nix.food.android.ui.main.MainCalback;

public class SignUpActivity extends BaseActivity<ActivitySignupBinding, SignUpViewModel> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding.setA(this);
        viewBinding.setVm(viewModel);
    }

    public void onSignUpClick(){
        signUp();
    }
    public void signUp() {
        SignUpRequest request = new SignUpRequest();
        request.setEmail(viewBinding.email.getText().toString());
        request.setPassword(viewBinding.password.getText().toString());
        viewModel.SignUp(new MainCalback<List<String>>() {
            @Override
            public void doError(Throwable error) {

            }

            @Override
            public void doSuccess() {

            }

            @Override
            public void doFail() {

            }
            @Override
            public void doSuccess(List<String> mgs) {
                navigateVerify(viewBinding.email.toString());
            }
        }, request);
    }
    public void navigateVerify(String email) {
        VerifyOtpActivity.EMAIL = email;
        Intent it = new Intent(this, VerifyOtpActivity.class);
        startActivity(it);
        this.finish();
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_signup;
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
