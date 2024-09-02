package com.example.testpeakphysique;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class dialog_input extends AppCompatActivity {

    private EditText editTextHeight;
    private EditText editTextWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_input);

        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextweight);

        Button buttonCalculate = findViewById(R.id.buttonCalculate);
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String height = editTextHeight.getText().toString();
                String weight = editTextWeight.getText().toString();

                // Pass the height and weight data back to HomeFragment
                Intent intent = new Intent();
                intent.putExtra("height", height);
                intent.putExtra("weight", weight);
                setResult(RESULT_OK, intent);
                finish(); // Close the dialog activity
            }
        });
    }
}
