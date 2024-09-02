package com.example.testpeakphysique.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.testpeakphysique.R;
import com.example.testpeakphysique.bodyfat;
import com.example.testpeakphysique.bmiACT;
import com.example.testpeakphysique.bmr;
import com.example.testpeakphysique.calories;
import com.example.testpeakphysique.databinding.FragmentHomeBinding;
import com.example.testpeakphysique.idealweight;
import com.example.testpeakphysique.WaterIntakeActivity;
import com.example.testpeakphysique.heartrate; // Import the heartrate activity class

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Find the BMI button and set OnClickListener
        Button buttonBmi = root.findViewById(R.id.button4);
        buttonBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToBmiPage();
            }
        });

        // Find the BMR button and set OnClickListener
        Button buttonBmr = root.findViewById(R.id.button5);
        buttonBmr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToBmrPage();
            }
        });

        // Find the Body Fat button and set OnClickListener
        Button buttonBodyFat = root.findViewById(R.id.button2);
        buttonBodyFat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToBodyFatPage();
            }
        });

        // Find the Calories button and set OnClickListener
        Button buttonCalories = root.findViewById(R.id.buttonCalories);
        buttonCalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCaloriesPage();
            }
        });

        // Find the Ideal Weight button and set OnClickListener
        Button buttonIdealWeight = root.findViewById(R.id.buttonc);
        buttonIdealWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToIdealWeightPage();
            }
        });

        // Find the Water Intake button and set OnClickListener
        Button buttonWaterIntake = root.findViewById(R.id.buttonb);
        buttonWaterIntake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWaterIntakePage();
            }
        });

        // Find the Heart Rate button and set OnClickListener
        Button buttonHeartRate = root.findViewById(R.id.buttona);
        buttonHeartRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHeartRatePage();
            }
        });

        return root;
    }

    // Method to navigate to the BMI activity
    private void goToBmiPage() {
        Intent intent = new Intent(getActivity(), bmiACT.class);
        startActivity(intent);
    }

    // Method to navigate to the BMR activity
    private void goToBmrPage() {
        Intent intent = new Intent(getActivity(), bmr.class);
        startActivity(intent);
    }

    // Method to navigate to the Body Fat activity
    private void goToBodyFatPage() {
        Intent intent = new Intent(getActivity(), bodyfat.class);
        startActivity(intent);
    }

    // Method to navigate to the Calories activity
    private void goToCaloriesPage() {
        Intent intent = new Intent(getActivity(), calories.class);
        startActivity(intent);
    }

    // Method to navigate to the Ideal Weight activity
    private void goToIdealWeightPage() {
        Intent intent = new Intent(getActivity(), idealweight.class);
        startActivity(intent);
    }

    // Method to navigate to the Water Intake activity
    private void goToWaterIntakePage() {
        Intent intent = new Intent(getActivity(), WaterIntakeActivity.class);
        startActivity(intent);
    }

    // Method to navigate to the Heart Rate activity
    private void goToHeartRatePage() {
        Intent intent = new Intent(getActivity(), heartrate.class);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
