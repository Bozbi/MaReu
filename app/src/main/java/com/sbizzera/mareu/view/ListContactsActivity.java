package com.sbizzera.mareu.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.sbizzera.mareu.R;
import com.sbizzera.mareu.model.AddActivityMeetingModel;
import com.sbizzera.mareu.view.utils.ListParticipantsRecyclerViewAdapter;

public class ListContactsActivity extends AppCompatActivity {

    private static final String MEETING_EXTRA = "MEETING_EXTRA";
    private RecyclerView mRcyclListParticipants;

    private AddActivityMeetingModel mMeetingSaved;

    private Intent resultIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contacts);

        mMeetingSaved = (AddActivityMeetingModel)getIntent().getSerializableExtra(AddMeetingActivity.MEETING_EXTRA);

        initToolBar();
        mRcyclListParticipants = findViewById(R.id.rcycl_list_participants);
        mRcyclListParticipants.setLayoutManager(new LinearLayoutManager(this));
        ListParticipantsRecyclerViewAdapter adapter = new ListParticipantsRecyclerViewAdapter();
        mRcyclListParticipants.setAdapter(adapter);




    }



    private void initToolBar() {
        Toolbar tlbar = findViewById(R.id.tbar_tb_contatcs_activity);
        setSupportActionBar(tlbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tlbar.setNavigationIcon(R.drawable.ic_close);
        tlbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void finish() {

        resultIntent = new Intent();
        resultIntent.putExtra(MEETING_EXTRA,mMeetingSaved);
        setResult(RESULT_OK,resultIntent);
        super.finish();
    }
}
