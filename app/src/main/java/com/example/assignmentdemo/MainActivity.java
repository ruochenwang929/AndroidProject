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

        Button reverseButton = findViewById(R.id.reverseBtn); //rep->id
        Button clearButton = findViewById(R.id.clearBtn);
        EditText editText = findViewById(R.id.editText);

        reverseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = editText.getText().toString();
                //abc -> cba
                String reverseStr = new StringBuilder(userInput).reverse().toString();
                editText.setText(reverseStr);
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
    }
}