package com.shepherdjerred.twister.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.shepherdjerred.twister.object.Twist;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

// https://gist.github.com/mikeplate/9173040
// https://stackoverflow.com/questions/433392/how-do-i-use-prepared-statements-in-sqlite-in-android
public class TwisterDatabase {

    private TwisterDatabaseHelper twisterDatabaseHelper;

    public TwisterDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        this.twisterDatabaseHelper = new TwisterDatabaseHelper(context, name, factory, version);
    }

    public void setTwists(List<Twist> twists) {
        for (Twist twist : twists) {
            addTwist(twist);
        }
    }

    public ArrayList<Twist> getTwists() {
        SQLiteDatabase database = twisterDatabaseHelper.getReadableDatabase();

        String table = "twist";
        String[] columnsToReturn = {"id", "username", "message", "timestamp"};

        Cursor cursor = database.query(table, columnsToReturn, null, null, null, null, null);

        ArrayList<Twist> twists = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String message = cursor.getString(cursor.getColumnIndex("message"));
            Date timestamp = new Date(cursor.getLong(cursor.getColumnIndex("timestamp")));
            Twist twist = new Twist(id, username, message, timestamp);
            twists.add(twist);
        }
        return twists;
    }

    public ArrayList<Twist> getTwists(String username) {
        SQLiteDatabase database = twisterDatabaseHelper.getReadableDatabase();

        String table = "twist";
        String[] columnsToReturn = {"id", "message", "timestamp"};
        String selection = "username = ?";
        String[] selectionArgs = {username};
        String orderBy = "timestamp";

        Cursor cursor = database.query(table, columnsToReturn, selection, selectionArgs, null, null, orderBy);

        ArrayList<Twist> twists = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String message = cursor.getString(cursor.getColumnIndex("message"));
            Date timestamp = new Date(cursor.getLong(cursor.getColumnIndex("timestamp")));
            Twist twist = new Twist(id, username, message, timestamp);
            twists.add(twist);
        }
        return twists;
    }

    public void addTwist(Twist twist) {
        SQLiteDatabase database = twisterDatabaseHelper.getWritableDatabase();
        SQLiteStatement statement = database.compileStatement("INSERT INTO twist (id, username, message, timestamp) VALUES (?, ?, ?, ?);");
        statement.bindLong(1, twist.getId());
        statement.bindString(2, twist.getUsername());
        statement.bindString(3, twist.getMessage());
        statement.bindLong(4, twist.getTimestamp().getTime());
        statement.executeInsert();
    }

}
