package com.sbizzera.mareu.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sbizzera.mareu.R;
import com.sbizzera.mareu.model.Meeting;
import com.sbizzera.mareu.model.ListMeetingsUiModel;
import com.sbizzera.mareu.view.utils.ListMeetingsRecyclerViewAdapter;
import com.sbizzera.mareu.viewmodel.ListMeetingsViewModel;

import java.util.List;

public class ListMeetingsActivity extends AppCompatActivity implements ListMeetingsRecyclerViewAdapter.OnMeetingClickListener {

    private ListMeetingsViewModel mListMeetingsViewModel;
    public static final String INTENT_ACTION_ADD_NOTE = "com.sbizzera.mareu.INTENT_ACTION_ADD_NOTE";
    public static final String INTENT_ACTION_UPDATE_NOTE = "com.sbizzera.mareu.INTENT_ACTION_UPDATE_NOTE";
    public static final String MEETING_EXTRA = "MEETING_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meetings);

        RecyclerView meetingListRecyclerView = findViewById(R.id.recycler_view_list_meeting);
        FloatingActionButton addMeetingFab = findViewById(R.id.fab_add_meeting);

        final ListMeetingsRecyclerViewAdapter adapter = new ListMeetingsRecyclerViewAdapter(this);
        meetingListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        meetingListRecyclerView.setAdapter(adapter);


        mListMeetingsViewModel = ViewModelProviders.of(this).get(ListMeetingsViewModel.class);
        mListMeetingsViewModel.getMeetings().observe(this, new Observer<List<ListMeetingsUiModel>>() {
            @Override
            public void onChanged(List<ListMeetingsUiModel> meetings) {
                adapter.setAllMeetingsList(meetings);
            }
        });

        mListMeetingsViewModel.getMenuLiveDate().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                invalidateOptionsMenu();
            }
        });

        addMeetingFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListMeetingsActivity.this, AddMeetingActivity.class);
                intent.setAction(INTENT_ACTION_ADD_NOTE);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Integer menuId = mListMeetingsViewModel.getMenuLiveDate().getValue();
        if (menuId != null){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(menuId, menu);
        return true;
        }
        return false;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getIcon().getConstantState().equals(getResources().getDrawable(R.drawable.ic_filter).getConstantState())) {
            FilterDialog dialog = FilterDialog.newInstance(mListMeetingsViewModel);
            dialog.show(getSupportFragmentManager(), "Filter Dialog");
        } else {
            mListMeetingsViewModel.setFilters(null,null);
        }
        return true;
    }


    @Override
    public void onDeleteClick(ListMeetingsUiModel meeting) {
        mListMeetingsViewModel.deleteMeeting(meeting);
    }

    @Override
    public void onEditClick(int position) {
        Intent intent = new Intent(ListMeetingsActivity.this, AddMeetingActivity.class);
        Meeting meetingToPass = mListMeetingsViewModel.getFilteredMeetings().getValue().get(position);
        intent.putExtra(MEETING_EXTRA,meetingToPass);
        intent.setAction(INTENT_ACTION_UPDATE_NOTE);
        startActivity(intent);
    }
}
