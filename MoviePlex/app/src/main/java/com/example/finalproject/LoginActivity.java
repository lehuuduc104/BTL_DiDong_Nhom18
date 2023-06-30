package com.example.finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText email;
    EditText password;
    Button login,btnsignup;

    DataManager dm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Đăng nhập");

        email = findViewById(R.id.signInEmail);
        password = findViewById(R.id.signInPassword);
        login = findViewById(R.id.Loginbutton);
        btnsignup = findViewById(R.id.Signupbutton);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển sang trang SignUpActivity
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        dm = new DataManager(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputEmail = email.getText().toString();
                String inputPassword = password.getText().toString();

                // Kiểm tra đăng nhập
                boolean loginSuccess = checkLogin(inputEmail, inputPassword);

                if (loginSuccess) {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    // Lưu email của người dùng đã đăng nhập vào SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email", inputEmail);
                    editor.putString("password",inputPassword);
                    editor.apply();
                    // Chuyển đến trang chính
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }

            }

        });


    }

    private boolean checkLogin(String email, String password) {
        Cursor cursor = dm.searchByEmailAndPassword(email, password);
        return cursor != null && cursor.getCount() > 0;
    }
}
