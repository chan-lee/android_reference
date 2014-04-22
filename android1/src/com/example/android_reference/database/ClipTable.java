package com.example.android_reference.database;

import java.util.ArrayList;

import com.example.android_reference.models.Clip;

import android.content.ContentValues;
import android.database.Cursor;

public class ClipTable extends Table<ArrayList<Clip>> {
    public static final String TABLE_NAME = "clips";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_PLAYTIME = "playtime";
    private static final String COLUMN_THUMBNAIL = "thumbnail";
    private static final String COLUMN_YOUTUBE = "youtube";
    private static final String COLUMN_CREATED = "created";
    private static final String COLUMN_UPDATED = "updated";

    private final String[] allColumns = { COLUMN_ID, COLUMN_TITLE, COLUMN_DESCRIPTION,
            COLUMN_PLAYTIME, COLUMN_THUMBNAIL, COLUMN_YOUTUBE,
            COLUMN_CREATED, COLUMN_UPDATED };

    // Database creation sql statement
    public static final String TABLE_CREATE = "create table "
        + TABLE_NAME + "("
        + COLUMN_ID + " long primary key, "
        + COLUMN_TITLE + " text not null, "
        + COLUMN_DESCRIPTION + " text, "
        + COLUMN_PLAYTIME + " integer not null, "
        + COLUMN_THUMBNAIL + " text not null, "
        + COLUMN_YOUTUBE + " text not null, "
        + COLUMN_CREATED + " datetime not null, "
        + COLUMN_UPDATED + " datetime not null"
        + ");";

    public ClipTable(DBOpenHelper dbHelper) {
        super(dbHelper);
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public ArrayList<Clip> getAllData() {
        ArrayList<Clip> clips = new ArrayList<Clip>();

        Cursor cursor = query(allColumns);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Clip clip = cursorToClip(cursor);
          clips.add(clip);
          cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return clips;
    }

    @Override
    public void updateData(ArrayList<Clip> clips) {
        for (Clip clip : clips) {
            upsert(clipToContentValues(clip), "_id=" + clip.id);
        }
    }

    public Clip insertClip(Clip clip) {
        ContentValues values = clipToContentValues(clip);
        long id = insert(values);
        assert(id == clip.id);
        return clip;
    }

    private ContentValues clipToContentValues(Clip clip) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, clip.id);
        values.put(COLUMN_TITLE, clip.title);
        values.put(COLUMN_DESCRIPTION, clip.description);
        values.put(COLUMN_PLAYTIME, clip.playtime);
        values.put(COLUMN_THUMBNAIL, clip.thumbnail);
        values.put(COLUMN_YOUTUBE, clip.youtube);
        //set the format to sql date time
        values.put(COLUMN_CREATED, getSqlDate(clip.created));
        values.put(COLUMN_UPDATED, getSqlDate(clip.updated));
        return values;
    }

    public void deleteClip(long id) {
        delete("_id = " + id);
    }

    private Clip cursorToClip(Cursor cursor) {
        Clip clip = new Clip();
        clip.id = cursor.getLong(0);
        clip.title = cursor.getString(1);
        clip.description = cursor.getString(2);
        clip.playtime = cursor.getInt(3);
        clip.thumbnail = cursor.getString(5);
        clip.youtube = cursor.getString(6);
        clip.created = getDate(cursor.getString(8));
        clip.updated = getDate(cursor.getString(9));
        return clip;
    }

}
