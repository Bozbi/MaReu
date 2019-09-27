package com.sbizzera.mareu.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sbizzera.mareu.R;
import com.sbizzera.mareu.model.AddMeetingUiModel;
import com.sbizzera.mareu.view.utils.ListContactsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListContactsActivity extends AppCompatActivity implements ListContactsRecyclerViewAdapter.OnDeleteParticipantClickListener {

    private static final String MEETING_EXTRA = "MEETING_EXTRA";
    private RecyclerView mRcyclListParticipants;

    private EditText mEdtxtAddContact;
    private TextView mTxtaddToList;

    private AddMeetingUiModel mMeetingSaved;

    private Intent resultIntent;
    private List<String> mParticipants = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contacts);
        mEdtxtAddContact = findViewById(R.id.edtxt_add_contact);
        mTxtaddToList = findViewById(R.id.txt_add_contact);

        mMeetingSaved = (AddMeetingUiModel) getIntent().getSerializableExtra(AddMeetingActivity.MEETING_EXTRA);
        String participants = mMeetingSaved.getParticipants();
        if (participants != null) {
            String[] participantsArray = TextUtils.split(participants, ", ");
            for (String participant : participantsArray) {
                mParticipants.add(participant);
            }
        }

        initToolBar();
        mRcyclListParticipants = findViewById(R.id.rcycl_list_participants);
        mRcyclListParticipants.setLayoutManager(new LinearLayoutManager(this));
        final ListContactsRecyclerViewAdapter adapter = initAdapter();

        mTxtaddToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String participantToAdd = mEdtxtAddContact.getText().toString();
                if (isEmailValid(participantToAdd)) {
                    mParticipants.add(mEdtxtAddContact.getText().toString());
                    mRcyclListParticipants.setAdapter(adapter);
                    mMeetingSaved.setParticipants(TextUtils.join(", ", mParticipants));
                    mEdtxtAddContact.setText(null);
                } else {
                    final AlertDialog dialog = new AlertDialog.Builder(ListContactsActivity.this).create();
                    dialog.setTitle("Adresse E-Mail non valide !!");
                    dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mEdtxtAddContact.setText(null);
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            }
        });


    }

    private ListContactsRecyclerViewAdapter initAdapter() {
        final ListContactsRecyclerViewAdapter adapter = new ListContactsRecyclerViewAdapter(mParticipants, this);
        mRcyclListParticipants.setAdapter(adapter);
        return adapter;
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
        resultIntent.putExtra(MEETING_EXTRA, mMeetingSaved);
        setResult(RESULT_OK, resultIntent);
        super.finish();
    }

    @Override
    public void deleteParticipant(int position) {
        mParticipants.remove(position);
        mMeetingSaved.setParticipants(TextUtils.join(", ", mParticipants));
        initAdapter();
    }

    private boolean isEmailValid(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
