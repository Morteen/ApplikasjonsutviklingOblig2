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
    String userData;
    static ArrayList<User> userList;
    private Boolean usernameCheck ;
    String age;

    /**
     * Lager en bruker og lagrer den i databasen.
     * Brukeren blir sendt til Login for å fortsette derfra
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Henter en liste over bruker navn
        new JSONAsynkTask().execute();


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

/**
 * Jeg har laget noen sjekker for bruker opplysningene, det er sikkert noen hull i dem
 */
                if(etAge.getText().toString().length()>0&&isStringInt(etAge.getText().toString())){
                    age =etAge.getText().toString();
                }else{
                    age="0" ;
                }
                Toast.makeText(getBaseContext(),"Navn lengde"+ etName.getText().length(), Toast.LENGTH_LONG).show();

                if(etName.getText().length()>0&&etLastName.getText().length()>0&&etUserName.getText().length()>0
                        &&etPassword.getText().length()>0){
                usernameCheck = true;
                for (User u : userList) {
                    if ( etUserName.getText().length()==0||u.getUsername().equals(etUserName.getText().toString())) {
                        usernameCheck = false;
                    }

                }

                if (usernameCheck) {
                    String method = "register";
                    BackgroundTask backgroundTask = new BackgroundTask(getBaseContext());
                    backgroundTask.execute(method, etEmail.getText().toString(), etPassword.getText().toString(),
                            etUserName.getText().toString(), etName.getText().toString(), etLastName.getText().toString()
                            ,age);

                    finish();
                } else {
                    Toast.makeText(getBaseContext(), getString(R.string.ErrorMessageUserNameCheck), Toast.LENGTH_LONG).show();
                }

                }else{
                    Toast.makeText(getBaseContext(), getString(R.string.ErrorUserReg), Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    class JSONAsynkTask extends AsyncTask<String, Void, Boolean> {

        ProgressDialog progressDialog;


        @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
        public JSONAsynkTask() {
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected Boolean doInBackground(String... params) {


            // HttpURLConnection connection= null;
            BufferedReader reader = null;
            String line = "";
            String result = "";
            InputStream is = null;


            HttpURLConnection connection = null;
            try {
                URL actorUrl = new URL("http://itfag.usn.no/~210144/api.php/Bruker?transform=1");

                connection = (HttpURLConnection) actorUrl.openConnection();
                connection.connect();
                int status = connection.getResponseCode();
                Log.d("connection", "status " + status);

                is = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(is));
                String responseString;
                StringBuilder sb = new StringBuilder();
                while ((responseString = reader.readLine()) != null) {
                    sb = sb.append(responseString);
                }
                userData = sb.toString();
                userList = User.lagUserListe(userData);
                Log.d("connection", userData);


                return true;


            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) {
                System.out.println("IOException");
                e.printStackTrace();

            } catch (NullPointerException e) {
                e.printStackTrace();

            } catch (Exception e) {
                e.printStackTrace();

            } finally {

                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                connection.disconnect();
            }


            return false;
        }


        @Override
        protected void onPostExecute(Boolean result) {

            if (!result) {

                Toast.makeText(getBaseContext(), getString(R.string.ConnectErrorMessageUserNameCheck), Toast.LENGTH_LONG).show();

            }
        }


    }

    /**
     * Sjekk for om alder stringen kan bli en integer
     * @param s er inputt fra bruker
     * @return
     */
    public boolean isStringInt(String s)
    {
        try
        {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }
}
