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
    public int day;
    public int sehrHour;
    public int sehrMinute;
    public int iftrHour;
    public int iftrMinute;


    WhereClause whereClause = new WhereClause();

    public RamadanDay() {
    }

    public RamadanDay(int ramadanDay, int month, int date, int day, int sehrHour, int sehrMinute, int iftrHour, int iftrMinute) {
        this.ramadanDay = ramadanDay;
        this.month = month;
        this.date = date;
        this.day = day;
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
                ""+Variable.GREGORIAN_MONTH+" text," +
                ""+Variable.GREGORIAN_DATE+" integer," +
                ""+Variable.GREGORIAN_DAY+" text," +
                ""+Variable.SEHR_TIME+" text," +
                ""+Variable.IFTR_TIME+" text)";
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
        if (cursor.getColumnIndex(Variable.GREGORIAN_DAY)!=-1){
            ramadanDay.day = cursor.getInt(cursor.getColumnIndex(Variable.GREGORIAN_DAY));
        }
        if (cursor.getColumnIndex(Variable.GREGORIAN_MONTH)!=-1){
            ramadanDay.month = cursor.getInt(cursor.getColumnIndex(Variable.GREGORIAN_MONTH));
        }
        if (cursor.getColumnIndex(Variable.IFTR_TIME)!=-1){
            ramadanDay.iftrMinute = cursor.getInt(cursor.getColumnIndex(Variable.GREGORIAN_DATE));
        }
        if (cursor.getColumnIndex(Variable.RAMADAN_DAY)!=-1){
            ramadanDay.ramadanDay = cursor.getInt(cursor.getColumnIndex(Variable.RAMADAN_DAY));
        }
        if (cursor.getColumnIndex(Variable.SEHR_TIME)!=-1){
            ramadanDay.sehrHour = cursor.getInt(cursor.getColumnIndex(Variable.SEHR_TIME));
        }
        return ramadanDay;
    }

    @Override
    public boolean isCloned(ITable iTable) {
        return false;
    }

    @Override
    public ITable toClone() {
        return null;
    }

    @Override
    public ContentValues getInsertContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Variable.GREGORIAN_DATE, date);
        contentValues.put(Variable.GREGORIAN_DAY, day);
        contentValues.put(Variable.GREGORIAN_MONTH, month);
        contentValues.put(Variable.IFTR_TIME, iftrMinute);
        contentValues.put(Variable.SEHR_TIME, sehrHour);
        contentValues.put(Variable.RAMADAN_DAY,ramadanDay);
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
                GREGORIAN_DAY="gregorian_day",
                SEHR_TIME="sehr_time",
                IFTR_TIME="iftr_time";
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
        localCalendar.set(Calendar.YEAR,2016);//YEAR
        localCalendar.set(Calendar.MONTH,month);//MONTH
        localCalendar.set(Calendar.DATE, date);//DATE
        localCalendar.set(Calendar.DAY_OF_WEEK,day);//DAY
        localCalendar.set(Calendar.HOUR,sehrHour);//HOUR
        localCalendar.set(Calendar.MINUTE,sehrMinute);//MINUTE
        return localCalendar.getTime();
    }

    public Date getIftrDate(){
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.set(Calendar.YEAR,2016);//YEAR
        localCalendar.set(Calendar.MONTH,month);//MONTH
        localCalendar.set(Calendar.DATE, date);//DATE
        localCalendar.set(Calendar.DAY_OF_WEEK,day);//DAY
        localCalendar.set(Calendar.HOUR,iftrHour);//HOUR
        localCalendar.set(Calendar.MINUTE,iftrMinute);//MINUTE
        return localCalendar.getTime();
    }
}
