package com.example.laundrymanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.laundrymanagementsystem.database.DatabaseManager;
import com.example.laundrymanagementsystem.model.Order;

import java.util.ArrayList;
import java.util.Objects;

public class AdminOrderListActivity extends AppCompatActivity {

    private Toolbar toolbar;

    DatabaseManager databaseManager;

    private TextView customerName;
    private TextView customerPhoneNumber;
    private TextView garmentCategory;
    private TextView garmentQuantity;
    private TextView orderPlacement;
    private TextView paymentOption;

    private ImageView backToHomeScreenId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order_list);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("All Order List Screen");

        databaseManager = new DatabaseManager(AdminOrderListActivity.this);

        customerName = findViewById(R.id.customer_name_id);
        customerPhoneNumber = findViewById(R.id.customer_phone_number_id);
        garmentCategory = findViewById(R.id.garment_category_id);
        garmentQuantity = findViewById(R.id.garment_quantity_id);
        orderPlacement = findViewById(R.id.order_placement_id);
        paymentOption = findViewById(R.id.payment_option_id);

        backToHomeScreenId = findViewById(R.id.back_to_home_screen_id);

        ArrayList<Order> orderArrayList = databaseManager.getOrderDetails();

        for (Order order : orderArrayList) {
            customerName.setText(order.getUserEmail());
            customerPhoneNumber.setText("0174******");
            garmentCategory.setText(order.getGarmentCategory());
            garmentQuantity.setText(order.getGarmentQuantity());
            orderPlacement.setText(order.getOrderPlacement());
            paymentOption.setText(order.getPaymentOption());
        }

        backToHomeScreenId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminOrderListActivity.this, AdminHomeActivity.class);
                startActivity(intent);
            }
        });
    }
}