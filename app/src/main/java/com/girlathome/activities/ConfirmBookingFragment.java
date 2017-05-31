package com.girlathome.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.girlathome.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by steve on 5/13/17.
 */
public class ConfirmBookingFragment extends Fragment {
    private static final String TAG = ConfirmBookingFragment.class.getSimpleName();
    Activity parentActivity;
    EditText edMessage;
    @BindView(R.id.note_preview)
    TextView tvNotePreview;
    @BindView(R.id.note_layout)
    CardView noteCardView;
    String message;


    public ConfirmBookingFragment() {
    }


    public static ConfirmBookingFragment newInstance() {
        ConfirmBookingFragment fragment = new ConfirmBookingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: hit");
        super.onCreate(savedInstanceState);
    }

    /**
     * Change the null parameter in {@code inflater.inflate()}
     * to a layout resource {@code R.layout.example}
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: hit");
        View rootView = inflater.inflate(R.layout.confirm_booking_layout, container, false);
        ButterKnife.bind(this, rootView);
//        title
        ((BookingActivity) parentActivity).setUpTitle(getString(R.string.confirm_booking));
        setViews();
        return rootView;
    }

    private void setViews() {
        noteCardView.setVisibility(View.GONE);
    }

    @OnClick(R.id.add_note_layout)
    void addNote() {
        showAddNoteDialog();
    }

    @OnClick(R.id.cancel)
    void removeNote() {
        noteCardView.setVisibility(View.GONE);
        message="";
    }

    private void showAddNoteDialog() {
        // custom dialog
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_note_dialog, null);
        dialogBuilder.setView(dialogView);

        edMessage = (EditText) dialogView.findViewById(R.id.ed_message);
        edMessage.setText(message);

        //dialogBuilder.setTitle("Add note");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                 message = edMessage.getText().toString();
                //do something with edt.getText().toString();
                if (!TextUtils.isEmpty(message)) {
                    noteCardView.setVisibility(View.VISIBLE);
                    tvNotePreview.setText(message);
                }

            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: hit");
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onResume() {
        Log.d(TAG, "onResume: hit");
        super.onResume();
    }


    @Override
    public void onPause() {
        Log.d(TAG, "onPause: hit");
        super.onPause();
    }


    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView: hit");
        super.onDestroyView();
    }


    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: hit");
        super.onDestroy();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        parentActivity = activity;
    }
} 