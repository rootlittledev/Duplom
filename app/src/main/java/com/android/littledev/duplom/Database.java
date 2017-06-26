package com.android.littledev.duplom;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Test";
    private static final String TABLE_NAME_1 = "Items";
    private static final String TABLE_NAME_2 = "Item_short";
    private static final String TABLE_NAME_3 = "Item_long";
    private static final String TABLE_NAME_4 = "Orders";

    Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME_1 +" ( ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT)");
        db.execSQL("create table " + TABLE_NAME_2 +" ( ID INTEGER PRIMARY KEY AUTOINCREMENT, Image_source INT, Item_name TEXT, Currency TEXT, Amount INT, Cost_per_item FLOAT, Discount INT, View INT, Time DATETIME)");
        db.execSQL("create table " + TABLE_NAME_3 +" ( ID INTEGER PRIMARY KEY AUTOINCREMENT, Image_source INT, Item_name TEXT, Currency TEXT, Amount INT, Cost_per_item FLOAT, Discount INT, View INT, Time DATETIME, Tag_1 TEXT, Tag_2 TEXT, Tag_3 TEXT, Tag_4 TEXT, Tag_5 TEXT, Description TEXT)");
        db.execSQL("create table " + TABLE_NAME_4 +" ( Item_id INT, Amount INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_3);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_3);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_4);
        onCreate(db);
    }

    void insertItem(int image_source, String label_string, String currency, int amount, float cost_per_item, String tag1, String tag2, String tag3, String tag4, String tag5, String description){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("Insert into " + TABLE_NAME_3 + " (Image_source, Item_name, Currency, Amount, Cost_per_item, Tag_1, Tag_2, Tag_3, Tag_4, Tag_5, Description, Discount, View, Time) Values('" + image_source + "', '" + label_string + "', '" + currency + "', '" + amount + "', '" + cost_per_item + "', '" + tag1 + "', '" + tag2 + "', '" + tag3 + "', '"  + tag4 +  "', '"  + tag5 + "', '" + description + "', 0, 0, 'datetime()')");
        db.execSQL("Insert into " + TABLE_NAME_2 + " (Image_source, Item_name, Currency, Amount, Cost_per_item, Discount, View, Time) Values('" + image_source + "', '" + label_string + "', '" + currency + "', '" + amount + "', '" + cost_per_item + "', 0 , 0, 'datetime()')");
    }

    void onAddOrder(int id, int amount){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("Insert into " + TABLE_NAME_4 + " (Item_id, Amount) Values ('" + id + "', '" + amount + "')");
    }

    void addViews(int id, int views){
        SQLiteDatabase db = this.getWritableDatabase();
        views++;
        db.execSQL("Insert into " + TABLE_NAME_2 + " Where id = '" + id + "' (View) Values ('" + views + "')");
    }

    Cursor getOrders(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("Select * From " + TABLE_NAME_4, null);
    }

    Cursor getNewest(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("Select ID, Image_source, Item_name From " + TABLE_NAME_2 + " Order By Time Desc Limit 9", null);
    }

    Cursor getRecommended(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("Select ID, Image_source, Item_name From " + TABLE_NAME_2 + " Order By View Desc Limit 9", null);
    }

    Cursor getItems(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("Select * From " + TABLE_NAME_1, null);
    }
    Cursor getItemShort(String narrow){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        Log.i("debug", narrow);
        if (narrow.equals("none"))
            cursor = db.rawQuery("Select * From " + TABLE_NAME_3, null);
        else
            cursor =  db.rawQuery("Select * From " + TABLE_NAME_3 + " Where Tag_1 = '" + narrow + "'", null);
        return cursor;
    }
    Cursor getItemLong(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * From " + TABLE_NAME_3 + " Where ID = " + id;
        Log.i("test", query);
        return db.rawQuery(query, null);
    }
}
