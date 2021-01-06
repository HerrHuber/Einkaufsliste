package com.example.einkaufsliste;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class ItemViewModel extends AndroidViewModel {

    private ItemRepository mRepository;

    private LiveData<List<RoomItem>> mAllItems;

    //private int count;

    public ItemViewModel(Application application) {
        super(application);
        mRepository = new ItemRepository(application);
        mAllItems = mRepository.getAllItems();
        //count = mRepository.
    }

    LiveData<List<RoomItem>> getAllItems() {
        return mAllItems;
    }

    /*
    public int getAllWordsCount() {
        if (mAllWords != null) {
            return mAllWords.size();
        } else {
            return 0;
        }
    }
    */

    public void insert(RoomItem roomItem) {
        mRepository.insert(roomItem);
    }

    public void delete(RoomItem roomItem) {
        mRepository.delete(roomItem);
    }

    public void update(RoomItem roomItem) {
        mRepository.update(roomItem);
    }
}
