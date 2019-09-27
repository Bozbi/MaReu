package com.sbizzera.mareu.model;

import androidx.annotation.ColorRes;

import java.io.Serializable;

/**
 * Creates by Boris SBIZZERA on 11/09/2019.
 */
public class ListMeetingsUiModel implements Serializable {

    private  int mId;
    private String mListMeetingsTitle;
    private String mMeetingDateAndRoom;
    private int mListMeetingsColor;
    private String mListMeetingsParticipants;

    public ListMeetingsUiModel(int id, String listMeetingsTitle, String meetingDate, @ColorRes int listMeetingsColor, String listMeetingsParticipants) {
        mListMeetingsTitle = listMeetingsTitle;
        mListMeetingsColor = listMeetingsColor;
        mMeetingDateAndRoom = meetingDate;
        mListMeetingsParticipants = listMeetingsParticipants;
        mId = id;
    }


    public int getId() {
        return mId;
    }

    public String getListMeetingsTitle() {
        return mListMeetingsTitle;
    }

    public void setListMeetingsTitle(String listMeetingsTitle) {
        mListMeetingsTitle = listMeetingsTitle;
    }

    public int getListMeetingsColor() {
        return mListMeetingsColor;
    }

    public void setListMeetingsColor(int listMeetingsColor) {
        mListMeetingsColor = listMeetingsColor;
    }

    public String getListMeetingsParticipants() {
        return mListMeetingsParticipants;
    }

    public void setListMeetingsParticipants(String listMeetingsParticipants) {
        mListMeetingsParticipants = listMeetingsParticipants;
    }

    public String getMeetingDateAndRoom() {
        return mMeetingDateAndRoom;
    }

    public void setMeetingDateAndRoom(String meetingDate) {
        mMeetingDateAndRoom = meetingDate;
    }
}
