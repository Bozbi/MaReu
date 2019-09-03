package com.sbizzera.mareu.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sbizzera.mareu.R;
import com.sbizzera.mareu.model.Meeting;
import com.sbizzera.mareu.viewmodel.ListMeetingsViewModel;

import java.util.List;

public class ListMeetingsActivity extends AppCompatActivity {

    private ListMeetingsViewModel mListMeetingsViewModel;

    private RecyclerView mMeetingListRecyclerView;
    private FloatingActionButton mAddMeetingFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meetings);

        mMeetingListRecyclerView = findViewById(R.id.recycler_view_list_meeting);
        mAddMeetingFab = findViewById(R.id.fab_add_meeting);

        mListMeetingsViewModel = ViewModelProviders.of(this).get(ListMeetingsViewModel.class);
        mListMeetingsViewModel.getAllMeetings().observe(this, new Observer<List<Meeting>>() {
            @Override
            public void onChanged(List<Meeting> meetings) {

            }
        });

        mMeetingListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

}
