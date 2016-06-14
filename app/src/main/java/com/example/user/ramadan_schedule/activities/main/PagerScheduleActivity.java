package com.example.user.ramadan_schedule.activities.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.ramadan_schedule.R;
import com.example.user.ramadan_schedule.activities.template.TemplateActivity;
import com.example.user.ramadan_schedule.adapters.CustomPagerAdapter;
import com.example.user.ramadan_schedule.data.constants.ApplicationConstants;
import com.example.user.ramadan_schedule.data.constants.RegistrationConstants;
import com.example.user.ramadan_schedule.datamodels.RamadanDay;
import com.example.user.ramadan_schedule.datamodels.interfaces.OnPagerViewItemListener;
import com.example.user.ramadan_schedule.utils.CustomTime;

import java.util.Calendar;
import java.util.Date;

public class PagerScheduleActivity extends TemplateActivity implements OnPagerViewItemListener {

    ViewPager pagerScheduleViewPager;
    String userDistrict;
    
    @Override
    public void initView() {
        setContentView(R.layout.activity_pager_schedule);
        pagerScheduleViewPager = (ViewPager) findViewById(R.id.view_pager_activity_pager_schedule);
    }

    @Override
    public void loadData() {
        ramadanDayList = RamadanDay.toRamadanDays(localDataBaseHelper.selectRows(new RamadanDay()));
        SharedPreferences sharedPreferences = getSharedPreferences(RegistrationConstants.APPLICATION_PREFERENCE, Context.MODE_PRIVATE);
        userDistrict = sharedPreferences.getString(RegistrationConstants.USER_DISTRICT, ApplicationConstants.USER_DEFAULT_DISTRICT);
    }
    @Override
    public void initializeViewByData() {
        pagerScheduleViewPager.setAdapter(new CustomPagerAdapter(this, ramadanDayList.size()));
        pagerScheduleViewPager.setCurrentItem(getCurrentItem());
    }

    @Override
    public void listenView() {

    }

    @Override
    public View objectInitiateView(ViewGroup container, int position) {

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.card_pager_item,container,false);

        TextView sherTimeTextView = (TextView) view.findViewById(R.id.text_card_pager_sehr_time);
        TextView iftrTimeTextView = (TextView) view.findViewById(R.id.text_card_pager_iftr_time);
        TextView ramadanDayTextView = (TextView) view.findViewById(R.id.text_card_pager_ramadan_day);
        TextView calendarDateTextView = (TextView) view.findViewById(R.id.text_card_pager_calendar_date);
        TextView districtTextView = (TextView) view.findViewById(R.id.text_card_pager_district);

        Date sherDate = ramadanDayList.get(position).getSehrDate();
        Date iftDate = ramadanDayList.get(position).getIftrDate();

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(sherDate);
        sherTimeTextView.setText(calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + " " + CustomTime.convertToAMPM(calendar));

        calendar.setTime(iftDate);
        iftrTimeTextView.setText(calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + " " + CustomTime.convertToAMPM(calendar));

        ramadanDayTextView.setText("Ramadan "+ramadanDayList.get(position).ramadanDay);
        calendarDateTextView.setText(CustomTime.convertToDayString(calendar)+" "+CustomTime.convertToMonthString(calendar) + " " + ramadanDayList.get(position).date + ", " + ramadanDayList.get(position).year);


        districtTextView.setText(userDistrict);

        ((ViewPager) container).addView(view);
        return view;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
