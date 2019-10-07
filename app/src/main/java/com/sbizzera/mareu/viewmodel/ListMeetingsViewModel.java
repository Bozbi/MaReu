package com.sbizzera.mareu.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.sbizzera.mareu.R;
import com.sbizzera.mareu.model.Meeting;
import com.sbizzera.mareu.model.MeetingRoom;
import com.sbizzera.mareu.repository.MeetingRepository;
import com.sbizzera.mareu.model.ListMeetingsUiModel;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates by Boris SBIZZERA on 03/09/2019.
 */

public class ListMeetingsViewModel extends ViewModel implements Serializable {

    private MeetingRepository mMeetingRepository;
    private LiveData<List<Meeting>> mAllMeetings;
    private MutableLiveData<MeetingRoom> mRoomFilter = new MutableLiveData<>();
    private MutableLiveData<LocalDate> mDateFilter = new MutableLiveData<>();
    private MediatorLiveData<Integer> mMenuMediatorLiveData = new MediatorLiveData<>();
    private MediatorLiveData<List<Meeting>> mFilteredMeetings = new MediatorLiveData<>();
    private LiveData<List<ListMeetingsUiModel>> mListMeetingsUiModel;


    private DateTimeFormatter mDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public ListMeetingsViewModel(MeetingRepository meetingRepository) {
        mMeetingRepository = meetingRepository;
        deleteOldMeetings();
        mAllMeetings = mMeetingRepository.getAllMeetings();

        mMenuMediatorLiveData.setValue(R.menu.filter_meetings_menu);


        mFilteredMeetings.addSource(mAllMeetings, new Observer<List<Meeting>>() {
            @Override
            public void onChanged(List<Meeting> meetings) {
                mFilteredMeetings.setValue(combineMeetingsRoomAndDateFilter(meetings, mRoomFilter.getValue(), mDateFilter.getValue()));
            }
        });

        mFilteredMeetings.addSource(mRoomFilter, new Observer<MeetingRoom>() {
            @Override
            public void onChanged(MeetingRoom roomName) {
                mFilteredMeetings.setValue(combineMeetingsRoomAndDateFilter(mAllMeetings.getValue(), roomName, mDateFilter.getValue()));
            }
        });

        mFilteredMeetings.addSource(mDateFilter, new Observer<LocalDate>() {
            @Override
            public void onChanged(LocalDate localDate) {
                mFilteredMeetings.setValue(combineMeetingsRoomAndDateFilter(mAllMeetings.getValue(), mRoomFilter.getValue(), localDate));
            }
        });

        mMenuMediatorLiveData.addSource(mDateFilter, new Observer<LocalDate>() {
            @Override
            public void onChanged(LocalDate localDate) {
                mMenuMediatorLiveData.setValue(combineFilters(localDate,mRoomFilter.getValue()));
            }
        });

        mMenuMediatorLiveData.addSource(mRoomFilter, new Observer<MeetingRoom>() {
            @Override
            public void onChanged(MeetingRoom meetingRoom) {
                mMenuMediatorLiveData.setValue(combineFilters(mDateFilter.getValue(), meetingRoom));
            }
        });


        mListMeetingsUiModel = Transformations.map(mFilteredMeetings, new Function<List<Meeting>, List<ListMeetingsUiModel>>() {
            @Override
            public List<ListMeetingsUiModel> apply(List<Meeting> input) {
                List<ListMeetingsUiModel> result = new ArrayList<>();
                for (Meeting meeting : input) {
                    if (meeting.getRoom() != null) {

                        String meetingTitle;
                        String meetingDateAndRoom;
                        String participant;
                        int color;


                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YY - HH:mm 'à' ");
                        String meetingDateStart = meeting.getMeetingStart().format(formatter);
                        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
                        String meetingEndDate = meeting.getMeetingStop().format(formatter2);
                        String room = meeting.getRoom().getRoomName();


                        meetingTitle = meeting.getTitle();
                        color = meeting.getRoom().getColor();
                        meetingDateAndRoom = meetingDateStart + meetingEndDate + " - " + room;
                        participant = TextUtils.join(", ", meeting.getParticipants());


                        ListMeetingsUiModel model = new ListMeetingsUiModel(meeting.getId(), meetingTitle, meetingDateAndRoom, color, participant);
                        result.add(model);
                    }
                }
                return result;
            }
        });

    }

    private Integer combineFilters(LocalDate localDate, MeetingRoom meetingRoom) {

        if (localDate == null && meetingRoom == null){
            return R.menu.filter_meetings_menu;
        } else{
            return R.menu.filtered_meetings_menu;
        }

    }

    private List<Meeting> combineMeetingsRoomAndDateFilter(List<Meeting> meetings, MeetingRoom room, LocalDate date) {
        List<Meeting> result = new ArrayList<>();
        if (meetings != null) {
            if (room == null && date == null) {
                return meetings;
            } else if (date == null) {
                for (Meeting meeting : meetings) {
                    if (meeting.getRoom() == room) {
                        result.add(meeting);
                    }
                }
            } else if (room == null) {
                for (Meeting meeting : meetings) {
                    if (meeting.getMeetingStart().toLocalDate().equals(date)) {
                        result.add(meeting);
                    }
                }
            } else {
                for (Meeting meeting : meetings) {
                    if (meeting.getMeetingStart().toLocalDate().equals(date) && meeting.getRoom() == room) {
                        result.add(meeting);
                    }
                }
            }
        }



        return result;
    }

    public LiveData<List<ListMeetingsUiModel>> getMeetings() {
        return mListMeetingsUiModel;
    }
    public MediatorLiveData<List<Meeting>> getFilteredMeetings(){return mFilteredMeetings;}

    public void deleteMeeting(ListMeetingsUiModel meeting) {
        mMeetingRepository.deleteMeeting(meeting.getId());
    }


    private void deleteOldMeetings() {
        List<Meeting> allMeetingsSync = mMeetingRepository.getAllMeetingsSync();
        for (Meeting meeting : allMeetingsSync) {
            if (meeting.getMeetingStop().isBefore(LocalDateTime.now())) {
                mMeetingRepository.deleteMeeting(meeting.getId());
            }
        }
    }

    public void setFilters(String date, String room) {

        if (date== null || date.isEmpty()) {
            mDateFilter.postValue(null);
        } else {
            mDateFilter.postValue(LocalDate.parse(date, mDateFormatter));
        }
        if (room== null || room.isEmpty()) {
            mRoomFilter.postValue(null);
        } else {
            mRoomFilter.postValue(MeetingRoom.valueOf(room.toUpperCase()));
        }

    }

    public DateTimeFormatter getDateFormatter() {
        return mDateFormatter;
    }

    public LiveData<Integer> getMenuLiveDate() {
        return mMenuMediatorLiveData;
    }



}
