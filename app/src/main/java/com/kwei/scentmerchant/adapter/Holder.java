package com.kwei.scentmerchant.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

class Holder extends RecyclerView.ViewHolder {

    Holder(@NonNull View itemView) {
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
