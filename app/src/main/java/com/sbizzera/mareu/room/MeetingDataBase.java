package com.sbizzera.mareu.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.sbizzera.mareu.model.Meeting;
import com.sbizzera.mareu.model.utils.Converters;

/**
 * Creates by Boris SBIZZERA on 02/09/2019.
 */
@Database(entities = Meeting.class,version = 3,exportSchema = false)
@TypeConverters({Converters.class})
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
