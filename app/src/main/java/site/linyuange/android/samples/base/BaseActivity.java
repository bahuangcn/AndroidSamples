package site.linyuange.android.samples.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Author: BaHuang
 * Date: 2018/7/4 14:27
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static final int ACTION_ADD = 1;
    private static final int ACTION_REPLACE = 2;
    private FragmentManager mManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mManager = getSupportFragmentManager();
        initView();
    }

    protected abstract void initView();

    @LayoutRes
    protected abstract int getLayoutRes();

    @IdRes
    protected int getContainerViewId() {
        return -1;
    }

    public void addFragment(BaseFragment fragment) {
        operateFragmentTransaction(fragment, ACTION_ADD, true, true);
    }

    protected void replaceFragment(BaseFragment fragment) {
        replaceFragment(fragment, true);
    }

    protected void replaceFragment(BaseFragment fragment, boolean addToBackStack) {
        operateFragmentTransaction(fragment, ACTION_REPLACE, true, addToBackStack);
    }

    public void addFragmentNotExecutePending(BaseFragment fragment) {
        operateFragmentTransaction(fragment, ACTION_ADD, false, true);
    }

    private void operateFragmentTransaction(BaseFragment fragment, int action, boolean executePending, boolean addToBackStack) {
        final int containerId = getContainerViewId();
        if (containerId <= 0) {
            throw new RuntimeException("Container View Id is Invalid.");
        }

        FragmentTransaction transaction = mManager.beginTransaction();

        if (ACTION_ADD == action) {
            transaction.add(containerId, fragment, fragment.getTransactionTag());
        } else if (ACTION_REPLACE == action) {
            transaction.replace(containerId, fragment, fragment.getTransactionTag());
        }

        if (addToBackStack) {
            transaction.addToBackStack(fragment.getTransactionTag());
        }
        transaction.commitAllowingStateLoss();
        if (executePending) {
            try {
                mManager.executePendingTransactions();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        final int count = mManager.getBackStackEntryCount();
        if (count > 1) {
            BaseFragment fragment = (BaseFragment) mManager.findFragmentById(getContainerViewId());
            if (fragment != null) {
                mManager.popBackStackImmediate();
            }
        } else {
            onFinishedActivity();
        }
    }

    protected void onFinishedActivity() {
        finish();
    }
}
