package com.sbizzera.mareu.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.sbizzera.mareu.R;
import com.sbizzera.mareu.model.ListMeetingsUiModel;
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

import static org.junit.Assert.assertEquals;

/**
 * Creates by Boris SBIZZERA on 02/10/2019.
 */

@RunWith(JUnit4.class)
public class ListMeetingsViewModelTest {


    private ListMeetingsViewModel viewModel;

    private MutableLiveData<List<Meeting>> mAllMeetings;

    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setup() {
        mAllMeetings = new MutableLiveData<>();

        MeetingRepository meetingRepository = Mockito.mock(MeetingRepository.class);
        Mockito.doReturn(mAllMeetings).when(meetingRepository).getAllMeetings();

        viewModel = new ListMeetingsViewModel(meetingRepository);
    }


    @Test
    public void shouldMapCorrectlyAllMeetingsAndFilters() throws InterruptedException {
        //Given
        List<Meeting> meetingList = new ArrayList<>();
        Meeting meeting1 = new Meeting("Meeting Test 1", LocalDateTime.of(LocalDate.of(2100, 1, 1), LocalTime.of(0, 0)),
                LocalDateTime.of(LocalDate.of(2100, 1, 1), LocalTime.of(1, 0)),
                MeetingRoom.MARIO, Arrays.asList("boris@oc.com", "nino@oc.com"));
        Meeting meeting2 = new Meeting("Meeting Test 2", LocalDateTime.of(LocalDate.of(2100, 1, 2), LocalTime.of(1, 0)),
                LocalDateTime.of(LocalDate.of(2100, 1, 2), LocalTime.of(2, 0)),
                MeetingRoom.MARIO, Arrays.asList("charles@oc.com", "gerard@oc.com"));
        meetingList.add(meeting1);
        meetingList.add(meeting2);
        mAllMeetings.setValue(meetingList);


        //When
        List<Meeting> result = LiveDataTestUtil.getValue(viewModel.getFilteredMeetings());

        //Then
        assertEquals(2, result.size());
    }

    @Test
    public void shouldTransformCorrectlyMeetingListToUIModelList() throws InterruptedException {
        //Given
        List<Meeting> meetingList = new ArrayList<>();
        Meeting meeting1 = new Meeting("Meeting Test 1", LocalDateTime.of(LocalDate.of(2030, 1, 1), LocalTime.of(0, 0)),
                LocalDateTime.of(LocalDate.of(2030, 1, 1), LocalTime.of(1, 0)),
                MeetingRoom.MARIO, Arrays.asList("boris@oc.com", "nino@oc.com"));
        Meeting meeting2 = new Meeting("Meeting Test 2", LocalDateTime.of(LocalDate.of(2030, 1, 2), LocalTime.of(1, 0)),
                LocalDateTime.of(LocalDate.of(2030, 1, 2), LocalTime.of(2, 0)),
                MeetingRoom.LUIGI, Arrays.asList("charles@oc.com", "gerard@oc.com"));
        meetingList.add(meeting1);
        meetingList.add(meeting2);
        mAllMeetings.setValue(meetingList);


        //When
        List<ListMeetingsUiModel> result = LiveDataTestUtil.getValue(viewModel.getMeetings());

//TODO idem
        //Then
        assertEquals("Meeting Test 1", result.get(0).getListMeetingsTitle());
        assertEquals("02/01/30 - 01:00 à 02:00 - Luigi", result.get(1).getMeetingDateAndRoom());
        assertEquals(R.color.mat3, result.get(0).getListMeetingsColor());
        assertEquals("charles@oc.com, gerard@oc.com", result.get(1).getListMeetingsParticipants());
    }

    @Test
    public void filtersShouldSetCorrectlyFilteredMeetings() throws InterruptedException {
        //Given Filters on Room Mario
        List<Meeting> meetingList = new ArrayList<>();
        Meeting meeting1 = new Meeting("Meeting Test 1", LocalDateTime.of(LocalDate.of(2030, 1, 1), LocalTime.of(0, 0)),
                LocalDateTime.of(LocalDate.of(2030, 1, 1), LocalTime.of(1, 0)),
                MeetingRoom.MARIO, Arrays.asList("boris@oc.com", "nino@oc.com"));
        Meeting meeting2 = new Meeting("Meeting Test 2", LocalDateTime.of(LocalDate.of(2030, 1, 2), LocalTime.of(1, 0)),
                LocalDateTime.of(LocalDate.of(2030, 1, 2), LocalTime.of(2, 0)),
                MeetingRoom.LUIGI, Arrays.asList("charles@oc.com", "gerard@oc.com"));
        meetingList.add(meeting1);
        meetingList.add(meeting2);
        mAllMeetings.setValue(meetingList);
        viewModel.setFilters("", "Mario");
        //When
        List<Meeting> result = LiveDataTestUtil.getValue(viewModel.getFilteredMeetings());
        //Then
        assertEquals(1, result.size());
        assertEquals("Meeting Test 1", result.get(0).getTitle());
        assertEquals("Mario", result.get(0).getRoom().getRoomName());


        //Given Filters on Date 2030/01/02
        LocalDate date = LocalDate.of(2030, 1, 2);
        String dateString = date.format(viewModel.getDateFormatter());
        viewModel.setFilters(dateString, "");
        //When
        result = LiveDataTestUtil.getValue(viewModel.getFilteredMeetings());
        //Then
        assertEquals(1, result.size());
        assertEquals("Meeting Test 2", result.get(0).getTitle());
        assertEquals(dateString, result.get(0).getMeetingStart().format(viewModel.getDateFormatter()));

        //Given Filters on Date 2030/01/02 and Room "Mario
        viewModel.setFilters(dateString, "Mario");
        //When
        result = LiveDataTestUtil.getValue(viewModel.getFilteredMeetings());
        assertEquals(0, result.size());

    }

    @Test
    public void menuIconShouldBeOnFilter() throws InterruptedException {
        //Given
        viewModel.setFilters("", "");

        //When
        int result = LiveDataTestUtil.getValue(viewModel.getMenuLiveDate());

        //Then
        assertEquals(R.menu.filter_meetings_menu, result);
    }

    @Test
    public void menuIconShouldBeOnRefresh() throws InterruptedException {
        //Given
        viewModel.setFilters("", "Mario");
        //When
        int result = LiveDataTestUtil.getValue(viewModel.getMenuLiveDate());
        //Then
        assertEquals(R.menu.filtered_meetings_menu, result);

        //Given
        viewModel.setFilters("02/01/2030", "");
        //When
        result = LiveDataTestUtil.getValue(viewModel.getMenuLiveDate());
        //Then
        assertEquals(R.menu.filtered_meetings_menu, result);


        //Given
        viewModel.setFilters("02/01/2030", "Mario");
        //When
        result = LiveDataTestUtil.getValue(viewModel.getMenuLiveDate());
        //Then
        assertEquals(R.menu.filtered_meetings_menu, result);
    }
}