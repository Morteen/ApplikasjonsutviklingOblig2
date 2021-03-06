package com.example.morten.oblig2;
/**
 * Dette er et fragment som vise oplysninger om brukeren
 */


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class ProfilFragment extends Fragment {

private TextView fname,ename,username,email,passw,alder;

    public ProfilFragment() {
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
        View view= inflater.inflate(R.layout.fragment_profil, container, false);
        User user=MinSideActivity.user;
        //MinSideActivity min =MinSideActivity.getApplicationActivity();
        fname=(TextView)view.findViewById(R.id.fname);
        ename=(TextView)view.findViewById(R.id.ename);
        username=(TextView)view.findViewById(R.id.username);
        passw=(TextView)view.findViewById(R.id.ePassword);
        email=(TextView)view.findViewById(R.id.email);
        alder=(TextView)view.findViewById(R.id.alder);

        fname.setText(user.getFornavn());
        ename.setText(user.getEtternavn());
        username.setText(user.getUsername());
        passw.setText((user.getPassord()));
        email.setText(user.getEmail());
       alder.setText(Integer.toString(user.getAlder()));

        return view;
    }

}
