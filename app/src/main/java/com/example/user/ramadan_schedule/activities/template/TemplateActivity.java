package com.example.user.ramadan_schedule.activities.template;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.user.ramadan_schedule.R;
import com.example.user.ramadan_schedule.activities.main.ListScheduleActivity;
import com.example.user.ramadan_schedule.activities.main.PagerScheduleActivity;
import com.example.user.ramadan_schedule.data.db.DataBaseHelper;
import com.example.user.ramadan_schedule.datamodels.RamadanDay;
import com.example.user.ramadan_schedule.utils.CustomToast;

import java.util.ArrayList;
import java.util.List;

public abstract class TemplateActivity extends AppCompatActivity {

    protected Toolbar templateToolbar;

    protected CustomToast customToast = new CustomToast(this);
    protected DataBaseHelper localDataBaseHelper = new DataBaseHelper(this);

    protected List<RamadanDay> ramadanDayList = new ArrayList<RamadanDay>();

    public abstract void initView();
    public abstract void loadData();
    public abstract void initializeViewByData();
    public abstract void listenView();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }
 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ViewLoadingTask().execute();
    }

    class ViewLoadingTask extends AsyncTask<Void,Void,Void> {
        ProgressDialog progressDialog = new ProgressDialog(TemplateActivity.this);
        @Override
        protected void onPreExecute() {
            initView();
            progressDialog.setMessage("Loading");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            loadData();
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            if (progressDialog.isShowing()){
                progressDialog.dismiss();
            }
            initializeViewByData();
            listenView();
        }
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_list){
            Intent intent = new Intent(this, ListScheduleActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.menu_pager){
            Intent intent = new Intent(this, PagerScheduleActivity.class);
            startActivity(intent);
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}