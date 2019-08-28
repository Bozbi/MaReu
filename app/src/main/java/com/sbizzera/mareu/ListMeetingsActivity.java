package com.sbizzera.mareu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ListMeetingsActivity extends AppCompatActivity {

    private static final String TAG = "ListMeetingsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meetings);

        TextView text = findViewById(R.id.text);


    }
}
