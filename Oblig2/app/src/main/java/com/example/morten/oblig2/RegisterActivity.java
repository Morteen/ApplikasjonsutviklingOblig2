package com.example.morten.oblig2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final EditText etName = (EditText) findViewById(R.id.eFName);
        final EditText etLastName = (EditText) findViewById(R.id.eLastName);
        final EditText etUserName = (EditText) findViewById(R.id.eUsername);
        final EditText etPassword = (EditText) findViewById(R.id.ePassword);
        final EditText etEmail = (EditText) findViewById(R.id.editEmail);
        final EditText etAge = (EditText) findViewById(R.id.eAge);
        final Button btnReg = (Button) findViewById(R.id.bRegister);


        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = new User();
                user.fornavn = etName.getText().toString();
                user.etternavn = etLastName.getText().toString();
                user.username = etUserName.getText().toString();
                user.email = etEmail.getText().toString();
                user.passord = etPassword.getText().toString();
                user.alder = Integer.parseInt(etAge.getText().toString());

                String method="register";
                BackgroundTask backgroundTask= new BackgroundTask(getBaseContext());
                backgroundTask.execute(method,user.email,user.passord,user.username,user.fornavn,user.etternavn,etAge.getText().toString());

finish();
            }
        });


    }




}
