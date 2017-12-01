package com.example.akila.myapplication.activites;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.example.akila.myapplication.R;
import com.example.akila.myapplication.helper.DonationValidation;
import com.example.akila.myapplication.model.Clothing;
import com.example.akila.myapplication.sql.DonationHelper;

// Name: Ananya Srinivasan
// Course: CSC 415
// Semester: Fall 2017
// Instructor: Dr. Pulimood
// SuitUp
// Description: An android app for work clothing donations
// Filename: Donation
// Description: The donation class manages the methods related to donations
// Last modified on: 10 November 2017

public class Donation extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = Donation.this;
    private NestedScrollView scrollView;
    private AppCompatButton donationbutton;

    //Define layouts
    private TextInputLayout itemLayout;
    private TextInputLayout descriptionLayout;
    private TextInputLayout sizeLayout;

    private TextInputEditText itemEditText;
    private TextInputEditText descriptionEditText;
    private TextInputEditText sizeEditText;

    private AppCompatButton donatebutton;

    private DonationValidation donationValidation;
    private DonationHelper dnHelper;
    private Clothing clothing;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    public void initViews(){
        scrollView = (NestedScrollView)findViewById(R.id.scroll_donation);

        itemLayout = (TextInputLayout)findViewById(R.id.textInputLayoutItem);
        descriptionLayout = (TextInputLayout)findViewById(R.id.textInputLayoutDescription);
        sizeLayout = (TextInputLayout)findViewById(R.id.textInputLayoutSize);

        itemEditText = (TextInputEditText)findViewById(R.id.item);
        descriptionEditText = (TextInputEditText)findViewById(R.id.description);
        sizeEditText = (TextInputEditText)findViewById(R.id.size);

        donatebutton = (AppCompatButton)findViewById(R.id.buttonDonate);
    }

    public void initListeners(){
        donatebutton.setOnClickListener(this);
    }

    public void initObjects(){
        donationValidation = new DonationValidation(activity);
        dnHelper = new DonationHelper(activity);
        clothing = new Clothing();
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.buttonDonate:
                postDataToSQLite();
                break;
        }
    }

    private void postDataToSQLite(){
        if(!donationValidation.isInputFilled(itemEditText, itemLayout, getString(R.string.error_db))){
            return;
        }

        if(!donationValidation.isInputFilled(descriptionEditText, descriptionLayout, getString(R.string.error_db))){
            return;
        }

        if(!donationValidation.isInputFilled(sizeEditText, sizeLayout, getString(R.string.error_db))){
            return;
        }

        dnHelper.addDonation(clothing);
        Snackbar.make(scrollView, getString(R.string.donation_success), Snackbar.LENGTH_LONG).show();
    }
}
