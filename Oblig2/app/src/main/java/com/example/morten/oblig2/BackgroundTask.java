package com.example.morten.oblig2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


/**
 * Created by morten on 24.03.2017.
 */

public class BackgroundTask extends AsyncTask<String, Void, String> {


    Context context;
    String method = "";

    public BackgroundTask(Context context) {


    }

    ProgressDialog progressDialog;

    @Override
    protected void onPreExecute() {


    }

    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        String reg_URL = "http://itfag.usn.no/~210144/register.php";
        String update_URL = "http://itfag.usn.no/~210144/update.php";
        String add_UserToKurs = "http://itfag.usn.no/~210144/addUserToKurs.php";
        String delete_UserOnKurs = "http://itfag.usn.no/~210144/deleteUserOnKurs.php";

        if (method.equals("register")) {
            ////Nr,Epost,Passord,username,Fornavn,Etternavn,Alder
            String email = params[1];
            String passW = params[2];
            String username = params[3];
            String fname = params[4];
            String etternavn = params[5];
            String age = params[6];

            try {
                URL url = new URL(reg_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));


                //Koder alle data før de sendes
                String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("passW", "UTF-8") + "=" + URLEncoder.encode(passW, "UTF-8") + "&" +
                        URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("fname", "UTF-8") + "=" + URLEncoder.encode(fname, "UTF-8") + "&" +
                        URLEncoder.encode("etternavn", "UTF-8") + "=" + URLEncoder.encode(etternavn, "UTF-8") + "&" +
                        URLEncoder.encode("age", "UTF-8") + "=" + URLEncoder.encode(age, "UTF-8");

                //legger alle data inn i bufferskriveren
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();


                InputStream is = httpURLConnection.getInputStream();
                is.close();
                return "Registrering er ferdig..";


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else if (method.equals("addUserToKurs")) {

            String nr = params[1];
            String KursNr = params[2];


            try {
                URL url = new URL(add_UserToKurs);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));


                //Koder alle data før de sendes
                String data = URLEncoder.encode("nr", "UTF-8") + "=" + URLEncoder.encode(nr, "UTF-8") + "&" +
                        URLEncoder.encode("kn", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8");
                Log.d("Kodet data", data);
                //legger alle data inn i bufferskriveren
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();


                InputStream is = httpURLConnection.getInputStream();
                is.close();
                return "Du er registrert på kurset..";


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else if (method.equals("delUserOnKurs")) {

            String nr = params[1];
            String KursNr = params[2];


            try {
                URL url = new URL(delete_UserOnKurs);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
               // httpURLConnection.setDoInput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));


                //Koder alle data før de sendes
                String data = URLEncoder.encode("nr", "UTF-8") + "=" + URLEncoder.encode(nr, "UTF-8") + "&" +
                        URLEncoder.encode("kn", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8");
                Log.d("Kodet data", data);
                //legger alle data inn i bufferskriveren
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();


                InputStream is = httpURLConnection.getInputStream();
                is.close();
                return "Du har kanselert kurset..";


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else if (method.equals("update")) {

            ////Nr,Epost,Passord,username,Fornavn,Etternavn,Alder
            String email = params[1];
            String passW = params[2];
            String username = params[3];
            String fname = params[4];
            String etternavn = params[5];
            String age = params[6];
            String nr = params[7];

            try {
                URL url = new URL(update_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));


                //Koder alle data før de sendes
                String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("passW", "UTF-8") + "=" + URLEncoder.encode(passW, "UTF-8") + "&" +
                        URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("fname", "UTF-8") + "=" + URLEncoder.encode(fname, "UTF-8") + "&" +
                        URLEncoder.encode("etternavn", "UTF-8") + "=" + URLEncoder.encode(etternavn, "UTF-8") + "&" +
                        URLEncoder.encode("nr", "UTF-8") + "=" + URLEncoder.encode(nr, "UTF-8") + "&" +
                        URLEncoder.encode("age", "UTF-8") + "=" + URLEncoder.encode(age, "UTF-8");
                Log.d("Kodet data", data);
                //legger alle data inn i bufferskriveren
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();

                int status = httpURLConnection.getResponseCode();
                Log.d("Reponse kode",Integer.toString(status));
                if(status < 400) {

                    InputStream is   = httpURLConnection.getInputStream();
                    is.close();
                }else{
                    InputStream is= httpURLConnection.getErrorStream();

                }




                return "Opplysningene er oppdater..";


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            }


        }//her slutter register if'n


        return null;
    }


    @Override
    protected void onPostExecute(String result) {

        if (method.equals("register")) {
            Toast.makeText(this.context, result, Toast.LENGTH_SHORT).show();
        } else if (method.equals("addUserToKurs")) {
            Toast.makeText(this.context, result, Toast.LENGTH_SHORT).show();


        } else if (method.equals("delUserOnKurs")) {
            Toast.makeText(this.context, result, Toast.LENGTH_SHORT).show();
        } else if (method.equals("update")) {
            Log.d("Result i post Ex",result);
            Toast.makeText(this.context, result, Toast.LENGTH_SHORT).show();
        }
    }


}












