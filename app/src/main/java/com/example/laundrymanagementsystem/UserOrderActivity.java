package com.example.laundrymanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import com.example.laundrymanagementsystem.adapter.UserOrderListRecyclerViewAdapter;
import com.example.laundrymanagementsystem.database.DatabaseManager;
import com.example.laundrymanagementsystem.model.Order;
import com.example.laundrymanagementsystem.model.OrderInformation;
import com.example.laundrymanagementsystem.utiles.Utiles;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class UserOrderActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_user_order);

        sharedPreferences = getSharedPreferences("Mode", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        userOrderList = findViewById(R.id.userOrderListId);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        userOrderList.setLayoutManager(linearLayoutManager);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("My Order List");
        toolbar.setTitleTextColor(Color.BLACK);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        databaseManager = new DatabaseManager(UserOrderActivity.this);

        String getUserEmail = sharedPreferences.getString("email", "");

        ArrayList<Order> orderArrayList = databaseManager.getUserOrderDetails(getUserEmail);

        Utiles.orderInformations.clear();

        if (!orderArrayList.isEmpty()) {
            for (Order order : orderArrayList) {
                OrderInformation orderInformation = new OrderInformation(order.getOrderId(), order.getVendorEmail(), order.getUserEmail(), order.getUserPhoneNumber(), order.getPaymentOption(), order.getGarmentCategory(), order.getGarmentQuantity(), order.getOrderPlacement(), order.getStatus());
                Utiles.orderInformations.add(orderInformation);
            }
            adapter = new UserOrderListRecyclerViewAdapter(UserOrderActivity.this);
            userOrderList.setAdapter(adapter);
        }
    }
}