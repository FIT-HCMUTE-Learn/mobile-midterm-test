package nix.food.android.ui.main.home;

import java.util.List;

import nix.food.android.MVVMApplication;
import nix.food.android.data.Repository;
import nix.food.android.data.model.api.response.category.CategoryResponse;
import nix.food.android.ui.base.fragment.BaseFragmentViewModel;

public class HomeViewModel extends BaseFragmentViewModel {
    List<CategoryResponse> categories;
    public HomeViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }
}
