package com.sbizzera.mareu.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.sbizzera.mareu.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Creates by Boris SBIZZERA on 05/09/2019.
 */
public class AddAndEditMeetingDialog extends AppCompatDialogFragment {

    private EditText mEditText;
    private SmartMaterialSpinner mSpinMeetingRoom;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_edit_meeting, null);


        builder.setView(view)
                .setPositiveButton("Enregistrer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });


        mEditText = view.findViewById(R.id.edtxt_meeting_date);

        mEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseDateDialog chooseDateDialog  =  new ChooseDateDialog();
                chooseDateDialog.show(getFragmentManager(),"Choose Date Dialog");
            }
        });
        mSpinMeetingRoom = view.findViewById(R.id.spin_meeting_room);
        List<String> roomList = new ArrayList<>();
        roomList.add("Peach");
        roomList.add("Luigi");
        roomList.add("Mario");
        roomList.add("Toad");
        roomList.add("Koopa");
        roomList.add("Boo");
        roomList.add("Bowser");
        roomList.add("Yoshi");
        roomList.add("Goomer");
        roomList.add("Wario");
        mSpinMeetingRoom.setItem(roomList);
//        mSpinMeetingRoom.setSelection(8,true);


        return builder.create();
    }
}


