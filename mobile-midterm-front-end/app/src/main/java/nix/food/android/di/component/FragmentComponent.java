package nix.food.android.di.component;


import nix.food.android.di.module.FragmentModule;
import nix.food.android.di.scope.FragmentScope;

import dagger.Component;
import nix.food.android.ui.main.home.HomeFragment;
import nix.food.android.ui.main.profile.ProfileFragment;

@FragmentScope
@Component(modules = {FragmentModule.class},dependencies = AppComponent.class)
public interface FragmentComponent {
    void inject(HomeFragment fragment);
    void inject(ProfileFragment fragment);
}
