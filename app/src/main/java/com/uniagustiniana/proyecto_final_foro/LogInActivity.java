package com.uniagustiniana.proyecto_final_foro;

import static com.uniagustiniana.proyecto_final_foro.utils.TitleType.ERROR;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
        btnLogin.setOnClickListener(this::login);

        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(v -> startActivity(new Intent(this, RegisterUserActivity.class)));

        Common.setTitleActionBar(this, "Iniciar sesión", false, false);
    }

    public void login(View view) {
        EditText email = findViewById(R.id.txtLoginEmail);
        EditText password = findViewById(R.id.txtLoginPassword);

        if (!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(this, UserConfigurationActivity.class));
                        } else {
                            Toast.makeText(this, "Error en el inicio de sesión", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Common.showAlert(ERROR, "El email y la contraseña son requeridos", this);
        }
    }

}