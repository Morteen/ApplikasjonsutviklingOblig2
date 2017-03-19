package com.example.morten.oblig2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final EditText etAge = (EditText)findViewById(R.id.eAge);
        final EditText etName = (EditText)findViewById(R.id.eName);
        final EditText etUserName = (EditText)findViewById(R.id.eUsername);
        final EditText etPassword = (EditText)findViewById(R.id.ePassword);
        final Button btnReg = (Button) findViewById(R.id.bRegister);
    }
}
