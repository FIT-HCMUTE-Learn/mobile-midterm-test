package nix.food.android.ui.main.login;

import nix.food.android.MVVMApplication;
import nix.food.android.data.Repository;
import nix.food.android.ui.base.activity.BaseViewModel;

public class LoginViewModel extends BaseViewModel {
    public LoginViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }
}
