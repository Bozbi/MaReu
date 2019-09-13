package com.sbizzera.mareu.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import androidx.lifecycle.ViewModelProviders;

import com.sbizzera.mareu.R;
import com.sbizzera.mareu.model.AddActivityMeetingModel;
import com.sbizzera.mareu.viewmodel.AddMeetingViewModel;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.format.DateTimeFormatter;

public class AddMeetingActivity extends AppCompatActivity {


    private static final String MEETING_KEY ="MEETING_KEY" ;
    public static final String MEETING_EXTRA ="MEETING_EXTRA" ;
    private static final int REQUEST_CODE = 1;

    private Button btnSave;
    private EditText edtxTitle;
    private TextView txtStartDate;
    private TextView txtStartHour;
    private TextView txtStopDate;
    private TextView txtStopHour;
    private ConstraintLayout ctnAddContacts;
    private ConstraintLayout ctnAddLocation;
    private TextView txtContacts;
    private TextView txtLocation;

    private AddMeetingViewModel mAddMeetingViewModel;

    private DateTimeFormatter mDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DateTimeFormatter mTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        initToolBar();
        btnSave = findViewById(R.id.btn_save_meeting);
        edtxTitle = findViewById(R.id.edtxt_meeting_title);
        txtStartDate = findViewById(R.id.txt_start_date);
        txtStartHour = findViewById(R.id.txt_start_time);
        txtStopDate = findViewById(R.id.txt_stop_date);
        txtStopHour = findViewById(R.id.txt_stop_time);
        ctnAddContacts = findViewById(R.id.container_contacts);
        ctnAddLocation = findViewById(R.id.container_location);
        txtContacts = findViewById(R.id.txt_contacts);
        txtLocation = findViewById(R.id.txt_room);


        mAddMeetingViewModel = ViewModelProviders.of(this).get(AddMeetingViewModel.class);

        txtStartDate.setText(mAddMeetingViewModel.getNowDate());
        txtStopDate.setText(mAddMeetingViewModel.getNowDate());
        txtStartHour.setText(mAddMeetingViewModel.getNowTime());
        txtStopHour.setText(mAddMeetingViewModel.getTimeInOneHour());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddMeetingViewModel.addTitle(edtxTitle.getText().toString());
                mAddMeetingViewModel.saveMeeting();
                finish();
            }
        });

        txtStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(AddMeetingActivity.this,R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        LocalDate date = LocalDate.of(i,i1,i2);
                        String datetxt = date.format(mDateFormatter);
                        txtStartDate.setText(datetxt);
                    }
                },LocalDateTime.now().getYear(),LocalDateTime.now().getMonthValue()-1,LocalDateTime.now().getDayOfMonth());
                dialog.show();
            }
        });

        txtStopDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(AddMeetingActivity.this,R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        LocalDate date = LocalDate.of(i,i1,i2);
                        String datetxt = date.format(mDateFormatter);
                        txtStopDate.setText(datetxt);
                    }
                }, LocalDateTime.now().getYear(),LocalDateTime.now().getMonthValue()-1,LocalDateTime.now().getDayOfMonth());
                dialog.show();

            }
        });

        txtStartHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(AddMeetingActivity.this,R.style.DialogTheme,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        LocalTime time = LocalTime.of(i,i1);
                        String timeTxt= time.format(mTimeFormatter);
                        txtStartHour.setText(timeTxt);
                    }
                }, LocalTime.now().getHour(), LocalTime.now().getMinute(), true);
                dialog.show();
            }
        });

        txtStopHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(AddMeetingActivity.this,R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        LocalTime time = LocalTime.of(i,i1);
                        String timeTxt= time.format(mTimeFormatter);
                        txtStopHour.setText(timeTxt);
                    }
                }, LocalTime.now().getHour(), LocalTime.now().getMinute(), true);
                dialog.show();
            }
        });


        ctnAddContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AddActivityMeetingModel meetingToSave = new AddActivityMeetingModel();

                meetingToSave.setTitle(edtxTitle.getText().toString());
                meetingToSave.setStartDate(txtStartDate.getText().toString());
                meetingToSave.setStartHour(txtStartHour.getText().toString());
                meetingToSave.setStopDate(txtStopDate.getText().toString());
                meetingToSave.setStopHour(txtStopHour.getText().toString());
                meetingToSave.setRoom(txtLocation.getText().toString());
                meetingToSave.setParticipants(txtContacts.getText().toString());

                Intent intent = new Intent(AddMeetingActivity.this, ListContactsActivity.class);
                intent.putExtra(MEETING_EXTRA,meetingToSave);
                startActivityForResult(intent,REQUEST_CODE);
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

    private void initToolBar() {
        Toolbar tlbar = findViewById(R.id.tbar_tb);
        setSupportActionBar(tlbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tlbar.setNavigationIcon(R.drawable.ic_close);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {

        AddActivityMeetingModel meetingToSave = new AddActivityMeetingModel();

        meetingToSave.setTitle(edtxTitle.getText().toString());
        meetingToSave.setStartDate(txtStartDate.getText().toString());
        meetingToSave.setStartHour(txtStartHour.getText().toString());
        meetingToSave.setStopDate(txtStopDate.getText().toString());
        meetingToSave.setStopHour(txtStopHour.getText().toString());
        meetingToSave.setRoom(txtLocation.getText().toString());
        meetingToSave.setParticipants(txtContacts.getText().toString());

        outState.putSerializable(MEETING_KEY,meetingToSave);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {

        AddActivityMeetingModel meetingSaved = (AddActivityMeetingModel) savedInstanceState.getSerializable(MEETING_KEY);

        String title = meetingSaved.getTitle();
        if(title != null){
            edtxTitle.setText(title);
        }
        String startDate = meetingSaved.getStartDate();
        if(startDate != null){
            txtStartDate.setText(startDate);
        }
        String starthour = meetingSaved.getStartHour();
        if(title != null){
            txtStartHour.setText(starthour);
        }
        String stopDate = meetingSaved.getStopDate();
        if(title != null){
            txtStopDate.setText(stopDate);
        }
        String stopHour = meetingSaved.getStopHour();
        if(title != null){
            txtStopHour.setText(stopHour);
        }
        String room = meetingSaved.getRoom();
        if(title != null){
            txtLocation.setText(room);
        }
        String participants = meetingSaved.getParticipants();
        if(title != null){
            txtContacts.setText(participants);
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE){
            AddActivityMeetingModel meetingSaved = (AddActivityMeetingModel)data.getSerializableExtra(MEETING_EXTRA);
            String title = meetingSaved.getTitle();
            if(title != null){
                edtxTitle.setText(title);
            }
            String startDate = meetingSaved.getStartDate();
            if(startDate != null){
                txtStartDate.setText(startDate);
            }
            String starthour = meetingSaved.getStartHour();
            if(title != null){
                txtStartHour.setText(starthour);
            }
            String stopDate = meetingSaved.getStopDate();
            if(title != null){
                txtStopDate.setText(stopDate);
            }
            String stopHour = meetingSaved.getStopHour();
            if(title != null){
                txtStopHour.setText(stopHour);
            }
            String room = meetingSaved.getRoom();
            if(title != null){
                txtLocation.setText(room);
            }
            String participants = meetingSaved.getParticipants();
            if(title != null){
                txtContacts.setText(participants);
            }
        }
    }
}
