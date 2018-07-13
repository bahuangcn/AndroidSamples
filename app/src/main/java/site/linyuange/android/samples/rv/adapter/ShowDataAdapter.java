package site.linyuange.android.samples.rv.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import site.linyuange.android.samples.R;

/**
 * Author: BaHuang
 * Date: 2018/7/4 15:30
 */
public class ShowDataAdapter extends RecyclerView.Adapter<ShowDataAdapter.VH> {

    private static final int ITEM_COUNT = 10;

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_show_data, parent, false);
        return new VH(view);
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
        holder.mIvSign.setImageResource(ivResId);
        holder.mTvPosition.setText(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return ITEM_COUNT;
    }

    static class VH extends RecyclerView.ViewHolder {
        ImageView mIvSign;
        TextView mTvPosition;

        VH(View itemView) {
            super(itemView);
            mIvSign = itemView.findViewById(R.id.iv_sign);
            mTvPosition = itemView.findViewById(R.id.tv_position);
        }
    }
}
