package com.example.e_wallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.e_wallet.Database.AppDatabase;
import com.example.e_wallet.View.FeedbackActivity;
import com.example.e_wallet.View.SupportActivity;

public class MainActivity extends AppCompatActivity {

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the database here
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "ewallet-database").build();

        Button btnFeedback = findViewById(R.id.btnFeedback);
        Button btnSupport = findViewById(R.id.btnSupport);

        btnFeedback.setOnClickListener(v -> {
            Intent feedbackIntent = new Intent(MainActivity.this, FeedbackActivity.class);
            startActivity(feedbackIntent);
        });

        btnSupport.setOnClickListener(v -> {
            Intent supportIntent = new Intent(MainActivity.this, SupportActivity.class);
            startActivity(supportIntent);
        });
    }
}