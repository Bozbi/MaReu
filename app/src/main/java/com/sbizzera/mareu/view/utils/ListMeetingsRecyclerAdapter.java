package com.sbizzera.mareu.view.utils;

import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sbizzera.mareu.R;
import com.sbizzera.mareu.model.Meeting;
import com.sbizzera.mareu.view.ListMeetingsActivity;
import com.sbizzera.mareu.view.MeetingsUiModel;
import com.sbizzera.mareu.viewmodel.ListMeetingsViewModel;

import java.util.List;

/**
 * Creates by Boris SBIZZERA on 03/09/2019.
 */
public class ListMeetingsRecyclerAdapter extends RecyclerView.Adapter<ListMeetingsRecyclerAdapter.MeetingViewHolder> {

    private List<MeetingsUiModel> mAllMeetings;
    private OnMeetingClickListener mOnMeetingClickListener;

    public ListMeetingsRecyclerAdapter(OnMeetingClickListener onMeetingClickListener){
        mOnMeetingClickListener = onMeetingClickListener;
    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meeting_item, parent, false);
        return new MeetingViewHolder(view,mOnMeetingClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {
        MeetingsUiModel currentMeeting = mAllMeetings.get(position);
        holder.mMeetingTitleTextView.setText(currentMeeting.getListMeetingsTitle());
        holder.mMeetingDateandRoomTextView.setText(currentMeeting.getMeetingDateAndRoom());
        holder.mMeetingParticiapantsTextView.setText(currentMeeting.getListMeetingsParticipants());
        holder.mImageView.setColorFilter(holder.mImageView.getContext().getColor(currentMeeting.getListMeetingsColor()), PorterDuff.Mode.MULTIPLY);

    }

    @Override
    public int getItemCount() {
        if (mAllMeetings == null){
            return 0;
        }
        return mAllMeetings.size();
    }

    public void setAllMeetingsList(List<MeetingsUiModel> allMeetings) {
        mAllMeetings = allMeetings;
        notifyDataSetChanged();
    }


    public class MeetingViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

        public TextView mMeetingTitleTextView;
        public TextView mMeetingDateandRoomTextView;
        public TextView mMeetingParticiapantsTextView;
        public ImageView mDeleteImageView;
        public ImageView mImageView;

        private OnMeetingClickListener mOnMeetingClickListener;

        public MeetingViewHolder(@NonNull View itemView, OnMeetingClickListener onMeetingClickListener) {
            super(itemView);
            mMeetingTitleTextView = itemView.findViewById(R.id.textview_meeting_title);
            mMeetingParticiapantsTextView = itemView.findViewById(R.id.textview_meeting_participants);
            mDeleteImageView = itemView.findViewById(R.id.imageview_delete_meeting);
            mMeetingDateandRoomTextView = itemView.findViewById(R.id.txt_meeting_date_and_room);
            mImageView = itemView.findViewById(R.id.imageview_meeting_color);
            mDeleteImageView.setOnClickListener(this);
            mOnMeetingClickListener = onMeetingClickListener;
        }

        @Override
        public void onClick(View view) {
            mOnMeetingClickListener.onDeleteClick(mAllMeetings.get(getAdapterPosition()));
        }
    }

    public interface OnMeetingClickListener{
        void onDeleteClick(MeetingsUiModel meeting);
    }
}
