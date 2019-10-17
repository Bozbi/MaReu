package com.sbizzera.mareu.viewmodel;

import android.content.Intent;
import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sbizzera.mareu.model.AddMeetingUiModel;
import com.sbizzera.mareu.model.Meeting;
import com.sbizzera.mareu.model.MeetingRoom;
import com.sbizzera.mareu.repository.MeetingRepository;
import com.sbizzera.mareu.view.ListMeetingsActivity;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.Arrays;
import java.util.List;


/**
 * Creates by Boris SBIZZERA on 09/09/2019.
 */

public class AddMeetingViewModel extends ViewModel {

    private MeetingRepository mMeetingRepository;

    public DateTimeFormatter mDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public DateTimeFormatter mTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    private MutableLiveData<String> mAlertMessage = new MutableLiveData<>();

    private MutableLiveData<ViewAction> mViewAction = new MutableLiveData<>();

    public LiveData<ViewAction> getViewAction() {
        return mViewAction;
    }

    public AddMeetingViewModel(MeetingRepository meetingRepository) {
        mMeetingRepository = meetingRepository;
    }

    public String[] getAllRoomsList() {
        return MeetingRoom.getRoomsList();
    }

    public LiveData<String> getAlertMessage() {
        return mAlertMessage;
    }

    public void insertOrUpdateMeeting(String title, String startDate, String startHour, String stopDate, String stopHour, String participants, String room, Intent intent) {
        if (areAllFieldsCompleted(title, participants, room)) {
            Meeting meeting = newMeetingFromStrings(title, startDate, startHour, stopDate, stopHour, participants, room);
            if (intent.hasExtra(ListMeetingsActivity.MEETING_EXTRA)) {
                meeting.setId(((Meeting) intent.getSerializableExtra(ListMeetingsActivity.MEETING_EXTRA)).getId());
            }
            if (isMeetingHoursCoherent(meeting)) {
                if (isMeetingRoomAvailable(meeting)) {
                    if (intent.getAction().equals(ListMeetingsActivity.INTENT_ACTION_ADD_NOTE)) {
                        mMeetingRepository.insertMeeting(meeting);
                    }
                    if (intent.getAction().equals(ListMeetingsActivity.INTENT_ACTION_UPDATE_NOTE)) {

                        mMeetingRepository.updateMeeting(meeting);

                    }
                    mViewAction.setValue(ViewAction.FINISH);
                } else {
                    mAlertMessage.setValue("La salle " + meeting.getRoom().getRoomName() + " est déjà prise à ces horaires");
                    mViewAction.setValue(null);
                }
            } else {
                mAlertMessage.setValue("La réunion que vous prévoyez n'a pas des horaires cohérents !");
                mViewAction.setValue(null);
            }
        } else {
            mAlertMessage.setValue("Tous les champs doivent être remplis pour pouvoir enregistrer la réunion !");
            mViewAction.setValue(null);
        }
    }

    private boolean areAllFieldsCompleted(String s1, String s2, String s3) {
        if (s1.isEmpty() || s2.isEmpty() || s3.isEmpty()) {
            return false;
        }
        return true;
    }

    private Meeting newMeetingFromStrings(String title, String startDate, String startHour, String stopDate, String stopHour, String participants, String room) {
        LocalDateTime meetingStart = LocalDateTime.of(LocalDate.parse(startDate, mDateFormatter), LocalTime.parse(startHour, mTimeFormatter));
        LocalDateTime meetingStop = LocalDateTime.of(LocalDate.parse(stopDate, mDateFormatter), LocalTime.parse(stopHour, mTimeFormatter));
        List<String> participantList = Arrays.asList(TextUtils.split(participants, ", "));
        MeetingRoom meetingRoom = MeetingRoom.fromName(room);
        return new Meeting(title, meetingStart, meetingStop, meetingRoom, participantList);
    }

    public boolean isMeetingHoursCoherent(Meeting meeting) {
        return meeting.getMeetingStart().isBefore(meeting.getMeetingStop()) && meeting.getMeetingStart().isAfter(LocalDateTime.now().minusMinutes(15));
    }

    public boolean isMeetingRoomAvailable(Meeting meeting) {
        List<Meeting> meetingList = mMeetingRepository.getAllMeetingsSync();
        for (Meeting meetingInList : meetingList) {
            if (meetingInList.getId() != meeting.getId()) { //same Meeting so don't go further
                if (isInTheSameRoom(meeting, meetingInList)) { //if room different don't go further
                    if (areMeetingsHoursCrossings(meeting, meetingInList)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean areMeetingsHoursCrossings(Meeting firstMeeting, Meeting secondMeeting) {
        LocalDateTime firstMeetingStart = firstMeeting.getMeetingStart();
        LocalDateTime firstMeetingStop = firstMeeting.getMeetingStop();
        LocalDateTime secondMeetingStart = secondMeeting.getMeetingStart();
        LocalDateTime secondMeetingStop = secondMeeting.getMeetingStop();

        if (firstMeetingStop.isBefore(secondMeetingStart) || firstMeetingStop.isEqual(secondMeetingStart)) {
            return false;
        }
        if (firstMeetingStart.isAfter(secondMeetingStop) || firstMeetingStart.isEqual(secondMeetingStop)) {
            return false;
        }
        return true;
    }

    private boolean isInTheSameRoom(Meeting firstMeeting, Meeting secondMeeting) {
        return firstMeeting.getRoom() == secondMeeting.getRoom();
    }

    public AddMeetingUiModel getAddMeetingUiModelfromMeeting(Meeting meeting) {
        return new AddMeetingUiModel(
                meeting.getTitle(),
                meeting.getMeetingStart().format(mDateFormatter),
                meeting.getMeetingStart().format(mTimeFormatter),
                meeting.getMeetingStop().format(mDateFormatter),
                meeting.getMeetingStop().format(mTimeFormatter),
                meeting.getRoom().getRoomName(),
                TextUtils.join(", ", meeting.getParticipants()));
    }

}
