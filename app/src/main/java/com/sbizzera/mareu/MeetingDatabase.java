package com.sbizzera.mareu;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Creates by Boris SBIZZERA on 28/08/2019.
 */

@Database(entities = Meeting.class,version = 1)
public abstract class MeetingDatabase extends RoomDatabase {

    private static MeetingDatabase instance;

    public abstract MeetingDAO meetingDAO();

    public static synchronized MeetingDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),MeetingDatabase.class,"meeting_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
