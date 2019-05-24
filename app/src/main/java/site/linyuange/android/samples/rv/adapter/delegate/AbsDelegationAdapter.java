package site.linyuange.android.samples.rv.adapter.delegate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Author: BaHuang
 * Date: 2018/7/23 17:25
 */
public abstract class AbsDelegationAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private AdapterDelegatesManager<T> mManager;
    private T items;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return mManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        mManager.onBindViewHolder(items, holder, position, null);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        mManager.onBindViewHolder(items, holder, position, payloads);
    }

    @Override
    public int getItemViewType(int position) {
        return mManager.getItemViewType(items, position);
    }

    public T getItems() {
        return items;
    }

    public void setItems(T items) {
        this.items = items;
    }
}
