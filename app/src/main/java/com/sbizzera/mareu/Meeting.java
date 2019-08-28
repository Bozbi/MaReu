package com.sbizzera.mareu;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.joda.time.DateTime;

import java.util.List;
import java.util.Set;


/**
 * Creates by Boris SBIZZERA on 26/08/2019.
 */

@Entity(tableName = "meeting_table")
public class Meeting {

    public static DateTime now = new DateTime();

    @PrimaryKey ( autoGenerate = true)
    private int mId;

    private DateTime mBeginsAt;
    private DateTime mEndsAt;

    @ColumnInfo(name = "room_number")
    private int mRoomNumber;
    private String mTitle;
    private List<String> mParticipants;
    private String mType;

    @ColumnInfo(name = "meeting_date")
    private DateTime mMeetingDate;

    public Meeting(DateTime beginsAt, DateTime endsAt, int roomNumber, String title, List<String> participants, String type) {
        mBeginsAt = beginsAt;
        mEndsAt = endsAt;
        mRoomNumber = roomNumber;
        mTitle = title;
        mParticipants = participants;
        mType = type;

        //TODO mMeetingDate = Truc a faire avec mBegins;
    }

    public void setId(int id) {
        mId = id;
    }

    public DateTime getBeginsAt() {
        return mBeginsAt;
    }

    public DateTime getEndsAt() {
        return mEndsAt;
    }

    public int getRoomNumber() {
        return mRoomNumber;
    }

    public String getTitle() {
        return mTitle;
    }

    public List<String> getParticipants() {
        return mParticipants;
    }

    public String getType() {
        return mType;
    }




}
