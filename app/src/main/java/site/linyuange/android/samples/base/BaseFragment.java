package site.linyuange.android.samples.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: BaHuang
 * Date: 2018/7/4 14:22
 */
public abstract class BaseFragment extends Fragment {

    private ViewDataBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view;
        if (enableDataBinding()) {
            view = createViewByDataBinding(inflater, container, savedInstanceState);
        } else {
            view = inflater.inflate(getLayoutRes(), container, false);
        }
        view.setBackgroundColor(Color.WHITE);
        return view;
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    protected boolean enableDataBinding() {
        return false;
    }

    protected View createViewByDataBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        return mBinding.getRoot();
    }

    @NonNull
    protected ViewDataBinding getBinding() {
        if (mBinding == null) {
            throw new RuntimeException("Not create view by DataBinding.");
        }
        return mBinding;
    }

    public String getTransactionTag() {
        return getClass().getName();
    }
}
