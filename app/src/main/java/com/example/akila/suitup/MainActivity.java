package com.example.akila.suitup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button login, signup;
    EditText usrnm, psswrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button)findViewById(R.id.log_in);
        signup = (Button)findViewById(R.id.sign_up);
        usrnm = (EditText)findViewById(R.id.username);
        psswrd = (EditText)findViewById(R.id.password);

        //Precondition: The login button is clicked, and triggers the onClickListenter
        //Post condition: The page is changed to the user dashboard from the login page
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(usrnm.getText().toString().equals("test") && psswrd.getText().toString().equals("test")){
                    Intent intent = new Intent(MainActivity.this, Dashboard.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Precondition: The sign up button is clicked and the click triggers the onClickListener; there exists a sign up page to which the log in page can move to
        //Post condition: The page is shifted to the sign up page
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SignUp.class);
                startActivity(i);
            }
        });
    }
}
