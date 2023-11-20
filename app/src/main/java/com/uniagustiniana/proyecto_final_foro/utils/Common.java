package com.uniagustiniana.proyecto_final_foro.utils;

import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.uniagustiniana.proyecto_final_foro.LogInActivity;

import java.util.Objects;

public class Common {

    public static void showAlert(TitleType titleType, String message, AppCompatActivity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(titleType.getTitle());
        builder.setMessage(message);
        builder.setPositiveButton("Aceptar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void setTitleActionBar(AppCompatActivity activity, String title, boolean showBackButton, boolean showHomeButton) {
        ActionBar actionBar = activity.getSupportActionBar();
        Objects.requireNonNull(actionBar).setTitle(title);
        actionBar.setDisplayHomeAsUpEnabled(showBackButton);
        actionBar.setDisplayShowHomeEnabled(showHomeButton);
    }

    public static void logout(AppCompatActivity activity) {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(activity, "Cierre de sesión exitoso", Toast.LENGTH_SHORT).show();
        activity.startActivity(new Intent(activity, LogInActivity.class));
    }

}
