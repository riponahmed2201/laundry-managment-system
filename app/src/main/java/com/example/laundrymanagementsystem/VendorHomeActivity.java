package com.example.laundrymanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class VendorHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;

    private Button goToOrderListButton;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_home);

        sharedPreferences = getSharedPreferences("Mode", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Vendor Dashboard");

        goToOrderListButton = findViewById(R.id.go_to_order_list_button_id);

        goToOrderListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VendorHomeActivity.this, VendorOrderActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.admin_home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.notification_menu_id) {
            Intent intent = new Intent(VendorHomeActivity.this, VendorOrderActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.logout_menu_id) {
            Toast.makeText(this, "Logout successfully!", Toast.LENGTH_SHORT).show();
            Intent logoutIntent = new Intent(VendorHomeActivity.this, LoginActivity.class);
            startActivity(logoutIntent);
            editor.putString("Login", "true");
            editor.apply();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}