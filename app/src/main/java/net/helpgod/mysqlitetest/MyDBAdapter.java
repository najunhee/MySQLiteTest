package net.helpgod.mysqlitetest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015-11-05.
 */
public class MyDBAdapter {

    private static final String DATABASE_NAME = "NEW_MY_DATABASE.db";
    private static final String DATABASE_TABLE_MASTER = "TT_TIMETABLE_MASTER";
    private static final int DATABASE_VERSION = 1;

    public static final String KEY_ID = "_id";

    private SQLiteDatabase db;
    private final Context mContext;
    private MyDBOpenHelper dbHelper;

    public MyDBAdapter(Context context){
        this.mContext = context;
        dbHelper = new MyDBOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void close() {
        db.close();
    }

    public void open() throws SQLiteException {
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbHelper.getReadableDatabase();
        }
    }

    public void insertData() {
        // TODO Auto-generated method stub
        db = dbHelper.getWritableDatabase();
        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO " + DATABASE_TABLE_MASTER );
        sb.append(" VALUES( null");
        sb.append(", 'KEYVALUE'");
        sb.append(", 'AAAA'");
        sb.append(", 'BBBB'");
        sb.append(", 'CCCC'");
        sb.append(", 'DDDD'");
        sb.append(", 'EEEE'");
        sb.append(");");

        //Log.d("TT",sb.toString() );

        db.execSQL(sb.toString());
        db.close();
    }

    public ArrayList<SampleDto> getSelectAll() {

        db = dbHelper.getReadableDatabase();
        Cursor cursor;

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * ");
        sb.append(" FROM " + DATABASE_TABLE_MASTER );
        sb.append(";");

        cursor = db.rawQuery(sb.toString(), null);

        ArrayList<SampleDto> mSubj = new ArrayList<SampleDto>();
        SampleDto es;

        while (cursor.moveToNext()) {
            es = new SampleDto();

            es.setId(cursor.getInt(0));
            es.setKEY(cursor.getString(1));
            es.setVALUE_01(cursor.getString(2));
            es.setVALUE_02(cursor.getString(3));
            es.setVALUE_03(cursor.getString(4));
            es.setVALUE_04(cursor.getString(5));
            es.setVALUE_05(cursor.getString(6));

            mSubj.add(es);
        }
        cursor.close();
        db.close();
        return mSubj;
    }


    private static class MyDBOpenHelper extends SQLiteOpenHelper{
        Context context;

        public MyDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            this.context = context;
            // TODO Auto-generated constructor stub
        }

        private static final String DATABASE_CREATE = "create table " +
                DATABASE_TABLE_MASTER + " (" + KEY_ID + " integer PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "KEY			text NOT NULL, " +
                "VALUE_01       text , " +
                "VALUE_02       text , " +
                "VALUE_03       text , " +
                "VALUE_04	    text , " +
                "VALUE_05       text );";

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onOpen(SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_MASTER);
            onCreate(db);
        }
    }
}
