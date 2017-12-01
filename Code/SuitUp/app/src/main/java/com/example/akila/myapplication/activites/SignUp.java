package com.example.akila.myapplication.activites;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.content.Intent;
import android.widget.AdapterView.OnItemSelectedListener;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;

import com.example.akila.myapplication.R;
import com.example.akila.myapplication.helper.InputValidation;
import com.example.akila.myapplication.sql.DatabaseHelper;
import com.example.akila.myapplication.model.User;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

// Name: Ananya Srinivasan
// Course: CSC 415
// Semester: Fall 2017
// Instructor: Dr. Pulimood
// SuitUp
// Description: An android app for work clothing donations
// Filename: SignUp
// Description: Class for handling the signup process for the application
// Last modified on: 10 November 2017

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity = SignUp.this;
    private NestedScrollView scrollView;
    private AppCompatButton signup;

    //Define variables for layout and edittext views
    private TextInputLayout textLayoutName;
    private TextInputLayout textLayoutSurname;
    private TextInputLayout textLayoutEmail;
    private TextInputLayout textLayoutAdd1;
    private TextInputLayout textLayoutAdd2;
    private TextInputLayout textLayoutCity;
    private TextInputLayout textLayoutZipcode;
    private TextInputLayout textLayoutPassword;
    private TextInputLayout textLayoutConfirmPassword;
    private TextInputLayout textLayoutState;

    private TextInputEditText editTextName;
    private TextInputEditText editTextSurname;
    private TextInputEditText editTextEmail;
    private TextInputEditText editTextAdd1;
    private TextInputEditText editTextAdd2;
    private TextInputEditText editTextCity;
    private TextInputEditText editTextZipcode;
    private TextInputEditText editTextState;
    private TextInputEditText editTextPassword;
    private TextInputEditText editTextConfirmPassword;

    private InputValidation inputValidation;
    private DatabaseHelper dbHelper;
    private User user;
    private String userState;

    //Precondition: All of the form items are filled in and the user clicks the button
    //Postcondition: The user is directed to the dashboard page
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();

        //States to fill the spinner
        ArrayList<String> categories = new ArrayList<String>();
        categories.add("AL");
        categories.add("AK");
        categories.add("AZ");
        categories.add("AR");
        categories.add("CA");
        categories.add("CO");
        categories.add("CT");
        categories.add("DC");
        categories.add("DE");
        categories.add("FL");
        categories.add("GA");
        categories.add("HI");
        categories.add("ID");
        categories.add("IL");
        categories.add("IN");
        categories.add("IA");
        categories.add("KS");
        categories.add("KY");
        categories.add("LA");
        categories.add("ME");
        categories.add("MD");
        categories.add("MA");
        categories.add("MI");
        categories.add("MN");
        categories.add("MS");
        categories.add("MO");
        categories.add("MT");
        categories.add("NE");
        categories.add("NV");
        categories.add("NH");
        categories.add("NJ");
        categories.add("NM");
        categories.add("NY");
        categories.add("NC");
        categories.add("ND");
        categories.add("OH");
        categories.add("OK");
        categories.add("OR");
        categories.add("PA");
        categories.add("RI");
        categories.add("SC");
        categories.add("SD");
        categories.add("TN");
        categories.add("TX");
        categories.add("UT");
        categories.add("VT");
        categories.add("VA");
        categories.add("WA");
        categories.add("WV");
        categories.add("WI");
        categories.add("WY");


        initViews();
        initListeners();
        initObjects();

    }

    private void initViews(){
        scrollView = (NestedScrollView)findViewById(R.id.scroll_register);

        editTextName = (TextInputEditText)findViewById(R.id.username);
        editTextSurname = (TextInputEditText)findViewById(R.id.surname);
        editTextAdd1 = (TextInputEditText)findViewById(R.id.address1);
        editTextAdd2 = (TextInputEditText)findViewById(R.id.address2);
        editTextEmail = (TextInputEditText)findViewById(R.id.email);
        editTextCity = (TextInputEditText)findViewById(R.id.city);
        editTextZipcode = (TextInputEditText)findViewById(R.id.zipcode);
        editTextPassword = (TextInputEditText)findViewById(R.id.password1);
        editTextConfirmPassword = (TextInputEditText)findViewById(R.id.password2);
        editTextState = (TextInputEditText)findViewById(R.id.state);
        signup = (AppCompatButton)findViewById(R.id.signupbutton);
    }

    public void initListeners(){
        signup.setOnClickListener(this);
    }

    private void initObjects(){
        inputValidation = new InputValidation(activity);
        dbHelper = new DatabaseHelper(activity);
        user = new User();
    }

    //From OnClick Interface
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.signupbutton:
                postDatatoSQLite();
                break;
        }
    }

    private void postDatatoSQLite(){
        //validates if data is filled
        if(!inputValidation.isInputFilled(editTextName, textLayoutName, getString(R.string.error_name)))
            return;
        if(!inputValidation.isInputFilled(editTextSurname, textLayoutSurname, getString(R.string.error_surname)))
            return;
        if(!inputValidation.isInputFilled(editTextEmail, textLayoutEmail, getString(R.string.error_email)))
            return;
        if(!inputValidation.isInputFilled(editTextAdd1, textLayoutAdd1, getString(R.string.error_address)))
            return;
        if(!inputValidation.isInputFilled(editTextCity, textLayoutCity, getString(R.string.error_city)))
            return;
        if(!inputValidation.isInputFilled(editTextZipcode, textLayoutZipcode, getString(R.string.error_zip)))
            return;
        if(!inputValidation.isInputFilled(editTextPassword, textLayoutPassword, getString(R.string.error_password)))
            return;
        if(!inputValidation.isInputFilled(editTextState, textLayoutState, getString(R.string.error_state)))
            return;

        if(!inputValidation.isInputEditTextEmail(editTextEmail, textLayoutEmail, getString(R.string.error_email)))
            return;

        if(!inputValidation.isInputEditTextMatches(editTextPassword, editTextConfirmPassword, textLayoutConfirmPassword, getString(R.string.password_doesnt_match)))
            return;

        if(!dbHelper.checkUser(editTextEmail.getText().toString().trim())){
            user.setFirst_name(editTextName.getText().toString().trim());
            user.setLast_name(editTextSurname.getText().toString().trim());
            user.setEmail(editTextAdd1.getText().toString().trim());
            user.setAdd2(editTextAdd2.getText().toString().trim());
            user.setCity(editTextCity.getText().toString().trim());
            user.setZipcode(editTextZipcode.toString().trim());
            user.setPassword(editTextPassword.getText().toString().trim());
            user.setState(editTextState.getText().toString().trim());

            dbHelper.addUser(user);
        }

    }

    private void emptyEditText(){
        editTextName.setText(null);
        editTextSurname.setText(null);
        editTextAdd1.setText(null);
        editTextAdd2.setText(null);
        editTextZipcode.setText(null);
    }
}

