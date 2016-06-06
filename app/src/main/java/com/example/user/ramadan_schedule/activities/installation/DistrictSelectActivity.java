package com.example.user.ramadan_schedule.activities.installation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.user.ramadan_schedule.R;
import com.example.user.ramadan_schedule.activities.main.PagerScheduleActivity;
import com.example.user.ramadan_schedule.activities.template.TemplateActivity;
import com.example.user.ramadan_schedule.data.constants.ApplicationConstants;
import com.example.user.ramadan_schedule.data.constants.RegistrationConstants;
import com.example.user.ramadan_schedule.datamodels.District;
import com.example.user.ramadan_schedule.datamodels.interfaces.ITable;
import com.example.user.ramadan_schedule.utils.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class DistrictSelectActivity extends TemplateActivity  {

    private static final String LOG = "DistrictSelectActivity";
    List<District> districtList;
    List<String> districtStringList;
    Spinner districtSpinner;
    Button okayButton;
    ArrayAdapter<String> stringArrayAdapter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_district_select);
        districtSpinner = (Spinner) findViewById(R.id.spinner_district_list);
        okayButton = (Button) findViewById(R.id.button_okay);
        districtList = new ArrayList<District>();
        districtStringList = new ArrayList<String>();
    }

    @Override
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(RegistrationConstants.APPLICATION_PREFERENCE, Context.MODE_PRIVATE);
        String status = sharedPreferences.getString(RegistrationConstants.REGISTRATION_STATUS,"null");
        if (!status.equals(RegistrationConstants.REGISTRATION_COMPLETED)){
                Log.d("DistrictSelectActivity",DataProvider.districts.toString());
                Log.d("DistrictSelectActivity",DataProvider.ramadanDays.toString());
            // insert the input
                for (int i = 0; i < DataProvider.districts.length; i++) {
                    localDataBaseHelper.insertRow(DataProvider.districts[i]);
                }
                for (int i = 0; i < DataProvider.ramadanDays.length; i++) {
                    localDataBaseHelper.insertRow(DataProvider.ramadanDays[i]);
                }
                // load the data of districts
                List<ITable> iTableList = localDataBaseHelper.selectRows(new District());
                for (ITable iTable: iTableList){
                    District district = (District) iTable;
                    districtList.add(district);
                    districtStringList.add(district.toString());
                }
                stringArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,districtStringList);
        }

    }

    @Override
    public void initializeViewByData() {
        districtSpinner.setAdapter(stringArrayAdapter);
    }

    @Override
    public void listenView() {
        districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ApplicationConstants.USER_DISTRICT = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        okayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(RegistrationConstants.APPLICATION_PREFERENCE, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(RegistrationConstants.USER_DISTRICT, ApplicationConstants.USER_DISTRICT);
                editor.putString(RegistrationConstants.REGISTRATION_STATUS, RegistrationConstants.REGISTRATION_COMPLETED);
                editor.commit();
                Intent intent = new Intent(DistrictSelectActivity.this, PagerScheduleActivity.class);
                startActivity(intent);
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences(RegistrationConstants.APPLICATION_PREFERENCE, Context.MODE_PRIVATE);
        String status = sharedPreferences.getString(RegistrationConstants.REGISTRATION_STATUS,"null");
        if (!status.equals("null")){
            if (status.equals(RegistrationConstants.REGISTRATION_COMPLETED)){
                Intent intent = new Intent(this, PagerScheduleActivity.class);
                startActivity(intent);
            }
        }
    }

}
