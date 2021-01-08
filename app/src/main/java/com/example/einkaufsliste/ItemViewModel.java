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
    private List<ItemSuggestion> mCurrentSugs;
    private LiveData<List<ItemSuggestion>> mAllItemSugs;
    private LiveData<List<ItemSuggestion>> mSearch;

    public ItemViewModel(Application application) {
        super(application);
        mRepository = new ItemRepository(application);
        mAllItems = mRepository.getAllItems();
        mAllItemSugs = mRepository.getmAllItemSugs();
        mSearch = null;
    }

    LiveData<List<RoomItem>> getAllItems() {
        return mAllItems;
    }

    public void insert(RoomItem roomItem) {
        mRepository.insert(roomItem);
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

    public List<ItemSuggestion> getItemSugs() {
        return mCurrentSugs;
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

    LiveData<List<ItemSuggestion>> getSearch1(String search) {
        return mRepository.getSearch1(search);
    }

    List<ItemSuggestion> getSearch(String search) {
        return mRepository.getSearch(search);
    }
}
