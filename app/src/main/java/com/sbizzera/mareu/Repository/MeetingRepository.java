package com.sbizzera.mareu.Repository;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.sbizzera.mareu.Model.Meeting;
import com.sbizzera.mareu.Room.MeetingDao;
import com.sbizzera.mareu.Room.MeetingDataBase;

import java.util.List;

/**
 * Creates by Boris SBIZZERA on 02/09/2019.
 */
public class MeetingRepository {

    private MeetingDao mMeetingDao;
    private LiveData<List<Meeting>> mAllMeetings;

    public MeetingRepository (Application application){
        mMeetingDao = MeetingDataBase.getInstance(application).meetingDao();
        mAllMeetings = mMeetingDao.getAllMeetings();
    }

    public LiveData<List<Meeting>> getAllMeetings() {
        return mAllMeetings;
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

}
