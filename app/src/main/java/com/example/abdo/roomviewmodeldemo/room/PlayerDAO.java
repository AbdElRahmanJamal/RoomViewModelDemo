package com.example.abdo.roomviewmodeldemo.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PlayerDAO {
    @Query("select * from players")
    List<PlayerEntity> getAllPlayers();

    @Query("delete  from players")
    void deleteAllPlayers();

    @Insert
    void insertPlayer(PlayerEntity player);

    @Query("delete  from players where player_iD  = :playerID")
    void deletePlayerByID(int playerID);

    @Query("select * from players where player_iD  = :playerID")
    List<PlayerEntity> getPlayerByID(int playerID);

    @Update
    void updateCurrentPlayerInfo(PlayerEntity player);
}
