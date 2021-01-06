package com.example.einkaufsliste;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;


public class ItemRepository {

    private ItemDAO mItemDao;
    private LiveData<List<RoomItem>> mAllItems;

    ItemRepository(Application application) {
        ItemRoomDatabase db = ItemRoomDatabase.getDatabase(application);
        mItemDao = db.itemDao();
        mAllItems = mItemDao.getAllItems();
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
}
