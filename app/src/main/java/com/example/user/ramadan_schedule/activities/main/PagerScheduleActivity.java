package com.example.user.ramadan_schedule.activities.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.ramadan_schedule.R;
import com.example.user.ramadan_schedule.activities.template.TemplateActivity;
import com.example.user.ramadan_schedule.adapters.CustomPagerAdapter;
import com.example.user.ramadan_schedule.data.constants.ApplicationConstants;
import com.example.user.ramadan_schedule.data.constants.RegistrationConstants;
import com.example.user.ramadan_schedule.datamodels.District;
import com.example.user.ramadan_schedule.datamodels.RamadanDay;
import com.example.user.ramadan_schedule.datamodels.interfaces.OnPagerViewItemListener;
import com.example.user.ramadan_schedule.utils.CustomTime;
import com.example.user.ramadan_schedule.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PagerScheduleActivity extends TemplateActivity implements OnPagerViewItemListener {

    ViewPager pagerScheduleViewPager;
    String userDistrict;
    TabLayout tabLayout;
    List<Fragment> fragmentList;
    Toolbar toolbar;

    @Override
    public void initView() {
        setContentView(R.layout.activity_pager_schedule);
        pagerScheduleViewPager = (ViewPager) findViewById(R.id.view_pager_activity_pager_schedule);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout_activity_pager_schedule);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fragmentList = new ArrayList<Fragment>();
    }

    @Override
    public void loadData() {
        ramadanDayList = RamadanDay.toRamadanDays(localDataBaseHelper.selectRows(new RamadanDay()));
        SharedPreferences sharedPreferences = getSharedPreferences(RegistrationConstants.APPLICATION_PREFERENCE, Context.MODE_PRIVATE);
        userDistrict = sharedPreferences.getString(RegistrationConstants.USER_DISTRICT, ApplicationConstants.USER_DEFAULT_DISTRICT);
    }
    @Override
    public void initializeViewByData() {
        for (int i = 0; i < ramadanDayList.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(Utils.convertDigitToNumberString(i+1)));
        }
        pagerScheduleViewPager.setAdapter(new CustomPagerAdapter(this,ramadanDayList.size()));
        pagerScheduleViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pagerScheduleViewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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

        District district = new District();
        district.districtName = userDistrict;
        district.whereClause.addYESWhereClauseString(District.Variable.DISTRICT_NAME,district.districtName);
        district = (District) localDataBaseHelper.selectRow(district);


        Date sherDate = ramadanDayList.get(position).getSehrDate();
        Date iftDate = ramadanDayList.get(position).getIftrDate();

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(sherDate);
        calendar.add(Calendar.MINUTE, district.sehrTimeCorrection);
        sherTimeTextView.setText(calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + " " + CustomTime.convertToAMPM(calendar));

        calendar.setTime(iftDate);
        calendar.add(Calendar.MINUTE,district.iftrTimeCorrection);
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
