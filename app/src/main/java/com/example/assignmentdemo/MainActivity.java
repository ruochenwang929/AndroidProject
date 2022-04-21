package com.example.assignmentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.assignmentdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    //According to layout name: activity_main
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Following three statements are viewBinding's fixed statement
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.reverseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = binding.editText.getText().toString();
                //abc -> cba
                String reverseStr = new StringBuilder(userInput).reverse().toString();
                binding.editText.setText(reverseStr);
            }
        });

        binding.clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editText.setText("");
            }
        });
    }
}