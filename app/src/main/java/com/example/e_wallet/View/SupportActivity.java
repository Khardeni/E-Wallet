package com.example.e_wallet.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.e_wallet.Dao.SupportDao;
import com.example.e_wallet.Database.AppDatabase;
import com.example.e_wallet.MyApplication;
import com.example.e_wallet.R;
import com.example.e_wallet.model.Support;
import android.app.AlertDialog;

import java.util.List;

public class SupportActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "support_channel_id"; // Define a channel ID for the notification channel
    private SupportDao supportDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        AppDatabase db = MyApplication.getDatabase(); // Assuming you have initialized database in MyApplication as shown in previous responses.
        supportDao = db.supportDao();

        Spinner supportOptions = findViewById(R.id.supportOptions);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.support_options, android.R.layout.simple_spinner_item);
        // ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, supportOptionsArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        supportOptions.setAdapter(adapter);


        EditText commentField = findViewById(R.id.commentField);
        Button submitBtn = findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(v -> {
            String selectedOption = supportOptions.getSelectedItem().toString();
            String comment = commentField.getText().toString();

            Support support = new Support();
            support.supportOption = selectedOption;
            support.comment = comment;

            // Insert the data in a background thread
            new Thread(() -> {
                supportDao.insertSupport(support);
                checkSupportData();

                // After inserting data, show the AlertDialog on the UI thread
                runOnUiThread(() -> {
                    new AlertDialog.Builder(SupportActivity.this)
                            .setTitle("Success")
                            .setMessage("Your support request has been submitted!")
                            .setPositiveButton(android.R.string.ok, (dialog, which) -> dialog.dismiss())
                            .show();
                });

            }).start();
        });

    }
    private void checkSupportData() {
        List<Support> supports = supportDao.getAllSupport();
        if (supports != null && !supports.isEmpty()) {
            for (Support support : supports) {
                Log.d("SupportData", "Option: " + support.supportOption + ", Comment: " + support.comment);
            }
        } else {
            Log.d("SupportData", "No support requests found in the database.");
        }
    }

}

