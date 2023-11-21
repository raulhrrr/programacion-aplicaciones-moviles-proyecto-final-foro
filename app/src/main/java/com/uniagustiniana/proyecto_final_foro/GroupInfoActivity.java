package com.uniagustiniana.proyecto_final_foro;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.uniagustiniana.proyecto_final_foro.utils.Common;

public class GroupInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_info);
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