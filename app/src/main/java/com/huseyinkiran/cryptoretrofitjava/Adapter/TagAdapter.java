package com.huseyinkiran.cryptoretrofitjava.Adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.huseyinkiran.cryptoretrofitjava.Model.Tag;
import com.huseyinkiran.cryptoretrofitjava.R;

import java.util.List;

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.TagViewHolder> {

    private List<Tag> tagList;

    public TagAdapter(List<Tag> tagList) {
        this.tagList = tagList;
    }

    @NonNull
    @Override
    public TagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_tag, parent, false);
        return new TagViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TagViewHolder holder, int position) {

        Tag tag = tagList.get(position);

        holder.txtTagName.setText(tag.getName());
        holder.txtTagName.setTextColor(Color.GREEN);

        holder.tagCard.setCardBackgroundColor(Color.TRANSPARENT);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setStroke(3, Color.GREEN);
        drawable.setColor(Color.TRANSPARENT);
        drawable.setCornerRadius(36);
        holder.tagCard.setBackground(drawable);

    }

    @Override
    public int getItemCount() {
        return tagList.size();
    }

    public static class TagViewHolder extends RecyclerView.ViewHolder {

        private CardView tagCard;
        private TextView txtTagName;

        public TagViewHolder(@NonNull View itemView) {
            super(itemView);
            tagCard = itemView.findViewById(R.id.tagCard);
            txtTagName = itemView.findViewById(R.id.txtTag);
        }
    }
}
