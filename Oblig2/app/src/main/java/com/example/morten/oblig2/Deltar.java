package com.example.morten.oblig2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;




public class Deltar extends Fragment {
    ListView DeltarListView;
    String kursData;
    static ArrayList<Kurs> deltarkursList;



    public Deltar() {
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


        View view=inflater.inflate(R.layout.fragment_deltar, container, false);
        deltarkursList=MinSideActivity.minekursList;
        DeltarListView=(ListView)view.findViewById(R.id.list_DeltarKurs);
        KursAdapter adapter = new KursAdapter(getContext(), deltarkursList);
        DeltarListView.setAdapter(adapter);

        return view;
    }





}
