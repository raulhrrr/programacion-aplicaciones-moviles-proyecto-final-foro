package com.uniagustiniana.proyecto_final_foro;

import static com.uniagustiniana.proyecto_final_foro.utils.TitleType.SUCCESS;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uniagustiniana.proyecto_final_foro.utils.Common;

import java.util.Objects;

public class UserConfigurationActivity extends AppCompatActivity {

    DatabaseReference users;
    FirebaseUser currentUser;
    FirebaseAuth firebaseAuth;
    EditText txtNameUpdate, txtLastnameUpdate, txtAgeUpdate, txtEmailUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_data);
        setup();
    }

    private void setup() {
        users = FirebaseDatabase.getInstance().getReference("Users");
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        txtNameUpdate = findViewById(R.id.txtNameUpdate);
        txtLastnameUpdate = findViewById(R.id.txtLastnameUpdate);
        txtAgeUpdate = findViewById(R.id.txtAgeUpdate);
        txtEmailUpdate = findViewById(R.id.txtEmailUpdate);

        users.child(Objects.requireNonNull(currentUser).getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String name = "" + snapshot.child("name").getValue();
                    String lastname = "" + snapshot.child("lastname").getValue();
                    String age = "" + snapshot.child("age").getValue();
                    String email = "" + snapshot.child("email").getValue();

                    txtNameUpdate.setText(name);
                    txtLastnameUpdate.setText(lastname);
                    txtAgeUpdate.setText(age);
                    txtEmailUpdate.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Button btnUpdate = findViewById(R.id.button);
        btnUpdate.setOnClickListener(this::updateUser);

        Common.setTitleActionBar(this, "Configuración", true, true);
    }

    private void updateUser(View view) {
        String uid = currentUser.getUid();

        users.child(uid).child("name").setValue(txtNameUpdate.getText().toString());
        users.child(uid).child("lastname").setValue(txtLastnameUpdate.getText().toString());
        users.child(uid).child("age").setValue(txtAgeUpdate.getText().toString());
        users.child(uid).child("email").setValue(txtEmailUpdate.getText().toString());

        Common.showAlert(SUCCESS, "Datos actualizados correctamente", this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.userConfig) {
            startActivity(new Intent(this, UserConfigurationActivity.class));
        } else if (id == R.id.logout) {
            Common.logout(this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        getOnBackPressedDispatcher().onBackPressed();
        return true;
    }

}