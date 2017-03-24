package com.example.morten.oblig2;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UserAeraActivity extends AppCompatActivity {
    String userData;
    String velkomTekst="Velkommen til SBDS ";

    public static final String ENDPOINT = "http://itfag.usn.no~210144";
    //private final String Url = "http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_aera);

        final EditText etUserName = (EditText) findViewById(R.id.etUsername);
        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final TextView velkom = (TextView) findViewById(R.id.tvVelkom);

           //Henter User objektet fra Login
        if(LoginActivity.userList!=null||LoginActivity.userList.size()!=0){
        User user=LoginActivity.userList.get(0);
        velkom.setText( velkomTekst+user.fornavn);
        }



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

            progressDialog = new ProgressDialog(UserAeraActivity.this);
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
                URL actorUrl = new URL("http://itfag.usn.no/~210144/api.php/Bruker?filter=Passord,eg,morten&filter=username,eq,mort&transform=1");
                //URL actorUrl = new URL("http://itfag.usn.no/~210144/login.php");
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
                //listitem = User.lagUserListe(userData);
                /*Log.d("connection", actorData);
                adapter = new ActorAdapter(getApplicationContext(), listitem);*/


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

            progressDialog.cancel();
            //adapter = new ActorAdapter(getApplicationContext(), listitem);
            //listV.setAdapter(adapter);
            Toast.makeText(getApplicationContext(), userData, Toast.LENGTH_SHORT).show();

            if (result == false) {
                Toast.makeText(getApplicationContext(), "Får ikke hentet data fra server", Toast.LENGTH_SHORT).show();

            }

        }
    }




    }

