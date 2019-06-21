package com.example.mypc.truyenoffline.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mypc.truyenoffline.R;
import com.example.mypc.truyenoffline.activity.ContentStoryActivity;
import com.example.mypc.truyenoffline.entity.Story;

import java.util.ArrayList;
import java.util.List;

public class FavoriteListAdapter extends RecyclerView.Adapter<FavoriteListAdapter.ViewHolder>{
    private Context mContext;
    private List<Story> mStoryArrayList = new ArrayList<>();

    public FavoriteListAdapter(Context mContext, List<Story> mStoryArrayList) {
        this.mContext = mContext;
        this.mStoryArrayList = mStoryArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.row_list_story, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(rootView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Story story = mStoryArrayList.get(position);
        viewHolder.textViewTitle.setText(story.getTitle());
        viewHolder.textViewIndex.setText("Truyện " + (position + 1));
    }

    @Override
    public int getItemCount() {
        return mStoryArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle;
        TextView textViewIndex;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.tvTitle);
            textViewIndex = itemView.findViewById(R.id.tvIndex);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Story story = mStoryArrayList.get(getAdapterPosition());
                    Intent intent = new Intent(mContext, ContentStoryActivity.class);
                    intent.putParcelableArrayListExtra("stores", (ArrayList<? extends Parcelable>) mStoryArrayList);
                    intent.putExtra("content_story", story);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
