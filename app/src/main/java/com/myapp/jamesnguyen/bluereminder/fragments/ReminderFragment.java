package com.myapp.jamesnguyen.bluereminder.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.myapp.jamesnguyen.bluereminder.activities.MainActivity;
import com.myapp.jamesnguyen.bluereminder.R;
import com.myapp.jamesnguyen.bluereminder.item_touch_helper.ItemTouchHelperCallback;
import com.myapp.jamesnguyen.bluereminder.recycler_view.ReminderAdapter;
import com.myapp.jamesnguyen.bluereminder.room.ItemEntity;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

/**
 * Created by jamesnguyen on 3/16/18.
 */

public class ReminderFragment extends Fragment {

    public static final String TAG = "ReminderFragmentTag";
    public static final String ITEM_EXTRA= "item_extra";
    public static final String POSITION_EXTRA = "position_extra";
    public static final int REQUEST_CODE = 0;
    RecyclerView mRecyclerView;
    ReminderAdapter mReminderAdapter;
    FloatingActionButton fab;
    int loadMode;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mReminderAdapter = new ReminderAdapter(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reminder_list_fragment, container,  false);
        mRecyclerView = view.findViewById(R.id.reminder_list_fragment);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fab = container.getRootView().findViewById(R.id.fab);

        //TODO Turn on loading icon here
        setReminderAdapter();
        setItemTouchHelper();
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        ((MainActivity)getActivity()).turnOffFabButton(fab);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).turnOnFabButton(fab);
        ((MainActivity)getActivity()).setToolBarTitle();
    }

    public void setReminderAdapter(){
        mRecyclerView.setAdapter(mReminderAdapter);
    }

    public void setItemTouchHelper(){
        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(getContext(), mReminderAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecyclerView);
    }
    public void turnOnLoadingIcon(){
        //TODO turn on loading icon
    }

    public void turnOffLoadingIcon(){
        //TODO Turn off loading icon
    }

    public static Intent createItent(ItemEntity item, int position){
        Intent intent =new Intent();
        intent.putExtra(ITEM_EXTRA, item);
        intent.putExtra(POSITION_EXTRA, position);
        return intent;
    }

    public void updateDatabase(List<ItemEntity> items){
        turnOffLoadingIcon();
        mReminderAdapter.updateItemList(items);
        ((MainActivity)getActivity()).setToolBarTitle();
    }

    public static ReminderFragment newInstance(){
        return new ReminderFragment();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            ItemEntity item = data.getParcelableExtra(ITEM_EXTRA);
            int position = data.getIntExtra(POSITION_EXTRA, -1);
            if(position!=-1) {
                 mReminderAdapter.onUpdateItemAt(item, position);
            }
        } else if(resultCode==ItemEditFragment.REMOVE_ITEM_REQUEST_CODE){
            int position = data.getIntExtra(POSITION_EXTRA,-1);
            if(position!=-1) {
                mReminderAdapter.onDeleteItemAt(position);
             }
        }
    }
}
