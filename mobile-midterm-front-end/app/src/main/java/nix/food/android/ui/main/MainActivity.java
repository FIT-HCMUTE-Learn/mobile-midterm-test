package nix.food.android.ui.main;

import static android.provider.Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import java.util.List;

import nix.food.android.BR;
import nix.food.android.BuildConfig;
import nix.food.android.R;
import nix.food.android.data.model.api.response.category.CategoryResponse;
import nix.food.android.databinding.ActivityMainBinding;
import nix.food.android.di.component.ActivityComponent;
import nix.food.android.ui.base.activity.BaseActivity;
import nix.food.android.ui.main.home.HomeFragment;


public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
    private Fragment active;
    private FragmentManager fm;
    private static final String HOME = "HOME";
    private HomeFragment homeFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding.setA(this);
        viewBinding.setVm(viewModel);
        getAllCategories();

        fm = getSupportFragmentManager();
        homeFragment = new HomeFragment();
        active = homeFragment;
        fm.beginTransaction().add(R.id.hot_fragment, homeFragment, HOME).commitNow();


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }
    public void getAllCategories() {
        viewModel.getAllCategories(new MainCalback<List<CategoryResponse>>() {
            @Override
            public void doError(Throwable error) {
                viewModel.hideLoading();
            }

            @Override
            public void doSuccess() {
            }

            @Override
            public void doFail() {
            }

            @Override
            public void doSuccess(List<CategoryResponse> objects) {
                HomeFragment.CATEGORIES_LIST = objects;
                if (homeFragment != null && homeFragment.isAdded()) {
                    homeFragment.setupAdapter();
                    viewModel.hideLoading();
                }
            }
        });
    }

}

