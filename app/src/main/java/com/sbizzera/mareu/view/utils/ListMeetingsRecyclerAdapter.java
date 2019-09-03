package com.sbizzera.mareu.view.utils;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Creates by Boris SBIZZERA on 03/09/2019.
 */
public class ListMeetingsRecyclerAdapter extends RecyclerView.Adapter {

    private class MeetingViewHolder extends RecyclerView.ViewHolder{
        public MeetingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
