package com.uniagustiniana.proyecto_final_foro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uniagustiniana.proyecto_final_foro.dto.Group;
import com.uniagustiniana.proyecto_final_foro.utils.Common;
import com.uniagustiniana.proyecto_final_foro.utils.GroupAdapter;

import java.util.ArrayList;
import java.util.List;

public class AllGroupsActivity extends AppCompatActivity {

    List<Group> groups;

    FirebaseAuth firebaseAuth;
    FirebaseUser currentUser;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_groups);
        setup();
    }

    public void setup() {
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Groups");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    groups = new ArrayList<>();
                    for (DataSnapshot groupsByUser : snapshot.getChildren()) {
                        for (DataSnapshot groupSnapshot : groupsByUser.getChildren()) {
                            Group group = groupSnapshot.getValue(Group.class);
                            groups.add(group);
                        }
                    }

                    String userUid = currentUser.getUid();
                    groups.removeIf(group -> group.getUserUid().equals(userUid));

                    GroupAdapter groupAdapter = new GroupAdapter(AllGroupsActivity.this, groups);
                    RecyclerView recyclerView = findViewById(R.id.allGroupsRecyclerView);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(AllGroupsActivity.this));
                    recyclerView.setAdapter(groupAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        Common.setTitleActionBar(this, "Todos los grupos", true, true);
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
        } else if (id == R.id.addGroup) {
            startActivity(new Intent(this, AddGroupActivity.class));
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