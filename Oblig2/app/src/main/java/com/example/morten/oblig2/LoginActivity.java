package com.example.morten.oblig2;

/**
 * Dette er en vanlig login side som logger inn brukeren og sender denne videre
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    String userData;
    static ArrayList<User> userList;
    static User loginuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText eUserName = (EditText) findViewById(R.id.etUsername);
        final EditText ePassword = (EditText) findViewById(R.id.etPassword);
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final TextView reglink = (TextView) findViewById(R.id.tvRegistrerHer);

        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", getApplicationContext().MODE_PRIVATE);
        if(!sharedPreferences.getString("username","").equals("")){

        eUserName.setText(sharedPreferences.getString("username",""));
        ePassword.setText(sharedPreferences.getString("password",""));
        }else{
            eUserName.setText("");
            ePassword.setText("");

        }




        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name = eUserName.getText().toString();
                String user_pass = ePassword.getText().toString();



                if (isOnline()) {

                    new LoginActivity.JSONAsynkTask().execute(user_name, user_pass);
                    eUserName.setText("");
                    ePassword.setText("");

                } else {
                    String message = getString(R.string.ConnectionErrorMessage);
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                }

            }
        });


        //Åpner registrer viewet hvis brukeren ikke har bruker navn og passord
        reglink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent registrerIntent = new Intent(LoginActivity.this, RegisterActivity.class);//RegisterActivity
                LoginActivity.this.startActivity(registrerIntent);

            }
        });
    }

    /**
     * En asynk klasse som sjekker om brukernavnet finnes i databasen
     * Hvis det finnes blir brukeren sendt til 'Minside' activity, hvis ikke
     * så blir brukeren sendt til registererings aktiviteten
     */


    class JSONAsynkTask extends AsyncTask<String, Void, Boolean> {

        ProgressDialog progressDialog;


        @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
        public JSONAsynkTask() {
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setMessage("Vent litt nå da..");
            progressDialog.setTitle("Kobler opp...");
            progressDialog.show();
            progressDialog.setProgressStyle(3);
            progressDialog.setCancelable(false);
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
                URL actorUrl = new URL("http://itfag.usn.no/~210144/api.php/Bruker?filter=Passord,eq," + params[1] + "&filter=username,eq," + params[0] + "&transform=1");

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

            if (result) {

                progressDialog.cancel();


                if (userList.size() > 0) {
                    loginuser = userList.get(0);
                    MinSideActivity.returnToMinside(LoginActivity.this);
                    finish();

                } else if (userList.size() == 0 || userList == null) {


                    Toast.makeText(LoginActivity.this, getString(R.string.UserLoginErrorMessage), Toast.LENGTH_SHORT).show();

                    Intent registrerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                    LoginActivity.this.startActivity(registrerIntent);

                }


            } else {

                Toast.makeText(getApplicationContext(), getString(R.string.NoDataLoginErrorMessage), Toast.LENGTH_SHORT).show();

                progressDialog.cancel();

            }

        }
    }


    /**
     * Sjekker om  det er nettverkstilgang
     *
     * @return true or  false
     */
    protected boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(LoginActivity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }


}
