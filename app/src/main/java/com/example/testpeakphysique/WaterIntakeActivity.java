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

public class WaterIntakeActivity extends AppCompatActivity {

    private EditText editTextWeight;
    private TextView textViewWaterIntakeResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waterintake);

        editTextWeight = findViewById(R.id.editTextHeight);
        textViewWaterIntakeResult = findViewById(R.id.textViewResult);

        Button buttonCalculateWaterIntake = findViewById(R.id.buttonCalculate);
        Button buttonBack = findViewById(R.id.buttonBack);
        Button buttonCopy = findViewById(R.id.buttonCopy);

        buttonCalculateWaterIntake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateWaterIntake();
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

    private void calculateWaterIntake() {
        String weightStr = editTextWeight.getText().toString();
        if (!weightStr.isEmpty()) {
            double weight = Double.parseDouble(weightStr);

            // Calculate water intake based on weight
            double waterIntakeLow = calculateWaterIntake(weight, "Low");
            double waterIntakeModerate = calculateWaterIntake(weight, "Moderate");
            double waterIntakeHigh = calculateWaterIntake(weight, "High");

            String result = String.format("Sedentary: %.2f liters\nModerate Activity: %.2f liters\nHigh Activity: %.2f liters",
                    waterIntakeLow, waterIntakeModerate, waterIntakeHigh);

            textViewWaterIntakeResult.setText(result);
        } else {
            Toast.makeText(WaterIntakeActivity.this, "Please enter your weight", Toast.LENGTH_SHORT).show();
        }
    }

    private double calculateWaterIntake(double weight, String activityLevel) {
        double baseWaterIntake;

        switch (activityLevel) {
            case "Low":
                baseWaterIntake = weight * 30; // 30 ml per kg
                break;
            case "Moderate":
                baseWaterIntake = weight * 35; // 35 ml per kg
                break;
            case "High":
                baseWaterIntake = weight * 40; // 40 ml per kg
                break;
            default:
                baseWaterIntake = weight * 30; // Default to low activity
                break;
        }

        // Convert ml to liters
        return baseWaterIntake / 1000;
    }

    private void copyToClipboard() {
        // Get result from textViewResult
        String result = textViewWaterIntakeResult.getText().toString();

        // Check if result is not empty
        if (!result.isEmpty()) {
            // Copy result to clipboard
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Water Intake", result);
            clipboard.setPrimaryClip(clip);

            // Show toast
            Toast.makeText(WaterIntakeActivity.this, "Result copied to clipboard", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(WaterIntakeActivity.this, "No result to copy", Toast.LENGTH_SHORT).show();
        }
    }
}
