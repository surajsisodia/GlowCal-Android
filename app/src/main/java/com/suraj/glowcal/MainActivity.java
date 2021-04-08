package com.suraj.glowcal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText emailEditText, password;
    ConstraintLayout loginBtn;
    TextView loginText;
    ImageView visibleBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        password = findViewById(R.id.pwdEditText);
        emailEditText = findViewById(R.id.emailEditText);
        loginBtn = findViewById(R.id.login_button);
        loginText = findViewById(R.id.loginText);
        visibleBtn = findViewById(R.id.visibility_icon);



        visibleBtn.setOnClickListener(v -> showHidePassword());
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
}