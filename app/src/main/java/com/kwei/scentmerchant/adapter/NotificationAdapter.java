package com.kwei.scentmerchant.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kwei.scentmerchant.R;
import com.kwei.scentmerchant.bean.NotificationItem;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.Holder> {

    private Context context;
    private List<NotificationItem> itemList;

    class Holder extends RecyclerView.ViewHolder {
        private TextView tvNotificationDate;
        private TextView tvNotificationTitle;
        private TextView tvNotificationMessage;

        private Holder(@NonNull View itemView) {
            super(itemView);
            tvNotificationDate = itemView.findViewById(R.id.tv_notification_date);
            tvNotificationTitle = itemView.findViewById(R.id.tv_notification_title);
            tvNotificationMessage = itemView.findViewById(R.id.tv_notification_message);
        }
    }

    public NotificationAdapter(Context context, List<NotificationItem> list) {
        this.context = context;
        this.itemList = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.notification_item, viewGroup, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), itemList.get(i).title, Toast.LENGTH_SHORT).show();
            }
        });
        return new NotificationAdapter.Holder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.tvNotificationDate.setText(itemList.get(i).date);
        holder.tvNotificationTitle.setText(itemList.get(i).title);
        holder.tvNotificationMessage.setText(itemList.get(i).message);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
