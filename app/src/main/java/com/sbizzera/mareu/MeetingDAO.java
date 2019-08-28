package com.sbizzera.mareu;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import org.joda.time.DateTime;

import java.util.List;
import java.util.Set;

/**
 * Creates by Boris SBIZZERA on 28/08/2019.
 */

@Dao
public interface MeetingDAO {

    @Insert
    void insert(Meeting meeting);

    @Update
    void update(Meeting meeting);

    @Delete
    void delete(Meeting meeting);

    @Query("SELECT * FROM meeting_table ")
    LiveData<List<Meeting>> getAllMeetings();

    @Query("SELECT * FROM meeting_table WHERE room_number = :roomNumber ")
    LiveData<List<Meeting>> getAllMeetingsInRoomNb(int roomNumber);

    @Query("SELECT * FROM meeting_table WHERE meeting_date = :date")
    LiveData<List<Meeting>> getAllMeetingsAtDate(DateTime date);
}
