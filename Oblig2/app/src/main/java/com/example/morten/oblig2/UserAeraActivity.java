package com.example.morten.oblig2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class UserAeraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_aera);

        final EditText etUserName= (EditText)findViewById(R.id.etUsername);
        final EditText etAge= (EditText)findViewById(R.id.etAge);
        final TextView velkom = (TextView)findViewById(R.id.tvVelkom);

    }
}
