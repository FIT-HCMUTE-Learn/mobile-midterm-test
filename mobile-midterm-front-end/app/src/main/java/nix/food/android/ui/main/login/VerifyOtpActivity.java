package nix.food.android.ui.main.login;
//Le Nhut Anh - 22110279
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import eu.davidea.flexibleadapter.databinding.BR;
import nix.food.android.R;
import nix.food.android.data.model.api.request.login.SignUpRequest;
import nix.food.android.data.model.api.request.login.VerifyOtpRequest;
import nix.food.android.databinding.ActivityVerifyOtpBinding;
import nix.food.android.di.component.ActivityComponent;
import nix.food.android.ui.base.activity.BaseActivity;
import nix.food.android.ui.main.MainCalback;

public class VerifyOtpActivity extends BaseActivity<ActivityVerifyOtpBinding, VerifyOtpViewModel> {

    public static String EMAIL;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding.setA(this);
        viewBinding.setVm(viewModel);
        viewBinding.email.setText(EMAIL);
    }
    public void onVerifyClick(){
        verifyOtp();
    }
    public void verifyOtp() {
        VerifyOtpRequest request = new VerifyOtpRequest();
        request.setEmail(viewBinding.email.toString());
        request.setOtp(viewBinding.otpET1.getText().toString() + viewBinding.otpET2.getText().toString() + viewBinding.otpET3.getText().toString() +
                viewBinding.otpET4.getText().toString() + viewBinding.otpET5.getText().toString() + viewBinding.otpET6.getText().toString());
        viewModel.VerifyOtp(new MainCalback<String>() {
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
            public void doSuccess(String mgs) {
                navigateLogin();
            }
        }, request);
    }
    public void navigateLogin() {
        this.finish();
        Intent it = new Intent(this, LoginActivity.class);
        startActivity(it);
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_verify_otp;
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
