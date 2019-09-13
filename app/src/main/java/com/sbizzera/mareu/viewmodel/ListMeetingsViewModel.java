package com.sbizzera.mareu.viewmodel;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.room.util.StringUtil;

import com.sbizzera.mareu.model.Meeting;
import com.sbizzera.mareu.model.MeetingRoom;
import com.sbizzera.mareu.repository.MeetingRepository;
import com.sbizzera.mareu.view.MeetingsUiModel;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Creates by Boris SBIZZERA on 03/09/2019.
 */
public class ListMeetingsViewModel extends AndroidViewModel {

    private MeetingRepository mMeetingRepository;
    private LiveData<List<Meeting>> mAllMeetings;
    private MutableLiveData<MeetingRoom> mRoomFilter = new MutableLiveData<>();
    private MediatorLiveData<List<Meeting>> mFilteredMeetings = new MediatorLiveData<>();
    private LiveData<List<MeetingsUiModel>> mListMeetingsUiModel;

    public ListMeetingsViewModel(@NonNull Application application) {
        super(application);
        mMeetingRepository = new MeetingRepository(application);
        mAllMeetings = mMeetingRepository.getAllMeetings();

        mFilteredMeetings.addSource(mAllMeetings, new Observer<List<Meeting>>() {
            @Override
            public void onChanged(List<Meeting> meetings) {
                mFilteredMeetings.setValue(combineMeetingsAndRoomFilter(meetings, mRoomFilter.getValue()));
            }
        });

        mFilteredMeetings.addSource(mRoomFilter, new Observer<MeetingRoom>() {
            @Override
            public void onChanged(MeetingRoom roomName) {
                mFilteredMeetings.setValue(combineMeetingsAndRoomFilter(mAllMeetings.getValue(), roomName));
            }
        });

        mListMeetingsUiModel = Transformations.map(mFilteredMeetings, new Function<List<Meeting>, List<MeetingsUiModel>>() {
            @Override
            public List<MeetingsUiModel> apply(List<Meeting> input) {
                List<MeetingsUiModel> result = new ArrayList<>();
                for (Meeting meeting : input) {

                    String meetingTitle;
                    String meetingDateAndRoom;
                    String participant;
                    int color;


                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'Le' dd/MM à hh:mm ");
                    String meetingDateStart = meeting.getMeetingStart().format(formatter);
                    String room = meeting.getRoom().getRoomName();


                    meetingTitle = meeting.getTitle();
                    color = meeting.getRoom().getColor();
                    meetingDateAndRoom = meetingDateStart+" - Salle "+ room ;
                    participant = TextUtils.join(", " ,meeting.getParticipants());


                    MeetingsUiModel model = new MeetingsUiModel(meeting.getId(),meetingTitle,meetingDateAndRoom, color, participant);
                    result.add(model);
                }
                return result;
            }
        });
    }

    private List<Meeting> combineMeetingsAndRoomFilter(List<Meeting> meetings, MeetingRoom room) {
        List<Meeting> result = new ArrayList<>();
        if (meetings != null) {
            if (room == null) {
                return meetings;
            }
            for (Meeting meeting : meetings) {
                if (meeting.getRoom() == room) {
                    result.add(meeting);
                }
            }
        }
        return result;
    }

    public LiveData<List<MeetingsUiModel>> getMeetings() {
        return mListMeetingsUiModel;
    }

    public void insertMeeting(Meeting meeting) {
        mMeetingRepository.insertMeeting(meeting);
    }

    public void deleteMeeting(MeetingsUiModel meeting) {
        mMeetingRepository.deleteMeeting(meeting.getId());
    }

    public void filterByDate(LocalDateTime date) {

    }

    public void filterByRoom(MeetingRoom roomName) {
        mRoomFilter.setValue(roomName);
    }


}
