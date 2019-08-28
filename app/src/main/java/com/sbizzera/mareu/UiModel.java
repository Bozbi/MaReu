package com.sbizzera.mareu;

/**
 * Creates by Boris SBIZZERA on 28/08/2019.
 */
public class UiModel {

    private final String mMeetingName;
    private final String mMeetingDate;
    private final String mMeetingRoom;
    private final String mMeetingParticipantsEMails;

    public UiModel(String meetingName, String meetingDate, String meetingRoom, String meetingParticipantsEMails) {
        mMeetingName = meetingName;
        mMeetingDate = meetingDate;
        mMeetingRoom = meetingRoom;
        mMeetingParticipantsEMails = meetingParticipantsEMails;
    }

    public String getMeetingName() {
        return mMeetingName;
    }

    public String getMeetingDate() {
        return mMeetingDate;
    }

    public String getMeetingRoom() {
        return mMeetingRoom;
    }

    public String getMeetingParticipantsEMails() {
        return mMeetingParticipantsEMails;
    }
}
