package com.sbizzera.mareu;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.threeten.bp.LocalDateTime;

import java.util.List;

/**
 * Creates by Boris SBIZZERA on 28/08/2019.
 */
public class ListMeetingsViewModel extends AndroidViewModel {

    private MeetingRepository mMeetingRepository;
    private LiveData<List<Meeting>> mAllMeetings;



    public ListMeetingsViewModel(@NonNull Application application) {
        super(application);
        mMeetingRepository = new MeetingRepository(application);
        mAllMeetings = mMeetingRepository.getAllMeetings();
    }


    public void insertMeeting(Meeting meeting) {
        mMeetingRepository.insert(meeting);
    }

    public void deleteMeeting (Meeting meeting){
        mMeetingRepository.delete(meeting);
    }

    public void updateMeeting (Meeting meeting){
        mMeetingRepository.update(meeting);
    }

    public LiveData<List<Meeting>> getAllMeetings(){
        return mAllMeetings;
    }

//    public LiveData<List<Meeting>> getAllMeetingsForASpecificDate (LocalDateTime date){
//
//        return mMeetingRepository.getAllMeetingsAtDate(date);
//    }
//
//    public LiveData<List<Meeting>> getAllMeetingsForASpecificRoom (int roomNumber){
//
//        return mMeetingRepository.getAllMeetingsInRoomNb(roomNumber);
//    }
}
