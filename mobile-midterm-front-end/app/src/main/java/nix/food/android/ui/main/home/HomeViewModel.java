package nix.food.android.ui.main.home;
//TRANG KIM LOI - 22110371 - Le Tan Tru - 22110447
import java.util.ArrayList;
import java.util.List;

import nix.food.android.MVVMApplication;
import nix.food.android.data.Repository;
import nix.food.android.data.model.api.response.category.CategoryResponse;
import nix.food.android.data.model.api.response.category.ProductResponse;
import nix.food.android.ui.base.fragment.BaseFragmentViewModel;

public class HomeViewModel extends BaseFragmentViewModel {
    String USER_NAME;
    List<CategoryResponse> categories = new ArrayList<>();
    List<ProductResponse> products = new ArrayList<>();
    public HomeViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }
    public List<ProductResponse> getProductByCate(String id) {
        List<ProductResponse> productsOfCate = new ArrayList<>();

        for (ProductResponse product : this.products) {
            if (product.getCategory().getId().toString().equals(id)) {
                productsOfCate.add(product);
            }
        }

        return productsOfCate;
    }
}
