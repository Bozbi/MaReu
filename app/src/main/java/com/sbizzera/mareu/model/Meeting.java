package com.sbizzera.mareu.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.threeten.bp.LocalDateTime;

/**
 * Creates by Boris SBIZZERA on 02/09/2019.
 */
@Entity(tableName = "meetings_table")
public class Meeting {

    @PrimaryKey(autoGenerate = true)
    private int mId;
    @ColumnInfo(name = "title")
    private String mTitle;
    @ColumnInfo(name = "particpant")
    private String mParticipants;
    @Ignore
    private LocalDateTime mMeetingStart;
    @Ignore
    private int mMeetingDurationInMins;
    @ColumnInfo(name = "roomNb")
    private int mRoomNumber;

    public Meeting(String title, int roomNumber, String participants) {
        mTitle = title;
        mParticipants = participants;
        mRoomNumber = roomNumber;
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

    public int getRoomNumber() {
        return mRoomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        mRoomNumber = roomNumber;
    }
}
