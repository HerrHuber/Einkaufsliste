package com.example.einkaufsliste;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class FoodListAdapter extends
        RecyclerView.Adapter<FoodListAdapter.FoodViewHolder>  {
    private final LinkedList<FoodItem> mFoodList;
    private LayoutInflater mInflater;

    public FoodListAdapter(Context context, LinkedList<FoodItem> foodList) {
        mInflater = LayoutInflater.from(context);
        this.mFoodList = foodList;
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
        FoodItem mCurrent = mFoodList.get(position);
        holder.foodItemView.setText(mCurrent.toString());
        holder.boughtCheckBox.setChecked(mCurrent.getbought());
    }

    @Override
    public int getItemCount() {
        return mFoodList.size();
    }

    class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //class FoodViewHolder extends RecyclerView.ViewHolder {
        public final TextView foodItemView;
        public final CheckBox boughtCheckBox;
        final FoodListAdapter mAdapter;

        public FoodViewHolder(View itemView, FoodListAdapter adapter) {
            super(itemView);
            foodItemView = itemView.findViewById(R.id.food);
            boughtCheckBox = itemView.findViewById(R.id.checkBox);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    // Get the position of the item that was clicked.
                    int mPosition = getLayoutPosition();
                    // Use that to access the affected item in mWordList.
                    FoodItem element = mFoodList.get(mPosition);
                    // Change the word in the mWordList.
                    mFoodList.set(mPosition, element);
                    // Notify the adapter, that the data has changed so it can
                    // update the RecyclerView to display the data.
                    mAdapter.notifyDataSetChanged();
                    // start activity for result
                    Intent intent = new Intent(view.getContext(), CreateActivity.class);
                    view.getContext().startActivity(intent);

                    return false;
                }
            });
        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            FoodItem element = mFoodList.get(mPosition);
            if(element.getbought()) {
                element.setBought(false);
                mFoodList.remove(element);
                mFoodList.addFirst(element);
            } else {
                element.setBought(true);
                mFoodList.remove(element);
                mFoodList.addLast(element);
            }
            mAdapter.notifyDataSetChanged();
        }

    }

}









