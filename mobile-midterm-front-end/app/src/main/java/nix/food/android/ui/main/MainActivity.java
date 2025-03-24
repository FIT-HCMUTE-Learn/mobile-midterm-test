package nix.food.android.ui.main;
// TRANG KIM LOI - 22110371
import static android.provider.Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.List;

import nix.food.android.BR;
import nix.food.android.R;
import nix.food.android.data.model.api.response.category.CategoryResponse;
import nix.food.android.data.model.api.response.category.ProductResponse;
import nix.food.android.databinding.ActivityMainBinding;
import nix.food.android.di.component.ActivityComponent;
import nix.food.android.ui.base.activity.BaseActivity;
import nix.food.android.ui.main.home.HomeFragment;
import nix.food.android.ui.main.login.LoginActivity;
import nix.food.android.ui.main.profile.ProfileFragment;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
    private Fragment active;
    private FragmentManager fm;
    private static final String HOME = "HOME";
    private HomeFragment homeFragment;
    private ProfileFragment profileFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding.setA(this);
        viewBinding.setVm(viewModel);
        fm = getSupportFragmentManager();

        homeFragment = new HomeFragment();
        active = homeFragment;

        fm.beginTransaction().add(R.id.hot_fragment, homeFragment, HOME).commitNow();
        getAllCategories();


        viewBinding.home.setOnClickListener(v -> switchFragment("home"));
        viewBinding.profile.setOnClickListener(v -> switchFragment("profile"));
        viewBinding.support.setOnClickListener(v -> switchFragment("support"));
        viewBinding.setting.setOnClickListener(v -> switchFragment("setting"));
    }

    private void switchFragment(String fragmentName) {
        Fragment fragment = null;
        switch (fragmentName) {
            case "home":
                fragment = homeFragment;
                getAllCategories();
                break;
            case "profile":
                if (profileFragment == null) {
                    profileFragment = new ProfileFragment();
                }
                fragment = profileFragment;
                break;
            default:
                viewModel.showDevelopmentMessage();
        }

        if (fragment != null) {
            fm.beginTransaction().replace(R.id.hot_fragment, fragment).commit();
            active = fragment;
        }
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
                HomeFragment.USER_NAME = viewModel.getRepository().getSharedPreferences().getUserName();
                HomeFragment.CATEGORIES_LIST = objects;
                viewModel.hideLoading();
                viewModel.getAllProducts(new MainCalback<List<ProductResponse>>() {
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
                    public void doSuccess(List<ProductResponse> objects) {
                        if (homeFragment != null && homeFragment.isAdded()) {
                            HomeFragment.Products_LIST = objects;
                            homeFragment.setupAdapter();
                        }
                    }
                });
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (homeFragment != null && homeFragment.isAdded()) {
            homeFragment.setupAdapter();
        }
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

    public void signOut() {
        viewModel.showLoading();
        Intent it = new Intent(this, LoginActivity.class);
        startActivity(it);
        finish();
    }

    private void requestFilePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                Intent intent = new Intent(ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                startActivityForResult(intent, 100);  // 100 là mã yêu cầu tùy chọn
            }
        }
    }
}
