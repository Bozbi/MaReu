package com.sbizzera.mareu.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sbizzera.mareu.model.Meeting;
import com.sbizzera.mareu.model.MeetingRoom;
import com.sbizzera.mareu.repository.MeetingRepository;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Creates by Boris SBIZZERA on 09/09/2019.
 */
public class AddMeetingViewModel extends AndroidViewModel {

    private MeetingRepository mMeetingRepository;

    private DateTimeFormatter mDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DateTimeFormatter mTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    private Meeting mCurrenMeeting = new Meeting(null, LocalDateTime.now(),LocalDateTime.now().plusHours(1),null,null);



    public AddMeetingViewModel(@NonNull Application application) {
        super(application);
        mMeetingRepository = new MeetingRepository(application);
    }


    public void saveMeeting(){
        if (mCurrenMeeting.getParticipants()==null){
            mCurrenMeeting.setParticipants(new ArrayList<String>(Arrays.asList("Pas d'inscrits")));
        }
        mMeetingRepository.insertMeeting(mCurrenMeeting);
    }
    public void addTitle(String title){
        mCurrenMeeting.setTitle(title);
    }

    public String[] getAllRoomsList(){
        String[] listRooms = new String[MeetingRoom.values().length];
        int i = 0;
        for (MeetingRoom value : MeetingRoom.values()) {
            listRooms[i] = value.getRoomName();
            i++;
        }
        return  listRooms;
    }

    public String getNowDate(){
        return LocalDateTime.now().format(mDateFormatter);
    }

    public String getNowTime(){
        return LocalDateTime.now().format(mTimeFormatter);
    }

    public String getTimeInOneHour(){
        return LocalDateTime.now().plusHours(1).format(mTimeFormatter);
    }


}
