package com.example.akila.myapplication.activites;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.akila.myapplication.R;
import com.example.akila.myapplication.sql.DatabaseHelper;
import com.example.akila.myapplication.helper.InputValidation;

import android.support.v7.widget.AppCompatButton;

// Name: Ananya Srinivasan
// Course: CSC 415
// Semester: Fall 2017
// Instructor: Dr. Pulimood
// SuitUp
// Description: An android app for work clothing donations
// Filename: Main Activity
// Description: This is the class responsible for the login screen
// Last modified on: 29 November 2017

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = MainActivity.this;

    private NestedScrollView nestedScrollView;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;

    private AppCompatTextView textViewRegister;

    private InputValidation inputValidation;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews(){
        nestedScrollView = (NestedScrollView)findViewById(R.id.nestedScrollView);
        textInputLayoutEmail = (TextInputLayout)findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout)findViewById(R.id.textInputLayoutPassword);
        textInputEditTextEmail = (TextInputEditText)findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText)findViewById(R.id.textInputEditTextPassword);
        appCompatButtonLogin = (AppCompatButton)findViewById(R.id.appCompatButtonLogin);
        textViewRegister = (AppCompatTextView)findViewById(R.id.textViewRegister);
    }

    private void initListeners(){
        appCompatButtonLogin.setOnClickListener(this);
        textViewRegister.setOnClickListener(this);
    }

    private void initObjects(){
        dbHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.appCompatButtonLogin:
                verifyFromSQLite();
                break;
            case R.id.textViewRegister:
                Intent intentRegister = new Intent(MainActivity.this, SignUp.class);
                startActivity(intentRegister);
                break;
        }
    }

    private void verifyFromSQLite(){
        if(!inputValidation.isInputFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_email)))
            return;
        if(!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_email)))
            return;
        if(!inputValidation.isInputFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_password)))
            return;
        if(dbHelper.checkUser(textInputEditTextEmail.getText().toString().trim(), textInputEditTextPassword.getText().toString().trim())){
            Intent accountIntent = new Intent(MainActivity.this, UsersActivity.class);
            accountIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountIntent);
        }else{
            Snackbar.make(nestedScrollView, getString(R.string.error_password), Snackbar.LENGTH_LONG).show();
        }

    }

    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }
}
