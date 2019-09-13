package com.sbizzera.mareu.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sbizzera.mareu.R;
import com.sbizzera.mareu.model.Meeting;
import com.sbizzera.mareu.model.MeetingRoom;
import com.sbizzera.mareu.view.utils.ListMeetingsRecyclerAdapter;
import com.sbizzera.mareu.viewmodel.ListMeetingsViewModel;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListMeetingsActivity extends AppCompatActivity implements ListMeetingsRecyclerAdapter.OnMeetingClickListener {

    private ListMeetingsViewModel mListMeetingsViewModel;
    private RecyclerView mMeetingListRecyclerView;
    private FloatingActionButton mAddMeetingFab;
    private Button mButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meetings);

        mMeetingListRecyclerView = findViewById(R.id.recycler_view_list_meeting);
        mAddMeetingFab = findViewById(R.id.fab_add_meeting);
        mButton = findViewById(R.id.buttun_filter);


        final ListMeetingsRecyclerAdapter adapter = new ListMeetingsRecyclerAdapter(this);

        mMeetingListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMeetingListRecyclerView.setAdapter(adapter);

        mListMeetingsViewModel = ViewModelProviders.of(this).get(ListMeetingsViewModel.class);

        mListMeetingsViewModel.getMeetings().observe(this, new Observer<List<MeetingsUiModel>>() {
            @Override
            public void onChanged(List<MeetingsUiModel> meetings) {
                adapter.setAllMeetingsList(meetings);

            }
        });


        mAddMeetingFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListMeetingsActivity.this, AddMeetingActivity.class);
                startActivity(intent);
                mListMeetingsViewModel.insertMeeting(new Meeting("Org", LocalDateTime.of(LocalDate.of(2019, 9, 1), LocalTime.of(1, 1)), LocalDateTime.now().plusHours(1), MeetingRoom.BOWSER, new ArrayList<String>(Arrays.asList("boris@gmail.com", "celine@gmail.com", "celine@gmail.com", "celine@gmail.com", "celine@gmail.com", "celine@gmail.com"))));

            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListMeetingsViewModel.filterByRoom(MeetingRoom.PEACH);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.filter_meetings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        FilterDialog dialog = new FilterDialog();
        dialog.show(getSupportFragmentManager(), "Filter Dialog");

        return true;
    }

    @Override
    public void onDeleteClick(MeetingsUiModel meeting) {
        mListMeetingsViewModel.deleteMeeting(meeting);
    }


}
