package com.sbizzera.mareu.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
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
import org.mockito.Spy;
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

    private MutableLiveData<List<Meeting>> mMockLiveData = new MutableLiveData<>();


    @Mock
    private MeetingDao mDao;


    private ListMeetingsViewModel mListMeetingsViewModel;

    @Rule
    public MockitoRule mMockitoRule = MockitoJUnit.rule();

    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mMockLiveData.setValue(null);
        mListMeetingsViewModel = Mockito.spy(new ListMeetingsViewModel(new MeetingRepository(mDao)));
    }

    @Test
    public void test(){
        Meeting meeting = new Meeting("Test", LocalDateTime.now(),LocalDateTime.now().plusHours(1), MeetingRoom.BLOOPS, Arrays.asList("nono@gmail.com"));
        Meeting meeting2= new Meeting("Test2", LocalDateTime.now().plusHours(2),LocalDateTime.now().plusHours(3), MeetingRoom.MARIO, Arrays.asList("bobo@gmail.com"));
        List<Meeting> meetingList = new ArrayList<>();
        meetingList.add(meeting);
        meetingList.add(meeting2);

        mMockLiveData.setValue(meetingList);///Liste a insérer
        Mockito.doReturn(mMockLiveData).when(mDao).getAllMeetings();
        List<ListMeetingsUiModel> meetingsUiModels = mListMeetingsViewModel.getMeetings().getValue();
        assertEquals(meetingsUiModels.get(0).getListMeetingsTitle(),"Test");
    }
}