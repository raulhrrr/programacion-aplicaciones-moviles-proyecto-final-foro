package com.uniagustiniana.proyecto_final_foro;

import static com.uniagustiniana.proyecto_final_foro.utils.TitleType.ERROR;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uniagustiniana.proyecto_final_foro.dto.Group;
import com.uniagustiniana.proyecto_final_foro.utils.Common;
import com.uniagustiniana.proyecto_final_foro.utils.Status;

import java.util.Objects;

public class AddGroupActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser currentUser;
    DatabaseReference databaseReference;

    String userUid;
    EditText name, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        setup();
    }

    private void setup() {
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Groups");

        userUid = currentUser.getUid();
        name = findViewById(R.id.groupName);
        description = findViewById(R.id.groupDescription);

        Button btnAddGroup = findViewById(R.id.btnAddGroup);
        btnAddGroup.setOnClickListener(this::addGroup);

        Common.setTitleActionBar(this, "Agregar grupo", true, true);
    }

    private void addGroup(View view) {
        Group group = new Group();
        group.setUserUid(userUid);
        group.setName(name.getText().toString());
        group.setDescription(description.getText().toString());
        group.setStatus(Status.ACTIVE.getName());

        databaseReference.child(Objects.requireNonNull(userUid)).push().setValue(group).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Grupo agregado", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MyGroupsActivity.class));
            } else {
                Common.showAlert(ERROR, "Error al agregar el grupo", this);
            }
        });
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
        } else if (id == R.id.allGroups) {
            startActivity(new Intent(this, AllGroupsActivity.class));
        } else if (id == R.id.myGroups) {
            startActivity(new Intent(this, MyGroupsActivity.class));
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