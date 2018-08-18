package com.example.abdo.roomviewmodeldemo.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.abdo.roomviewmodeldemo.room.PlayerEntity;

import java.util.List;
//to save view state when screen is rotated
public class PlayerInfoViewModel extends ViewModel {
    List<PlayerEntity> playerEntityList;

    public List<PlayerEntity> getPlayerEntityList() {
        return playerEntityList;
    }

    public void setPlayerEntityList(List<PlayerEntity> playerEntityList) {
        this.playerEntityList = playerEntityList;
    }
}
