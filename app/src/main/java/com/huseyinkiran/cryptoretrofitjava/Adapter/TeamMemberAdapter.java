package com.huseyinkiran.cryptoretrofitjava.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.huseyinkiran.cryptoretrofitjava.Model.TeamMember;
import com.huseyinkiran.cryptoretrofitjava.R;

import java.util.List;

public class TeamMemberAdapter extends RecyclerView.Adapter<TeamMemberAdapter.TeamMemberViewHolder> {

    private List<TeamMember> teamMembers;

    public TeamMemberAdapter(List<TeamMember> teamMembers) {
        this.teamMembers = teamMembers;
    }

    @NonNull
    @Override
    public TeamMemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_teammembers, parent, false);
        return new TeamMemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamMemberViewHolder holder, int position) {
        holder.memberName.setText(teamMembers.get(position).getName());
        holder.memberPosition.setText(teamMembers.get(position).getPosition());
    }

    @Override
    public int getItemCount() {
        return teamMembers.size();
    }

    public static class TeamMemberViewHolder extends RecyclerView.ViewHolder {

        private TextView memberName, memberPosition;

        public TeamMemberViewHolder(@NonNull View itemView) {
            super(itemView);
            memberName = itemView.findViewById(R.id.txtMemberName);
            memberPosition = itemView.findViewById(R.id.txtMemberTitle);
        }
    }
}
