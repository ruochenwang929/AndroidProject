package com.example.assignmentdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.assignmentdemo.databinding.ActivityLoginBinding;
import com.example.assignmentdemo.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    private ActivitySignupBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        binding.backToLoginView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    doValidationAndSignup();
            }
        });
    }

    private void doValidationAndSignup() {

        boolean isValidEmail = false;

        //empty
        if (binding.emailInput.getText().toString().isEmpty())
        {
            binding.emailInputError.setError("Email can not be empty");
            isValidEmail = false;
        }
        //format incorrect
        else if (!Patterns.EMAIL_ADDRESS.matcher(binding.emailInput.getText().toString()).matches())
        {
            binding.emailInputError.setError(getResources().getString(R.string.invalidEmail));
            //关联至string.xml
            isValidEmail = false;
        }
        //input correct
        else
        {
            binding.emailInputError.setErrorEnabled(false);
            isValidEmail = true;
        }

        //After validation
        if (isValidEmail)
        {
            binding.progressBar.setVisibility(View.VISIBLE);

            mAuth.createUserWithEmailAndPassword(binding.emailInput.getText().toString(), binding.passwordInput.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                binding.progressBar.setVisibility(View.GONE);
                                Toast.makeText(SignupActivity.this, "Sign up successfully", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignupActivity.this, "Sign up failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
    }

}