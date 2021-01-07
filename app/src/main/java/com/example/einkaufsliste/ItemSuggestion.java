package com.example.einkaufsliste;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "item_suggestion_table")
public class ItemSuggestion {

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

    public ItemSuggestion(@NonNull int id, String name, int count, String comment, boolean bought) {
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

    /*
    @Override
    public String toString() {
        return "Item" + id + ":" +
                "\nname: " + name +
                ", count: " + count +
                ", \ncomment: " + comment +
                ", bought: " + bought;
    }
     */

    @Override
    public String toString() {
        return this.count + "x " + this.name + " " + this.comment;
    }
}
