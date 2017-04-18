package com.example.morten.oblig2;
/**
 * Dette er et fragment som viser alle tilgjengelige kurs i databasen. Det er en onItemClickListener
 * på listen over kurs slik at man kan velge et kurs og melde seg på eller av
 */

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class VisKursFragment extends Fragment {

    private ListView kursListView;
    private String kursData;
    static ArrayList<Kurs> allekursList;


    public VisKursFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vis_kurs, container, false);

        //TODO:Legge til if connected betingelser
        kursListView = (ListView) view.findViewById(R.id.list_viewKurs);
        new KursBackgroundTask().execute();
        kursListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle bundel = new Bundle();

                bundel.putInt("KurslisteNr", allekursList.get(position).getKursNr());
                bundel.putInt("ListPos", position);

                AddDelFragment addDelFrag = new AddDelFragment();
                addDelFrag.setArguments(bundel);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.content, addDelFrag);
                ft.addToBackStack(null);
                ft.commit();


            }
        });


        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
       /* if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }*/
    }


    class KursBackgroundTask extends AsyncTask<String, Void, Boolean> {

        ProgressDialog progressDialog;


        public KursBackgroundTask() {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(getContext());
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
                URL actorUrl = new URL("http://itfag.usn.no/~210144/api.php/Kurs?transform=1");

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
                kursData = sb.toString();
                allekursList = Kurs.lagKursListe(kursData);
                Log.d("connection", kursData);


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


                KursAdapter adapter = new KursAdapter(getContext(), allekursList);
                kursListView.setAdapter(adapter);


            }

        }
    }


}
