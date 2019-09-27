package com.sbizzera.mareu.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.sbizzera.mareu.model.Meeting;

import java.util.List;

/**
 * Creates by Boris SBIZZERA on 02/09/2019.
 */
@Dao
public interface MeetingDao {

    @Insert
    void insertMeeting(Meeting meeting);

    @Update
    void updateMeeting(Meeting meeting);

    @Query(value = "DELETE fROM meetings_table WHERE mId = :meetingId")
    void deleteMeeting(int meetingId);

    @Query(value = "Select * from meetings_table")
    LiveData<List<Meeting>> getAllMeetings();

    @Query(value = "Select * from meetings_table")
    List<Meeting> getAllMeetingsSync();

}
