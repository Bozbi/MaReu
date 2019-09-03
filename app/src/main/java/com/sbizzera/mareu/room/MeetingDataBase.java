package com.sbizzera.mareu.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.sbizzera.mareu.model.Meeting;

/**
 * Creates by Boris SBIZZERA on 02/09/2019.
 */
@Database(entities = Meeting.class,version = 1)
public abstract class MeetingDataBase extends RoomDatabase {

    public static MeetingDataBase instance;

    public abstract MeetingDao meetingDao();

    public static synchronized MeetingDataBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),MeetingDataBase.class,"meeting_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
