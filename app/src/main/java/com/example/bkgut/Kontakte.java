package com.example.bkgut;

import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class Kontakte extends Fragment {

    String selector = null;
    ListView lv;

    DatabaseHandler dh;

    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> dataOfSQLQuery;

    String table = "tbl_Lehrer";
    String[] colums = null;
    String whereClause = null;
    String[] whereArgs = null;
    String groupBy = null;
    String having = null;
    String orderBy = null;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View vw = inflater.inflate(R.layout.content_kontakte, container,false);

        final SearchView searchView = vw.findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                whereClause = "Vorname LIKE ? OR Nachname LIKE ? OR Anrede LIKE ?";
                whereArgs = new String[]{"%"+query+"%","%"+query+"%","%"+query+"%"};

                refreshListData();
                //setListViewHeightBasedOnChildren(lv);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (newText.equals("")){
                    this.onQueryTextSubmit("");
                }else {
                    whereClause = "Vorname LIKE ? OR Nachname LIKE ? OR Anrede LIKE ?";
                    whereArgs = new String[]{"%"+newText+"%","%"+newText+"%","%"+newText+"%"};
                    refreshListData();
                    //setListViewHeightBasedOnChildren(lv);
                }
                return false;
            }
        });

        dataOfSQLQuery = tblLehrer();
        arrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),R.layout.mylist,dataOfSQLQuery);

        lv = (ListView) vw.findViewById(R.id.lmoaList);
        lv.setNestedScrollingEnabled(true);
        lv.setAdapter(arrayAdapter);

        //setListViewHeightBasedOnChildren(lv);

        return vw;
    }

    public ArrayList<String> tblLehrer(){

        ArrayList<String> ar = new ArrayList<String>();
        dh = new DatabaseHandler(getActivity().getApplicationContext());
        Cursor c = dh.makeSQLData(table, colums, whereClause, whereArgs, groupBy, having, orderBy);

        if (c.moveToFirst()){
            do{
                ar.add(c.getString(6)+ " "
                        +c.getString(1)+", "
                        +c.getString(0)+"\n"
                        +c.getString(2)+"\n"
                        +c.getString(3)+"\n"
                        +c.getString(4));
            }while (c.moveToNext());
        }

        return ar;
    }


    /*public static void setListViewHeightBasedOnChildren(ListView lv){
        ListAdapter la = lv.getAdapter();
        if (la == null){
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < la.getCount(); i++){
            View listItem = la.getView(i,null,lv);
            listItem.measure(0,0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = lv.getLayoutParams();
        params.height = totalHeight + (lv.getDividerHeight() * (la.getCount() - 1));
        lv.setLayoutParams(params);
        lv.requestLayout();
    }*/

    public void refreshListData(){

        final Runnable searchListRun = new Runnable() {
            @Override
            public void run() {
                arrayAdapter.clear();
                arrayAdapter.addAll(dataOfSQLQuery);
                arrayAdapter.notifyDataSetChanged();
                arrayAdapter.notifyDataSetInvalidated();
                //lv.invalidateViews();
                //lv.refreshDrawableState();
            }
        };

        dataOfSQLQuery = tblLehrer();
        arrayAdapter.notifyDataSetChanged();
        getActivity().runOnUiThread(searchListRun);
    }

    /*public void copyDatabase() throws IOException {
        String path1 = "/data/data/com.example.bkgut/databases/";
        String path = getActivity().getApplicationInfo().dataDir+"/databases/";

        String dbName = "lehrer_data";

        InputStream in = getActivity().getApplicationContext().getAssets().open("lehrer_data");
        OutputStream out = new FileOutputStream(path+dbName);
        byte[] buff = new byte[1024];
        int length;
        while ((length = in.read(buff))>0){
            out.write(buff, 0, length);
        }
        out.flush();
        out.close();
        in.close();
    }*/


}

