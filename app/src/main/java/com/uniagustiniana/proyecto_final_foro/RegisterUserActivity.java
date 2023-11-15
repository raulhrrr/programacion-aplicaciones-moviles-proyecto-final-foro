package com.uniagustiniana.proyecto_final_foro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.uniagustiniana.proyecto_final_foro.utils.Common;

public class RegisterUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        setup();
    }

    private void setup() {
        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(v -> {
            TextView email = findViewById(R.id.txtRegisterEmail);
            TextView password = findViewById(R.id.txtRegisterPassword);

            if (!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                goToLoginActivity();
                            } else {
                                Common.showAlert("Error al registrar el usuario", this);
                            }
                        });
            } else {
                email.setError("El email es requerido");
                password.setError("La contraseña es requerida");
            }
        });
    }

    private void goToLoginActivity() {
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }

}