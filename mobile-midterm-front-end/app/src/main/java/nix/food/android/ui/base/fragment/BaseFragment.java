package nix.food.android.ui.base.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import nix.food.android.MVVMApplication;
import nix.food.android.R;
import nix.food.android.di.component.DaggerFragmentComponent;
import nix.food.android.di.component.FragmentComponent;
import nix.food.android.di.module.FragmentModule;
import nix.food.android.utils.DialogUtils;

import javax.inject.Inject;
import javax.inject.Named;


public abstract class BaseFragment <B extends ViewDataBinding,V extends BaseFragmentViewModel> extends Fragment {

    protected B binding;
    @Inject
    protected V viewModel;

    private Dialog progressDialog;


    @Named("access_token")
    @Inject
    protected String token;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        performDependencyInjection(getBuildComponent());
        binding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false);
        binding.setVariable(getBindingVariable(),viewModel);
        performDataBinding();
        viewModel.setToken(token);
        viewModel.mErrorMessage.observe(getViewLifecycleOwner(),toastMessage -> {
            if (toastMessage!=null){
                toastMessage.showMessage(requireContext());
            }
        });
        viewModel.mIsLoading.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback(){

            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if(((ObservableBoolean)sender).get()){
                    showProgressbar(getResources().getString(R.string.msg_loading));
                }else{
                    hideProgress();
                }
            }
        });
        setupHideKeyboardOnTouch(binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void hideKeyboard() {
        View view = getActivity().getCurrentFocus(); // Lấy focus hiện tại từ Activity
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupHideKeyboardOnTouch(View rootView) {
        rootView.setOnTouchListener((v, event) -> {
            hideKeyboard();
            return false;
        });
    }
    public abstract int getBindingVariable();

    protected abstract int getLayoutId();

    protected abstract void performDataBinding();

    protected abstract void performDependencyInjection(FragmentComponent buildComponent);

    private FragmentComponent getBuildComponent(){
        return DaggerFragmentComponent.builder()
                .appComponent(((MVVMApplication) requireActivity().getApplication()).getAppComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
    }

    public void showProgressbar(String msg){
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        progressDialog = DialogUtils.createDialogLoading(requireContext(), msg);
        progressDialog.show();
    }

    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

}
