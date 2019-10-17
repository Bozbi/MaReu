package com.sbizzera.mareu.repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.sbizzera.mareu.model.Meeting;
import com.sbizzera.mareu.room.MeetingDao;

import java.util.List;

/**
 * Creates by Boris SBIZZERA on 02/09/2019.
 */

public class MeetingRepository {

    private MeetingDao mMeetingDao;

    public MeetingRepository(MeetingDao meetingDao) {
        mMeetingDao = meetingDao;
    }

    public LiveData<List<Meeting>> getAllMeetings() {
        return mMeetingDao.getAllMeetings();
    }

    public void insertMeeting(Meeting meeting) {
        new InsertMeetingAsyncTask(mMeetingDao).execute(meeting);
    }

    private static class InsertMeetingAsyncTask extends AsyncTask<Meeting, Void, Void> {
        private MeetingDao mMeetingDao;

        InsertMeetingAsyncTask(MeetingDao meetingDao) {
            mMeetingDao = meetingDao;
        }

        @Override
        protected Void doInBackground(Meeting... meetings) {
            mMeetingDao.insertMeeting(meetings[0]);
            return null;
        }
    }

    public void updateMeeting(Meeting meeting) {
        new UpdateMeetingAsyncTask(mMeetingDao).execute(meeting);
    }

    private static class UpdateMeetingAsyncTask extends AsyncTask<Meeting, Void, Void> {
        private MeetingDao mMeetingDao;

        UpdateMeetingAsyncTask(MeetingDao meetingDao) {
            mMeetingDao = meetingDao;
        }

        @Override
        protected Void doInBackground(Meeting... meetings) {
            mMeetingDao.updateMeeting(meetings[0]);
            return null;
        }
    }

    public void deleteMeeting(int meetingId) {
        new DeleteMeetingAsyncTask(mMeetingDao).execute(meetingId);
    }

    private static class DeleteMeetingAsyncTask extends AsyncTask<Integer, Void, Void> {
        private MeetingDao mMeetingDao;

        DeleteMeetingAsyncTask(MeetingDao meetingDao) {
            mMeetingDao = meetingDao;
        }

        @Override
        protected Void doInBackground(Integer... meetingsId) {
            mMeetingDao.deleteMeeting(meetingsId[0]);
            return null;
        }
    }

    public List<Meeting>
    getAllMeetingsSync() {
        return mMeetingDao.getAllMeetingsSync();
    }


}
