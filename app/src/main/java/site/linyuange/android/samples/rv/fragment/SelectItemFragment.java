package site.linyuange.android.samples.rv.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import site.linyuange.android.samples.R;
import site.linyuange.android.samples.base.BaseFragment;
import site.linyuange.android.samples.rv.adapter.SelectItemAdapter;

/**
 * Author: BaHuang
 * Date: 2018/7/13 16:58
 */
public class SelectItemFragment extends BaseFragment {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_rv_sample_layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView rvSelectItem = view.findViewById(R.id.recycler_view);
        rvSelectItem.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
        rvSelectItem.setHasFixedSize(true);
        rvSelectItem.setAdapter(new SelectItemAdapter());
    }
}
