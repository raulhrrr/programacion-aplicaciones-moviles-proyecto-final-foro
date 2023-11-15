package com.uniagustiniana.proyecto_final_foro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.uniagustiniana.proyecto_final_foro.utils.Common;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setup();
    }

    private void setup() {
        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v -> {
            TextView email = findViewById(R.id.txtLoginEmail);
            TextView password = findViewById(R.id.txtLoginPassword);

            if (!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                System.out.println("Login success");
                                //goToHomeActivity(email.getText().toString());
                            } else {
                                Common.showAlert("Error al iniciar sesión", this);
                            }
                        });
            } else {
                email.setError("El email es requerido");
                password.setError("La contraseña es requerida");
            }
        });

        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this::goToRegisterUserActivity);
    }

    protected void goToRegisterUserActivity(View view) {
        Intent intent = new Intent(this, RegisterUserActivity.class);
        startActivity(intent);
    }

}