package com.example.user.ramadan_schedule.datamodels;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.user.ramadan_schedule.datamodels.interfaces.ITable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by chandradasdipok on 6/5/2016.
 */
public class RamadanDay implements ITable {

    public int ramadanDay;
    public int month;
    public int date;
    public int year;
    public int sehrHour;
    public int sehrMinute;
    public int iftrHour;
    public int iftrMinute;


    WhereClause whereClause = new WhereClause();

    public RamadanDay() {
    }

    public RamadanDay(int ramadanDay, int month, int date, int year, int sehrHour, int sehrMinute, int iftrHour, int iftrMinute) {
        this.ramadanDay = ramadanDay;
        this.month = month;
        this.date = date;
        this.year = year;
        this.sehrHour = sehrHour;
        this.sehrMinute = sehrMinute;
        this.iftrHour = iftrHour;
        this.iftrMinute = iftrMinute;
    }

    @Override
    public String tableName() {
        return "RamadanDay";
    }

    @Override
    public String toCreateTableString() {
        return "create table if not exists "+tableName()+" (" +
                ""+Variable.RAMADAN_DAY+" integer primary key ," +
                ""+Variable.GREGORIAN_MONTH+" integer," +
                ""+Variable.GREGORIAN_DATE+" integer," +
                ""+Variable.GREGORIAN_YEAR +" integer," +
                ""+Variable.SEHR_HOUR +" integer," +
                ""+Variable.SEHR_MINUTE +" integer," +
                ""+Variable.IFTR_HOUR +" integer," +
                ""+Variable.IFTR_MINUTE +" integer )";
    }

    @Override
    public String toInsertString() {
        return null;
    }

    @Override
    public String toSelectString() {
        return "select * from "+tableName()+" where "+getWhereClauseString();
    }

    @Override
    public String toDeleteSingleRowString() {
        return null;
    }

    @Override
    public String toDeleteRows() {
        return null;
    }

    @Override
    public String toSelectSingleRowString() {
        return null;
    }

    @Override
    public ITable toITableFromCursor(Cursor cursor) {
        RamadanDay ramadanDay = new RamadanDay();
        if (cursor.getColumnIndex(Variable.GREGORIAN_DATE)!=-1){
            ramadanDay.date = cursor.getInt(cursor.getColumnIndex(Variable.GREGORIAN_DATE));
        }
        if (cursor.getColumnIndex(Variable.GREGORIAN_YEAR)!=-1){
            ramadanDay.year = cursor.getInt(cursor.getColumnIndex(Variable.GREGORIAN_YEAR));
        }
        if (cursor.getColumnIndex(Variable.GREGORIAN_MONTH)!=-1){
            ramadanDay.month = cursor.getInt(cursor.getColumnIndex(Variable.GREGORIAN_MONTH));
        }
        if (cursor.getColumnIndex(Variable.RAMADAN_DAY)!=-1){
            ramadanDay.ramadanDay = cursor.getInt(cursor.getColumnIndex(Variable.RAMADAN_DAY));
        }
        if (cursor.getColumnIndex(Variable.SEHR_HOUR)!=-1){
            ramadanDay.sehrHour = cursor.getInt(cursor.getColumnIndex(Variable.SEHR_HOUR));
        }
        if (cursor.getColumnIndex(Variable.SEHR_MINUTE)!=-1){
            ramadanDay.sehrMinute = cursor.getInt(cursor.getColumnIndex(Variable.SEHR_MINUTE));
        }
        if (cursor.getColumnIndex(Variable.IFTR_HOUR)!=-1){
            ramadanDay.iftrHour = cursor.getInt(cursor.getColumnIndex(Variable.IFTR_HOUR));
        }
        if (cursor.getColumnIndex(Variable.IFTR_MINUTE)!=-1){
            ramadanDay.iftrMinute = cursor.getInt(cursor.getColumnIndex(Variable.IFTR_MINUTE));
        }
        return ramadanDay;
    }

    @Override
    public boolean isCloned(ITable iTable) {
        return false;
    }

    @Override
    public String toString() {
        return "("+ramadanDay+","+date+","+year+","+sehrHour+":"+sehrMinute+","+iftrHour+":"+iftrMinute+")";
    }

    @Override
    public ITable toClone() {
        return null;
    }

    @Override
    public ContentValues getInsertContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Variable.GREGORIAN_DATE, date);
        contentValues.put(Variable.GREGORIAN_YEAR, year);
        contentValues.put(Variable.GREGORIAN_MONTH, month);
        contentValues.put(Variable.IFTR_HOUR, iftrHour);
        contentValues.put(Variable.SEHR_HOUR, sehrHour);
        contentValues.put(Variable.RAMADAN_DAY,ramadanDay);
        contentValues.put(Variable.SEHR_MINUTE, sehrMinute);
        contentValues.put(Variable.IFTR_MINUTE,iftrMinute);
        return contentValues;
    }

    @Override
    public void setUpdateContentValues(ContentValues updateContentValues) {

    }

    @Override
    public ContentValues getUpdateContentValues() {
        return null;
    }

    @Override
    public String getWhereClauseString() {
        return whereClause.toString();
    }

    public static class Variable{
        public static final String
                RAMADAN_DAY="ramadan_day",
                GREGORIAN_MONTH="gregorian_month",
                GREGORIAN_DATE="gregorian_date",
                GREGORIAN_YEAR ="gregorian_year",
                SEHR_HOUR ="sehr_hour",
                SEHR_MINUTE="sehr_minute",
                IFTR_HOUR ="iftr_hour",
                IFTR_MINUTE="iftr_minute";
    }

    public static List<RamadanDay> toRamadanDays(List<ITable> iTableList){
        List<RamadanDay> ramadanDays = new ArrayList<RamadanDay>();
        for (ITable iTable: iTableList){
            ramadanDays.add((RamadanDay) iTable);
        }
        return ramadanDays;
    }

    public Date getSehrDate(){
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.set(Calendar.AM_PM,Calendar.AM);
        localCalendar.set(Calendar.MONTH,month);//MONTH
        localCalendar.set(Calendar.DATE, date);//DATE
        localCalendar.set(Calendar.YEAR,year);//YEAR
        localCalendar.set(Calendar.HOUR,sehrHour);//HOUR
        localCalendar.set(Calendar.MINUTE,sehrMinute);//MINUTE
        return localCalendar.getTime();
    }

    public Date getIftrDate(){
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.set(Calendar.AM_PM,Calendar.PM);
        localCalendar.set(Calendar.MONTH,month);//MONTH
        localCalendar.set(Calendar.DATE, date);//DATE
        localCalendar.set(Calendar.YEAR,year);//YEAR
        localCalendar.set(Calendar.HOUR,iftrHour);//HOUR
        localCalendar.set(Calendar.MINUTE,iftrMinute);//MINUTE
        return localCalendar.getTime();
    }
}
