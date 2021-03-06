package com.example.mypc.truyenoffline.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mypc.truyenoffline.entity.Story;
import com.example.mypc.truyenoffline.entity.Total;

import java.util.ArrayList;
import java.util.List;

import static com.example.mypc.truyenoffline.database.DataColumns.COLUMN_CONTENT;
import static com.example.mypc.truyenoffline.database.DataColumns.COLUMN_ID;
import static com.example.mypc.truyenoffline.database.DataColumns.COLUMN_TITLE;
import static com.example.mypc.truyenoffline.database.DataColumns.TABLE_ADULT;
import static com.example.mypc.truyenoffline.database.DataColumns.TABLE_ANIMALS;
import static com.example.mypc.truyenoffline.database.DataColumns.TABLE_BOM_NHAU;
import static com.example.mypc.truyenoffline.database.DataColumns.TABLE_CON_GAI;
import static com.example.mypc.truyenoffline.database.DataColumns.TABLE_DAN_GIAN;
import static com.example.mypc.truyenoffline.database.DataColumns.TABLE_FAVORITE;
import static com.example.mypc.truyenoffline.database.DataColumns.TABLE_HOC_DUONG;
import static com.example.mypc.truyenoffline.database.DataColumns.TABLE_LOVE;
import static com.example.mypc.truyenoffline.database.DataColumns.TABLE_MUON_MAU;
import static com.example.mypc.truyenoffline.database.DataColumns.TABLE_NHA_BINH;
import static com.example.mypc.truyenoffline.database.DataColumns.TABLE_QUYNH;
import static com.example.mypc.truyenoffline.database.DataColumns.TABLE_TIEU_LAM;
import static com.example.mypc.truyenoffline.database.DataColumns.TABLE_TOTAL;
import static com.example.mypc.truyenoffline.database.DataColumns.TABLE_VIET_NAM;
import static com.example.mypc.truyenoffline.database.DataColumns.TABLE_VOVA;
import static com.example.mypc.truyenoffline.database.DataColumns.TABLE_YHOC;

public class MyDatabase {
    private static Context context;
    static SQLiteDatabase db;
    private DatabaseHelper openHelper;
    private String[] tableName = {
            TABLE_QUYNH, TABLE_VOVA, TABLE_BOM_NHAU,
            TABLE_HOC_DUONG, TABLE_NHA_BINH, TABLE_TIEU_LAM,
            TABLE_DAN_GIAN, TABLE_CON_GAI, TABLE_ANIMALS,
            TABLE_VIET_NAM, TABLE_LOVE, TABLE_MUON_MAU,
            TABLE_YHOC, TABLE_ADULT, TABLE_FAVORITE
    };

    public MyDatabase(Context c) {
        MyDatabase.context = c;
    }

    public MyDatabase open()  {
        openHelper = DatabaseHelper.getInstance(context);
        db = openHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        openHelper.close();
    }

    // Lay du lieu tu bang Total
    public List<Total> getDataFromTotal() {

        List<Total> totals = new ArrayList<>();

        String[] columns = new String[]{COLUMN_ID, COLUMN_TITLE};
        Cursor cursor = db.query(TABLE_TOTAL, columns, null, null, null, null, null);

        int rowID = cursor.getColumnIndex(COLUMN_ID);
        int rowTitle = cursor.getColumnIndex(COLUMN_TITLE);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            Total total = new Total();
            total.setId(cursor.getInt(rowID));
            total.setTitle(cursor.getString(rowTitle));

            totals.add(total);
        }
        cursor.close();
        return totals;
    }

    public List<Story> GetDataTruyen(int id) {
        List<Story> stories = new ArrayList<>();

        String[] columns = new String[]{COLUMN_ID, COLUMN_TITLE, COLUMN_CONTENT};
        Cursor cursor = db.query(tableName[id - 1], columns, null, null, null, null, null);

        int rowID      = cursor.getColumnIndex(COLUMN_ID);
        int rowTitle   = cursor.getColumnIndex(COLUMN_TITLE);
        int rowContent = cursor.getColumnIndex(COLUMN_CONTENT);

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            Story story = new Story();
            story.setId(cursor.getInt(rowID));
            story.setTitle(cursor.getString(rowTitle));
            story.setContent(cursor.getString(rowContent));

            stories.add(story);
        }
        cursor.close();
        return stories;
    }

    public long favorite(Story story) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, story.getId());
        contentValues.put(COLUMN_TITLE, story.getTitle());
        contentValues.put(COLUMN_CONTENT, story.getContent());
        return db.insert(TABLE_FAVORITE, null, contentValues);
    }

    public int deleteFavorite(int id) {
        return db.delete(TABLE_FAVORITE, COLUMN_ID + "='" + id + "'", null);
    }

    public boolean kiemtraTitle(String title) {
        Cursor cursor = db.rawQuery("select * from " + TABLE_FAVORITE + " where " + COLUMN_TITLE + " =?", new String[]{title});
        if (cursor.getCount() == 1) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }
}
