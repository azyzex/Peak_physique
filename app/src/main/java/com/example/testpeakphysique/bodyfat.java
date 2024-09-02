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

public class bodyfat extends AppCompatActivity {

    // Variable to store the selected gender
    private boolean isMaleSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bodyfat);

        EditText editTextAge = findViewById(R.id.editTextAge);
        EditText editTextBMI = findViewById(R.id.editTextBMI);
        TextView textViewResult = findViewById(R.id.textViewResult);

        Button buttonCalculate = findViewById(R.id.buttonCalculate);
        Button buttonBack = findViewById(R.id.buttonBack);
        Button buttonCopy = findViewById(R.id.buttonCopy);
        Button buttonMale = findViewById(R.id.buttonMale);
        Button buttonFemale = findViewById(R.id.buttonFemale);

        buttonMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMaleSelected = true;
                // Change button background to indicate selection
                buttonMale.setSelected(true);
                buttonFemale.setSelected(false);
            }
        });

        buttonFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMaleSelected = false;
                // Change button background to indicate selection
                buttonFemale.setSelected(true);
                buttonMale.setSelected(false);
            }
        });

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if gender is selected
                if (!isMaleSelected && !buttonFemale.isSelected()) {
                    Toast.makeText(bodyfat.this, "Please select gender", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Get user inputs
                String ageStr = editTextAge.getText().toString();
                String bmiStr = editTextBMI.getText().toString();

                // Check if inputs are not empty
                if (!ageStr.isEmpty() && !bmiStr.isEmpty()) {
                    int age = Integer.parseInt(ageStr);
                    double bmi = Double.parseDouble(bmiStr);

                    // Calculate Body Fat Percentage
                    double bodyFatPercentage = ((1.20 * bmi) + (0.23 * age) - (10.8 * (isMaleSelected ? 1 : 0)) - 5.4);

                    // Display result
                    textViewResult.setText(String.valueOf(bodyFatPercentage));
                } else {
                    Toast.makeText(bodyfat.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Button to go back to fragment_home
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to fragment_home
                onBackPressed();
            }
        });

        // Button to copy BMI result to clipboard
        buttonCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get result from textViewResult
                String result = textViewResult.getText().toString();

                // Check if result is not empty
                if (!result.isEmpty()) {
                    // Format result before copying to clipboard
                    String formattedResult = "Body Fat Percentage: " + String.format("%.2f", Double.parseDouble(result));

                    // Copy result to clipboard
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Body Fat Percentage", formattedResult);
                    clipboard.setPrimaryClip(clip);

                    // Show toast
                    Toast.makeText(bodyfat.this, "Result copied to clipboard", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(bodyfat.this, "No result to copy", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
