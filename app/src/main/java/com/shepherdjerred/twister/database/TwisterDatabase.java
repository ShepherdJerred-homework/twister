package com.shepherdjerred.twister.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.shepherdjerred.twister.object.Twist;

import java.util.ArrayList;
import java.util.List;

// https://gist.github.com/mikeplate/9173040
// https://stackoverflow.com/questions/433392/how-do-i-use-prepared-statements-in-sqlite-in-android
public class TwisterDatabase {

    private TwisterDatabaseHelper twisterDatabaseHelper;

    public TwisterDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        this.twisterDatabaseHelper = new TwisterDatabaseHelper(context, name, factory, version);
    }

    public void setTwists(List<Twist> twists) {
        for (Twist twist : twists) {
            // TODO insert into database
        }
    }

    public ArrayList<Twist> getTwists() {
        SQLiteDatabase database = twisterDatabaseHelper.getReadableDatabase();

        String table = "twist";
        String[] columnsToReturn = { "id", "username", "message", "timestamp" };

        Cursor cursor = database.query(table, columnsToReturn, null, null, null, null, null);

        // TODO go through selected rows
    }

    public ArrayList<Twist> getTwists(String username) {
        SQLiteDatabase database = twisterDatabaseHelper.getReadableDatabase();

        String table = "twist";
        String[] columnsToReturn = { "id", "username", "message", "timestamp" };

        Cursor cursor = database.query(table, columnsToReturn, null, null, null, null, null);

        // TODO go through selected rows
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
