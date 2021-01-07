package com.example.einkaufsliste;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ItemDAO {

    @Insert
    void insert(RoomItem item);

    @Query("DELETE FROM item_table")
    void deleteAll();

    @Delete
    void delete(RoomItem roomItem);

    @Update
    void update(RoomItem roomItem);

    @Query("SELECT * from item_table ORDER BY id DESC")
    LiveData<List<RoomItem>> getAllItems();


    @Insert
    void insertSug(ItemSuggestion itemSuggestion);

    @Query("DELETE FROM item_suggestion_table")
    void deleteAllSugs();

    @Delete
    void deleteSug(ItemSuggestion itemSuggestion);

    @Update
    void updateSug(ItemSuggestion itemSuggestion);

    @Query("SELECT * from item_suggestion_table ORDER BY id DESC")
    LiveData<List<ItemSuggestion>> getAllItemSugs();

    @Query("SELECT * from item_suggestion_table where name like :search")
    LiveData<List<ItemSuggestion>> getSearchSugs1(String search);

    @Query("SELECT * from item_suggestion_table where name like :search")
    List<ItemSuggestion> getSearchSugs(String search);
}
