package com.example.laundrymanagementsystem;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.laundrymanagementsystem.database.DatabaseManager;
import com.example.laundrymanagementsystem.model.Login;

import java.util.ArrayList;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private Button createAnAccountBtnId;
    private EditText emailId;
    private EditText passwordId;
    private Button loginButton;

    private String staticVendorEmail = "vendor@gmail.com";
    private String staticVendorPassword = "123456";

    private String staticAdminEmail = "admin@gmail.com";
    private String staticAdminPassword = "123456";

    private String staticUserEmail = "user@gmail.com";
    private String staticUserPassword = "123456";

    private String getInputEmailValue;
    private String getInputPasswordValue;

    DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Set the toolbar title
        this.setTitle("Login Page");

        databaseManager = new DatabaseManager(LoginActivity.this);

        emailId = findViewById(R.id.email);
        passwordId = findViewById(R.id.password);

        loginButton = findViewById(R.id.login_btn);
        createAnAccountBtnId = findViewById(R.id.create_an_account_btn);

        //Not an account then go to register activity
        createAnAccountBtnId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Go to Register Activity
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(intent);
            }
        });

        //Go to place order Activity
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getInputEmailValue = emailId.getText().toString().trim();
                getInputPasswordValue = passwordId.getText().toString().trim();

                if (getInputEmailValue.isEmpty()) {
                    emailId.setError("Email is required.");
                    emailId.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(getInputEmailValue).matches()) {
                    emailId.setError("Please enter a valid email.");
                    emailId.requestFocus();
                } else if (getInputPasswordValue.isEmpty()) {
                    passwordId.setError("Password is required.");
                    passwordId.requestFocus();
                } else {
                    ArrayList<Login> loginArrayList = databaseManager.getUserDetails(getInputEmailValue, getInputPasswordValue);

                    String roleName;

                    for (Login login : loginArrayList) {
                        roleName = login.getRoleName();

                        if (Objects.equals(roleName, "USER")) {
                            Intent intent = new Intent(LoginActivity.this, PlaceOrderActivity.class);
                            startActivity(intent);
                        } else if (Objects.equals(roleName, "VENDOR")) {
                            Intent intent = new Intent(LoginActivity.this, VendorHomeActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Invalid credentials info!", Toast.LENGTH_SHORT).show();

                        }
                    }
                }

//                if (getInputEmailValue.equals(staticAdminEmail)) {
//                    Intent intent = new Intent(LoginActivity.this, AdminHomeActivity.class);
//                    startActivity(intent);
//                }
//
//                if (getInputEmailValue.equals(staticVendorEmail)) {
//                    Intent intent = new Intent(LoginActivity.this, VendorHomeActivity.class);
//                    startActivity(intent);
//                }
//
//                if (getInputEmailValue.equals(staticUserEmail)) {
//                    Intent intent = new Intent(LoginActivity.this, PlaceOrderActivity.class);
//                    startActivity(intent);
//                }
            }
        });
    }
}