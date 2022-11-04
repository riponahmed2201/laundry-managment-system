package com.example.laundrymanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class PlaceOrderActivity extends AppCompatActivity {

    String[] getStaticVendorName;
    String[] getStaticPaymentOption;

    private Spinner paymentOptionSpinnerId;
    private Spinner selectVendorSpinnerId;

    private EditText garmentCategoryId;
    private EditText garmentQuantityId;
    private EditText orderPlacementId;

    private Button orderPlaceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        getStaticVendorName = getResources().getStringArray(R.array.select_vendor);
        getStaticPaymentOption = getResources().getStringArray(R.array.payment_option);

        paymentOptionSpinnerId = findViewById(R.id.payment_option_spinner);
        selectVendorSpinnerId = findViewById(R.id.select_vendor_spinner);

        garmentCategoryId = findViewById(R.id.garment_category);
        garmentQuantityId = findViewById(R.id.garment_quantity);
        orderPlacementId = findViewById(R.id.order_placement);

        orderPlaceButton = findViewById(R.id.place_order);

        //Payment Option Spinner Data set
        ArrayAdapter<String> paymentOptionAdapter = new ArrayAdapter<String>(this, R.layout.payment_option_view, R.id.payment_option_view_id, getStaticPaymentOption);
        paymentOptionSpinnerId.setAdapter(paymentOptionAdapter);

        //Vendor Select Spinner Data set
        ArrayAdapter<String> vendorSelectAdapter = new ArrayAdapter<String>(this, R.layout.select_vendor_view, R.id.select_vendor_view_id, getStaticVendorName);
        selectVendorSpinnerId.setAdapter(vendorSelectAdapter);

        orderPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlaceOrderActivity.this, UserHomeActivity.class);
                startActivity(intent);
            }
        });
    }
}