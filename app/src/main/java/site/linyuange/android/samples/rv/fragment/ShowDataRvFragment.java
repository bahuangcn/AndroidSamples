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
import site.linyuange.android.samples.rv.adapter.ShowDataAdapter;

/**
 * Author: BaHuang
 * Date: 2018/7/4 15:39
 */
public class ShowDataRvFragment extends BaseFragment {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_rv_layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView rvShowData = view.findViewById(R.id.recycler_view);
        rvShowData.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
        rvShowData.setHasFixedSize(true);
        rvShowData.setAdapter(new ShowDataAdapter());
    }
}
