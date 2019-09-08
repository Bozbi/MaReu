package com.sbizzera.mareu.view.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sbizzera.mareu.R;

/**
 * Creates by Boris SBIZZERA on 08/09/2019.
 */
public class MeetingParticipantRecyclerViewAdapter extends RecyclerView.Adapter<MeetingParticipantRecyclerViewAdapter.MeetingParticipantViewHolder> {


    @NonNull
    @Override
    public MeetingParticipantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingParticipantViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    
    public class MeetingParticipantViewHolder extends RecyclerView.ViewHolder{

        private TextView mEmail;
        private ImageView mRemove;

        public MeetingParticipantViewHolder(@NonNull View itemView) {
            super(itemView);
            mEmail = itemView.findViewById(R.id.txt_participant_email);
            mRemove = itemView.findViewById(R.id.img_remove_participant_email);
        }
    }
}
