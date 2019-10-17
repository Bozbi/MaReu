package com.sbizzera.mareu;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.runner.AndroidJUnit4;

import com.sbizzera.mareu.model.Meeting;
import com.sbizzera.mareu.model.MeetingRoom;
import com.sbizzera.mareu.room.MeetingDataBase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * Creates by Boris SBIZZERA on 23/09/2019.
 */

@RunWith(AndroidJUnit4.class)
public class MeetingDaoTest {

    private MeetingDataBase dataBase;

    //DATA SET FOR MEETINGS
    private static Meeting MEETING_DEMO = new Meeting(
            "Meeting for Test",
            LocalDateTime.of(LocalDate.of(2020, 1, 1), LocalTime.of(14, 0)),
            LocalDateTime.of(LocalDate.of(2020, 1, 1), LocalTime.of(16, 0)),
            MeetingRoom.MARIO,
            Arrays.asList("boris@gmail.com", "celine@gmail.com"));

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.dataBase = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), MeetingDataBase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() throws Exception {
        dataBase.close();

    }

    @Test
    public void insertAndGetMeeting() throws InterruptedException {
        //BEFORE : Adding a new User
        dataBase.meetingDao().insertMeeting(MEETING_DEMO);
        // TEST
        List<Meeting> meetingList = LiveDataTestUtil.getValue(dataBase.meetingDao().getAllMeetings());
        Meeting meeting = LiveDataTestUtil.getValue(dataBase.meetingDao().getAllMeetings()).get(0);
        assertEquals(meetingList.size(), 1);
//        assertEquals(meeting.getId(),MEETING_DEMO.getId());
        assertEquals(meeting.getMeetingStart(), MEETING_DEMO.getMeetingStart());
        assertEquals(meeting.getMeetingStop(), MEETING_DEMO.getMeetingStop());
        assertEquals(meeting.getRoom(), MEETING_DEMO.getRoom());
        assertEquals(meeting.getParticipants(), MEETING_DEMO.getParticipants());
    }

    @Test
    public void deleteMeeting() throws InterruptedException {
        //BEFORE: Adding a new User
        dataBase.meetingDao().insertMeeting(MEETING_DEMO);
        //TEST
        List<Meeting> meetingList = LiveDataTestUtil.getValue(dataBase.meetingDao().getAllMeetings());
        assertEquals(meetingList.size(), 1);
        dataBase.meetingDao().deleteMeeting(1);
        meetingList = LiveDataTestUtil.getValue(dataBase.meetingDao().getAllMeetings());
        assertEquals(meetingList.size(), 0);
    }

    @Test
    public void updateMeeting() throws InterruptedException {
        dataBase.meetingDao().insertMeeting(MEETING_DEMO);
        MEETING_DEMO.setRoom(MeetingRoom.LUIGI);
        dataBase.meetingDao().updateMeeting(MEETING_DEMO);
        List<Meeting> meetingList = LiveDataTestUtil.getValue(dataBase.meetingDao().getAllMeetings());
        Meeting meeting = meetingList.get(0);
        assertNotEquals(MeetingRoom.LUIGI, meeting.getRoom());
    }

}