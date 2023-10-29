package com.example.e_wallet.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RatingBar;

import com.example.e_wallet.Dao.FeedbackDao;
import com.example.e_wallet.Database.AppDatabase;
import com.example.e_wallet.MyApplication;
import com.example.e_wallet.R;
import com.example.e_wallet.model.Feedback;
import android.app.AlertDialog;

import java.util.List;

public class FeedbackActivity extends AppCompatActivity {

    AppDatabase db;
    FeedbackDao feedbackDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        db = MyApplication.getDatabase();
        feedbackDao = db.feedbackDao();

        // Retrieve the RatingBars from the layout
        RatingBar appRatingBar = findViewById(R.id.rateApp);
        RatingBar serviceRatingBar = findViewById(R.id.rateServices);

        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(view -> {
            Feedback feedback = new Feedback();

            // Get ratings from the RatingBars
            feedback.appRating = appRatingBar.getRating();
            feedback.serviceRating = serviceRatingBar.getRating();

            new Thread(() -> {
                feedbackDao.insertFeedback(feedback);
                runOnUiThread(() -> showAlertDialog());
                checkFeedbackData();
            }).start();
        });
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Feedback Submitted")
                .setMessage("Thank you for your feedback!")
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }

    private void checkFeedbackData() {
        List<Feedback> feedbackList = feedbackDao.getAllFeedback();
        if (feedbackList != null && !feedbackList.isEmpty()) {
            for (Feedback feedback : feedbackList) {
                Log.d("FeedbackData", "App Rating: " + feedback.appRating + ", Service Rating: " + feedback.serviceRating);
            }
        } else {
            Log.d("FeedbackData", "No feedback found in the database.");
        }
    }
}