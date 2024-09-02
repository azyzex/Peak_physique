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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class idealweight extends AppCompatActivity {

    // Variable to store the selected gender
    private boolean isMaleSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idealweight);

        EditText editTextHeight = findViewById(R.id.editTextHeight);
        TextView textViewResult = findViewById(R.id.textViewResult);

        Button buttonCalculate = findViewById(R.id.buttonCalculate);
        Button buttonBack = findViewById(R.id.buttonBack);
        Button buttonCopy = findViewById(R.id.buttonCopy);
        Button buttonMale = findViewById(R.id.buttonMale);
        Button buttonFemale = findViewById(R.id.buttonFemale);

        // Handle gender selection
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

        // Handle calculate button click
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if gender is selected
                if (!isMaleSelected && !buttonFemale.isSelected()) {
                    Toast.makeText(idealweight.this, "Please select gender", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Get user inputs
                String heightStr = editTextHeight.getText().toString();

                // Check if inputs are not empty
                if (!heightStr.isEmpty()) {
                    double height = Double.parseDouble(heightStr);

                    // Calculate ideal weight
                    double idealWeight;
                    if (isMaleSelected) {
                        idealWeight = 50 + 0.91 * (height - 152.4);
                    } else {
                        idealWeight = 45.5 + 0.91 * (height - 152.4);
                    }

                    // Display result
                    textViewResult.setText(String.format("Your ideal weight is: %.2f kg", idealWeight));
                } else {
                    Toast.makeText(idealweight.this, "Please enter your height", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Handle back button click
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to previous activity
                onBackPressed();
            }
        });

        // Handle copy result button click
        buttonCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get result from textViewResult
                String result = textViewResult.getText().toString();

                // Check if result is not empty
                if (!result.isEmpty()) {
                    // Format result before copying to clipboard
                    String formattedResult = "Ideal Weight: " + result;

                    // Copy result to clipboard
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Ideal Weight", formattedResult);
                    clipboard.setPrimaryClip(clip);

                    // Show toast
                    Toast.makeText(idealweight.this, "Result copied to clipboard", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(idealweight.this, "No result to copy", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Apply window insets for padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.idealw), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
