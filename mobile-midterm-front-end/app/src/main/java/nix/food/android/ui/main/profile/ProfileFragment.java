
package nix.food.android.ui.main.profile;

import android.view.View;

import eu.davidea.flexibleadapter.databinding.BR;
import nix.food.android.R;
import nix.food.android.databinding.FragmentProfileBinding;
import nix.food.android.di.component.FragmentComponent;
import nix.food.android.ui.base.fragment.BaseFragment;
import nix.food.android.ui.main.MainActivity;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding, ProfileViewModel> implements View.OnClickListener {
    @Override
    protected void performDataBinding() {
        binding.setF(this);
        binding.setVm(viewModel);
    }
    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    public void onClick(View view) {
    }
    public void onSignUpClick() {
        ((MainActivity) requireActivity()).signOut();
    }
}
