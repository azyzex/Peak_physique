package com.example.testpeakphysique;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class bmr extends AppCompatActivity {

    // Variable to store the selected gender
    private boolean isMaleSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmr);

        EditText editTextHeight = findViewById(R.id.editTextHeight);
        EditText editTextWeight = findViewById(R.id.editTextweight);
        EditText editTextAge = findViewById(R.id.editTextAge);
        TextView textViewResult = findViewById(R.id.textViewResult);

        Button buttonCalculate = findViewById(R.id.buttonCalculate);
        Button buttonBack = findViewById(R.id.buttonBack);
        Button buttonCopy = findViewById(R.id.buttonCopy);
        Button buttonMale = findViewById(R.id.buttonMale);
        Button buttonFemale = findViewById(R.id.buttonFemale);

        buttonMale.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                isMaleSelected = true;
                // Change button background to indicate selection
                buttonMale.setSelected(true);
                buttonFemale.setSelected(false);
            }
        });

        buttonFemale.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                isMaleSelected = false;
                // Change button background to indicate selection
                buttonFemale.setSelected(true);
                buttonMale.setSelected(false);
            }
        });

        buttonCalculate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if gender is selected
                if (!isMaleSelected && !buttonFemale.isSelected()) {
                    Toast.makeText(bmr.this, "Please select gender", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Get user inputs
                String heightStr = editTextHeight.getText().toString();
                String weightStr = editTextWeight.getText().toString();
                String ageStr = editTextAge.getText().toString();

                // Check if inputs are not empty
                if (!heightStr.isEmpty() && !weightStr.isEmpty() && !ageStr.isEmpty()) {
                    double height = Double.parseDouble(heightStr);
                    double weight = Double.parseDouble(weightStr);
                    int age = Integer.parseInt(ageStr);

                    // Calculate BMR based on selected gender
                    double bmr;
                    if (isMaleSelected) {
                        bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
                    } else {
                        bmr = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
                    }

                    // Display result
                    textViewResult.setText("BMR: " + bmr);
                } else {
                    Toast.makeText(bmr.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Button to go back to fragment_home
        buttonBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to fragment_home
                onBackPressed();
            }
        });

        // Button to copy BMI result to clipboard
        buttonCopy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get result from textViewResult
                String result = textViewResult.getText().toString();
                // Copy result to clipboard
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("BMR Result", result);
                clipboard.setPrimaryClip(clip);
                // Show toast
                Toast.makeText(bmr.this, "Result copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
