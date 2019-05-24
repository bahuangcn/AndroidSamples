package site.linyuange.android.samples.rv.adapter.delegate;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Author: BaHuang
 * Date: 2018/7/23 17:13
 */
public abstract class AdapterDelegate<T> {

    protected abstract boolean isForViewType(@NonNull T items, int position);

    @LayoutRes
    protected abstract int getViewType();

    @NonNull
    protected abstract RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent);

    protected abstract void onBindViewHolder(@NonNull T items, @NonNull RecyclerView.ViewHolder holder, int position,
                                             @NonNull List<Object> payloads);
}
