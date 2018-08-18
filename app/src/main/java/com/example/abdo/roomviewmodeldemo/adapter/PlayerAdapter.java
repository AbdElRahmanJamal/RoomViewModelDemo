package com.example.abdo.roomviewmodeldemo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abdo.roomviewmodeldemo.R;
import com.example.abdo.roomviewmodeldemo.room.PlayerDAO;
import com.example.abdo.roomviewmodeldemo.room.PlayerEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.DataViewHolder> {

    private List<PlayerEntity> playerEntityList;
    private PlayerDAO appDatabase;

    public PlayerAdapter(PlayerDAO appDatabase) {
        this.playerEntityList = new ArrayList<>();
        this.appDatabase = appDatabase;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.player_card, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        holder.playerName.setText(playerEntityList.get(position).getPlayerName());
        holder.playerTeam.setText(playerEntityList.get(position).getPlayerTeam());

    }

    public void setDataArrayList(List<PlayerEntity> arrayList) {
        if (playerEntityList == null) {
            return;
        }
        playerEntityList.clear();
        playerEntityList.addAll(arrayList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return playerEntityList.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.player_name)
        TextView playerName;
        @BindView(R.id.player_team)
        TextView playerTeam;

        public DataViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    appDatabase.deletePlayerByID(playerEntityList.get(getAdapterPosition()).getPlayerID());
                    playerEntityList.remove(getAdapterPosition());
                    notifyDataSetChanged();

                }
            });

        }
    }

}