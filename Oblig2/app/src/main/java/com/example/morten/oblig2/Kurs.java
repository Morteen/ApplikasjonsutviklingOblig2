package com.example.morten.oblig2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by morten on 26.03.2017.
 */

public class Kurs {


    //	KursNr 	Kursnavn 	Dag 	Kursholder
    String Kursnavn, Dag, Kursholder;
    int KursNr;



    static final String TABELL_NAVN = "Kurs";
    static final String KOL_NAVN_Kursnavn = "Kursnavn";
    static final String KOL_NAVN_Dag = "Dag";
    static final String KOL_NAVN_Kursholder = "Kursholder";
    static final String KOL_NAVN_KursNr = "KursNr";







    // "Vanlig" konstruktør
    public Kurs(String Kursnavn, String Dag, String Kursholder,int KursNr) {
        this.Kursnavn=Kursnavn;
        this.Dag=Dag;
        this.Kursholder=Kursholder;
        this.KursNr=KursNr;

    }

    public Kurs(String Kursnavn, String Dag, String Kursholder) {
        this.Kursnavn=Kursnavn;
        this.Dag=Dag;
        this.Kursholder=Kursholder;


    }

    // Tom konstruktør
    public Kurs() {
    }

    // Konstruktør som bygger userobjekt basert på et JSONObject-objekt
    public Kurs(JSONObject jsonUser) {
        this.Kursnavn= jsonUser.optString(KOL_NAVN_Kursnavn );
        this.Dag = jsonUser.optString(KOL_NAVN_Dag);
        this.Kursholder = jsonUser.optString(KOL_NAVN_Kursholder);
        this.KursNr = jsonUser.optInt(KOL_NAVN_KursNr);

    }

    // Metode som lager en ArrayList med User-objekter basert på en streng med JSONdata

    public static ArrayList<Kurs> lagKursListe(String jsonKursString)
            throws JSONException, NullPointerException {
        ArrayList<Kurs> kursListe = new ArrayList<Kurs>();
        JSONObject jsonData = new JSONObject(jsonKursString);
        JSONArray jsonUserTabell = jsonData.optJSONArray(TABELL_NAVN);
        for (int i = 0; i < jsonUserTabell.length(); i++) {
            JSONObject jsonKurs = (JSONObject) jsonUserTabell.get(i);
            Kurs thisKurs = new Kurs(jsonKurs);
            kursListe.add(thisKurs);
        }
        return kursListe;
    }

    @Override
    public String toString() {

        return Kursnavn;
    }


    public JSONObject toJSONObject() {
        JSONObject jsonKurs = new JSONObject();
        try {
            jsonKurs.put(KOL_NAVN_KursNr , this.KursNr);
            jsonKurs.put(KOL_NAVN_Kursnavn, this.Kursnavn);
            jsonKurs.put(KOL_NAVN_Dag, this.Dag);
            jsonKurs.put(KOL_NAVN_Kursholder, this.Kursholder);


        } catch (JSONException e) {
            return null;
        }
        return jsonKurs;
    }


}
