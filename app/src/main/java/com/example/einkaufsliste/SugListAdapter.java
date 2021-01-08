package com.example.einkaufsliste;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class SugListAdapter extends
        RecyclerView.Adapter<SugListAdapter.SugViewHolder> {
    private static final String LOG_TAG6 = MainActivity.class.getSimpleName();
    private LinkedList<FoodItem> mSugList;
    private LayoutInflater mInflater;
    private List<ItemSuggestion> mItemSuggestions;
    private Context mContext;

    public SugListAdapter(Context context, LinkedList<FoodItem> foodItems) {
        mInflater = LayoutInflater.from(context);
        this.mSugList = foodItems;
        mContext = context;
    }

    @NonNull
    @Override
    public SugListAdapter.SugViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.suglist_item,
                parent, false);
        return new SugViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull SugListAdapter.SugViewHolder holder, int position) {
        if(mItemSuggestions != null) {
            ItemSuggestion current = mItemSuggestions.get(position);
            holder.sugItemView.setText(current.toString());
        } else {
            holder.sugItemView.setText("No Item");
        }
    }

    @Override
    public int getItemCount() {
        int result;
        if (mItemSuggestions != null) {
            result = mItemSuggestions.size();
        } else {
            result = 0;
        }
        return result;
    }

    void setItems(List<ItemSuggestion> itemSuggestions){
        mItemSuggestions = itemSuggestions;
        notifyDataSetChanged();
    }

    class SugViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView sugItemView;
        final SugListAdapter mAdapter;

        public SugViewHolder(View itemView, SugListAdapter adapter) {
            super(itemView);
            sugItemView = itemView.findViewById(R.id.sug);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Get the position of the item that was clicked
            int mPosition = getLayoutPosition();
            // Use that to access the affected item in mWordList
            ItemSuggestion element = mItemSuggestions.get(mPosition);
            Log.e(LOG_TAG6, "onClick: " + element.getName());
            EditText name = (EditText) ((Activity) mContext).findViewById(R.id.editTextTextPersonName);
            EditText count = (EditText) ((Activity) mContext).findViewById(R.id.editTextNumber);
            EditText comment = (EditText) ((Activity) mContext).findViewById(R.id.editTextTextPersonName2);
            Log.e(LOG_TAG6, "name: " + element.getCount());
            name.setText(element.getName());
            count.setText(String.valueOf(element.getCount()));
            comment.setText(element.getComment());
            mAdapter.notifyDataSetChanged();
        }
    }
}
