package com.example.morten.oblig2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EditUserFragment extends Fragment {


    EditText userFname, userLname, userUsername, userPassword, userEmail, userAge;
    Button updateBtn;
    private String method="update";
    private String userNr;
    private User user;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public EditUserFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static EditUserFragment newInstance(String param1, String param2) {
        EditUserFragment fragment = new EditUserFragment();
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
        View view = inflater.inflate(R.layout.fragment_edit_user, container, false);

        userFname = (EditText) view.findViewById(R.id.editUserFname);
        userLname = (EditText) view.findViewById(R.id.editUserLastName);
        userUsername = (EditText) view.findViewById(R.id.editUserUsername);
        userPassword = (EditText) view.findViewById(R.id.editUserPassword);
        userEmail = (EditText) view.findViewById(R.id.editUserEmail);
        userAge = (EditText) view.findViewById(R.id.editUserAge);
        updateBtn=(Button)view.findViewById(R.id.editUserButton);

         user=MinSideActivity.user;
        userFname.setText(user.fornavn);
        userLname.setText(user.etternavn);
        userUsername.setText(user.username);
        userPassword.setText(user.passord);
        userEmail.setText(user.email);
        userAge.setText(Integer.toString(user.alder));
        userNr=Integer.toString(user.nr);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new BackgroundTask(getActivity()).execute(method,userEmail.getText().toString(),userPassword.getText().toString(),
                        userUsername.getText().toString(),userFname.getText().toString()
                        ,userLname.getText().toString(),userAge.getText().toString(),userNr);

               MinSideActivity.user.fornavn=userFname.getText().toString();
                MinSideActivity.user.etternavn=userLname.getText().toString();
                MinSideActivity.user.email=userEmail.getText().toString();
                MinSideActivity.user.passord=userPassword.getText().toString();
                MinSideActivity.user.alder=Integer.parseInt(userAge.getText().toString());

        String UpdateMessage=getString(R.string.UpdateUserMessage);
                Toast.makeText(getActivity(),UpdateMessage,Toast.LENGTH_LONG).show();

                MinSideActivity.returnToMinside(getActivity());

            }
        });










        return view;
    }


}
