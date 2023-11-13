package com.uniagustiniana.proyecto_final_foro.services;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.uniagustiniana.proyecto_final_foro.dto.User;

import java.util.HashMap;
import java.util.Map;

public class UserApiImpl {

    public void registerUser(User user, AppCompatActivity activity) {
        String url = "http://192.168.10.14/final-project/register_user.php";
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                response -> Toast.makeText(activity, "Usuario registrado correctamente", Toast.LENGTH_LONG).show(),
                error -> Toast.makeText(activity, error.getMessage(), Toast.LENGTH_LONG).show()
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", user.getName());
                params.put("lastname", user.getLastname());
                params.put("age", String.valueOf(user.getAge()));
                params.put("email", user.getEmail());
                params.put("username", user.getUsername());
                params.put("password", user.getPassword());
                return params;
            }
        };
        requestQueue.add(request);
        requestQueue.start();
    }
}
