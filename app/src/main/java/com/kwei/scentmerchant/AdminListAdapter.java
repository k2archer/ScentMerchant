package com.kwei.scentmerchant;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kwei.scentmerchant.bean.SetAdminEvent;

import java.util.List;

import de.greenrobot.event.EventBus;

class AdminListAdapter extends RecyclerView.Adapter<AdminListAdapter.Holder> {

    private Context context;
    private List<AdminItem> itemList;

    public AdminListAdapter(Context context, List<AdminItem> list) {
        this.context = context;
        this.itemList = list;
    }

    class Holder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvPhone;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_admin_name);
            tvPhone = itemView.findViewById(R.id.tv_admin_phone);
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.admin_item, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder viewHolder, int i) {
        viewHolder.tvName.setText(itemList.get(i).name);
        viewHolder.tvPhone.setText(itemList.get(i).phone);

        viewHolder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                String name = itemList.get(i).name;
                builder.setTitle("设置管理员")
                        .setMessage("是否把 " + name + " 设置为超级管理员?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // todo ...
                                EventBus.getDefault().post(new SetAdminEvent(name));
                            }
                        })
                        .setNegativeButton("取消", null)
                        .setCancelable(false);

                builder.create().show();
            }
        });
        viewHolder.tvName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                String name = itemList.get(i).name;
                builder.setTitle("删除管理员")
                        .setMessage("是否确定删除该 " + name + " 管理员?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // todo ...
                                removeItem(i);
                            }
                        })
                        .setNegativeButton("取消", null)
                        .setCancelable(false);

                builder.create().show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void addItem(AdminItem admin) {
        itemList.add(admin);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        itemList.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

}
