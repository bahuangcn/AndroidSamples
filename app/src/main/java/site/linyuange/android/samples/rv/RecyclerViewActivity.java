package site.linyuange.android.samples.rv;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;

import site.linyuange.android.samples.R;
import site.linyuange.android.samples.base.BaseActivity;
import site.linyuange.android.samples.rv.fragment.RvSummaryFragment;
import site.linyuange.android.samples.rv.fragment.SelectItemFragment;
import site.linyuange.android.samples.rv.fragment.ShowDataRvFragment;

/**
 * Author: BaHuang
 * Date: 2018/7/4 11:08
 */
public class RecyclerViewActivity extends BaseActivity implements RvNavigation {

    public static void startInstance(Activity activity) {
        Intent intent = new Intent(activity, RecyclerViewActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void initView() {
        DataBindingUtil.setContentView(this, getLayoutRes());
        addFragment(new RvSummaryFragment());
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_recycler_view;
    }

    @Override
    protected int getContainerViewId() {
        return R.id.container;
    }

    @Override
    public void navToShowDataPage() {
        addFragment(new ShowDataRvFragment());
    }

    @Override
    public void navToSelectItemPage() {
        addFragment(new SelectItemFragment());
    }
}
