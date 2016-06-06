package com.example.user.ramadan_schedule.activities.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.user.ramadan_schedule.R;
import com.example.user.ramadan_schedule.activities.template.TemplateActivity;
import com.example.user.ramadan_schedule.adapters.RecyclerViewListAdapter;
import com.example.user.ramadan_schedule.datamodels.RamadanDay;
import com.example.user.ramadan_schedule.datamodels.interfaces.OnRecyclerViewItemListener;

import java.util.List;

public class ListScheduleActivity extends TemplateActivity implements OnRecyclerViewItemListener {

    RecyclerView listScheduleRecyclerView;

    @Override
    public void initView() {
        setContentView(R.layout.activity_list_schedule);
        listScheduleRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_activity_list_schedule);
    }

    @Override
    public void loadData() {
        ramadanDayList = RamadanDay.toRamadanDays(localDataBaseHelper.selectRows(new RamadanDay()));
    }

    @Override
    public void initializeViewByData() {
        listScheduleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        listScheduleRecyclerView.setAdapter(new RecyclerViewListAdapter(this,R.layout.card_recycler_item,ramadanDayList.size()));
    }

    @Override
    public void listenView() {

    }

    @Override
    public void listenItem(View view, int position) {

    }
}
