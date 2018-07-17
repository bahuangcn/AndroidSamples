package site.linyuange.android.samples.rv.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

import site.linyuange.android.samples.R;

/**
 * Author: BaHuang
 * Date: 2018/7/13 17:00
 */
public class SelectItemAdapter extends RecyclerView.Adapter<SelectItemAdapter.VH> {

    private static final int ITEM_COUNT = 20;
    private Set<Integer> mSelectedItemSet = new HashSet<>();

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_select_item, parent, false);
        VH vh = new VH(itemView);
        // you can set click listener here
        vh.itemView.setOnClickListener(view -> {
            Log.d("BaHuang -- ", "onCreateViewHolder: Item Clicked.");
        });
        vh.mCbSelect.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Log.d("BaHuang -- ", "onCreateViewHolder: isChecked: " + isChecked);
            if (isChecked) {
                mSelectedItemSet.add(vh.getAdapterPosition());
            } else {
                mSelectedItemSet.remove(vh.getAdapterPosition());
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        int ivResId = R.drawable.ic_baseline_filter;
        // REMEMBER: position from 0
        switch (position % 3) {
            case 0:
                ivResId = R.drawable.ic_baseline_filter_1;
                break;
            case 1:
                ivResId = R.drawable.ic_baseline_filter_2;
                break;
            case 2:
                ivResId = R.drawable.ic_baseline_filter_3;
                break;
            default:
                // impossible
                break;
        }

        // you can set click listener here

        holder.mIvSign.setImageResource(ivResId);
        holder.mTvPosition.setText(String.valueOf(holder.getAdapterPosition()));
        holder.mCbSelect.setChecked(mSelectedItemSet.contains(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return ITEM_COUNT;
    }

    static class VH extends RecyclerView.ViewHolder {
        ImageView mIvSign;
        CheckBox mCbSelect;
        TextView mTvPosition;

        VH(View itemView) {
            super(itemView);
            mIvSign = itemView.findViewById(R.id.iv_sign);
            mCbSelect = itemView.findViewById(R.id.cb_select);
            mTvPosition = itemView.findViewById(R.id.tv_position);
        }
    }
}
