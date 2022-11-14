package com.example.laundrymanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.laundrymanagementsystem.database.DatabaseManager;

public class OrderAssignToVendorActivity extends AppCompatActivity {

    private Spinner vendorIdSpinner;
    private Button assignOrderToVendorButton;

    DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_assign_to_vendor);

        databaseManager = new DatabaseManager(OrderAssignToVendorActivity.this);

        vendorIdSpinner = findViewById(R.id.assign_vendor_id);
        assignOrderToVendorButton = findViewById(R.id.assign_order_to_vendor_button_id);

        //vendor list Spinner Data set
//        ArrayAdapter<String> vendorListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, databaseManager.getVendorsByVendorEmail());
//        vendorListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        vendorIdSpinner.setAdapter(vendorListAdapter);
//
//        assignOrderToVendorButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(OrderAssignToVendorActivity.this, "Send order to vendor", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}