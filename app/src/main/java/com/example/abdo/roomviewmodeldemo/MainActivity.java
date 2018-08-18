package com.example.abdo.roomviewmodeldemo;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.abdo.roomviewmodeldemo.adapter.PlayerAdapter;
import com.example.abdo.roomviewmodeldemo.room.AppDatabase;
import com.example.abdo.roomviewmodeldemo.room.PlayerDAO;
import com.example.abdo.roomviewmodeldemo.room.PlayerEntity;
import com.example.abdo.roomviewmodeldemo.viewmodel.PlayerInfoViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    /*
     *    to use room database there are 4 steps
     *       1- add room dependency to module.app
     *       2- create class entity that hold data that represent table data
     *       3- create interface DAO that represent query for entity
     *       4- create class that extend RoomDatabase to create Room Database object and use it
     * */
    @BindView(R.id.player_rec_view)
    RecyclerView recyclerView;
    @BindView(R.id.empty_state)
    LinearLayout emptyStateLayout;
    private PlayerAdapter playerAdapter;
    private PlayerDAO appDatabase;
    private PlayerInfoViewModel playerInfoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        appDatabase = AppDatabase.getAppDatabaseBuilderInstance(this).playerDAO();
        playerAdapter = new PlayerAdapter(appDatabase);
        setLayoutVisibility(View.VISIBLE, View.GONE);
        playerInfoViewModel = ViewModelProviders.of(this).get(PlayerInfoViewModel.class);
        if (playerInfoViewModel.getPlayerEntityList() != null) {
            updateRecView(playerInfoViewModel.getPlayerEntityList());
        }
    }

    public void getAllPlayers(View view) {
        List<PlayerEntity> playerEntityList = appDatabase.getAllPlayers();
        if (playerEntityList.size() > 0) {
            updateRecView(playerEntityList);
        } else {
            setLayoutVisibility(View.VISIBLE, View.GONE);
        }
    }

    public void deleteAllPlayers(View view) {
        appDatabase.deleteAllPlayers();
        setLayoutVisibility(View.VISIBLE, View.GONE);
    }

    public void insertDialog(View view) {
        customDialog();
    }

    private void setLayoutVisibility(int emptyState, int nonEmptyState) {
        emptyStateLayout.setVisibility(emptyState);
        recyclerView.setVisibility(nonEmptyState);
    }

    private void customDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.insert_screen);
        dialog.setTitle("Insert Player Info ...");
        final EditText playerName = (EditText) dialog.findViewById(R.id.et_player_name);
        final EditText playerTeam = (EditText) dialog.findViewById(R.id.et_player_team);
        Button insertPlayer = (Button) dialog.findViewById(R.id.bu_insert);

        insertPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playerNameString = playerName.getText().toString();
                String playerTeamString = playerTeam.getText().toString();
                if (playerNameString.isEmpty() || playerTeamString.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter All Fields", Toast.LENGTH_LONG).show();
                    return;
                }
                appDatabase.insertPlayer(new PlayerEntity(playerNameString, playerTeamString));
                updateRecView(appDatabase.getAllPlayers());
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void updateRecView(List<PlayerEntity> playerEntityList) {
        setLayoutVisibility(View.GONE, View.VISIBLE);
        playerInfoViewModel.setPlayerEntityList(playerEntityList);
        playerAdapter.setDataArrayList(playerEntityList);
        recyclerView.setAdapter(playerAdapter);
        playerAdapter.notifyDataSetChanged();
    }
}
