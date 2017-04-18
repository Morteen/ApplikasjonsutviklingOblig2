package com.example.morten.oblig2;
/**
 * Dette er et adapter som viser obkejter av Kurs klassen  i en liste
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by morten on 26.03.2017.
 */

public class KursAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<Kurs> mKurs;
    LayoutInflater mInflater;

    public KursAdapter(Context c, ArrayList<Kurs> kurs) {
        mContext = c;
        mKurs = kurs;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    //teller antal kus
    public int getCount() {

        return mKurs.size();
    }

    //Finner kurs basert på posisjo
    public Object getItem(int position) {
        return mKurs.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {

            //XML fil-navnet
            convertView = mInflater.inflate(R.layout.kurs_liste, null);
            viewHolder = new ViewHolder();

            ///Dette må tilpasses XML filen

            viewHolder.KursViewName = (TextView) convertView.findViewById(R.id.KursViewName);

            viewHolder.KursViewDag = (TextView) convertView.findViewById(R.id.KursViewDag);

            viewHolder.KursViewKursholder = (TextView) convertView.findViewById(R.id.KursViewKursholder);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        Kurs currentKurs = mKurs.get(position);


        if (position == 0) {
            viewHolder.KursViewName.setText("Kursnavn\n\n" + currentKurs.getKursnavn());
            viewHolder.KursViewDag.setText("Dag\n\n" + currentKurs.getDag());
            viewHolder.KursViewKursholder.setText("Lærer\n\n" + currentKurs.getKursholder());
        } else {
            viewHolder.KursViewName.setText(currentKurs.getKursnavn());
            viewHolder.KursViewDag.setText(currentKurs.getDag());
            viewHolder.KursViewKursholder.setText(currentKurs.getKursholder());
        }
        return convertView;
    }

    ///Dette må tilpasses XML filen
    private static class ViewHolder {
        public TextView KursViewName;
        public TextView KursViewDag;
        public TextView KursViewKursholder;

    }


}
