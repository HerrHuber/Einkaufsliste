package com.example.einkaufsliste;

import android.app.Activity;
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
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FoodListAdapter extends
        RecyclerView.Adapter<FoodListAdapter.FoodViewHolder>  {
    private static final String LOG_TAG3 = FoodListAdapter.class.getSimpleName();
    private final LinkedList<FoodItem> mFoodList;
    private LayoutInflater mInflater;
    private Context mContext;
    public static final String EXTRA_NAME = "com.example.eikaufsliste.extra.NAME";
    public static final String EXTRA_COUNT = "com.example.eikaufsliste.extra.COUNT";
    public static final String EXTRA_COMMENT = "com.example.eikaufsliste.extra.COMMENT";
    public static final String EXTRA_POSITION = "com.example.eikaufsliste.extra.POSITION";
    public List<RoomItem> mItems;

    public FoodListAdapter(Context context, LinkedList<FoodItem> foodList) {
        mInflater = LayoutInflater.from(context);
        this.mFoodList = foodList;
        mContext = context;
    }

    @NonNull
    @Override
    public FoodListAdapter.FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.foodlist_item,
                parent, false);
        return new FoodViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodListAdapter.FoodViewHolder holder, int position) {
        if(mItems != null) {
            RoomItem current = mItems.get(position);
            holder.foodItemView.setText(current.toString());
            holder.boughtCheckBox.setChecked(current.getBought());
        } else {
            holder.foodItemView.setText("No Item");
            holder.boughtCheckBox.setChecked(true);
        }
    }

    void setItems(List<RoomItem> roomItems){
        mItems = roomItems;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        int result;
        if (mItems != null) {
            result = mItems.size();
        } else {
            result = 0;
        }
        return result;
    }

    public RoomItem getItem(int pos) {
        return mItems.get(pos);
    }

    public String getIndices() {
        return mItems.toString();
    }

    class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView foodItemView;
        public final CheckBox boughtCheckBox;
        final FoodListAdapter mAdapter;
        private ItemViewModel mItemViewModel;

        public FoodViewHolder(View itemView, FoodListAdapter adapter) {
            super(itemView);
            foodItemView = itemView.findViewById(R.id.food);
            boughtCheckBox = itemView.findViewById(R.id.checkBox);
            this.mAdapter = adapter;
            mItemViewModel = new ViewModelProvider((ViewModelStoreOwner) mContext).get(ItemViewModel.class);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position = getLayoutPosition();
                    RoomItem item = mItems.get(position);
                    Intent intent = new Intent(mContext, CreateActivity.class);
                    intent.putExtra(EXTRA_NAME, item.getName());
                    intent.putExtra(EXTRA_COUNT, "" + item.getCount());
                    intent.putExtra(EXTRA_COMMENT, item.getComment());
                    intent.putExtra(EXTRA_POSITION, String.valueOf(position));
                    ((Activity) mContext).startActivityForResult(intent, 2);
                    return false;
                }
            });
        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();
            int length = mAdapter.getItemCount();
            RoomItem item = mItems.get(position);
            LinkedList<RoomItem> list = new LinkedList<>();
            for (RoomItem t : mItems) {
                list.add(t);
            }
            if(item.getBought()) {
                list.remove(item);
                RoomItem newItem = new RoomItem(item.getId(), item.getName(), item.getCount(), item.getComment(), false);
                mItemViewModel.update(newItem);
                list.addFirst(newItem);
                for (int i = 0; i < length; i++) {
                    // because order of list inverse of order of layout
                    RoomItem current = list.get(length - 1 - i);
                    RoomItem n = new RoomItem(i, current.getName(), current.getCount(), current.getComment(), current.getBought());
                    mItemViewModel.update(n);
                }
            } else {
                list.remove(item);
                RoomItem newItem = new RoomItem(item.getId(), item.getName(), item.getCount(), item.getComment(), true);
                mItemViewModel.update(newItem);
                list.addLast(newItem);
                for (int i = 0; i < length; i++) {
                    // because order of list inverse of order of layout
                    RoomItem current = list.get(length - 1 - i);
                    RoomItem n = new RoomItem(i, current.getName(), current.getCount(), current.getComment(), current.getBought());
                    mItemViewModel.update(n);
                }
            }
            mAdapter.notifyDataSetChanged();
        }

    }

}









