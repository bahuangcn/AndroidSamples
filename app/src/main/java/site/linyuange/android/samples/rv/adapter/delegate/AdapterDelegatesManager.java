package site.linyuange.android.samples.rv.adapter.delegate;

import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Author: BaHuang
 * Date: 2018/7/23 17:12
 */
public class AdapterDelegatesManager<T> {

    protected SparseArrayCompat<AdapterDelegate<T>> mDelegateArray = new SparseArrayCompat<>();
    protected AdapterDelegate fallbackDelegate;


    public AdapterDelegatesManager<T> addDelegate(@NonNull AdapterDelegate<T> delegate) {
        if (mDelegateArray.get(delegate.getViewType()) == null) {
            mDelegateArray.append(delegate.getViewType(), delegate);
        }
        return this;
    }

    public int getItemViewType(@NonNull T items, int position) {
        int count = mDelegateArray.size();
        for (int index = 0; index < count; index++) {
            AdapterDelegate<T> delegate = mDelegateArray.valueAt(index);
            if (delegate.isForViewType(items, position)) {
                return mDelegateArray.keyAt(index);
            }
        }
        throw new NullPointerException(
                "No AdapterDelegate added that matches position=" + position + " in data source");
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterDelegate<T> delegate = mDelegateArray.get(viewType);
        return delegate.onCreateViewHolder(parent);
    }

    public void onBindViewHolder(@NonNull T items, RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        AdapterDelegate<T> delegate = mDelegateArray.get(holder.getItemViewType());
        delegate.onBindViewHolder(items, holder, position, payloads);
    }

}
