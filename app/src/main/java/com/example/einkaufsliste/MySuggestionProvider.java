package com.example.einkaufsliste;

import android.content.SearchRecentSuggestionsProvider;

import static android.content.SearchRecentSuggestionsProvider.DATABASE_MODE_QUERIES;

public class MySuggestionProvider extends SearchRecentSuggestionsProvider {
    public final static String AUTHORITY = "com.example.MySuggestionProvider";
    public final static int MODE = DATABASE_MODE_QUERIES;

    public MySuggestionProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }

}
