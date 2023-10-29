package com.example.e_wallet.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.e_wallet.model.Support;

import java.util.List;

@Dao
public interface SupportDao {
    @Insert
    void insertSupport(Support support);

    @Query("SELECT * FROM Support")
    List<Support> getAllSupport();


}