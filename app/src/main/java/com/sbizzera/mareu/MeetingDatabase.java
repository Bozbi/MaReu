package com.sbizzera.mareu;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;

import java.util.List;

/**
 * Creates by Boris SBIZZERA on 28/08/2019.
 */

@Database(entities = Meeting.class,version = 1)
@TypeConverters({ListofStringTypeConverter.class,DateTimeTypeConverter.class})
public abstract class MeetingDatabase extends RoomDatabase {

    private static MeetingDatabase instance;

    public abstract MeetingDAO meetingDAO();

    public static synchronized MeetingDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),MeetingDatabase.class,"meeting_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    public static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private MeetingDAO meetingDao;

        public PopulateDbAsyncTask(MeetingDatabase db) {
            this.meetingDao = db.meetingDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            meetingDao.insert(new Meeting(2019, 9 ,30,15, 15, 2019, 9, 30,17, 15, 1, "Peach", "Compta","boris@gmail.com","celine@gmail.com"));
            meetingDao.insert(new Meeting(2019, 9 ,15,10, 0, 2019, 9, 15,13, 30, 2, "Mario", "Admin","boris@gmail.com","celine@gmail.com"));
            meetingDao.insert(new Meeting(2019, 9 ,17,18, 30, 2019, 9, 17,20, 0, 3, "Luigi", "Dev","boris@gmail.com","celine@gmail.com"));

            return null;
        }
    }
}
