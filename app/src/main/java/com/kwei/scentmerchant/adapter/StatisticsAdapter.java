package com.kwei.scentmerchant.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kwei.scentmerchant.R;
import com.kwei.scentmerchant.activity.IncomeDetailActivity;
import com.kwei.scentmerchant.bean.StatisticsItem;

import java.util.List;

public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.Holder> {

    private Context context;
    private List<StatisticsItem> itemList;

    class Holder extends RecyclerView.ViewHolder {
        private TextView tvScanType;
        private TextView tvScanDate;
        private TextView tvScanIncome;
        private TextView tvAdvSharingType;

        private Holder(@NonNull View itemView) {
            super(itemView);
            tvScanType = itemView.findViewById(R.id.tv_scan_type);
            tvScanDate = itemView.findViewById(R.id.tv_scan_date);
            tvScanIncome = itemView.findViewById(R.id.tv_scan_income);
            tvAdvSharingType = itemView.findViewById(R.id.tv_adv_sharing_type);
        }
    }

    public StatisticsAdapter(Context context, List<StatisticsItem> list) {
        this.context = context;
        this.itemList = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.statistics_item, viewGroup, false);
        return new StatisticsAdapter.Holder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.tvScanType.setText(itemList.get(i).scanType);
        holder.tvScanDate.setText(itemList.get(i).scanDate);
        holder.tvScanIncome.setText(itemList.get(i).scanIncom);
        holder.tvAdvSharingType.setText(itemList.get(i).advSharingType);

        holder.tvScanIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, IncomeDetailActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
