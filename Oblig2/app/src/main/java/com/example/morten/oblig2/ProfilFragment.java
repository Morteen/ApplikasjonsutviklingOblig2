package com.example.morten.oblig2;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class ProfilFragment extends Fragment {

TextView fname,ename,username,email,passw,alder;

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
        User user=LoginActivity.userList.get(0);
        //MinSideActivity min =MinSideActivity.getApplicationActivity();
        fname=(TextView)view.findViewById(R.id.fname);
        ename=(TextView)view.findViewById(R.id.ename);
        username=(TextView)view.findViewById(R.id.username);
        passw=(TextView)view.findViewById(R.id.passw);
        email=(TextView)view.findViewById(R.id.email);
        alder=(TextView)view.findViewById(R.id.alder);

        fname.setText(user.fornavn);
        ename.setText(user.etternavn);
        username.setText(user.username);

        return view;
    }

}
