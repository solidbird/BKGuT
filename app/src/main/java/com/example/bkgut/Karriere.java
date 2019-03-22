package com.example.bkgut;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class Karriere extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    String table = "tbl_Abschluss";
    String[] colums = null;
    String whereClause = null;
    String[] whereArgs = null;
    String groupBy = null;
    String having = null;
    String orderBy = null;

    int rowNumb= 0;
    String arg1 = "0", arg2="Berufsvorbereitung";
    Space sp;
    LinearLayout ln;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vw = inflater.inflate(R.layout.content_karriere, container,false);

        ln = (LinearLayout) vw.findViewById(R.id.karriere_card);

        sp = new Space(getActivity().getApplicationContext());
        sp.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 1000));

        final ArrayList<String> arrayStr1 = tblAbschluss();
        String[] arrayStr2 = new String[]{"Berufsvorbereitung","Informationstechnik","Gestaltungstechnik","Elektrotechnik","Gesundheitstechnik"};
        ArrayList<String> arrayStr = new ArrayList<String>();
        arrayStr.addAll(Arrays.asList(arrayStr2));

        Spinner spn = (Spinner) vw.findViewById(R.id.karr_spin1);
        //System.out.println(arrayStr1.indexOf("Hauptschulabschluss"));
        ArrayAdapter<String> data = new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_spinner_dropdown_item, arrayStr1);
        data.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(data);

        spn.setOnItemSelectedListener(this);

        Spinner spn1 = (Spinner) vw.findViewById(R.id.karr_spin2);
        ArrayAdapter<String> data1 = new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_spinner_dropdown_item, arrayStr2);
        data1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn1.setAdapter(data1);

        spn1.setOnItemSelectedListener(this);
/*
        TextView txt = new TextView(getActivity().getApplicationContext());
        txt.setText("LMAO");

        CardView cv1 = (CardView) vw.findViewById(R.id.card1);
        CardView cv2 = (CardView) vw.findViewById(R.id.card2);
        CardView cv3 = (CardView) vw.findViewById(R.id.card3);
        CardView cv4 = (CardView) vw.findViewById(R.id.card4);

        DatabaseHandler dh = new DatabaseHandler(getActivity().getApplicationContext());
        //ArrayList<String> oof = dh.makeSQLData(null,"tbl_Bildungsgang");


        //TextView txt = new TextView(getActivity().getApplicationContext());
        //txt.setText((String)oof.toArray()[4]);
        //cv1.addView(txt);

        cv1.setOnClickListener(this);
        cv2.setOnClickListener(this);
        cv3.setOnClickListener(this);
        cv4.setOnClickListener(this);*/


        return vw;
    }

    public ArrayList<String> tblAbschluss(){
        ArrayList<String> ar = new ArrayList<String>();
        DatabaseHandler dbh = new DatabaseHandler(getActivity().getApplicationContext());
        Cursor c = dbh.makeSQLData(table, colums, whereClause, whereArgs, groupBy, having, orderBy);
        if (c.moveToFirst()) {
            do {
                ar.add(Integer.parseInt(c.getString(1)), c.getString(0));
            } while (c.moveToNext());
        }
        return ar;
    }

    public ArrayList<String> tblBildungsgang(){
        ArrayList<String> ar = new ArrayList<String>();
        DatabaseHandler dbh = new DatabaseHandler(getActivity().getApplicationContext());
        Cursor c = dbh.makeSQLData(table, colums, whereClause, whereArgs, groupBy, having, orderBy);
        rowNumb =c.getCount();
        if (c.moveToFirst()){
            do {
                ar.add(c.getString(0)+ "\n"
                        +c.getString(1)+"\n"
                        +c.getString(2)+"\n"
                        +c.getString(3)+"\n"
                        +c.getString(4));
            }while (c.moveToNext());
        }
        return ar;
    }

    public ArrayList<String> getLinkOfBildungsgang(){
        ArrayList<String> ar = new ArrayList<String>();
        DatabaseHandler dbh = new DatabaseHandler(getActivity().getApplicationContext());
        Cursor c = dbh.makeSQLData(table, colums, whereClause, whereArgs, groupBy, having, orderBy);
        rowNumb =c.getCount();
        if (c.moveToFirst()){
            do {
                ar.add(c.getString(6));
            }while (c.moveToNext());
        }
        return ar;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity().getApplicationContext(),"LMAO clicked",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        table="tbl_Bildungsgang";
        whereClause = "AbschlussPrio <= ? AND Bereich = ?";

        if(parent.getId()==R.id.karr_spin1){
            arg1 = String.valueOf(position);

        }else if (parent.getId()==R.id.karr_spin2){
            arg2 = parent.getItemAtPosition(position).toString();

        }

        System.out.println("Karr_spin1 AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA: " + arg1);
        System.out.println("Karr_spin2 BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB: " + arg2);

        whereArgs=new String[]{ arg1, arg2 };

        ArrayList<String> ar = tblBildungsgang();
        final ArrayList<String> links = getLinkOfBildungsgang();

        if (rowNumb > 0){
            CardView[] card = new CardView[rowNumb];

            ViewGroup.LayoutParams pamCard = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            ((LinearLayout.LayoutParams) pamCard).setMargins(10,10,10,10);
            ViewGroup.LayoutParams pamLin = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            ViewGroup.LayoutParams pamTxt = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            ln.removeAllViews();

            for (int i = 0; i < card.length; i++){

                TextView txt = new TextView(getActivity().getApplicationContext());
                txt.setPadding(15,15,15,15);
                txt.setLayoutParams(pamTxt);
                txt.setTextSize(20);
                txt.setText(ar.get(i));

                LinearLayout linLay = new LinearLayout(getActivity().getApplicationContext());
                linLay.setLayoutParams(pamLin);
                linLay.removeAllViews();
                linLay.addView(txt);

                card[i] = new CardView(getActivity().getApplicationContext());
                card[i].setRadius(15);
                card[i].setElevation(15);
                card[i].setLayoutParams(pamCard);
                card[i].removeAllViews();
                card[i].addView(linLay);

                card[i].setOnClickListener(this);
                ln.addView(card[i]);
            }
        }else{
            ln.removeAllViews();
            ln.addView(sp);

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
