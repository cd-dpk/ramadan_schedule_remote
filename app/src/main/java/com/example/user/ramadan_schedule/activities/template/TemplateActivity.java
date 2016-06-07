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
import com.example.user.ramadan_schedule.data.constants.ApplicationConstants;
import com.example.user.ramadan_schedule.data.db.DataBaseHelper;
import com.example.user.ramadan_schedule.datamodels.District;
import com.example.user.ramadan_schedule.datamodels.RamadanDay;
import com.example.user.ramadan_schedule.datamodels.interfaces.ITable;
import com.example.user.ramadan_schedule.utils.CustomToast;

import java.util.ArrayList;
import java.util.List;

public abstract class TemplateActivity extends AppCompatActivity {

    protected Toolbar templateToolbar;
    protected int numberOfBackButtonTyped = 0;
    protected long lastBackButtonTypedInMilliseconds = 0;
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
        else if(item.getItemId() == R.id.menu_switch_district){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Switch District");
            List<ITable>iTables = localDataBaseHelper.selectRows(new District());
            String[] iTablesString = new String[iTables.size()];
            for (int i = 0; i < iTables.size(); i++) {
                iTablesString[i] = iTables.get(i).toString();
            }
            alertDialogBuilder.setItems(iTablesString, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialogBuilder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        if (numberOfBackButtonTyped == ApplicationConstants.BACK_BUTTON_INITIALIZE_STATE){
            numberOfBackButtonTyped = ApplicationConstants.BACK_BUTTON_TYPED_ONCE;
            lastBackButtonTypedInMilliseconds = System.currentTimeMillis();
        }
        else if(numberOfBackButtonTyped == ApplicationConstants.BACK_BUTTON_TYPED_ONCE){
            if ((System.currentTimeMillis()-lastBackButtonTypedInMilliseconds)<(5*1000)){
                numberOfBackButtonTyped = ApplicationConstants.BACK_BUTTON_TYPED_TWICE;
            }
            else {
                numberOfBackButtonTyped = ApplicationConstants.BACK_BUTTON_INITIALIZE_STATE;
            }
        }
        if (numberOfBackButtonTyped == ApplicationConstants.BACK_BUTTON_TYPED_ONCE){
            customToast.showShortToast("Press back once more to exit");
        }
        else if (numberOfBackButtonTyped == ApplicationConstants.BACK_BUTTON_TYPED_TWICE){
            numberOfBackButtonTyped = ApplicationConstants.BACK_BUTTON_INITIALIZE_STATE;
            lastBackButtonTypedInMilliseconds = 0;
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}