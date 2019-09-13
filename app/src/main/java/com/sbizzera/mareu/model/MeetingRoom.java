package com.sbizzera.mareu.model;

import androidx.annotation.ColorRes;

import com.sbizzera.mareu.R;

/**
 * Creates by Boris SBIZZERA on 09/09/2019.
 */
public enum MeetingRoom {


    PEACH("Peach", R.color.mat1),
    BOWSER("Bowser", R.color.mat2),
    MARIO("Mario", R.color.mat3),
    LUIGI("Luigi", R.color.mat4),
    TOAD("Toad", R.color.mat5),
    LAKITU("Lakitu", R.color.mat6),
    KOOPA("Koopa", R.color.mat7),
    GOOMER("Goomer", R.color.mat8),
    BLOOPS("Bloops", R.color.mat9),
    PIRANHA("Piranha", R.color.mat10);

    private String mRoomName;
    @ColorRes
    private int mColor;


    MeetingRoom(String roomName, @ColorRes int color) {
        mRoomName = roomName;
        mColor = color;
    }

    public String getRoomName() {
        return mRoomName;
    }

    @ColorRes
    public int getColor() {
        return mColor;
    }

}
