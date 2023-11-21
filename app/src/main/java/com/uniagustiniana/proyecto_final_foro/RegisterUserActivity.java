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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uniagustiniana.proyecto_final_foro.dto.User;
import com.uniagustiniana.proyecto_final_foro.utils.Common;

import java.util.Objects;

public class RegisterUserActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    EditText name, lastname, age, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        setup();
    }

    private void setup() {
        Button btnRegister = findViewById(R.id.btnRegister);

        name = findViewById(R.id.txtName);
        lastname = findViewById(R.id.txtLastname);
        age = findViewById(R.id.txtAge);
        email = findViewById(R.id.txtRegisterEmail);
        password = findViewById(R.id.txtRegisterPassword);

        btnRegister.setOnClickListener(this::registerUser);
        Common.setTitleActionBar(this, "Registrarse", true, true);
    }

    public void registerUser(View view) {
        firebaseAuth = FirebaseAuth.getInstance();

        if (!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
            firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            saveUserData();
                            startActivity(new Intent(this, LogInActivity.class));
                        } else {
                            Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Common.showAlert(ERROR, "El email y la contraseña son requeridos", this);
        }
    }

    private void saveUserData() {
        String uid = firebaseAuth.getUid();

        User user = new User();
        user.setUid(uid);
        user.setName(name.getText().toString());
        user.setLastname(lastname.getText().toString());
        user.setAge(Integer.parseInt(age.getText().toString()));
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.child(Objects.requireNonNull(uid)).setValue(user).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                Common.showAlert(ERROR, "Error al agregar el grupo", this);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        getOnBackPressedDispatcher().onBackPressed();
        return true;
    }

}