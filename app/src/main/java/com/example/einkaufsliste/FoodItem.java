package com.example.einkaufsliste;

public class FoodItem {
    private String name;
    private int count;
    private String comment;
    private boolean bought;

    public FoodItem(String name) {
        this.name = name;
        this.count = 1;
        this.comment = "";
        this.bought = false;
    }

    public FoodItem(String name, int count) {
        this.name = name;
        this.count = count;
        this.comment = "";
        this.bought = false;
    }

    public FoodItem(String name, String comment) {
        this.name = name;
        this.count = 1;
        this.comment = comment;
        this.bought = false;
    }

    public FoodItem(String name, int count, String comment) {
        this.name = name;
        this.count = count;
        this.comment = comment;
        this.bought = false;
    }

    public String getName() {
        return this.name;
    }

    public int getCount() {
        return this.count;
    }

    public String getComment() {
        return this.comment;
    }

    public boolean getbought() {
        return this.bought;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public String toString() {
        return this.count + "x " + this.name + " " + this.comment;
    }
}
