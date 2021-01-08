package com.example.einkaufsliste;

import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;


/**
 * For future use not relevant for grading
 * Will be used for separating the search functionality to a different Activity
 */
public class SearchActivity extends Activity {
    /*
    private static final String LOG_TAG5 = SearchActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Log.e(LOG_TAG5, "in onCreate: ");

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if(Intent.ACTION_SEARCH.equals(intent.getAction())) {
            Log.e(LOG_TAG5, "in onCreate intent: ");
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
    }

    private void doMySearch(String query) {
        Log.e(LOG_TAG5, "Query: " + query);
    }

     */
}