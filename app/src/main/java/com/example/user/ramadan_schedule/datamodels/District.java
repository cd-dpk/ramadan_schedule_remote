package com.example.user.ramadan_schedule.datamodels;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.user.ramadan_schedule.datamodels.interfaces.ITable;

import org.json.JSONObject;

/**
 * Created by chandradasdipok on 6/5/2016.
 */
public class District implements ITable {

    public String districtName;
    public int sehrTimeCorrection;
    public int iftrTimeCorrection;

    WhereClause whereClause = new WhereClause();

    public District() {
    }

    public District(String districtName, int sehrTimeCorrection, int iftrTimeCorrection) {
        this.districtName = districtName;
        this.sehrTimeCorrection = sehrTimeCorrection;
        this.iftrTimeCorrection = iftrTimeCorrection;
    }

    @Override
    public String tableName() {
        return "District";
    }

    @Override
    public String toCreateTableString() {
        return "create table if not exists "+ tableName()+" (" +
                ""+Variable.DISTRICT_NAME+" text primary key" +
                ","+Variable.SEHR_TIME_CORRECTION+" integer " +
                ","+Variable.IFTR_TIME_CORRECTION+" integer" +
                ")";
    }

    @Override
    public String toInsertString() {
        return null;
    }

    @Override
    public String toSelectString() {
        return "select * from "+tableName()+" where "+getWhereClauseString()+" order by "+Variable.DISTRICT_NAME+" asc";
    }

    @Override
    public String toDeleteSingleRowString() {
        return null;
    }

    @Override
    public String toDeleteRows() {
        return "";
    }

    @Override
    public String toSelectSingleRowString() {
        return "select * from "+tableName()+" where "+getWhereClauseString();
    }
    @Override
    public ITable toITableFromCursor(Cursor cursor) {
        District district = new District();
        if (cursor.getColumnIndex(Variable.DISTRICT_NAME)!=-1){
            district.districtName = cursor.getString(cursor.getColumnIndex(Variable.DISTRICT_NAME));
        }
        if (cursor.getColumnIndex(Variable.SEHR_TIME_CORRECTION)!=-1){
            district.sehrTimeCorrection = cursor.getInt(cursor.getColumnIndex(Variable.SEHR_TIME_CORRECTION));
        }
        if (cursor.getColumnIndex(Variable.IFTR_TIME_CORRECTION)!=-1){
            district.iftrTimeCorrection = cursor.getInt(cursor.getColumnIndex(Variable.IFTR_TIME_CORRECTION));
        }
        return district;
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
        contentValues.put(Variable.DISTRICT_NAME,districtName);
        contentValues.put(Variable.SEHR_TIME_CORRECTION,sehrTimeCorrection);
        contentValues.put(Variable.IFTR_TIME_CORRECTION,iftrTimeCorrection);
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
        public static final String DISTRICT_NAME ="district_name";
        public static final String SEHR_TIME_CORRECTION="sehr_time_correction";
        public static final String IFTR_TIME_CORRECTION="iftr_time_correction";
    }

    @Override
    public String toString() {
        return districtName;
    }
}
