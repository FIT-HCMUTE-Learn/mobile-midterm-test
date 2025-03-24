package nix.food.android.ui.main.home;
// TRANG KIM LOI - 22110371
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.databinding.BR;
import nix.food.android.R;
import nix.food.android.data.model.api.response.category.CategoryResponse;
import nix.food.android.data.model.api.response.category.ProductResponse;
import nix.food.android.databinding.FragmentHomeBinding;
import nix.food.android.di.component.FragmentComponent;
import nix.food.android.ui.base.fragment.BaseFragment;
import nix.food.android.ui.main.home.adapter.CategoryAdapter;
import nix.food.android.ui.main.home.adapter.ProductAdapter;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> implements View.OnClickListener, CategoryAdapter.OnCategoryClickListener {

    public static List<CategoryResponse> CATEGORIES_LIST;
    public static List<ProductResponse> Products_LIST;
    public static String USER_NAME;

    private CategoryAdapter categoryAdapter;
    private ProductAdapter productAdapter;
    private int page = 1;
    private boolean isLoading = false;

    @Override
    protected void performDataBinding() {
        binding.setF(this);
        binding.setVm(viewModel);
    }


    private void getData() {
        viewModel.categories = CATEGORIES_LIST;
        viewModel.products = Products_LIST;
        viewModel.USER_NAME = USER_NAME;
    }


    public void setupAdapter() {
        getData();
        binding.nameUser.setText(USER_NAME);
        if (viewModel.categories != null && !viewModel.categories.isEmpty()) {
            categoryAdapter = new CategoryAdapter(getContext(), viewModel.categories, this);
            RecyclerView.LayoutManager categoryLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            binding.rvCate.setLayoutManager(categoryLayoutManager);
            binding.rvCate.setAdapter(categoryAdapter);
        } else {
            Toast.makeText(getContext(), "No Data!", Toast.LENGTH_SHORT).show();
        }

        if (viewModel.products != null && !viewModel.products.isEmpty()) {
            productAdapter = new ProductAdapter(getContext(), viewModel.getProductByCate(viewModel.categories.get(0).getId().toString()));
            RecyclerView.LayoutManager productLayoutManager = new GridLayoutManager(getContext(), 2);
            binding.rvProduct.setLayoutManager(productLayoutManager);
            binding.rvProduct.setAdapter(productAdapter);
            binding.titleProduct.setText(viewModel.categories.get(0).getName());

        }
    }

    @Override
    public void onCategoryClick(String idCate) {
        loadProductsByCategory(idCate);
    }

    private void loadProductsByCategory(String idCate) {
        productAdapter.setData(viewModel.getProductByCate(idCate));
        CategoryResponse selectedCategory = viewModel.categories.stream()
                .filter(c -> c.getId().toString().equals(idCate))
                .findFirst()
                .orElse(null);

        if (selectedCategory != null) {
            binding.titleProduct.setText(selectedCategory.getName());
        }
    }

    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    public void onClick(View view) {
    }
}



