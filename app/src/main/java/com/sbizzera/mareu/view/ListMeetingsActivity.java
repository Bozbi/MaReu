package com.sbizzera.mareu.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sbizzera.mareu.R;
import com.sbizzera.mareu.model.Meeting;
import com.sbizzera.mareu.view.utils.FilterDialog;
import com.sbizzera.mareu.view.utils.ListMeetingsRecyclerAdapter;
import com.sbizzera.mareu.viewmodel.ListMeetingsViewModel;

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

        mListMeetingsViewModel.getMeetings().observe(this, new Observer<List<Meeting>>() {
            @Override
            public void onChanged(List<Meeting> meetings) {
                adapter.setAllMeetingsList(meetings);
            }
        });


        mAddMeetingFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListMeetingsViewModel.insertMeeting(new Meeting("Test2", 1, "bosbizz@gmail.com, celinetrambaud@gmail.com, gerard@hotmail.com"));

            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListMeetingsViewModel.filterByRoom(1);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.filter_meetings_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        FilterDialog dialog = new FilterDialog();
        dialog.show(getSupportFragmentManager(),"example");

        return true;
    }

    @Override
    public void onDeleteClick(Meeting meeting) {
        mListMeetingsViewModel.deleteMeeting(meeting);
    }

}
