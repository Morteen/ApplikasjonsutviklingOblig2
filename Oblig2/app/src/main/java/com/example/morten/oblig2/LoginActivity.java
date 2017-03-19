package com.example.morten.oblig2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText eUserName= (EditText)findViewById(R.id.etUsername);
        final EditText ePassword= (EditText)findViewById(R.id.ePassword);
        final Button btnLogin= (Button) findViewById(R.id.btnLogin);
        final TextView reglink= (TextView)findViewById(R.id.tvRegistrerHer);

       //Åpner registrer viewet hvis brukeren ikke har bruker navn og passord
        reglink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                LoginActivity.this.startActivity(registrerIntent);
            }
        });
    }
}
