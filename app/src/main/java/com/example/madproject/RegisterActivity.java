package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText firstname, lastname, username, dob, email, password;
    MaterialButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstname = findViewById(R.id.firstName);
        lastname = findViewById(R.id.lastName);
        username = findViewById(R.id.userName);
        dob = findViewById(R.id.dateOfBirth);
        email = findViewById(R.id.emailAddress);
        password = findViewById(R.id.password);
        button = findViewById(R.id.signup);

        button.setOnClickListener((v) -> registerUser());
    }

    private void registerUser() {
        String firstnameInput, lastnameInput, usernameInput, dobInput, emailInput, passwordInput;
        firstnameInput = firstname.getText().toString();
        lastnameInput = lastname.getText().toString();
        usernameInput = username.getText().toString();
        dobInput = dob.getText().toString();
        emailInput = email.getText().toString();
        passwordInput = password.getText().toString();

        boolean isValidated = validateData(emailInput, passwordInput);

        if (!isValidated) return;
        else createAccountInFirebase(firstnameInput, lastnameInput, usernameInput, dobInput, passwordInput, emailInput);
    }

    private void createAccountInFirebase(String firstnameInput, String lastnameInput, String usernameInput, String dobInput, String passwordInput, String emailInput) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(emailInput, passwordInput).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Utilities.createToast(getApplicationContext(), "User creation successful");
                    firebaseAuth.getCurrentUser().sendEmailVerification();
                    firebaseAuth.signOut();
                    finish();
                } else {
                    Utilities.createToast(getApplicationContext(), task.getException().getLocalizedMessage());
                }
            }
        });
    }

    private boolean validateData(String emailInput, String passwordInput) {
        if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Invalid email address");
            return false;
        }
        if (passwordInput.length() < 6) {
            password.setError("Password should contain at least 6 characters");
            return false;
        }
        return true;
    }
}