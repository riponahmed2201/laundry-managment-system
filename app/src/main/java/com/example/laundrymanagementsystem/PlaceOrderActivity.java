package com.example.laundrymanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laundrymanagementsystem.database.DatabaseHelper;
import com.example.laundrymanagementsystem.database.DatabaseManager;
import com.example.laundrymanagementsystem.model.Order;
import com.example.laundrymanagementsystem.model.Register;

import java.util.Objects;

public class PlaceOrderActivity extends AppCompatActivity {

    String[] getStaticPaymentOption;

    private Spinner paymentOptionSpinnerId;
    private Spinner garmentCategorySpinnerId;

    private EditText garmentQuantityId;
    private EditText orderPlacementId;

    private Button orderPlaceButton;

    private Toolbar toolbar;

    DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Place Order Screen");

        databaseManager = new DatabaseManager(PlaceOrderActivity.this);

        getStaticPaymentOption = getResources().getStringArray(R.array.payment_option);

        paymentOptionSpinnerId = findViewById(R.id.payment_option_spinner);
        garmentCategorySpinnerId = findViewById(R.id.garment_category);

        garmentQuantityId = findViewById(R.id.garment_quantity);
        orderPlacementId = findViewById(R.id.order_placement);

        orderPlaceButton = findViewById(R.id.place_order);

        //Payment Option Spinner Data set
        ArrayAdapter<String> paymentOptionAdapter = new ArrayAdapter<String>(this, R.layout.payment_option_view, R.id.payment_option_view_id, getStaticPaymentOption);
        paymentOptionSpinnerId.setAdapter(paymentOptionAdapter);

        //garment category Spinner Data set
        ArrayAdapter<String> garmentCategoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.garment_category_name));
        garmentCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        garmentCategorySpinnerId.setAdapter(garmentCategoryAdapter);

        orderPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validationAndCreateNewOrder();
            }
        });
    }

    public void validationAndCreateNewOrder() {

        try {

            String userEmail = "user@gmail.com";
            String paymentOption = paymentOptionSpinnerId.getSelectedItem().toString();
            String garmentCategory = garmentCategorySpinnerId.getSelectedItem().toString();
            String garmentQuantity = garmentQuantityId.getText().toString();
            String orderPlacement = orderPlacementId.getText().toString();
            String status = "UNSEEN";

            if (paymentOption.isEmpty()) {
                ((TextView) paymentOptionSpinnerId.getSelectedView()).setError("Payment option is required.");
                paymentOptionSpinnerId.requestFocus();
            } else if (garmentCategory.isEmpty()) {
                ((TextView) garmentCategorySpinnerId.getSelectedView()).setError("Garment category is required.");
                garmentCategorySpinnerId.requestFocus();
            } else if (garmentQuantity.isEmpty()) {
                garmentQuantityId.setError("Garment quantity is required.");
                garmentQuantityId.requestFocus();
            } else if (orderPlacement.isEmpty()) {
                orderPlacementId.setError("Order placement is required.");
                orderPlacementId.requestFocus();
            }

            Log.d("InputData", " \nPayment option : " + paymentOption + "\ngarmentCategory :" + garmentCategory + "\ngarmentQuantity :" + garmentQuantity + "\norderPlacement :" + orderPlacement);

            Order order = new Order(userEmail, paymentOption, garmentCategory, garmentQuantity, orderPlacement, status);

            long insertData = databaseManager.createNewOrder(order);

            if (insertData > 0) {

                Toast.makeText(this, "Order created success", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(PlaceOrderActivity.this, UserHomeActivity.class);
                startActivity(intent);

            } else {
                Toast.makeText(this, "Order created failed!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception exception) {
            Toast.makeText(this, "Error Found: " + exception, Toast.LENGTH_LONG).show();
        }
    }
}