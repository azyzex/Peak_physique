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

public class calories extends AppCompatActivity {

    private double tdee;
    private boolean isMaleSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories);

        EditText editTextHeight = findViewById(R.id.editTextHeight);
        EditText editTextWeight = findViewById(R.id.editTextweight);
        EditText editTextAge = findViewById(R.id.editTextAge);
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
                buttonMale.setSelected(true);
                buttonFemale.setSelected(false);
            }
        });

        buttonFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMaleSelected = false;
                buttonFemale.setSelected(true);
                buttonMale.setSelected(false);
            }
        });

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String heightStr = editTextHeight.getText().toString();
                String weightStr = editTextWeight.getText().toString();
                String ageStr = editTextAge.getText().toString();

                if (!heightStr.isEmpty() && !weightStr.isEmpty() && !ageStr.isEmpty()) {
                    double height = Double.parseDouble(heightStr);
                    double weight = Double.parseDouble(weightStr);
                    int age = Integer.parseInt(ageStr);

                    if (isMaleSelected) {
                        tdee = (10 * weight) + (6.25 * height) - (5 * age) + 5;
                    } else {
                        tdee = (10 * weight) + (6.25 * height) - (5 * age) - 161;
                    }

                    textViewResult.setText(String.format("%.2f", tdee));
                } else {
                    Toast.makeText(calories.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
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

    private void copyToClipboard() {
        String result = "TDEE: " + String.format("%.2f", tdee);
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("TDEE Result", result);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(calories.this, "Result copied to clipboard", Toast.LENGTH_SHORT).show();
    }
}
