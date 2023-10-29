package com.example.e_wallet.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.e_wallet.Dao.FeedbackDao;
import com.example.e_wallet.Dao.SupportDao;
import com.example.e_wallet.model.Feedback;
import com.example.e_wallet.model.Support;

@Database(entities = {Feedback.class, Support.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {
    public abstract FeedbackDao feedbackDao();
    public abstract SupportDao supportDao();
}