package com.example.abdo.roomviewmodeldemo.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "players")
public class PlayerEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "player_iD")
    private int playerID;
    @ColumnInfo(name = "player_name") //@ColumnInfo use to set name of column in table if i not use
    //column name will be the same of variable name
    private String playerName;
    @ColumnInfo(name = "player_team")
    private String playerTeam;

    public PlayerEntity(String playerName, String playerTeam) {
        this.playerName = playerName;
        this.playerTeam = playerTeam;
    }

    @NonNull
    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(@NonNull int playerID) {
        this.playerID = playerID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerTeam() {
        return playerTeam;
    }

    public void setPlayerTeam(String playerTeam) {
        this.playerTeam = playerTeam;
    }
}
