package com.example.einkaufsliste;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private final LinkedList<FoodItem> mFoodList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private FoodListAdapter mAdapter;
    public static final int FoodItem_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_add_for_fab);
        /*
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();


                // startActivityForResult

                int wordListSize = mFoodList.size();
                // Add a new word to the wordList.
                mFoodList.addLast("+ Word " + wordListSize);
                // Notify the adapter, that the data has changed.
                mRecyclerView.getAdapter().notifyItemInserted(wordListSize);
                // Scroll to the bottom.
                mRecyclerView.smoothScrollToPosition(wordListSize);
                Intent intent = new Intent(this, CreateActivity.class);
                startActivity(intent);
            }
        }); */

        FoodItem elem1 = new FoodItem("Milch", 1, "1.5%");
        FoodItem elem2 = new FoodItem("Bort", 1, "Vollkorn");
        FoodItem elem3 = new FoodItem("Milch", 2, "3.5%");
        mFoodList.addLast(elem1);
        mFoodList.addLast(elem2);
        mFoodList.addLast(elem3);

        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new FoodListAdapter(this, mFoodList);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void createFood(View view) {
        Intent intent = new Intent(this, CreateActivity.class);
        startActivityForResult(intent, FoodItem_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Test for the right intent reply.
        if(requestCode == FoodItem_REQUEST) {
            // Test to make sure the intent reply result was good.
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra(CreateActivity.EXTRA_NAME);
                String temp = data.getStringExtra(CreateActivity.EXTRA_COUNT);
                int count = 1;
                if(!"".equals(temp)) {
                    count = Integer.parseInt(temp);
                }
                String comment = data.getStringExtra(CreateActivity.EXTRA_COMMENT);

                FoodItem newItem = new FoodItem(name, count, comment);

                mFoodList.addFirst(newItem);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    /*
    public void editFood(View view) {
        Log.e(LOG_TAG, "editFood");
    }

     */
}