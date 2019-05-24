package site.linyuange.android.samples.rv.adapter.delegate;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import site.linyuange.android.samples.rv.model.Cat;

/**
 * Author: BaHuang
 * Date: 2018/7/23 18:05
 */
public class CatDelegate extends AdapterDelegate<List<Object>> {

    @Override
    protected boolean isForViewType(@NonNull List<Object> items, int position) {
        return items.get(position) instanceof Cat;
    }

    @LayoutRes
    @Override
    protected int getViewType() {
        return 0;
    }

    @NonNull
    @Override
    protected VH onCreateViewHolder(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getViewType(), parent, false);
        return new VH(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull List<Object> items, @NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        Cat cat = (Cat) items.get(position);
        VH vh = (VH) holder;
    }

    static class VH extends RecyclerView.ViewHolder {
        public VH(View itemView) {
            super(itemView);
        }
    }
}
