package nix.food.android.ui.main.home;

import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import eu.davidea.flexibleadapter.databinding.BR;
import nix.food.android.R;
import nix.food.android.data.model.api.response.category.CategoryResponse;
import nix.food.android.databinding.FragmentHomeBinding;
import nix.food.android.di.component.FragmentComponent;
import nix.food.android.ui.base.fragment.BaseFragment;
import nix.food.android.ui.main.home.adapter.CategoryAdapter;

public class HomeFragment extends BaseFragment<FragmentHomeBinding,HomeViewModel> implements
        View.OnClickListener{
    public static List<CategoryResponse> CATEGORIES_LIST;
    private CategoryAdapter categoryAdapter;
    @Override
    protected void performDataBinding() {
        binding.setF(this);
        binding.setVm(viewModel);
    }


    private void getData(){
        viewModel.categories = CATEGORIES_LIST;
    }
    public void setupAdapter() {
        getData();
        if (viewModel.categories != null && !viewModel.categories.isEmpty()) {
            categoryAdapter = new CategoryAdapter(getContext(), viewModel.categories);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            binding.rvCate.setLayoutManager(layoutManager);
            binding.rvCate.setAdapter(categoryAdapter);
            viewModel.hideLoading();
        } else {
            Toast.makeText(getContext(), "Không có dữ liệu", Toast.LENGTH_SHORT).show();
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
