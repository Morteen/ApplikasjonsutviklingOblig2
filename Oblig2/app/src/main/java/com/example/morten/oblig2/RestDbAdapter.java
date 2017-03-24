package com.example.morten.oblig2;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 210144 on 16.03.2017.
 */

public class RestDbAdapter {



    public RestDbAdapter(){

    }

    /*AsynkRestUserAdapter adapter = new AsynkRestUserAdapter();

    public void insertUser(User nUser) {

        adapter.setHttpMethod("POST");
        adapter.setUser(nUser);
        adapter.execute();

    }


    public void updateUser(String nr, User eUser) {

        adapter.setHttpMethod("PUT");
        adapter.setUsernr(nr);
        adapter.execute();

    }


    public void deletUser(String nr) {

        adapter.setHttpMethod("DELETE");
        adapter.setUsernr(nr);
        adapter.execute();

    }
    public void newUser(User user) {

        adapter.setHttpMethod("Post");
        adapter.setUser(user);
        adapter.execute();

    }


    private class AsynkRestUserAdapter extends AsyncTask<String, String, Long> {

        ProgressDialog progressDialog;
        private String HttpMethod;
        private User user;
        private String c;
        private String nr;

        public void setHttpMethod(String m) {
            this.HttpMethod = m;
        }

        public void setUser(User u) {
            this.user = u;
        }

        public void setUsernr(String nr) {
            this.nr = nr;
        }


        @Override
        protected Long doInBackground(String... strings) {

            HttpURLConnection connection = null;                      // Hele tekster 	Nr 	Epost 	Passord 	username 	Fornavn 	Etternavn 	Alder
            String user_URI = "POST http://itfag.usn.no/~210144/api.php/Bruker";//{'Nr':null',Epost':'post@post','Passord':'pass','username':'mmnvb','Fornavn':'KK','Etternavn':'LL','Alder':'1'}";
            if (HttpMethod.equals("PUT") && HttpMethod.equals("DELETE")) {
                //user_URI += "/" + nr;
            }
            try {
                URL userURL = new URL(user_URI);
                connection.setDoInput(true);
                connection.setRequestMethod(HttpMethod);
                if (HttpMethod.equals("PUT") | HttpMethod.equals("POST")) {
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Content-Type","application/json;charset=utf-8");

                } else {
                    connection.setDoOutput(false);

                }
                connection.connect();
                if (HttpMethod.equals("PUT") | HttpMethod.equals("POST")) {
                    JSONObject jsonUser = user.toJSONObject();
                    OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                    out.write(jsonUser.toString());
                    out.close();
                }

                int status = connection.getResponseCode();
                if (status == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String responseString = reader.readLine();



                    if (responseString.equals("0")) {
                        return (0L);


                    } else return (1l);
                }


            } catch (Exception e) {
                return (1l);
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           progressDialog = new ProgressDialog(UserAeraActivity.get);
            progressDialog.setMessage("Vent litt nå da..");
            progressDialog.setTitle("Kobler opp...");
            progressDialog.show();
            progressDialog.setProgressStyle(3);
            progressDialog.setCancelable(false);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
        }
    }*/

}
