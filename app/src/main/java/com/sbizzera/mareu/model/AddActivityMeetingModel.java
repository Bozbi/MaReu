package com.sbizzera.mareu.model;

import java.io.Serializable;

/**
 * Creates by Boris SBIZZERA on 13/09/2019.
 */
public class AddActivityMeetingModel implements Serializable {
    private String mTitle;
    private String mStartDate;
    private String mStartHour;
    private String mStopDate;
    private String mStopHour;
    private String mRoom;
    private String mParticipants;

    public AddActivityMeetingModel(String title, String startDate, String startHour, String stopDate, String stopHour, String room, String participants) {
        mTitle = title;
        mStartDate = startDate;
        mStartHour = startHour;
        mStopDate = stopDate;
        mStopHour = stopHour;
        mRoom = room;
        mParticipants = participants;
    }

    public AddActivityMeetingModel() {
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getStartDate() {
        return mStartDate;
    }

    public void setStartDate(String startDate) {
        mStartDate = startDate;
    }

    public String getStartHour() {
        return mStartHour;
    }

    public void setStartHour(String startHour) {
        mStartHour = startHour;
    }

    public String getStopDate() {
        return mStopDate;
    }

    public void setStopDate(String stopDate) {
        mStopDate = stopDate;
    }

    public String getStopHour() {
        return mStopHour;
    }

    public void setStopHour(String stopHour) {
        mStopHour = stopHour;
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
