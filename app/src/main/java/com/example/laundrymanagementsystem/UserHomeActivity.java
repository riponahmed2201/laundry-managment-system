package com.example.laundrymanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.laundrymanagementsystem.adapter.UserOrderListRecyclerViewAdapter;
import com.example.laundrymanagementsystem.database.DatabaseManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UserHomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    FloatingActionButton fab;
    LinearLayoutManager linearLayoutManager;
    UserOrderListRecyclerViewAdapter adapter;
    DatabaseManager databaseManager;

    Button goToOrderListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        sharedPreferences = getSharedPreferences("Mode", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        fab = findViewById(R.id.fab);
        goToOrderListButton = findViewById(R.id.go_to_order_list_button_id);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("User Dashboard");
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserHomeActivity.this, PlaceOrderActivity.class));
            }
        });

        goToOrderListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHomeActivity.this, UserOrderActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        super.onCreatePanelMenu(featureId, menu);
        getMenuInflater().inflate(R.menu.user_home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logout_menu_id:
                Toast.makeText(this, "Logout successfully!", Toast.LENGTH_SHORT).show();
                Intent logoutIntent = new Intent(UserHomeActivity.this, LoginActivity.class);
                startActivity(logoutIntent);
                editor.putString("Login", "true");
                editor.apply();
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}