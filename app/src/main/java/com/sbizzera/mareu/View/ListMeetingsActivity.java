package com.sbizzera.mareu.View;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sbizzera.mareu.R;

public class ListMeetingsActivity extends AppCompatActivity {

    private RecyclerView mMeetingListRecyclerView;
    private FloatingActionButton mAddMeetingFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meetings);

        mMeetingListRecyclerView = findViewById(R.id.recycler_view_list_meeting);
        mAddMeetingFab = findViewById(R.id.fab_add_meeting);
    }

}
