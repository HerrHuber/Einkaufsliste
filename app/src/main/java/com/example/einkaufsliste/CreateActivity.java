package com.example.einkaufsliste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);


        mName = findViewById(R.id.editTextTextPersonName);
        mCount = findViewById(R.id.editTextNumber);
        mComment = findViewById(R.id.editTextTextPersonName2);

        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.EXTRA_NAME);
        String count = intent.getStringExtra(MainActivity.EXTRA_COUNT);
        String comment = intent.getStringExtra(MainActivity.EXTRA_COMMENT);
        mPosition = intent.getStringExtra(FoodListAdapter.EXTRA_POSITION);

        mName.setText(name);
        mCount.setText(count);
        mComment.setText(comment);
    }

    public void createFoodItem(View view) {
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