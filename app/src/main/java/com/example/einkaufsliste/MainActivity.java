package com.example.einkaufsliste;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedList;
import java.util.List;

/**
 * Einkaufsliste - Grocery list
 * Implements a basic RecyclerView that displays a list of generated items
 * - Clicking an item marks it as bought and changes its list position to last
 *   Clicking an item marked as bought will delete the mark and change its position to first
 * - Long clicking an item starts CreateActivity to edit or delete the selected item
 * - Clicking the fab button starts CreateActivity to add a new item to the list
 */
public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private final LinkedList<FoodItem> mFoodList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private FoodListAdapter mAdapter;
    private ItemViewModel mItemViewModel;
    public static final int FoodItem_REQUEST = 1;
    public static final int EditItem_REQUEST = 2;
    public static final String EXTRA_NAME = "com.example.eikaufsliste.extra.NAME";
    public static final String EXTRA_COUNT = "com.example.eikaufsliste.extra.COUNT";
    public static final String EXTRA_COMMENT = "com.example.eikaufsliste.extra.COMMENT";
    private List<ItemSuggestion> mItemSuggestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_add_for_fab);

        // Get a handle to the RecyclerView
        mRecyclerView = findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed
        mAdapter = new FoodListAdapter(this, mFoodList);
        // Connect the adapter with the RecyclerView
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mItemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);

        mItemViewModel.getAllItems().observe(this, new Observer<List<RoomItem>>() {
            @Override
            public void onChanged(@Nullable final List<RoomItem> roomItems) {
                // Update the cached copy of the items in the adapter
                mAdapter.setItems(roomItems);
            }
        });

        mItemViewModel.getAllItemSugs().observe(this, new Observer<List<ItemSuggestion>>() {
            @Override
            public void onChanged(@Nullable final List<ItemSuggestion> itemSuggestions) {
                // Update the cached copy of the items in the adapter
                mItemSuggestions = itemSuggestions;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void createFood(View view) {
        Intent intent = new Intent(this, CreateActivity.class);
        startActivityForResult(intent, FoodItem_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Create new item
        if(requestCode == FoodItem_REQUEST) {
            if(resultCode == RESULT_OK) {
                String delete = data.getStringExtra(CreateActivity.EXTRA_DELETE);
                // Test if delete button is pressed while creating a new item
                if(!"".equals(delete) && !(delete == null)) {
                    // If delete is pressed do nothing, jsut return to MainActivity
                } else {
                    // Else create new item and return to MainActivity
                    String name = data.getStringExtra(CreateActivity.EXTRA_NAME);
                    String temp = data.getStringExtra(CreateActivity.EXTRA_COUNT);
                    int count = 1;
                    if (!"".equals(temp)) {
                        count = Integer.parseInt(temp);
                    }
                    String comment = data.getStringExtra(CreateActivity.EXTRA_COMMENT);

                    RoomItem newItem = new RoomItem(mAdapter.getItemCount(), name, count, comment, false);
                    Log.e(LOG_TAG, "in Create item: " + mAdapter.getItemCount());
                    Log.e(LOG_TAG, "Items: " + mAdapter.mItems);

                    // Before creating new item insert it to item suggestions
                    int len = mItemSuggestions.size();
                    ItemSuggestion itemSuggestion = new ItemSuggestion(len, newItem.getName(), newItem.getCount(), newItem.getComment(), newItem.getBought());
                    mItemViewModel.insertSug(itemSuggestion);
                    mItemViewModel.insert(newItem);
                }
            }
            // Edit or delete item
        } else if(requestCode == EditItem_REQUEST) {
            if(resultCode == RESULT_OK) {
                String delete = data.getStringExtra(CreateActivity.EXTRA_DELETE);
                if(!"".equals(delete) && !(delete == null)) {
                    String pos = data.getStringExtra(CreateActivity.EXTRA_POSITION);
                    int position = -1;
                    if(!"".equals(pos) && !(pos == null)) {
                        position = Integer.parseInt(pos);

                        // Put item to delete at the end of the list
                        // than delete last item
                        // because (unique) item id corresponds to list index
                        int length = mAdapter.getItemCount();
                        RoomItem item = mAdapter.mItems.get(position);
                        LinkedList<RoomItem> list = new LinkedList<>();
                        for (RoomItem t : mAdapter.mItems) {
                            list.add(t);
                        }
                        list.remove(item);
                        RoomItem newItem = new RoomItem(item.getId(), item.getName(), item.getCount(), item.getComment(), item.getBought());
                        list.addFirst(newItem);
                        for (int i = 0; i < length; i++) {
                            // Because order of list inverse of order of layout
                            RoomItem current = list.get(length - 1 - i);
                            RoomItem n = new RoomItem(i, current.getName(), current.getCount(), current.getComment(), current.getBought());
                            mItemViewModel.update(n);
                        }
                        RoomItem deleteItem = new RoomItem(length-1, item.getName(), item.getCount(), item.getComment(), item.getBought());
                        mItemViewModel.delete(deleteItem);
                    }
                } else {
                    String name = data.getStringExtra(CreateActivity.EXTRA_NAME);
                    String temp = data.getStringExtra(CreateActivity.EXTRA_COUNT);
                    int count = 1;
                    if(!"".equals(temp)) {
                        count = Integer.parseInt(temp);
                    }
                    String comment = data.getStringExtra(CreateActivity.EXTRA_COMMENT);
                    String pos = data.getStringExtra(CreateActivity.EXTRA_POSITION);
                    int position = -1;
                    if(!"".equals(pos) && !(pos == null)) {
                        position = Integer.parseInt(pos);

                        RoomItem editItem = mAdapter.getItem(position);
                        boolean bought = editItem.getBought();

                        // because order of list inverse of order of layout
                        int length = mAdapter.getItemCount();
                        position = length - 1 - position;

                        RoomItem newItem = new RoomItem(position, name, count, comment, bought);
                        mItemViewModel.update(newItem);
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}