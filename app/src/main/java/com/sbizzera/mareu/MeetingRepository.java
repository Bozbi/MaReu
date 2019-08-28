package com.sbizzera.mareu;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
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
public class MeetingRepository {

    private MeetingDAO meetingDao;
    private LiveData<List<Meeting>> allMeetings;


    public MeetingRepository(Application application){
        MeetingDatabase database = MeetingDatabase.getInstance(application);
        meetingDao = database.meetingDAO();
        allMeetings = meetingDao.getAllMeetings();
    }


    public void insert(Meeting meeting){
        new InsertMeetingAsyncTask(meetingDao).execute(meeting);
    }


    public void update(Meeting meeting){
        new UpdateMeetingAsyncTask(meetingDao).execute(meeting);
    }

    public void delete(Meeting meeting){
        new DeleteMeetingAsyncTask(meetingDao).execute(meeting);
    }

    public LiveData<List<Meeting>> getAllMeetings(){
        return allMeetings;
    }


        //TODO comment faire ça (2 autres constructeurs?);
//    public LiveData<List<Meeting>> getAllMeetingsInRoomNb(int roomNumber){
//        return meetingDao.getAllMeetingsInRoomNb(roomNumber);
//    }

//    public LiveData<List<Meeting>> getAllMeetingsAtDate(DateTime date){
//        return meetingDao.getAllMeetingsAtDate(date);
//    }

    public static class InsertMeetingAsyncTask extends AsyncTask <Meeting,Void, Void>{
        private MeetingDAO meetingDAO;

        public InsertMeetingAsyncTask(MeetingDAO meetingDAO) {
            this.meetingDAO = meetingDAO;
        }

        @Override
        protected Void doInBackground(Meeting... meetings) {
            meetingDAO.insert(meetings[0]);
            return null;
        }
    }

    public static class UpdateMeetingAsyncTask extends AsyncTask <Meeting,Void, Void>{
        private MeetingDAO meetingDAO;

        public UpdateMeetingAsyncTask(MeetingDAO meetingDAO) {
            this.meetingDAO = meetingDAO;
        }

        @Override
        protected Void doInBackground(Meeting... meetings) {
            meetingDAO.update(meetings[0]);
            return null;
        }
    }

    public static class DeleteMeetingAsyncTask extends AsyncTask <Meeting,Void, Void>{
        private MeetingDAO meetingDAO;

        public DeleteMeetingAsyncTask(MeetingDAO meetingDAO) {
            this.meetingDAO = meetingDAO;
        }

        @Override
        protected Void doInBackground(Meeting... meetings) {
            meetingDAO.delete(meetings [0]);
            return null;
        }
    }


}
