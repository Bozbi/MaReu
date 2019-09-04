package com.sbizzera.mareu.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.sbizzera.mareu.model.Meeting;
import com.sbizzera.mareu.repository.MeetingRepository;

import org.threeten.bp.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates by Boris SBIZZERA on 03/09/2019.
 */
public class ListMeetingsViewModel extends AndroidViewModel {

    private MeetingRepository mMeetingRepository;
    private LiveData<List<Meeting>> mAllMeetings;
    private MutableLiveData<Integer> mRoomFilter = new MutableLiveData<>();
    private MediatorLiveData<List<Meeting>> mFilteredMeetings = new MediatorLiveData<>();

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

        mFilteredMeetings.addSource(mRoomFilter, new Observer<Integer>() {
            @Override
            public void onChanged(Integer roomNb) {
                mFilteredMeetings.setValue(combineMeetingsAndRoomFilter(mAllMeetings.getValue(), roomNb));
            }
        });
    }

    private List<Meeting> combineMeetingsAndRoomFilter(List<Meeting> meetings, Integer roomNb) {
        List<Meeting> result = new ArrayList<>();
        if (meetings != null ) {
            if (roomNb == null){
                return meetings;
            }
            for (Meeting meeting : meetings) {
                if (meeting.getRoomNumber() == roomNb) {
                    result.add(meeting);
                }
            }
        }
        return result;
    }

    public LiveData<List<Meeting>> getMeetings() {
        return mFilteredMeetings;
    }

    public void insertMeeting(Meeting meeting) {
        mMeetingRepository.insertMeeting(meeting);
    }

    public void deleteMeeting(Meeting meeting) {
        mMeetingRepository.deleteMeeting(meeting);
    }

    public void filterByDate(LocalDateTime date) {

    }

    public void filterByRoom(int roomNb) {
        mRoomFilter.setValue(roomNb);
    }


}
