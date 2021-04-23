package com.suraj.glowcal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.snackbar.SnackbarContentLayout;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import java.time.Duration;

public class LoginActivity extends AppCompatActivity {
    EditText emailEditText, password;
    ConstraintLayout loginBtn;
    TextView loginText;
    ImageView visibleBtn;
    TextView signUpBtn;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        password = findViewById(R.id.pwdEditText);
        emailEditText = findViewById(R.id.emailEditText);
        loginBtn = findViewById(R.id.login_button);
        loginText = findViewById(R.id.loginText);
        visibleBtn = findViewById(R.id.visibility_icon);
        signUpBtn = findViewById(R.id.signUpBtn);


        visibleBtn.setOnClickListener(v -> showHidePassword());
        signUpBtn.setOnClickListener(v -> {
            Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(i);
        });

        loginBtn.setOnClickListener(v -> loginWithEmail());
    }

    public void showHidePassword() {
        if (password.getTransformationMethod() == PasswordTransformationMethod.getInstance()) {
            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            visibleBtn.setImageResource(R.drawable.icon_password_visibility_off);
        } else if (password.getTransformationMethod() == HideReturnsTransformationMethod.getInstance()) {
            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            visibleBtn.setImageResource(R.drawable.icon_password_visible);
        }
    }

    private void loginWithEmail() {
        String email = emailEditText.getText().toString().trim();
        String pwd = password.getText().toString().trim();

        try {
            Task<AuthResult> authResultTask = auth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        System.out.println("Login Successful");
                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(i);
                        Snackbar.make(loginBtn, "Login Successful", Snackbar.LENGTH_LONG).setBackgroundTint(Color.GREEN).show();
                    }
                    else {
                        System.out.println("Error Encountered");
                        Snackbar.make(loginBtn, task.getException().getMessage(), Snackbar.LENGTH_LONG).setBackgroundTint(Color.RED).show();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
}