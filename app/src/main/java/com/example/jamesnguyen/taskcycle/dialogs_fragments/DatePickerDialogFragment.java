package com.example.jamesnguyen.taskcycle.dialogs_fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import com.example.jamesnguyen.taskcycle.fragments.ItemEditFragment;
import com.example.jamesnguyen.taskcycle.room.ItemEntity;

import java.util.Calendar;

public class DatePickerDialogFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    public static final String TAG = "DatePickerDialogFragment";
    public static final String DATE_ARGS = "date_args";
    public static final String MONTH_EXTRA = "month_extra";
    public static final String DAY_EXTRA = "day_extra";
    public static final String YEAR_EXTRA = "year_extra";

    Calendar calendar;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle args = getArguments();
        //Calendar calendar = null;
        if(args!=null){
            calendar = (Calendar) args.getSerializable(DATE_ARGS);
        } else {
            calendar = Calendar.getInstance();
        }
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public static DatePickerDialogFragment newInstance(ItemEntity item){
        Calendar date = Calendar.getInstance();
        DatePickerDialogFragment fragment = new DatePickerDialogFragment();

        if(item!=null){
            date.setTimeInMillis(item.getDate());
            Bundle args = new Bundle();
            args.putSerializable(DATE_ARGS, date);
            fragment.setArguments(args);
        }
        return fragment;
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Intent intent = new Intent();

//        ca.putExtra(YEAR_EXTRA, year);
//        intent.putExtra(MONTH_EXTRA, month);
//        intent.putExtra(DAY_EXTRA, dayOfMonth);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        intent.putExtra(ItemEditFragment.CALENDAR_EXTRA, calendar);

        getTargetFragment().onActivityResult(ItemEditFragment.REQUEST_CODE,
                Activity.RESULT_OK, intent);
    }
}