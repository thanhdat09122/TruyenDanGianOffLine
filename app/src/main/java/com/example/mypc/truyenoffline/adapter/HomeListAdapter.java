package com.example.mypc.truyenoffline.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mypc.truyenoffline.R;
import com.example.mypc.truyenoffline.activity.ListStoryActivity;
import com.example.mypc.truyenoffline.entity.Total;

import java.util.ArrayList;
import java.util.List;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {

    Context mContext;
    List<Total> mTotals = new ArrayList<>();

    public HomeListAdapter(Context context, List<Total> totals) {
        mContext = context;
        mTotals = totals;
    }
    @NonNull
    @Override
    public HomeListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_list_home, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeListAdapter.ViewHolder viewHolder, int position) {
        Total total = mTotals.get(position);
        viewHolder.textViewTitle.setText(total.getTitle());
    }

    @Override
    public int getItemCount() {
        return mTotals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.tvTitle);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Total total = mTotals.get(getAdapterPosition());
                    Intent intent = new Intent(mContext, ListStoryActivity.class);
                    intent.putExtra("list_store", total);
                    mContext.startActivity(intent);
                    Toast.makeText(mContext, total.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
