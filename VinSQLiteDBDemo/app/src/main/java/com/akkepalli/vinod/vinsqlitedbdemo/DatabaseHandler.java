package com.akkepalli.vinod.vinsqlitedbdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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

    public DatabaseHandler(Context context){
        super(context, DB_NAME, null , DB_VERSION);
    }
    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    //for creating new database
    @Override
    public void onCreate(SQLiteDatabase db) {
        //String sqlQuery =  "create table " + DB_TABLE_NAME + " ( id integer primary key, name text, phoneNumber text )";
        String sqlQuery = "CREATE TABLE " + DB_TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        db.execSQL(sqlQuery);
    }

    //for upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop table
        db.execSQL("drop table if exists" + DB_TABLE_NAME);

        //Update the database version
        if(oldVersion == DB_VERSION){
            DB_VERSION  = newVersion;
        }

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

    public List<Contact>  getAllContacts(){

        List<Contact> contactsList = new ArrayList<Contact>();

        String selectQuery = "select * from " + DB_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();

        //Cursor is used to parse through the query results record by record
        //We do not want to pass any selection arguments hence passing null
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));

                contactsList.add(contact);
            }while(cursor.moveToNext());
        }

        return contactsList;
    }

    public int updateContact(Contact contact){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        //contentValues.put(KEY_ID, contact.getId());
        contentValues.put(KEY_NAME, contact.getName());
        contentValues.put(KEY_PH_NO, contact.getPhoneNumber());

        return db.update(DB_TABLE_NAME, contentValues, KEY_ID + "=?", new String[]{String.valueOf(contact.getId())});
    }

    public void deleteContact(Contact contact){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(DB_TABLE_NAME, KEY_ID + "=?", new String[]{String.valueOf(contact.getId())});
        db.close();
    }

    public int getContactsCount(){

        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery = "select * from " + DB_TABLE_NAME;

        Cursor cursor =  db.rawQuery(sqlQuery,null);
        cursor.close();

        return cursor.getCount();
    }

}
