package com.example.morten.oblig2;

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

    Button delete, add;
    int userNr, listNr, listPos;
    Kurs kurs;
    String addMethod = "addUserToKurs";
    String delMethod = "delUserOnKurs";
    String userNrstring, kursNrString;
    boolean sjekk;

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

        userNr = MinSideActivity.user.nr;
        Bundle bundle = getArguments();
        listNr = bundle.getInt("KurslisteNr");
        listPos = bundle.getInt("ListPos");
        kurs = VisKursFragment.allekursList.get(listPos);
        userNrstring = Integer.toString(userNr);
        kursNrString = Integer.toString(listNr);
         sjekk=false;
        for(Kurs k : MinSideActivity.minekursList){
            if(k.Kursnavn!= null && k.Kursnavn.contains(kurs.Kursnavn)){
                sjekk=true;
            }

        }


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!sjekk) {
                    MinSideActivity.minekursList.add(kurs);

                    new BackgroundTask(getActivity()).execute(addMethod, userNrstring, kursNrString);


                } else {
                    String regMessage = getResources().getString(R.string.RegMessage);
                    Toast.makeText(getActivity(), regMessage + ":" + kurs.Kursnavn, Toast.LENGTH_SHORT).show();

                }
                Intent i = new Intent(getActivity(), MinSideActivity.class);
                getActivity().startActivity(i);

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sjekk) {

                    new BackgroundTask(getActivity()).execute(delMethod, userNrstring, kursNrString);
                     MinSideActivity.minekursList.remove(kurs);



                } else {
                    String delMessage = getResources().getString(R.string.DeleteMessage);
                    Toast.makeText(getActivity(), delMessage + ":" + kurs.Kursnavn, Toast.LENGTH_SHORT).show();
                }
                Intent i = new Intent(getActivity(), MinSideActivity.class);
                getActivity().startActivity(i);
            }
        });
        return view;
    }


}
