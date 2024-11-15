package com.huseyinkiran.cryptoretrofitjava.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.huseyinkiran.cryptoretrofitjava.Model.CryptoModel;
import com.huseyinkiran.cryptoretrofitjava.R;
import com.huseyinkiran.cryptoretrofitjava.View.CoinDetailActivity;

import java.util.List;

public class CryptoAdapter extends RecyclerView.Adapter<CryptoAdapter.RowHolder> {

    private List<CryptoModel> cryptoList;

    public CryptoAdapter(List<CryptoModel> cryptoList) {
        this.cryptoList = cryptoList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setFilteredList(List<CryptoModel> filteredList) {
        this.cryptoList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CryptoAdapter.RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cell_crypto, parent, false);
        return new RowHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CryptoAdapter.RowHolder holder, int position) {

        holder.txtName.setText(cryptoList.get(position).getName().trim() + " (" + cryptoList.get(position).getSymbol().trim() + ")");
        holder.txtRank.setText(cryptoList.get(position).getRank() + ".");

        if (cryptoList.get(position).isActive()) {
            holder.txtIsActive.setText("Active");
            holder.txtIsActive.setTextColor(Color.GREEN);
        } else {
            holder.txtIsActive.setText("Inactive");
            holder.txtIsActive.setTextColor(Color.RED);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), CoinDetailActivity.class);
            intent.putExtra("coinId", cryptoList.get(position).getId());
            v.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return cryptoList.size();
    }

    public static class RowHolder extends RecyclerView.ViewHolder {

        private TextView txtName, txtRank, txtIsActive;

        public RowHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtRank = itemView.findViewById(R.id.txtRank);
            txtIsActive = itemView.findViewById(R.id.txtIsActive);
        }

    }

}
