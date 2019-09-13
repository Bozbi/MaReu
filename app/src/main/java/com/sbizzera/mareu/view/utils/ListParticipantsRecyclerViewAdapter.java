package com.sbizzera.mareu.view.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sbizzera.mareu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates by Boris SBIZZERA on 10/09/2019.
 */
public class ListParticipantsRecyclerViewAdapter extends RecyclerView.Adapter<ListParticipantsRecyclerViewAdapter.ParticipantViewHolder> {

    private List<String> mParticipantsList = new ArrayList<>();

    @NonNull
    @Override
    public ParticipantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.participant_item,parent,false);
        return new ParticipantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParticipantViewHolder holder, int position) {
        holder.mTxtParticipant.setText( mParticipantsList.get(position));
    }



    @Override
    public int getItemCount() {
        return mParticipantsList.size();
    }

    public class ParticipantViewHolder extends RecyclerView.ViewHolder{

        public TextView mTxtParticipant;
        public ImageView mImgDeleteParticipant;
        public ParticipantViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtParticipant = itemView.findViewById(R.id.txt_participant_item);
            mImgDeleteParticipant = itemView.findViewById(R.id.img_delete_participant);
        }
    }
}
