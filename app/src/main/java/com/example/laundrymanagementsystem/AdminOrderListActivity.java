package com.example.laundrymanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.laundrymanagementsystem.adapter.OrderListRecyclerViewAdapter;
import com.example.laundrymanagementsystem.database.DatabaseManager;
import com.example.laundrymanagementsystem.model.Order;
import com.example.laundrymanagementsystem.model.OrderInformation;
import com.example.laundrymanagementsystem.utiles.Utiles;

import java.util.ArrayList;

public class AdminOrderListActivity extends AppCompatActivity {

    private Toolbar toolbar;
    RecyclerView orderList;
    private LinearLayoutManager linearLayoutManager;

    DatabaseManager databaseManager;
    OrderListRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order_list);

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

        databaseManager = new DatabaseManager(AdminOrderListActivity.this);

        ArrayList<Order> orderArrayList = databaseManager.getOrderDetails();

        for (Order order : orderArrayList) {
            OrderInformation orderInformation = new OrderInformation(order.getOrderId(), order.getVendorEmail(), order.getUserEmail(), order.getUserPhoneNumber(), order.getPaymentOption(), order.getGarmentCategory(), order.getGarmentQuantity(), order.getOrderPlacement(), order.getStatus());
            Utiles.orderInformations.clear();
            Utiles.orderInformations.add(orderInformation);
        }

        adapter = new OrderListRecyclerViewAdapter(this);
        orderList.setAdapter(adapter);
    }
}