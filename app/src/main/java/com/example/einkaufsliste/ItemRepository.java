package com.example.einkaufsliste;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;


public class ItemRepository {

    private ItemDAO mItemDao;
    private LiveData<List<RoomItem>> mAllItems;
    private LiveData<List<ItemSuggestion>> mAllItemSugs;
    private LiveData<List<ItemSuggestion>> mSearch;

    ItemRepository(Application application) {
        ItemRoomDatabase db = ItemRoomDatabase.getDatabase(application);
        mItemDao = db.itemDao();
        mAllItems = mItemDao.getAllItems();
        mAllItemSugs = mItemDao.getAllItemSugs();
        mSearch = null;
    }

    LiveData<List<RoomItem>> getAllItems() {
        return mAllItems;
    }

    public void insert (RoomItem roomItem) {
        new insertAsyncTask(mItemDao).execute(roomItem);
    }

    public void delete(RoomItem roomItem) {
        new deleteAsyncTask(mItemDao).execute(roomItem);
    }

    public void update(RoomItem roomItem) {
        new updateAsyncTask(mItemDao).execute(roomItem);
    }

    LiveData<List<ItemSuggestion>> getSearch1(String search) {
        return mItemDao.getSearchSugs1(search);
    }

    List<ItemSuggestion> getSearch(String search) {
        return mItemDao.getSearchSugs(search);
    }

    private static class insertAsyncTask extends AsyncTask<RoomItem, Void, Void> {

        private ItemDAO mAsyncTaskDao;

        insertAsyncTask(ItemDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final RoomItem... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<RoomItem, Void, Void> {

        private ItemDAO mAsyncTaskDao;

        deleteAsyncTask(ItemDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final RoomItem... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<RoomItem, Void, Void> {

        private ItemDAO mAsyncTaskDao;

        updateAsyncTask(ItemDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final RoomItem... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }


    LiveData<List<ItemSuggestion>> getmAllItemSugs() {
        return mAllItemSugs;
    }

    public void insertSug(ItemSuggestion itemSuggestion) {
        new insertSugAsyncTask(mItemDao).execute(itemSuggestion);
    }

    public void deleteSug(ItemSuggestion itemSuggestion) {
        new deleteSugAsyncTask(mItemDao).execute(itemSuggestion);
    }

    public void updateSug(ItemSuggestion itemSuggestion) {
        new updateSugAsyncTask(mItemDao).execute(itemSuggestion);
    }

    private static class insertSugAsyncTask extends AsyncTask<ItemSuggestion, Void, Void> {

        private ItemDAO mAsyncTaskDao;

        insertSugAsyncTask(ItemDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ItemSuggestion... params) {
            mAsyncTaskDao.insertSug(params[0]);
            return null;
        }
    }

    private static class deleteSugAsyncTask extends AsyncTask<ItemSuggestion, Void, Void> {

        private ItemDAO mAsyncTaskDao;

        deleteSugAsyncTask(ItemDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ItemSuggestion... params) {
            mAsyncTaskDao.deleteSug(params[0]);
            return null;
        }
    }

    private static class updateSugAsyncTask extends AsyncTask<ItemSuggestion, Void, Void> {

        private ItemDAO mAsyncTaskDao;

        updateSugAsyncTask(ItemDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ItemSuggestion... params) {
            mAsyncTaskDao.updateSug(params[0]);
            return null;
        }
    }
}
