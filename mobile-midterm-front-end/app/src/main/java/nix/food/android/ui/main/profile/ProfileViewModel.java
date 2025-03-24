package nix.food.android.ui.main.profile;

import nix.food.android.MVVMApplication;
import nix.food.android.data.Repository;
import nix.food.android.ui.base.fragment.BaseFragmentViewModel;

public class ProfileViewModel extends BaseFragmentViewModel {
    public ProfileViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }
}