package com.sbizzera.mareu;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Creates by Boris SBIZZERA on 26/08/2019.
 */
public class Meeting {

    private LocalDateTime mMeetingStartHourAndDate;
    private LocalDateTime mMeetingEndHourAndDate;
    private MeetingRoom mMeetingRoom;
    private String mMeetingSubject;
    private List<String> mMeetingParticipantsList;
    private MeetingType mMeetingType;

}
