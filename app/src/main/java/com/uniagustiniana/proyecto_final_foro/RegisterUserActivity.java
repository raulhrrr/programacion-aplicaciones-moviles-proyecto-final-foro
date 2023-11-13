package com.uniagustiniana.proyecto_final_foro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.uniagustiniana.proyecto_final_foro.dto.User;
import com.uniagustiniana.proyecto_final_foro.services.UserApiImpl;

public class RegisterUserActivity extends AppCompatActivity {

    private UserApiImpl userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        userService = new UserApiImpl();

        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this::registerUser);

    }

    private void registerUser(View view) {

        TextView name = findViewById(R.id.txtUser);
        TextView lastname = findViewById(R.id.txtLastname);
        TextView age = findViewById(R.id.txtAge);
        TextView email = findViewById(R.id.txtEmail);
        TextView username = findViewById(R.id.txtRegisterUser);
        TextView password = findViewById(R.id.txtRegisterPassword);

        User user = new User();
        user.setName(name.getText().toString());
        user.setLastname(lastname.getText().toString());
        user.setAge(Integer.valueOf(age.getText().toString()));
        user.setEmail(email.getText().toString());
        user.setUsername(username.getText().toString());
        user.setPassword(password.getText().toString());

        userService.registerUser(user, this);
    }

}