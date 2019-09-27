package com.sbizzera.mareu.view.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sbizzera.mareu.R;

import java.util.List;

/**
 * Creates by Boris SBIZZERA on 10/09/2019.
 */
public class ListContactsRecyclerViewAdapter extends RecyclerView.Adapter<ListContactsRecyclerViewAdapter.ParticipantViewHolder> {

    private List<String> mParticipantsList ;
    private OnDeleteParticipantClickListener mOnDeleteParticipantClickListener;

    public ListContactsRecyclerViewAdapter(List<String> participantsList, OnDeleteParticipantClickListener onDeleteParticipantClickListener) {
        super();
        mParticipantsList = participantsList;
        mOnDeleteParticipantClickListener = onDeleteParticipantClickListener;
    }

    @NonNull
    @Override
    public ParticipantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.participant_item,parent,false);
        return new ParticipantViewHolder(view,mOnDeleteParticipantClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ParticipantViewHolder holder, int position) {
        holder.mTxtParticipant.setText( mParticipantsList.get(position));
    }



    @Override
    public int getItemCount() {
        return mParticipantsList.size();
    }

    public class ParticipantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnDeleteParticipantClickListener mOnDeleteParticipantClickListener;

        public TextView mTxtParticipant;
        public ImageView mImgDeleteParticipant;

        public ParticipantViewHolder(@NonNull View itemView, OnDeleteParticipantClickListener onDeleteParticipantClickListener) {
            super(itemView);
            mTxtParticipant = itemView.findViewById(R.id.txt_participant_item);
            mImgDeleteParticipant = itemView.findViewById(R.id.img_delete_participant);
            mImgDeleteParticipant.setOnClickListener(this);
            mOnDeleteParticipantClickListener=onDeleteParticipantClickListener;
        }

        @Override
        public void onClick(View view) {
            mOnDeleteParticipantClickListener.deleteParticipant(getAdapterPosition());

        }
    }

    public interface OnDeleteParticipantClickListener{
        void deleteParticipant(int position);
    }
}
