package com.kwei.scentmerchant.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kwei.scentmerchant.R;
import com.kwei.scentmerchant.activity.IncomeDetailActivity;
import com.kwei.scentmerchant.bean.StatisticsItem;

import java.io.Serializable;
import java.util.List;

public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.Holder> {

    private static final int ITEM_LOAD_MORE = Integer.MAX_VALUE - 2;

    private Context context;
    private List<StatisticsItem> itemList;
    private View loadMoreView;
    private int loadMoreLayoutResId;

    class Holder extends RecyclerView.ViewHolder {

        private Holder(@NonNull View itemView) {
            super(itemView);

            mViews = new SparseArray<View>();
            mConvertView = itemView;
        }

        private SparseArray<View> mViews;
        private View mConvertView;

        public <T extends View> T getView(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = mConvertView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }

        public Holder setText(int viewId, String text) {
            TextView tv = getView(viewId);
            tv.setText(text);
            return this;
        }
    }

    public StatisticsAdapter(Context context, List<StatisticsItem> list) {
        this.context = context;
        this.itemList = list;
    }

    @Override
    public int getItemViewType(int position) {
        boolean isMore = isShowLoadMore(position);
        if (isMore) {
            return ITEM_LOAD_MORE;
        }

        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        if (viewType == ITEM_LOAD_MORE) {
            if (loadMoreView == null) {
                loadMoreView = inflater.inflate(loadMoreLayoutResId, viewGroup, false);
            }
            return new Holder(loadMoreView);
        }
        View view = inflater.inflate(R.layout.statistics_item, viewGroup, false);
        return new Holder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        if (isShowLoadMore(i)) {
            if (mOnLoadMoreListener != null) {
                mOnLoadMoreListener.onLoadMoreRequested();
            }
            return;
        }

        convert(holder, itemList.get(i));
    }

    public <T> void convert(Holder holder, T t) {
        StatisticsItem item = (StatisticsItem) t;
        holder.setText(R.id.tv_scan_type_title, item.scanType);
        holder.setText(R.id.tv_scan_income, "¥ " + item.scanIncome);
        holder.setText(R.id.tv_scan_date_title, item.scanDate);
        holder.setText(R.id.tv_adv_sharing_type, item.advSharingType);

        TextView income = holder.getView(R.id.tv_scan_income);
        income.setTag(item);
        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatisticsItem item = (StatisticsItem)v.getTag();
                Intent intent = new Intent(context, IncomeDetailActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtra("record", item);
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });

    }

    private boolean isShowLoadMore(int i) {
        return i >= itemList.size();
    }

    @Override
    public int getItemCount() {
        return itemList.size() + (hasLoadMore() ? 1 : 0);
    }

    private boolean hasLoadMore() {
        return loadMoreView != null || loadMoreLayoutResId != 0;
    }

    public void setLoadMoreLayout(int layoutResId) {
        this.loadMoreLayoutResId = layoutResId;
    }

    public interface OnLoadMoreListener {
        void onLoadMoreRequested();
    }

    private OnLoadMoreListener mOnLoadMoreListener;

    public void setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        if (loadMoreListener != null) {
            mOnLoadMoreListener = loadMoreListener;
        }
    }
}
