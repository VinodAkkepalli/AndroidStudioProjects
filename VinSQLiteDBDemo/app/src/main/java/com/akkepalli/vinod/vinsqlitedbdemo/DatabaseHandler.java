package com.akkepalli.vinod.vinsqlitedbdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by VinodAkkepalli
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    static int DB_VERSION = 1;
    static String DB_NAME = "ContactsManager";
    static String DB_TABLE_NAME = "contacts";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    //for creating new database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery =  "create table " + DB_NAME + " ( id integer primary key, name string, phoneNumber string )";
        db.execSQL(sqlQuery);
    }

    //for upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop table
        db.execSQL("drop table if exists" + DB_NAME);

        onCreate(db);
    }

    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        //We need to create ContentValues object to insert content into a db table
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",contact.getName());
        contentValues.put("phoneNumber", contact.getPhoneNumber());

        db.insert(DB_TABLE_NAME, null, contentValues);
        db.close();
    }

    public Contact getContact(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DB_TABLE_NAME, new String[]{KEY_ID, KEY_NAME, KEY_PH_NO}, KEY_ID + "=?",
                new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));

        return contact;
    }
}
