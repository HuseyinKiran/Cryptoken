package com.huseyinkiran.cryptoretrofitjava.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.huseyinkiran.cryptoretrofitjava.Model.Link;
import com.huseyinkiran.cryptoretrofitjava.R;

import java.util.List;

public class LinksAdapter extends RecyclerView.Adapter<LinksAdapter.LinkViewHolder> {

    private List<Link> linkList;
    private Context context;

    public LinksAdapter(List<Link> linkList, Context context) {
        this.linkList = linkList;
        this.context = context;
    }

    @NonNull
    @Override
    public LinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_link, parent, false);
        return new LinkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LinkViewHolder holder, int position) {
        Link link = linkList.get(position);
        holder.txtLink.setText(link.getName());

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float widthInInches = displayMetrics.widthPixels / displayMetrics.xdpi;
        float heightInInches = displayMetrics.heightPixels / displayMetrics.ydpi;
        double screenInches = Math.sqrt(Math.pow(widthInInches, 2) + Math.pow(heightInInches, 2));

        if (screenInches >= 9.0) {
            if (link.getName().contains("Facebook")) {
                holder.linkImgBtn.setImageResource(R.drawable.ic_facebook_72);
            } else if (link.getName().contains("YouTube")) {
                holder.linkImgBtn.setImageResource(R.drawable.ic_youtube_72);
            } else if (link.getName().contains("Reddit")) {
                holder.linkImgBtn.setImageResource(R.drawable.ic_reddit_72);
            } else if (link.getName().contains("GitHub")) {
                holder.linkImgBtn.setImageResource(R.drawable.ic_github_72);
            } else if (link.getName().contains("Website")) {
                holder.linkImgBtn.setImageResource(R.drawable.ic_website_72);
            } else {
                holder.linkImgBtn.setImageResource(R.drawable.ic_explorer_72);
            }
        } else {
            if (link.getName().contains("Facebook")) {
                holder.linkImgBtn.setImageResource(R.drawable.ic_facebook);
            } else if (link.getName().contains("YouTube")) {
                holder.linkImgBtn.setImageResource(R.drawable.ic_youtube);
            } else if (link.getName().contains("Reddit")) {
                holder.linkImgBtn.setImageResource(R.drawable.ic_reddit);
            } else if (link.getName().contains("GitHub")) {
                holder.linkImgBtn.setImageResource(R.drawable.ic_github);
            } else if (link.getName().contains("Website")) {
                holder.linkImgBtn.setImageResource(R.drawable.ic_website);
            } else {
                holder.linkImgBtn.setImageResource(R.drawable.ic_explorer);
            }
        }

        holder.linkImgBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link.getUrl()));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return linkList.size();
    }

    public static class LinkViewHolder extends RecyclerView.ViewHolder {
        private TextView txtLink;
        private ImageButton linkImgBtn;

        public LinkViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLink = itemView.findViewById(R.id.txtLink);
            linkImgBtn = itemView.findViewById(R.id.linkImgBtn);
        }
    }
}
