package com.sbizzera.mareu.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.sbizzera.mareu.R;
import com.sbizzera.mareu.model.AddMeetingUiModel;
import com.sbizzera.mareu.model.Meeting;
import com.sbizzera.mareu.viewmodel.AddMeetingViewModel;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;

public class AddMeetingActivity extends AppCompatActivity {

    //Need To


    private static final String MEETING_KEY = "MEETING_KEY";
    public static final String MEETING_EXTRA = "MEETING_EXTRA";
    private static final int REQUEST_CODE = 1;

    private EditText edtxTitle;
    private TextView txtStartDate;
    private TextView txtStartHour;
    private TextView txtStopDate;
    private TextView txtStopHour;
    private TextView txtContacts;
    private TextView txtLocation;

    private AddMeetingViewModel mAddMeetingViewModel;
    private String mAlerteMessage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        initToolBar();

        Button btnSave = findViewById(R.id.btn_save_meeting);
        edtxTitle = findViewById(R.id.edtxt_meeting_title);
        txtStartDate = findViewById(R.id.txt_start_date);
        txtStartHour = findViewById(R.id.txt_start_time);
        txtStopDate = findViewById(R.id.txt_stop_date);
        txtStopHour = findViewById(R.id.txt_stop_time);
        ConstraintLayout ctnAddContacts = findViewById(R.id.container_contacts);
        ConstraintLayout ctnAddLocation = findViewById(R.id.container_location);
        txtContacts = findViewById(R.id.txt_contacts);
        txtLocation = findViewById(R.id.txt_room);

        mAddMeetingViewModel = ViewModelProviders.of(this).get(AddMeetingViewModel.class);

        setFieldDependingOnIntentAction(getIntent());


        mAddMeetingViewModel.getAlertMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String message) {
                mAlerteMessage = message;
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mAddMeetingViewModel.insertOrUpdateMeeting(edtxTitle.getText().toString(), txtStartDate.getText().toString(), txtStartHour.getText().toString(), txtStopDate.getText().toString(), txtStopHour.getText().toString(), txtContacts.getText().toString(), txtLocation.getText().toString(), getIntent())) {
                    finish();
                } else {
                    new AlertDialog.Builder(AddMeetingActivity.this)
                            .setMessage(mAlerteMessage)
                            .show();
                }

            }
        });


        txtStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(AddMeetingActivity.this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        LocalDate date = LocalDate.of(i, i1 + 1, i2);
                        String datetxt = date.format(mAddMeetingViewModel.mDateFormatter);
                        txtStartDate.setText(datetxt);
                        txtStopDate.setText(datetxt);
                    }
                }, LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue() - 1, LocalDateTime.now().getDayOfMonth());
                dialog.show();
            }
        });

        txtStopDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(AddMeetingActivity.this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        LocalDate date = LocalDate.of(i, i1 + 1, i2);
                        String datetxt = date.format(mAddMeetingViewModel.mDateFormatter);
                        txtStopDate.setText(datetxt);
                    }
                }, LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue() - 1, LocalDateTime.now().getDayOfMonth());
                dialog.show();

            }
        });

        txtStartHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(AddMeetingActivity.this, R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        LocalTime time = LocalTime.of(i, i1);
                        String timeTxt = time.format(mAddMeetingViewModel.mTimeFormatter);
                        String timeStopTxt = time.plusHours(1).format(mAddMeetingViewModel.mTimeFormatter);
                        txtStartHour.setText(timeTxt);
                        txtStopHour.setText(timeStopTxt);
                    }
                }, LocalTime.now().getHour(), LocalTime.now().getMinute(), true);
                dialog.show();
            }
        });

        txtStopHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(AddMeetingActivity.this, R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        LocalTime time = LocalTime.of(i, i1);
                        String timeTxt = time.format(mAddMeetingViewModel.mTimeFormatter);
                        txtStopHour.setText(timeTxt);
                    }
                }, LocalTime.now().getHour(), LocalTime.now().getMinute(), true);
                dialog.show();
            }
        });


        ctnAddContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddMeetingActivity.this, ListContactsActivity.class);
                intent.putExtra(MEETING_EXTRA, createAddMeetingUiModelFromFields());
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        ctnAddLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AddMeetingActivity.this);
                mBuilder.setTitle("Choisissez une Salle de Réunion");
                mBuilder.setSingleChoiceItems(mAddMeetingViewModel.getAllRoomsList(), -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        txtLocation.setText(mAddMeetingViewModel.getAllRoomsList()[i]);
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });


    }

    private void setFieldDependingOnIntentAction(Intent intentAction) {
        if(intentAction.getAction().equals(ListMeetingsActivity.INTENT_ACTION_UPDATE_NOTE)){
            Meeting meeting = (Meeting) intentAction.getSerializableExtra(ListMeetingsActivity.MEETING_EXTRA);
            AddMeetingUiModel meetingUiModel = mAddMeetingViewModel.getAddMeetingUiModelfromMeeting(meeting);
            setFieldsFromAddActivityUiModel(meetingUiModel);
        }else{
            txtStartDate.setText(LocalDateTime.now().format(mAddMeetingViewModel.mDateFormatter));
            txtStartHour.setText(LocalDateTime.now().format(mAddMeetingViewModel.mTimeFormatter));
            txtStopDate.setText(LocalDateTime.now().plusHours(1).format(mAddMeetingViewModel.mDateFormatter));
            txtStopHour.setText(LocalDateTime.now().plusHours(1).format(mAddMeetingViewModel.mTimeFormatter));
        }
    }

    private void initToolBar() {
        Toolbar tlbar = findViewById(R.id.tbar_tb);
        setSupportActionBar(tlbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tlbar.setNavigationIcon(R.drawable.ic_close);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putSerializable(MEETING_KEY, createAddMeetingUiModelFromFields());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {

        AddMeetingUiModel meetingSaved = (AddMeetingUiModel) savedInstanceState.getSerializable(MEETING_KEY);
        setFieldsFromAddActivityUiModel(meetingSaved);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data!= null) {
            AddMeetingUiModel meetingSaved = (AddMeetingUiModel) data.getSerializableExtra(MEETING_EXTRA);
            setFieldsFromAddActivityUiModel(meetingSaved);
        }
    }

    private AddMeetingUiModel createAddMeetingUiModelFromFields() {
        return new AddMeetingUiModel(
                edtxTitle.getText().toString(),
                txtStartDate.getText().toString(),
                txtStartHour.getText().toString(),
                txtStopDate.getText().toString(),
                txtStopHour.getText().toString(),
                txtLocation.getText().toString(),
                txtContacts.getText().toString());
    }



    private void setFieldsFromAddActivityUiModel(AddMeetingUiModel meeting) {
            edtxTitle.setText(meeting.getTitle());
            txtStartDate.setText(meeting.getStartDate());
            txtStartHour.setText(meeting.getStartHour());
            txtStopDate.setText(meeting.getStopDate());
            txtStopHour.setText(meeting.getStopHour());
            txtLocation.setText(meeting.getRoom());
            txtContacts.setText(meeting.getParticipants());
    }


}
