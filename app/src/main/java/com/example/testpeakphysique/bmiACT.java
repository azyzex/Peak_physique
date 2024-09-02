package com.example.testpeakphysique;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class bmiACT extends AppCompatActivity {

    private double bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        // Find the buttons by their IDs
        Button buttonCalculate = findViewById(R.id.buttonCalculate);
        Button buttonBack = findViewById(R.id.buttonBack);
        Button buttonCopy = findViewById(R.id.buttonCopy);

        // Button click listeners for calculation, navigation, and copying result
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        buttonCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyToClipboard();
            }
        });
    }

    private void calculateBMI() {
        // Get the height and weight entered by the user
        EditText editTextHeight = findViewById(R.id.editTextHeight);
        EditText editTextWeight = findViewById(R.id.editTextweight);

        // Retrieve the height and weight values as strings
        String heightStr = editTextHeight.getText().toString();
        String weightStr = editTextWeight.getText().toString();

        // Check if both height and weight are entered
        if (!heightStr.isEmpty() && !weightStr.isEmpty()) {
            // Parse the height and weight values to double
            double height = Double.parseDouble(heightStr) / 100; // convert cm to meters
            double weight = Double.parseDouble(weightStr);

            // Calculate the BMI
            bmi = weight / (height * height);

            // Display the BMI result
            TextView textViewResult = findViewById(R.id.textViewResult);
            textViewResult.setText("BMI: " + bmi);
        } else {
            // Display a message prompting the user to enter both height and weight
            Snackbar.make(findViewById(android.R.id.content), "Please enter both height and weight", Snackbar.LENGTH_SHORT).show();
        }
    }



    private void copyToClipboard() {
        // Retrieve the BMI result text
        String bmiResult = "BMI: " + bmi;

        // Get the ClipboardManager
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        // Create a ClipData object to store the BMI result
        ClipData clip = ClipData.newPlainText("BMI Result", bmiResult);

        // Copy the ClipData to the clipboard
        clipboard.setPrimaryClip(clip);

        // Show a Snackbar message indicating the BMI result has been copied
        Snackbar.make(findViewById(android.R.id.content), "Results copied", Snackbar.LENGTH_SHORT).show();
    }
}
