package com.uniagustiniana.proyecto_final_foro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this::goToRegisterUserActivity);

    }

    protected void goToRegisterUserActivity(View view) {
        Intent intent = new Intent(this, RegisterUserActivity.class);
        startActivity(intent);
    }

}