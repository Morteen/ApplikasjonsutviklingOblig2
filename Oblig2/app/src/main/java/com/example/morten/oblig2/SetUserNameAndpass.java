package com.example.morten.oblig2;
/**
 * Lagrer brukernavn og passord som SharedPreferences
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SetUserNameAndpass extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    private EditText shareUsename, sharePasword;
    private Button shareBtn, delShared;
    private SharedPreferences sharedPreferences;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public SetUserNameAndpass() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static SetUserNameAndpass newInstance(String param1, String param2) {
        SetUserNameAndpass fragment = new SetUserNameAndpass();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_set_user_name_andpass, container, false);


        shareUsename = (EditText) view.findViewById(R.id.sharedEtextusernam);
        sharePasword = (EditText) view.findViewById(R.id.sharedEtextPassW);
        delShared = (Button) view.findViewById(R.id.SharedPrefDelete);
        shareBtn = (Button) view.findViewById(R.id.SharedPrefBtn);

        shareUsename.setText(MinSideActivity.user.getUsername());
        sharePasword.setText(MinSideActivity.user.getPassord());

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getActivity().getSharedPreferences("userInfo", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", MinSideActivity.user.getUsername());
                editor.putString("password", MinSideActivity.user.getPassord());
                editor.apply();
                Toast.makeText(getActivity(), "Opplysningene er lagret", Toast.LENGTH_LONG).show();
            }
        });

        delShared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareUsename.setText("");
                sharePasword.setText("");
                sharedPreferences = getActivity().getSharedPreferences("userInfo", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", "");
                editor.putString("password", "");
                editor.apply();

            }
        });


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }


}
