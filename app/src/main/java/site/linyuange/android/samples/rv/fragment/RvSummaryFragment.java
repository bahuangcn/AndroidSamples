package site.linyuange.android.samples.rv.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import site.linyuange.android.samples.R;
import site.linyuange.android.samples.base.BaseFragment;
import site.linyuange.android.samples.databinding.FragmentRvSummaryBinding;
import site.linyuange.android.samples.rv.RvNavigation;

/**
 * Author: BaHuang
 * Date: 2018/7/4 14:25
 */
public class RvSummaryFragment extends BaseFragment {

    private RvNavigation mNavigation;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RvNavigation) {
            mNavigation = (RvNavigation) context;
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_rv_summary;
    }

    @Override
    protected boolean enableDataBinding() {
        return true;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        FragmentRvSummaryBinding binding = (FragmentRvSummaryBinding) getBinding();
        binding.setNavigation(mNavigation);
    }
}
