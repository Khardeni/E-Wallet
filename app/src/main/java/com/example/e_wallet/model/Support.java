package com.example.e_wallet.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Support {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "support_option")
    public String supportOption;

    @ColumnInfo(name = "comment")
    public String comment;
}