package com.example.morten.oblig2;

/**
 * Dette er et fragment som lar brukeren melde seg på eller av et valgt kurs
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class AddDelFragment extends Fragment {

    private Button delete, add;
    private int userNr, listNr, listPos;
    private Kurs kurs;
    private String addMethod = "addUserToKurs";
    private String delMethod = "delUserOnKurs";
    private String userNrstring, kursNrString;
    private boolean sjekk;

    public AddDelFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_del, container, false);

        add = (Button) view.findViewById(R.id.add);
        delete = (Button) view.findViewById(R.id.delete);

        userNr = MinSideActivity.user.getNr();
        Bundle bundle = getArguments();
        listNr = bundle.getInt("KurslisteNr");
        listPos = bundle.getInt("ListPos");
        kurs = VisKursFragment.allekursList.get(listPos);
        userNrstring = Integer.toString(userNr);
        kursNrString = Integer.toString(listNr);

        /**
         * Sjekker om det valgte kurset er i listen over kurs deltakeren er med på
         */
        sjekk = false;
        for (Kurs k : MinSideActivity.minekursList) {
            if (k.getKursnavn() != null && k.getKursnavn().contains(kurs.getKursnavn())) {
                sjekk = true;
            }

        }


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!sjekk) {
                    //Legger til kurset i minekurs listen og i databasen
                    MinSideActivity.minekursList.add(kurs);
                    new BackgroundTask(getActivity()).execute(addMethod, userNrstring, kursNrString);


                } else {
                    String regMessage = getResources().getString(R.string.RegMessage);
                    Toast.makeText(getActivity(), regMessage + ":" + kurs.getKursnavn(), Toast.LENGTH_SHORT).show();

                }
                MinSideActivity.returnToMinside(getActivity());

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sjekk) {
                    //Sletter kurset i minekurs listen og i databasen
                    new BackgroundTask(getActivity()).execute(delMethod, userNrstring, kursNrString);
                    MinSideActivity.minekursList.remove(kurs);


                } else {
                    String delMessage = getResources().getString(R.string.DeleteMessage);
                    Toast.makeText(getActivity(), delMessage + ":" + kurs.getKursnavn(), Toast.LENGTH_SHORT).show();
                }
                MinSideActivity.returnToMinside(getActivity());
            }
        });
        return view;
    }


}
