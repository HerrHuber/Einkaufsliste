package com.example.einkaufsliste;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {RoomItem.class, ItemSuggestion.class}, version = 2, exportSchema = false)
public abstract class ItemRoomDatabase extends RoomDatabase {
    private static ItemRoomDatabase INSTANCE;
    private static final String TAG = "ItemRoomDatabase";

    public abstract ItemDAO itemDao();

    public static ItemRoomDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (ItemRoomDatabase.class) {
                if(INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ItemRoomDatabase.class, "item_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            //.fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    /**
     * Populate the database in the background.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ItemDAO mDao;

        PopulateDbAsync(ItemRoomDatabase db) {
            mDao = db.itemDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Just for testing purposes
            // Start the app with a clean database every time
            // Not needed if you only populate the database
            // when it is first created
            /*
            mDao.deleteAll();
            RoomItem item1 = new RoomItem(3, "Milch", 1, "1.5%", false);
            RoomItem item2 = new RoomItem(2, "Brot", 2, "Vollkorn", false);
            RoomItem item3 = new RoomItem(1, "Wasser", 6, "Still", false);
            RoomItem item4 = new RoomItem(0, "Milch", 2, "3.5%", true);
            //Log.e(TAG, "in PopulateAsyncDB: " + item1.toString());
            mDao.insert(item1);
            mDao.insert(item2);
            mDao.insert(item3);
            mDao.insert(item4);

            mDao.deleteAllSugs();
            ItemSuggestion itemSug1 = new ItemSuggestion(3, "Milch", 1, "1.5%", false);
            ItemSuggestion itemSug2 = new ItemSuggestion(2, "Brot", 2, "Vollkorn", false);
            ItemSuggestion itemSug3 = new ItemSuggestion(1, "Wasser", 6, "Still", false);
            ItemSuggestion itemSug4 = new ItemSuggestion(0, "Milch", 2, "3.5%", true);
            //Log.e(TAG, "in PopulateAsyncDB: " + item1.toString());
            mDao.insertSug(itemSug1);
            mDao.insertSug(itemSug2);
            mDao.insertSug(itemSug3);
            mDao.insertSug(itemSug4);
             */

            return null;
        }
    }
}
