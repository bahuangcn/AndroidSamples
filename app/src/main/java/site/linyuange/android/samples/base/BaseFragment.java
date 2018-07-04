package site.linyuange.android.samples.base;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutRes(), container, false);
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    public String getTransactionTag() {
        return getClass().getName();
    }
}
