package com.sbizzera.mareu.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sbizzera.mareu.repository.MeetingRepository;
import com.sbizzera.mareu.room.MeetingDataBase;
import com.sbizzera.mareu.view.ListMeetingsActivity;
import com.sbizzera.mareu.view.MainApplication;

/**
 * Creates by Boris SBIZZERA on 02/10/2019.
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final MeetingRepository mDataSource;
    private static ViewModelFactory sFactory;


    public static ViewModelFactory getInstance() {
        if (sFactory == null) {
            synchronized (ViewModelFactory.class) {
                if (sFactory == null) {
                    sFactory = new ViewModelFactory(new MeetingRepository(MeetingDataBase.getInstance(MainApplication.getApplication()).meetingDao()));
                }
            }
        }
        return sFactory;
    }

    public ViewModelFactory(MeetingRepository dataSource) {
        mDataSource = dataSource;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ListMeetingsViewModel.class)) {
            return (T) new ListMeetingsViewModel(mDataSource);
        }
        else if (modelClass.isAssignableFrom(AddMeetingViewModel.class)){
            return(T) new AddMeetingViewModel(mDataSource);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}