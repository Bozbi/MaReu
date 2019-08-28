package com.sbizzera.mareu;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


import org.threeten.bp.LocalDateTime;

import java.util.List;



/**
 * Creates by Boris SBIZZERA on 26/08/2019.
 */

@Entity(tableName = "meeting_table")
public class Meeting {

    ;

    @PrimaryKey ( autoGenerate = true)
    private int mId;

    private LocalDateTime mBeginsAt;
    private LocalDateTime mEndsAt;

    @ColumnInfo(name = "room_number")
    private int mRoomNumber;
    private String mTitle;
    private List<String> mParticipants;
    private String mType;

    @ColumnInfo(name = "meeting_date")
    private LocalDateTime mMeetingDate;

    public Meeting(LocalDateTime beginsAt, LocalDateTime endsAt, int roomNumber, String title, List<String> participants, String type) {
        mBeginsAt = beginsAt;
        mEndsAt = endsAt;
        mRoomNumber = roomNumber;
        mTitle = title;
        mParticipants = participants;
        mType = type;

        //TODO mMeetingDate = Truc a faire avec mBegins;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public LocalDateTime getBeginsAt() {
        return mBeginsAt;
    }

    public LocalDateTime getEndsAt() {
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

    public LocalDateTime getMeetingDate() {
        return mMeetingDate;
    }

    public void setMeetingDate(LocalDateTime meetingDate) {
        mMeetingDate = meetingDate;
    }
}
