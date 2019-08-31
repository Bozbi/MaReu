package com.sbizzera.mareu;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class ListMeetingsActivity extends AppCompatActivity {

    private ListMeetingsViewModel mListMeetingsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meetings);



        mListMeetingsViewModel = ViewModelProviders.of(this).get(ListMeetingsViewModel.class);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.list_meetings_menu,menu);
        return true;
    }
}
