package com.example.testpeakphysique;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class heartrate extends AppCompatActivity {

    private EditText editTextAge;
    private Button buttonCalculate;
    private Button buttonBack;
    private Button buttonCopy;
    private TextView textViewResult; // Use TextView or MaterialTextView depending on your setup

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heartrate); // Make sure this layout exists

        // Initialize views
        editTextAge = findViewById(R.id.editTextAge);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        buttonBack = findViewById(R.id.buttonBack);
        buttonCopy = findViewById(R.id.buttonCopy);
        textViewResult = findViewById(R.id.textViewResult);

        // Set click listeners
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateHeartRate();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the current activity and go back
            }
        });

        buttonCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyResultToClipboard();
            }
        });
    }

    private void calculateHeartRate() {
        // Example calculation (you should implement your own logic)
        try {
            int age = Integer.parseInt(editTextAge.getText().toString().trim());
            int maxHeartRate = 220 - age;

            // Display the result
            textViewResult.setText("Max Heart Rate: " + maxHeartRate);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid age", Toast.LENGTH_SHORT).show();
        }
    }

    private void copyResultToClipboard() {
        // Get the text from textViewResult
        String result = textViewResult.getText().toString();

        // Copy to clipboard
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("Heart Rate Result", result);
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(clipData);

            // Show toast message
            Toast.makeText(this, "Result copied to clipboard", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Clipboard not available", Toast.LENGTH_SHORT).show();
        }
    }
}
