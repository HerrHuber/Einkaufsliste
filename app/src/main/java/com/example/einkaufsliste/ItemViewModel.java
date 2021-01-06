package com.example.einkaufsliste;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class ItemViewModel extends AndroidViewModel {
    private static final String LOG_TAG4 = ItemViewModel.class.getSimpleName();
    private ItemRepository mRepository;
    private LiveData<List<RoomItem>> mAllItems;
    private LiveData<List<ItemSuggestion>> mAllItemSugs;

    public ItemViewModel(Application application) {
        super(application);
        mRepository = new ItemRepository(application);
        mAllItems = mRepository.getAllItems();
        mAllItemSugs = mRepository.getmAllItemSugs();
    }

    LiveData<List<RoomItem>> getAllItems() {
        return mAllItems;
    }

    public void insert(RoomItem roomItem) {
        //int len = mAllItemSugs.getValue().size();
        //ItemSuggestion itemSuggestion = new ItemSuggestion(len, roomItem.getName(), roomItem.getCount(), roomItem.getComment(), roomItem.getBought());
        //insertSug(itemSuggestion);
        mRepository.insert(roomItem);
        //Log.e(LOG_TAG4, "mAllItemSugs: " + mAllItemSugs);
    }

    public void delete(RoomItem roomItem) {
        mRepository.delete(roomItem);
    }

    public void update(RoomItem roomItem) {
        mRepository.update(roomItem);
    }


    LiveData<List<ItemSuggestion>> getAllItemSugs() {
        return mAllItemSugs;
    }

    public void insertSug(ItemSuggestion itemSuggestion) {
        mRepository.insertSug(itemSuggestion);
    }

    public void deleteSug(ItemSuggestion itemSuggestion) {
        mRepository.deleteSug(itemSuggestion);
    }

    public void updateSug(ItemSuggestion itemSuggestion) {
        mRepository.updateSug(itemSuggestion);
    }
}
