package com.sbizzera.mareu;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

/**
 * Creates by Boris SBIZZERA on 28/08/2019.
 */
public class ListMeetingsViewModel extends ViewModel {

    private MeetingRepository mMeetingRepository = new MeetingRepository(MainApplication.getInstance());
    public MutableLiveData<List<UiModel>> uiModelsListLiveData ;

//    public ListMeetingsViewModel() {
//        uiModelsListLiveData = mMeetingRepository.getAllMeetings();
//    }

    public void addMeeting(Meeting meeting) {
        mMeetingRepository.insert(meeting);
    }


}
