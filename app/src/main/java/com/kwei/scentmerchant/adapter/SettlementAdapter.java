package com.kwei.scentmerchant.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kwei.scentmerchant.R;
import com.kwei.scentmerchant.bean.SettlementItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettlementAdapter extends RecyclerView.Adapter<SettlementAdapter.Holder> {

    private Context context;
    private List<SettlementItem> itemList;

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_settlement_date)
        TextView tvSettlementDate;
        @BindView(R.id.tv_settlement_amount)
        TextView tvSettlementAmount;
        @BindView(R.id.tv_settlement_message)
        TextView tvSettlementMessage;

        private Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public SettlementAdapter(Context context, List<SettlementItem> list) {
        this.context = context;
        this.itemList = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.settlement_item, viewGroup, false);
        return new Holder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.tvSettlementDate.setText(itemList.get(i).date);
        holder.tvSettlementAmount.setText(itemList.get(i).amount);
        holder.tvSettlementMessage.setText(itemList.get(i).message);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
