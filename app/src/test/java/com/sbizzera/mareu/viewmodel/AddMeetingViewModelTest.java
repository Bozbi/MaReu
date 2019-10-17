package com.sbizzera.mareu.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.sbizzera.mareu.model.Meeting;
import com.sbizzera.mareu.model.MeetingRoom;
import com.sbizzera.mareu.repository.MeetingRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Creates by Boris SBIZZERA on 10/10/2019.
 */

@RunWith(JUnit4.class)
public class AddMeetingViewModelTest {

    private AddMeetingViewModel viewModel;

    private List<Meeting> mAllMeetings;

    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setup() {
        //DATA SET
        mAllMeetings = new ArrayList<Meeting>();

        Meeting meeting1 = new Meeting("Meeting Test 1", LocalDateTime.of(LocalDate.of(2100, 1, 1), LocalTime.of(0, 0)),
                LocalDateTime.of(LocalDate.of(2100, 1, 1), LocalTime.of(1, 0)),
                MeetingRoom.MARIO, Arrays.asList("boris@oc.com", "nino@oc.com"));
        meeting1.setId(1);

        Meeting meeting2 = new Meeting("Meeting Test 2", LocalDateTime.of(LocalDate.of(2100, 1, 2), LocalTime.of(1, 0)),
                LocalDateTime.of(LocalDate.of(2100, 1, 2), LocalTime.of(2, 0)),
                MeetingRoom.MARIO, Arrays.asList("charles@oc.com", "gerard@oc.com"));
        meeting2.setId(2);
        mAllMeetings.add(meeting1);
        mAllMeetings.add(meeting2);

        MeetingRepository meetingRepository = Mockito.mock(MeetingRepository.class);
        Mockito.doReturn(mAllMeetings).when(meetingRepository).getAllMeetingsSync();

        viewModel = new AddMeetingViewModel(meetingRepository);
    }

    @Test
    public void meetingHoursShouldBeCoherentToProceesMeetingInsertOrUpdate() {
        //Given
        Meeting meeting = new Meeting("Meeting Test 1", LocalDateTime.of(LocalDate.of(2030, 1, 1), LocalTime.of(0, 0)),
                LocalDateTime.of(LocalDate.of(2030, 1, 1), LocalTime.of(1, 0)),
                MeetingRoom.MARIO, Arrays.asList("boris@oc.com", "nino@oc.com"));
        //When
        Boolean result = viewModel.isMeetingHoursCoherent(meeting);
        //Then
        assertTrue(result);

        //Given
        meeting.setMeetingStop(meeting.getMeetingStart().minusHours(1));
        //When
        result = viewModel.isMeetingHoursCoherent(meeting);
        //Then
        assertFalse(result);

        //Given
        meeting.setMeetingStart(LocalDateTime.now().minusHours(1));
        //When
        result = viewModel.isMeetingHoursCoherent(meeting);
        //Then
        assertFalse(result);
    }


    @Test
    public void meetingRoomShouldBeAvailableToProccessInsertorUpdate() {
        //Given
        Meeting meetingToInsertTest = new Meeting("Meeting to insert Test",
                LocalDateTime.of(LocalDate.of(2100, 1, 1), LocalTime.of(0, 30)),
                LocalDateTime.of(LocalDate.of(2100, 1, 1), LocalTime.of(1, 30)),
                MeetingRoom.MARIO, Arrays.asList("boris@oc.com", "nino@oc.com"));
        meetingToInsertTest.setId(3);
        //When
        Boolean result = viewModel.isMeetingRoomAvailable(meetingToInsertTest);
        //Then
        assertFalse(result);

        //Given
        meetingToInsertTest.setRoom(MeetingRoom.BLOOPS);
        //When
        result = viewModel.isMeetingRoomAvailable(meetingToInsertTest);
        //Then
        assertTrue(result);

        //Given
        meetingToInsertTest.setRoom(MeetingRoom.MARIO);
        meetingToInsertTest.setId(1);
        //When
        result = viewModel.isMeetingRoomAvailable(meetingToInsertTest);
        //Then
        assertTrue(result);

    }
}