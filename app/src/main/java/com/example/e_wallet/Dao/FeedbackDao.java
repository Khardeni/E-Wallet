package com.example.e_wallet.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.e_wallet.model.Feedback;

import java.util.List;

@Dao
public interface FeedbackDao {
    @Insert
    void insertFeedback(Feedback feedback);

    @Query("SELECT * FROM Feedback")
    List<Feedback> getAllFeedback();
}

