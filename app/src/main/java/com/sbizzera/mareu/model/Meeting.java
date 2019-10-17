package com.sbizzera.mareu.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.threeten.bp.LocalDateTime;

import java.io.Serializable;
import java.util.List;

/**
 * Creates by Boris SBIZZERA on 02/09/2019.
 */
@Entity(tableName = "meetings_table")
public class Meeting implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int mId;
    @ColumnInfo(name = "title")
    private String mTitle;
    @ColumnInfo(name = "particpant")
    private List<String> mParticipants;
    @ColumnInfo(name = "meeting_start")
    private LocalDateTime mMeetingStart;
    @ColumnInfo(name = "meeting_stop")
    private LocalDateTime mMeetingStop;
    @ColumnInfo(name = "roomName")
    private MeetingRoom mRoom;

    public Meeting(String title, LocalDateTime meetingStart, LocalDateTime meetingStop, MeetingRoom room, List<String> participants) {
        mTitle = title;
        mParticipants = participants;
        mRoom = room;
        mMeetingStart = meetingStart;
        mMeetingStop = meetingStop;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public List<String> getParticipants() {
        return mParticipants;
    }

    public LocalDateTime getMeetingStart() {
        return mMeetingStart;
    }

    public void setMeetingStart(LocalDateTime meetingStart) {
        mMeetingStart = meetingStart;
    }

    public LocalDateTime getMeetingStop() {
        return mMeetingStop;
    }

    public void setMeetingStop(LocalDateTime meetingStop) {
        mMeetingStop = meetingStop;
    }

    public MeetingRoom getRoom() {
        return mRoom;
    }

    public void setRoom(MeetingRoom room) {
        mRoom = room;
    }

}
