package com.sbizzera.mareu.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.sbizzera.mareu.model.ListMeetingsUiModel;
import com.sbizzera.mareu.model.Meeting;
import com.sbizzera.mareu.model.MeetingRoom;
import com.sbizzera.mareu.repository.MeetingRepository;
import com.sbizzera.mareu.room.MeetingDao;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

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
    public void setup(){

        MeetingRepository meetingRepository = Mockito.mock(MeetingRepository.class);
        viewModel = new ListMeetingsViewModel(meetingRepository);

        mAllMeetings = new MutableLiveData<>();
        Mockito.doReturn(mAllMeetings).when(meetingRepository).getAllMeetings();

    }

    @Test
    public void stupidTest(){
        assertEquals(2,1+1);
    }

//    @Test
//    public void shouldMapCorrectlyAllMeetingsAndFilters() throws InterruptedException{
//        //Given
//        List<Meeting> meetingList = new ArrayList<>();
//        Meeting meeting1 = new Meeting("Meeting Test 1", LocalDateTime.now(),LocalDateTime.now().plusHours(1),
//                MeetingRoom.MARIO,Arrays.asList("boris@oc.com","nino@oc.com"));
//        Meeting meeting2 = new Meeting("Meeting Test 2", LocalDateTime.now(),LocalDateTime.now().plusHours(1),
//                MeetingRoom.LUIGI,Arrays.asList("boris@oc.com","nino@oc.com"));
//        meetingList.add(meeting1);
//        meetingList.add(meeting2);
//        mAllMeetings.setValue(meetingList);
//
//
//        //When
//        List<Meeting> result = LiveDataTestUtil.getValue(viewModel.getFilteredMeetings());
//
//        //Then
//        assertEquals(2,result.size());
//
//    }


}