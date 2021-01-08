package com.example.einkaufsliste;
/*
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

 */

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


/**
 * A basic class representing an entity that is a row in a five-column database table
 * This class is used for storing the main list of items
 * It is very similar to ItemSuggestion but it represents different table in the database
 *
 * @ Entity - Annotates the class as an entity and supplies a table name if not class name
 * @ PrimaryKey - Unique id corresponds to the index/position of an item in the list
 * @ ColumnInfo - Name of the item, count, comment and mark as bought/not bought
 *
 * See the documentation for the full rich set of annotations
 * https://developer.android.com/topic/libraries/architecture/room.html
 */
@Entity(tableName = "item_table")
public class RoomItem {

    @PrimaryKey
    @NonNull
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "count")
    private int count;

    @ColumnInfo(name = "comment")
    private String comment;

    @ColumnInfo(name = "bought")
    private boolean bought;

    public RoomItem(@NonNull int id, String name, int count, String comment, boolean bought) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.comment = comment;
        this.bought = bought;
    }

    public int getId() {
        return  this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getCount() {
        return this.count;
    }

    public String getComment() {
        return this.comment;
    }

    public boolean getBought() {
        return this.bought;
    }

    @Override
    public String toString() {
        return this.count + "x " + this.name + " " + this.comment;
    }
}

