package site.linyuange.android.samples.rv.adapter.delegate;

import java.util.List;

/**
 * Author: BaHuang
 * Date: 2018/7/23 17:33
 */
public class ListDelegationAdapter<T extends List<?>> extends AbsDelegationAdapter<T> {
    @Override
    public int getItemCount() {
        return getItems() == null ? 0 : getItems().size();
    }

}