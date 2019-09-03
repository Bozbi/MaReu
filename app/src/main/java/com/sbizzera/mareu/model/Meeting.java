package com.sbizzera.mareu.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Creates by Boris SBIZZERA on 02/09/2019.
 */
@Entity(tableName = "meetings_table")
public class Meeting {

    @PrimaryKey
    private int mId;
    @ColumnInfo(name = "title")
    private String mTitle;
    @ColumnInfo(name = "particpant")
    private String mParticipants;

    public Meeting(String title, String participants){
        mTitle = title;
        mParticipants = participants;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }


    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getParticipants() {
        return mParticipants;
    }

    public void setParticipants(String participants) {
        mParticipants = participants;
    }
}
