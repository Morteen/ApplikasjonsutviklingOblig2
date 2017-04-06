package com.example.morten.oblig2;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MinSideActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    TextView ukedag;
    User user;
    Kurs kurs;
    String kursData;
    static ArrayList<Kurs> kursList;


    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_min_side);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VisKursFragment kursfrag = new VisKursFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.content, kursfrag);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // Henter bruker fra Loginsiden
        ///Default
        //Henter User objektet fra Login
        if (LoginActivity.loginuser != null) {
            user = LoginActivity.loginuser;
            //velkom.setText( velkomTekst+user.fornavn);
            ukedag = (TextView) findViewById(R.id.day);

            new MinBackgroundTask(this).execute(user.nr);


        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.min_side, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.profil) {
            ProfilFragment pf = new ProfilFragment();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content, pf);
            ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.kurs) {
            VisKursFragment kursfrag = new VisKursFragment();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content, kursfrag);
            ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class MinBackgroundTask extends AsyncTask<Integer, Void, String> {

        ProgressDialog progressDialog;
        Context context;

        public MinBackgroundTask(Context context) {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MinSideActivity.this);
            progressDialog.setMessage("Vent litt nå da..");
            progressDialog.setTitle("Kobler opp...");
            progressDialog.show();
            progressDialog.setProgressStyle(3);
            progressDialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(Integer... params) {

            String brukerId = params[0].toString();
            //int kursNr=params[1];
            // HttpURLConnection connection= null;
            BufferedReader reader = null;
            String line = "";
            String result = "";
            InputStream is = null;


            HttpURLConnection connection = null;
            try {
                URL kursUrl = new URL("http://itfag.usn.no/~210144/mineKurs.php");

                connection = (HttpURLConnection) kursUrl.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);

                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                //Koder alle data før de sendes
                String data = URLEncoder.encode("brukerId", "UTF-8") + "=" + URLEncoder.encode(brukerId, "UTF-8");

                //Sender data
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();

                //Skaffer data tilbake

                int status = connection.getResponseCode();
                Log.d("connection", "status " + status);

                is = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(is));
                String responseString;
                StringBuilder sb = new StringBuilder();
                while ((responseString = reader.readLine()) != null) {
                    sb = sb.append(responseString);
                }

                reader.close();
                is.close();


                String respons = sb.toString();


                connection.disconnect();

                return respons;


            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) {
                System.out.println("IOException");
                e.printStackTrace();

            } catch (NullPointerException e) {
                e.printStackTrace();

            } finally {

                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }


            return null;
        }


        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onPostExecute(String result) {


            if (result != null) {


                try {
                    kursList = Kurs.lagKursListe(result);
                    if (kursList != null) {
                        //Lager kalender og uke dager
                        Calendar calendar = Calendar.getInstance();//() {


                        String[] days = new String[]{"Søndag", "Mandag", "Tirsdag", "Onsdag", "Torsdag", "Fredag", "Lørdag"};

                        String day = days[calendar.get(Calendar.DAY_OF_WEEK) - 1];
                        String deltar = "";
                        if (kursList.size() != 0) {
                            kurs = kursList.get(0);
                            for (Kurs k : kursList) {
                                if (k.Dag.equals(day)) {
                                    deltar += k.Kursnavn + ",";
                                }
                            }
                            if (deltar.equals("")) {
                                ukedag.setText("Hei " + user.fornavn + "\n i dag er det " + day + "\n Og du deltar ikke på noe kurs idag");
                            } else {
                                ukedag.setText("Hei " + user.fornavn + " \ni dag er det " + day + "\n og du er med på " + deltar + " kurset");
                            }


                        } else {
                            ukedag.setText("Du er ikke påmeldt noen kurs " + user.fornavn);
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            } else {

                Toast.makeText(MinSideActivity.this, "Result = null", Toast.LENGTH_LONG).show();
            }
            progressDialog.cancel();

        }


    }//Slutt på Asynk
}
