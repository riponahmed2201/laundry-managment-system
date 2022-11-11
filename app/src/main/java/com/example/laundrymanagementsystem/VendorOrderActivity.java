package com.example.laundrymanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class VendorOrderActivity extends AppCompatActivity {

    private ImageView backToHomeScreenId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_order);

        backToHomeScreenId = findViewById(R.id.back_to_user_home_screen_id);

        backToHomeScreenId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VendorOrderActivity.this, VendorHomeActivity.class);
                startActivity(intent);
            }
        });

    }
}