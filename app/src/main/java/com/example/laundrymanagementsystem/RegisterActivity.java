package com.example.laundrymanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.laundrymanagementsystem.database.DatabaseManager;
import com.example.laundrymanagementsystem.model.Register;

public class RegisterActivity extends AppCompatActivity {

    String[] getStaticRoleName;

    private Spinner roleNameSpinnerId;

    private EditText fullNameId;
    private EditText emailId;
    private EditText addressWareHouseId;
    private EditText phoneNumberId;
    private EditText confirmPasswordId;
    private EditText passwordId;
    private Button registerBtnId;
    private Button backLoginBtnId;

    DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Set the toolbar title
        this.setTitle("Register Screen");

        databaseManager = new DatabaseManager(RegisterActivity.this);

        getStaticRoleName = getResources().getStringArray(R.array.role_name);

        roleNameSpinnerId = findViewById(R.id.role_name_spinner_id);

        fullNameId = findViewById(R.id.full_name);
        emailId = findViewById(R.id.email);
        addressWareHouseId = findViewById(R.id.address_ware_house);
        phoneNumberId = findViewById(R.id.phone_number);
        confirmPasswordId = findViewById(R.id.confirm_password);
        passwordId = findViewById(R.id.password);
        registerBtnId = findViewById(R.id.register_btn);
        backLoginBtnId = findViewById(R.id.back_login_btn);


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.role_name_view, R.id.role_name_view_id, getStaticRoleName);
        roleNameSpinnerId.setAdapter(arrayAdapter);

        registerBtnId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerValidation();
            }
        });

        //back to login activity
        backLoginBtnId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(intent);
            }
        });
    }

    public void registerValidation() {

        String roleName = roleNameSpinnerId.getSelectedItem().toString();
        String fullName = fullNameId.getText().toString();
        String email = emailId.getText().toString();
        String address = addressWareHouseId.getText().toString();
        String phoneNumber = phoneNumberId.getText().toString();
        String confirmPassword = confirmPasswordId.getText().toString();
        String password = passwordId.getText().toString();

        if (fullName.isEmpty()) {
            fullNameId.setError("Full name is required.");
            fullNameId.requestFocus();
        } else if (email.isEmpty()) {
            emailId.setError("Email is required.");
            emailId.requestFocus();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailId.setError("Please enter a valid email.");
            emailId.requestFocus();
        } else if (address.isEmpty()) {
            addressWareHouseId.setError("Address is required.");
            addressWareHouseId.requestFocus();
        } else if (roleName.isEmpty()) {
            emailId.setError("Role name is required.");
            emailId.requestFocus();
        } else if (phoneNumber.isEmpty()) {
            phoneNumberId.setError("Phone number is required.");
            phoneNumberId.requestFocus();
        } else if (password.isEmpty()) {
            passwordId.setError("Password is required.");
            passwordId.requestFocus();
        } else if (confirmPassword.isEmpty()) {
            confirmPasswordId.setError("Confirm password is required.");
            confirmPasswordId.requestFocus();
        } else if (!password.equals(confirmPassword)) {
            passwordId.setError("Password and confirm password do not match.");
            passwordId.requestFocus();
        } else {

            Log.d("TAG", " \nFull Name : " + fullName + " \nEmail : " + email + " \nAddress : " + address + " \nRole Name : " + roleName + " \nPhone Number : " + phoneNumber + " \nPassword : " + password);

            Register register = new Register(fullName, email, address, roleName, phoneNumber, password);

            long insertData = databaseManager.userRegister(register);

            if (insertData > 0) {

                Toast.makeText(this, "Registration success.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);

                finish();
                startActivity(intent);

            } else {
                Toast.makeText(this, "Registration failed!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}