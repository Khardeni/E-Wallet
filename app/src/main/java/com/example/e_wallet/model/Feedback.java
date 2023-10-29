package com.example.e_wallet.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Feedback {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "app_rating")
    public float appRating;

    @ColumnInfo(name = "service_rating")
    public float serviceRating;
}

