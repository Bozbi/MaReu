package com.sbizzera.mareu.model;

import com.sbizzera.mareu.repository.MeetingRepository;

import java.io.Serializable;

/**
 * Creates by Boris SBIZZERA on 13/09/2019.
 */
public class AddMeetingUiModel implements Serializable {

    private String mTitle;
    private String mStartDate;
    private String mStartHour;
    private String mStopDate;
    private String mStopHour;
    private String mRoom;
    private String mParticipants;



    public AddMeetingUiModel(String title, String startDate, String startHour, String stopDate, String stopHour, String room, String participants) {
        mTitle = title;
        mStartDate = startDate;
        mStartHour = startHour;
        mStopDate = stopDate;
        mStopHour = stopHour;
        mRoom = room;
        mParticipants = participants;
    }


    public String getTitle() {
        return mTitle;
    }

    public String getStartDate() {
        return mStartDate;
    }

    public String getStartHour() {
        return mStartHour;
    }

    public String getStopDate() {
        return mStopDate;
    }

    public String getStopHour() {
        return mStopHour;
    }

    public String getRoom() {
        return mRoom;
    }

    public void setRoom(String room) {
        mRoom = room;
    }

    public String getParticipants() {
        return mParticipants;
    }

    public void setParticipants(String participants) {
        mParticipants = participants;
    }
}
