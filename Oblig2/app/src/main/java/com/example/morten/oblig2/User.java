package com.example.morten.oblig2;

/**
 * Created by morten on 21.03.2017.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class User {



    String fornavn, etternavn, passord, username,email;
    int nr ,alder;



    static final String TABELL_NAVN = "Bruker";
    static final String KOL_NAVN_Fornavn = "Fornavn";
    static final String KOL_NAVN_Etternavn = "Etternavn";
    static final String KOL_NAVN_Passord = "Passord";
    static final String KOL_NAVN_Username = "username";
    static final String KOL_NAVN_Email = "Epost";
    static final String  KOL_NAVN_Nr = "Nr";
    static final String KOL_NAVN_Alder = "Alder";






    // "Vanlig" konstruktør
    public User(String fornavn, String etternavn, String password, String username, String email, int nr, int alder) {
        this.fornavn=fornavn;
        this.etternavn=etternavn;
        this.passord=password;
        this.username=username;
        this.email=email;
        this.nr= nr;
        this.alder=alder;
    }
    public User(String fornavn, String etternavn, String password, String username, String email,  int alder) {
        this.fornavn=fornavn;
        this.etternavn=etternavn;
        this.passord=password;
        this.username=username;
        this.email=email;

        this.alder=alder;
    }

    // Tom konstruktør
    public User() {
    }

    // Konstruktør som bygger Vare-objekt basert på et JSONObject-objekt
    public User(JSONObject jsonUser) {
        this.fornavn= jsonUser.optString(KOL_NAVN_Fornavn );
        this.etternavn = jsonUser.optString(KOL_NAVN_Etternavn);
        this.passord = jsonUser.optString(KOL_NAVN_Passord);
        this.username = jsonUser.optString(KOL_NAVN_Username);
        this.email = jsonUser.optString(KOL_NAVN_Email);
        this.nr = jsonUser.optInt(KOL_NAVN_Nr );
        this.alder = jsonUser.optInt(KOL_NAVN_Alder );
    }

    // Metode som lager en ArrayList med User-objekter basert på en streng med JSONdata

    public static ArrayList<User> lagUserListe(String jsonUserString)
            throws JSONException, NullPointerException {
        ArrayList<User> userListe = new ArrayList<User>();
        JSONObject jsonData = new JSONObject(jsonUserString);
        JSONArray jsonUserTabell = jsonData.optJSONArray(TABELL_NAVN);
        for (int i = 0; i < jsonUserTabell.length(); i++) {
            JSONObject jsonUser = (JSONObject) jsonUserTabell.get(i);
            User thisUser = new User(jsonUser);
            userListe.add(thisUser);
        }
        return userListe;
    }

    @Override
    public String toString() {
        return fornavn+ " " + etternavn;
    }


    public JSONObject toJSONObject() {
        JSONObject jsonUser = new JSONObject();
        try {
            jsonUser.put(KOL_NAVN_Nr , this.nr);
            jsonUser.put(KOL_NAVN_Fornavn, this.fornavn);
            jsonUser.put(KOL_NAVN_Etternavn, this.etternavn);
            jsonUser.put(KOL_NAVN_Passord, this.passord);
            jsonUser.put(KOL_NAVN_Username, this.username);
            jsonUser.put(KOL_NAVN_Email, this.email);
            jsonUser.put(KOL_NAVN_Alder, this.alder);

        } catch (JSONException e) {
            return null;
        }
        return jsonUser;
    }


}



