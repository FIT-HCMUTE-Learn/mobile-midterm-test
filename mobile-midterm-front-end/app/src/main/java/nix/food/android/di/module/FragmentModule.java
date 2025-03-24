package nix.food.android.di.module;

import android.content.Context;

import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;

import nix.food.android.MVVMApplication;
import nix.food.android.ViewModelProviderFactory;
import nix.food.android.data.Repository;
import nix.food.android.di.scope.FragmentScope;
import nix.food.android.ui.base.fragment.BaseFragment;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import nix.food.android.ui.main.home.HomeViewModel;
import nix.food.android.ui.main.profile.ProfileViewModel;

@Module
public class FragmentModule {

    private BaseFragment<?, ?> fragment;

    public FragmentModule(BaseFragment<?, ?> fragment) {
        this.fragment = fragment;
    }

    @Named("access_token")
    @Provides
    @FragmentScope
    String provideToken(Repository repository) {
        return repository.getToken();
    }

    @Provides
    @FragmentScope
    HomeViewModel provideHomeViewModel(Repository repository, Context application) {
        Supplier<HomeViewModel> supplier = () -> new HomeViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<HomeViewModel> factory = new ViewModelProviderFactory<>(HomeViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(HomeViewModel.class);
    }

    @Provides
    @FragmentScope
    ProfileViewModel provideProfileViewModel(Repository repository, Context application) {
        Supplier<ProfileViewModel> supplier = () -> new ProfileViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<ProfileViewModel> factory = new ViewModelProviderFactory<>(ProfileViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(ProfileViewModel.class);
    }
}
