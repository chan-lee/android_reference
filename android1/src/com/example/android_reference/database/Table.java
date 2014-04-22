package com.example.android_reference.database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public abstract class Table<T> {
    protected DBOpenHelper mDBHelper;

    public static SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

    abstract public String getTableName();
    abstract public T getAllData();
    abstract public void updateData(T data);

    public Table(DBOpenHelper dbHelper) {
        mDBHelper = dbHelper;
    }

    public static String getSqlDate(Date date) {
        return sqlDateFormat.format(date);
    }

    public static Date getDate(String string) {
        try {
            return sqlDateFormat.parse(string);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public long insert(ContentValues values) {
        return mDBHelper.getWritableDatabase().insert(getTableName(), null, values);
    }

    public void delete(String where) {
        mDBHelper.getWritableDatabase().delete(getTableName(), where, null);
    }

    public Cursor query(String[] columns) {
        return mDBHelper.getWritableDatabase().query(getTableName(), columns, null, null, null, null, null);
    }

    public long upsert(ContentValues values, String where) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        //Log.d("db", values.toString());
        // if you can serialize ContentValues, you might change to "insert or update" query
        long id = db.insertWithOnConflict(getTableName(), null, values, SQLiteDatabase.CONFLICT_IGNORE);
        if (id == -1) {
            db.update(getTableName(), values, where, null);
        }
        return id;
    }
}
