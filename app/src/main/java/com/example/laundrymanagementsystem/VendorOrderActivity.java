package com.example.laundrymanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.laundrymanagementsystem.adapter.OrderListRecyclerViewAdapter;
import com.example.laundrymanagementsystem.adapter.VendorOrderListRecyclerViewAdapter;
import com.example.laundrymanagementsystem.database.DatabaseManager;
import com.example.laundrymanagementsystem.model.Order;
import com.example.laundrymanagementsystem.model.OrderInformation;
import com.example.laundrymanagementsystem.utiles.Utiles;

import java.util.ArrayList;

public class VendorOrderActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private LinearLayoutManager linearLayoutManager;

    DatabaseManager databaseManager;
    VendorOrderListRecyclerViewAdapter adapter;

    RecyclerView orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_order);

        toolbar = findViewById(R.id.toolbar);
        orderList = findViewById(R.id.orderList);

        toolbar.setTitle("Order List");
        toolbar.setTitleTextColor(Color.BLACK);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        orderList.setLayoutManager(linearLayoutManager);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        databaseManager = new DatabaseManager(VendorOrderActivity.this);

        ArrayList<Order> orderArrayList = databaseManager.getOrderDetails();

        for (Order order : orderArrayList) {
            OrderInformation orderInformation = new OrderInformation(order.getOrderId(), order.getVendorEmail(), order.getUserEmail(), order.getUserPhoneNumber(), order.getPaymentOption(), order.getGarmentCategory(), order.getGarmentQuantity(), order.getOrderPlacement(), order.getStatus());
            Utiles.orderInformations.add(orderInformation);
        }

        adapter = new VendorOrderListRecyclerViewAdapter(this);
        orderList.setAdapter(adapter);
    }
}