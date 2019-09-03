package com.sbizzera.mareu.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.sbizzera.mareu.model.Meeting;

import java.util.List;

/**
 * Creates by Boris SBIZZERA on 02/09/2019.
 */
@Dao
public interface MeetingDao {

    @Insert
    void insertMeeting(Meeting meeting);

    @Query(value = "Select * from meetings_table")
    LiveData<List<Meeting>> getAllMeetings();

}
