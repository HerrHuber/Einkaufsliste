package com.example.einkaufsliste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateActivity extends AppCompatActivity {
    public static final String EXTRA_NAME= "com.example.eikaufsliste.extra.NAME";
    public static final String EXTRA_COUNT = "com.example.eikaufsliste.extra.COUNT";
    public static final String EXTRA_COMMENT = "com.example.eikaufsliste.extra.COMMENT";
    private EditText mName;
    private EditText mCount;
    private EditText mComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);


        mName = findViewById(R.id.editTextTextPersonName);
        mCount = findViewById(R.id.editTextNumber);
        mComment = findViewById(R.id.editTextTextPersonName2);
    }

    public void createFoodItem(View view) {
        // Get the reply message from the edit text.
        String name = mName.getText().toString();
        String count = mCount.getText().toString();
        String comment = mComment.getText().toString();

        // Create a new intent for the reply, add the reply message to it
        // as an extra, set the intent result, and close the activity.
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_NAME, name);
        replyIntent.putExtra(EXTRA_COUNT, count);
        replyIntent.putExtra(EXTRA_COMMENT, comment);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}