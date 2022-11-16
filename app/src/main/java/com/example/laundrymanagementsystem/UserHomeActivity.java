package com.example.laundrymanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.laundrymanagementsystem.adapter.UserOrderListRecyclerViewAdapter;
import com.example.laundrymanagementsystem.database.DatabaseManager;
import com.example.laundrymanagementsystem.model.Order;
import com.example.laundrymanagementsystem.model.OrderInformation;
import com.example.laundrymanagementsystem.utiles.Utiles;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class UserHomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    RecyclerView userOrderList;
    FloatingActionButton fab;
    LinearLayoutManager linearLayoutManager;
    UserOrderListRecyclerViewAdapter adapter;
    DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        sharedPreferences = getSharedPreferences("Mode", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        userOrderList = findViewById(R.id.userOrderList);
        fab = findViewById(R.id.fab);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        userOrderList.setLayoutManager(linearLayoutManager);

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

        databaseManager = new DatabaseManager(UserHomeActivity.this);

        String getUserEmail = sharedPreferences.getString("email", "");

        ArrayList<Order> orderArrayList = databaseManager.getUserOrderDetails(getUserEmail);

        if (!orderArrayList.isEmpty()) {
            for (Order order : orderArrayList) {
                OrderInformation orderInformation = new OrderInformation(order.getOrderId(), order.getVendorEmail(), order.getUserEmail(), order.getUserPhoneNumber(), order.getPaymentOption(), order.getGarmentCategory(), order.getGarmentQuantity(), order.getOrderPlacement(), order.getStatus());
                Utiles.orderInformations.clear();
                Utiles.orderInformations.add(orderInformation);
            }

            adapter = new UserOrderListRecyclerViewAdapter(UserHomeActivity.this);
            userOrderList.setAdapter(adapter);
        }
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