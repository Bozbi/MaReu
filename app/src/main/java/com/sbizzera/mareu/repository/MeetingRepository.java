package com.sbizzera.mareu.repository;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.sbizzera.mareu.model.Meeting;
import com.sbizzera.mareu.room.MeetingDao;
import com.sbizzera.mareu.room.MeetingDataBase;

import java.util.List;

/**
 * Creates by Boris SBIZZERA on 02/09/2019.
 */
public class MeetingRepository {

    private MeetingDao mMeetingDao;


    public MeetingRepository (Application application){
        mMeetingDao = MeetingDataBase.getInstance(application).meetingDao();
    }

    public LiveData<List<Meeting>> getAllMeetings() {
        return mMeetingDao.getAllMeetings();
    }



    public void insertMeeting(Meeting meeting){
        new InsertMeetingAsyncTask(mMeetingDao).execute(meeting);
    }

    private static class InsertMeetingAsyncTask extends AsyncTask<Meeting,Void,Void>{
        private MeetingDao mMeetingDao;

         InsertMeetingAsyncTask(MeetingDao meetingDao){
            mMeetingDao = meetingDao;
        }

        @Override
        protected Void doInBackground(Meeting... meetings) {
            mMeetingDao.insertMeeting(meetings[0]);
            return null;
        }
    }

    public void deleteMeeting(Meeting meeting){
        new DeleteMeetingAsyncTask(mMeetingDao).execute(meeting);
    }

    private static class DeleteMeetingAsyncTask extends AsyncTask<Meeting,Void,Void>{
        private MeetingDao mMeetingDao;

        DeleteMeetingAsyncTask(MeetingDao meetingDao){
            mMeetingDao = meetingDao;
        }

        @Override
        protected Void doInBackground(Meeting... meetings) {
            mMeetingDao.deleteMeeting(meetings[0]);
            return null;
        }
    }

}