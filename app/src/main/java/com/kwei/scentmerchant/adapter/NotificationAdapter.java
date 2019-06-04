package com.kwei.scentmerchant.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kwei.scentmerchant.R;
import com.kwei.scentmerchant.bean.NotificationItem;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<Holder> {

    private static final int ITEM_LOAD_MORE = Integer.MAX_VALUE - 2;

    private Context context;
    private List<NotificationItem> itemList;
    private View loadMoreView;
    private int loadMoreLayoutResId;

    public NotificationAdapter(Context context, List<NotificationItem> list) {
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
        View view = inflater.inflate(R.layout.notification_item, viewGroup, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), itemList.get(viewType).title, Toast.LENGTH_SHORT).show();
            }
        });
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
        NotificationItem item = (NotificationItem) t;
        holder.setText(R.id.tv_notification_date, item.date);
        holder.setText(R.id.tv_notification_title, item.title);
        holder.setText(R.id.tv_notification_message, item.message);
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
