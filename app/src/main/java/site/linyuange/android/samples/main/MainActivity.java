package site.linyuange.android.samples.main;

import android.databinding.DataBindingUtil;

import site.linyuange.android.samples.R;
import site.linyuange.android.samples.base.BaseActivity;
import site.linyuange.android.samples.databinding.ActivityMainBinding;
import site.linyuange.android.samples.rv.RecyclerViewActivity;

public class MainActivity extends BaseActivity implements Navigation {

    private ActivityMainBinding mBinding;

    @Override
    protected void initView() {
        mBinding = DataBindingUtil.setContentView(this, getLayoutRes());
        mBinding.setNavigator(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void navToRvPage() {
        RecyclerViewActivity.startInstance(this);
    }
}
