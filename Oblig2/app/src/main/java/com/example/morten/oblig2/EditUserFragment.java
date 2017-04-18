package com.example.morten.oblig2;
/**
 * Dette er et fragment som lar brukeren oppdatere sine opplysninger
 */

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


    private EditText userFname, userLname, userUsername, userPassword, userEmail, userAge;
    private Button updateBtn;
    private String method = "update";
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
        updateBtn = (Button) view.findViewById(R.id.editUserButton);

        user = MinSideActivity.user;
        userFname.setText(user.getFornavn());
        userLname.setText(user.getEtternavn());
        userUsername.setText(user.getUsername());
        userPassword.setText(user.getPassord());
        userEmail.setText(user.getEmail());
        userAge.setText(Integer.toString(user.getAlder()));
        userNr = Integer.toString(user.getNr());

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new BackgroundTask(getActivity()).execute(method, userEmail.getText().toString(), userPassword.getText().toString(),
                        userUsername.getText().toString(), userFname.getText().toString()
                        , userLname.getText().toString(), userAge.getText().toString(), userNr);

                MinSideActivity.user.setFornavn(userFname.getText().toString());
                MinSideActivity.user.setEtternavn(userLname.getText().toString());
                MinSideActivity.user.setEmail(userEmail.getText().toString());
                MinSideActivity.user.setPassord(userPassword.getText().toString());
                MinSideActivity.user.setAlder(Integer.parseInt(userAge.getText().toString()));

                String UpdateMessage = getString(R.string.UpdateUserMessage);
                Toast.makeText(getActivity(), UpdateMessage, Toast.LENGTH_LONG).show();

                MinSideActivity.returnToMinside(getActivity());

            }
        });


        return view;
    }


}
