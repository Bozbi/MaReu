package com.sbizzera.mareu.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sbizzera.mareu.model.Meeting;
import com.sbizzera.mareu.repository.MeetingRepository;
import com.sbizzera.mareu.room.MeetingDao;
import com.sbizzera.mareu.room.MeetingDataBase;

import java.util.List;

/**
 * Creates by Boris SBIZZERA on 03/09/2019.
 */
public class ListMeetingsViewModel extends AndroidViewModel {

    private MeetingRepository mMeetingRepository;
    private LiveData<List<Meeting>> mAllMeetings;

    public ListMeetingsViewModel(@NonNull Application application) {
        super(application);
        mMeetingRepository = new MeetingRepository(application);
        mAllMeetings = mMeetingRepository.getAllMeetings();
    }

    public LiveData<List<Meeting>> getAllMeetings(){
        return mAllMeetings;
    }

    public void insertMeeting(Meeting meeting){
        mMeetingRepository.insertMeeting(meeting);
    }
}
