package com.example.einkaufsliste;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
//import android.widget.SearchView;

import java.util.LinkedList;
import java.util.List;

public class CreateActivity extends AppCompatActivity {
    private static final String LOG_TAG2 = CreateActivity.class.getSimpleName();
    public static final String EXTRA_NAME = "com.example.eikaufsliste.extra.NAME";
    public static final String EXTRA_COUNT = "com.example.eikaufsliste.extra.COUNT";
    public static final String EXTRA_COMMENT = "com.example.eikaufsliste.extra.COMMENT";
    public static final String EXTRA_POSITION = "com.example.eikaufsliste.extra.POSITION";
    public static final String EXTRA_DELETE = "com.example.eikaufsliste.extra.DELETE";
    private EditText mName;
    private EditText mCount;
    private EditText mComment;
    private String mPosition;
    private RecyclerView mSugRecyclerView;
    private SugListAdapter mSugAdapter;
    private LinkedList<FoodItem> mSugList = new LinkedList<>();
    private List<ItemSuggestion> mItemSuggestions;
    private SearchView mSearchView;
    private ItemViewModel mItemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        mItemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        //mItemSuggestions = (List<ItemSuggestion>) mItemViewModel.getAllItemSugs();

        mItemViewModel.getAllItemSugs().observe(this, new Observer<List<ItemSuggestion>>() {
            @Override
            public void onChanged(@Nullable final List<ItemSuggestion> itemSuggestions) {
                // Update the cached copy of the items in the adapter.
                //mSugAdapter.setItems(itemSuggestions);
                mItemSuggestions = itemSuggestions;
                Log.e(LOG_TAG2, "itemSuggestions[0]: " + itemSuggestions.get(0));
                //mListLength = roomItems.size();
            }
        });

        mItemSuggestions = mItemViewModel.getItemSugs();

        FoodItem foodItem = new FoodItem("Name", 2, "test");
        mSugList.addFirst(foodItem);

        // Get a handle to the RecyclerView.
        mSugRecyclerView = findViewById(R.id.recyclerView2);
        // Create an adapter and supply the data to be displayed.
        mSugAdapter = new SugListAdapter(this, mSugList);
        // Connect the adapter with the RecyclerView.
        mSugRecyclerView.setAdapter(mSugAdapter);
        // Give the RecyclerView a default layout manager.
        mSugRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get the SearchView and set the searchable configuration
        //SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        //SearchView mSearchView = (SearchView) findViewById(R.id.searchView2);
        // Assumes current activity is the searchable activity
        //mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        //mSearchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        doMySearch(intent);

        /*
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i(LOG_TAG2, "onQueryTextSubmit: Query was submitted");
                // -------------------------------------------------------
                SearchRecentSuggestions suggestions = new SearchRecentSuggestions(MainActivity.this,
                       MySuggestionProvider.AUTHORITY,
                       MySuggestionProvider.MODE);
                suggestions.saveRecentQuery(query, null);
                // -------------------------------------------------------

                displaySearchResults(query);

                return true;
            }
        };

         */

        //mSearchView = (SearchView) findViewById(R.id.searchView2):

        mName = findViewById(R.id.editTextTextPersonName);
        mCount = findViewById(R.id.editTextNumber);
        mComment = findViewById(R.id.editTextTextPersonName2);

        //Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.EXTRA_NAME);
        String count = intent.getStringExtra(MainActivity.EXTRA_COUNT);
        String comment = intent.getStringExtra(MainActivity.EXTRA_COMMENT);
        mPosition = intent.getStringExtra(FoodListAdapter.EXTRA_POSITION);

        mName.setText(name);
        mCount.setText(count);
        mComment.setText(comment);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);

        doMySearch(intent);
    }

    private void doMySearch(Intent intent) {
        if(Intent.ACTION_SEARCH.equals(intent.getAction())) {
            Log.e(LOG_TAG2, "in onCreate intent: ");
            String query = intent.getStringExtra(SearchManager.QUERY);
            // save query suggestion
            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                    MySuggestionProvider.AUTHORITY, MySuggestionProvider.MODE);
            suggestions.saveRecentQuery(query, null);

            //displaySearchResults(query);
            Log.e(LOG_TAG2, "Query: " + query);
            //FoodItem foodItem = new FoodItem(query);
            //mSugAdapter.addSug(foodItem);
            //mSugList.addLast((foodItem));
            //mSugAdapter.notifyDataSetChanged();
            Log.e(LOG_TAG2, "mItemSuggestions: " + mItemSuggestions);
            mSugAdapter.setItems(mItemSuggestions);
            mSugAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView;
        MenuItem menuItem = menu.findItem(R.id.search);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            searchView = (SearchView) menuItem.getActionView();
        } else {
            searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        }

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint(getResources().getString(R.string.search_hint));
        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e(LOG_TAG2, "query listener: " + query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e(LOG_TAG2, "query listener: " + newText);
                LinkedList<ItemSuggestion> res = new LinkedList<>();
                for (int i = 0; i < mItemSuggestions.size(); i++) {
                    String current = mItemSuggestions.get(i).getName();
                    if(current.contains(newText)) {
                        res.add(mItemSuggestions.get(i));
                    }
                    if(res.size() > 2) {
                        i = mItemSuggestions.size();
                    }
                }
                mSugAdapter.setItems(res);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public void createFoodItem(View view) {
        // Get the reply message from the edit text.
        String name = mName.getText().toString();
        String count = mCount.getText().toString();
        String comment = mComment.getText().toString();
        String position = mPosition;
        Log.e(LOG_TAG2, "name: " + name);
        Log.e(LOG_TAG2, "count: " + count);
        Log.e(LOG_TAG2, "comment: " + comment);
        Log.e(LOG_TAG2, "pos: " + position);

        // Create a new intent for the reply, add the reply message to it
        // as an extra, set the intent result, and close the activity.
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_NAME, name);
        replyIntent.putExtra(EXTRA_COUNT, count);
        replyIntent.putExtra(EXTRA_COMMENT, comment);
        replyIntent.putExtra(EXTRA_POSITION, position);
        setResult(RESULT_OK, replyIntent);
        finish();
    }

    public void deleteFoodItem(View view) {
        // Get the reply message from the edit text.
        String name = mName.getText().toString();
        String count = mCount.getText().toString();
        String comment = mComment.getText().toString();
        String position = mPosition;

        // Create a new intent for the reply, add the reply message to it
        // as an extra, set the intent result, and close the activity.
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_NAME, name);
        replyIntent.putExtra(EXTRA_COUNT, count);
        replyIntent.putExtra(EXTRA_COMMENT, comment);
        replyIntent.putExtra(EXTRA_POSITION, position);
        replyIntent.putExtra(EXTRA_DELETE, "delete");
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}