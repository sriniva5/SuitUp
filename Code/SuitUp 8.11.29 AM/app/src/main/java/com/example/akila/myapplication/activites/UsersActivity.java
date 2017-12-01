package com.example.akila.myapplication.activites;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.os.Bundle;
import com.example.akila.myapplication.R;

/**
 * Created by Akila on 11/27/17.
 */

public class UsersActivity extends AppCompatActivity{
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        textView = (TextView)findViewById(R.id.text1);
        String nameFromIntent = getIntent().getStringExtra("EMAIL");
        textView.setText("Welcome" + nameFromIntent);
    }
}
