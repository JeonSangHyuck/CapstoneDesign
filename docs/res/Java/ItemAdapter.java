package com.cookandroid.test0427;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{
    private Context mCtx;
    private List<item> items= null;
    private ArrayList<item> arrayList;

    public ItemAdapter(Context context, List<item> items) {
        this.mCtx=context;
        this.items=items;
        arrayList = new ArrayList<item>();
        arrayList.addAll(items);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.week_item,null);

        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final item item=items.get(position);
        holder.tv_data.setText(item.getData());
    }


    @Override
    public int getItemCount() {
        return this.items.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_data;
        public ViewHolder(View itemView) {
            super(itemView);

            tv_data = (TextView) itemView.findViewById(R.id.tv_data);
        }
    }
}
