package com.sbizzera.mareu.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.sbizzera.mareu.R;
import com.sbizzera.mareu.model.MeetingRoom;
import com.sbizzera.mareu.viewmodel.ListMeetingsViewModel;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;

/**
 * Creates by Boris SBIZZERA on 04/09/2019.
 */
public class FilterDialog extends AppCompatDialogFragment {


    private TextView mTxtDate;
    private TextView mTxtRoom;
    private ImageView mImgCloseDate;
    private ImageView mImgCloseRoom;

    private ListMeetingsViewModel mListMeetingsViewModel;

    public FilterDialog() {
        super();
    }

    public static FilterDialog newInstance(ListMeetingsViewModel listMeetingsViewModel) {
        Bundle args = new Bundle();
        args.putSerializable("LISTMEETINGSVIEWMODEL_EXTRA", listMeetingsViewModel);
        FilterDialog dialog = new FilterDialog();
        dialog.setArguments(args);
        return dialog;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        mListMeetingsViewModel = (ListMeetingsViewModel) getArguments().getSerializable("LISTMEETINGSVIEWMODEL_EXTRA");

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_filter, null);

        mTxtDate = view.findViewById(R.id.txt_filter_dialog_time);
        mTxtRoom = view.findViewById(R.id.txt_filter_dialog_room);
        mImgCloseDate = view.findViewById(R.id.img_filter_dialog_time_close);
        mImgCloseRoom = view.findViewById(R.id.img_filter_dialog_room_close);

        mTxtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        LocalDate date = LocalDate.of(i, i1 + 1, i2);
                        String datetxt = date.format(mListMeetingsViewModel.getDateFormatter());
                        mTxtDate.setText(datetxt);
                        mImgCloseDate.setVisibility(View.VISIBLE);
                        mImgCloseDate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mImgCloseDate.setVisibility(View.INVISIBLE);
                                mTxtDate.setText(null);
                            }
                        });
                    }
                }, LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue() - 1, LocalDateTime.now().getDayOfMonth());
                dialog.show();


            }
        });

        mTxtRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                mBuilder.setTitle("Choisissez une Salle de Réunion");
                final String[] listRooms = MeetingRoom.getRoomsList();

                mBuilder.setSingleChoiceItems(listRooms, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        mTxtRoom.setText(listRooms[i]);
                        mImgCloseRoom.setVisibility(View.VISIBLE);
                        mImgCloseRoom.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mTxtRoom.setText(null);
                                mImgCloseRoom.setVisibility(View.INVISIBLE);
                            }
                        });
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();

            }
        });


        builder.setView(view)
                .setPositiveButton("Filtrer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mTxtDate.setText(null);
                        mTxtRoom.setText(null);
                    }
                });

        builder.setTitle("Filtrer par Date et/ou par Salle");


        return builder.create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        ListMeetingsViewModel viewModel = (ListMeetingsViewModel) getArguments().getSerializable("LISTMEETINGSVIEWMODEL_EXTRA");
        viewModel.setFilters(mTxtDate.getText().toString(), mTxtRoom.getText().toString());
        super.onDismiss(dialog);
    }
}
