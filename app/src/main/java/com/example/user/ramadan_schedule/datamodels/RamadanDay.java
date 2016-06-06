package com.example.user.ramadan_schedule.datamodels;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.user.ramadan_schedule.datamodels.interfaces.ITable;
import com.example.user.ramadan_schedule.utils.CustomTime;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chandradasdipok on 6/5/2016.
 */
public class RamadanDay implements ITable {

    public int ramadanDay;
    public String gregorianMonth;
    public int gregorianDate;
    public String gregorianDay;
    public String  sehrTime;
    public String  iftrTime;

    WhereClause whereClause = new WhereClause();

    public RamadanDay() {
    }

    public RamadanDay(int ramadanDay, String gregorianMonth, int gregorianDate, String gregorianDay, String sehrTime, String iftrTime) {
        this.ramadanDay = ramadanDay;
        this.gregorianMonth = gregorianMonth;
        this.gregorianDate = gregorianDate;
        this.gregorianDay = gregorianDay;
        this.sehrTime = sehrTime;
        this.iftrTime = iftrTime;
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
            ramadanDay.gregorianDate = cursor.getInt(cursor.getColumnIndex(Variable.GREGORIAN_DATE));
        }
        if (cursor.getColumnIndex(Variable.GREGORIAN_DAY)!=-1){
            ramadanDay.gregorianDay = cursor.getString(cursor.getColumnIndex(Variable.GREGORIAN_DAY));
        }
        if (cursor.getColumnIndex(Variable.GREGORIAN_MONTH)!=-1){
            ramadanDay.gregorianMonth = cursor.getString(cursor.getColumnIndex(Variable.GREGORIAN_MONTH));
        }
        if (cursor.getColumnIndex(Variable.IFTR_TIME)!=-1){
            ramadanDay.iftrTime = cursor.getString(cursor.getColumnIndex(Variable.GREGORIAN_DATE));
        }
        if (cursor.getColumnIndex(Variable.RAMADAN_DAY)!=-1){
            ramadanDay.ramadanDay = cursor.getInt(cursor.getColumnIndex(Variable.RAMADAN_DAY));
        }
        if (cursor.getColumnIndex(Variable.SEHR_TIME)!=-1){
            ramadanDay.sehrTime = cursor.getString(cursor.getColumnIndex(Variable.SEHR_TIME));
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
        contentValues.put(Variable.GREGORIAN_DATE,gregorianDate);
        contentValues.put(Variable.GREGORIAN_DAY,gregorianDay);
        contentValues.put(Variable.GREGORIAN_MONTH,gregorianMonth);
        contentValues.put(Variable.IFTR_TIME, iftrTime);
        contentValues.put(Variable.SEHR_TIME,sehrTime);
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
}
