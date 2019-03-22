package com.example.bkgut;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHandler extends SQLiteOpenHelper {


    private static String DATABASE_NAME = "schule_db";
    private static String DATABASE_PATH = "";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;
    private Context context;
    private boolean needUpdate = false;
    Cursor c;



    public DatabaseHandler(Context context){
        super(context,DATABASE_NAME,null,1);

        if(Build.VERSION.SDK_INT >= 17){
            DATABASE_PATH = context.getApplicationInfo().dataDir + "/databases/";
        }else {
            DATABASE_PATH = "/data/data/"+context.getPackageName()+"/databases/";
        }
        this.context = context;

        copyDataBase();

        this.getReadableDatabase();
    }


    public void updateDataBase() throws IOException{
        if (needUpdate){
            File fl = new File(DATABASE_PATH+DATABASE_NAME);
            if (fl.exists()){
                fl.delete();
            }
            copyDataBase();

            needUpdate = false;
        }
    }

    private boolean checkDataBase(){
        File fl = new File(DATABASE_PATH+DATABASE_NAME);
        return fl.exists();
    }

    private void copyDataBase(){
        if (!checkDataBase()){
            this.getReadableDatabase();
            this.close();

            try {
                copyDBFile();
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void copyDBFile() throws IOException {

        InputStream in = context.getApplicationContext().getAssets().open(DATABASE_NAME);
        OutputStream out = new FileOutputStream(DATABASE_PATH+DATABASE_NAME);
        byte[] buff = new byte[1024];
        int length;
        while ((length = in.read(buff))>0){
            out.write(buff, 0, length);
        }
        out.flush();
        out.close();
        in.close();
    }

    public boolean openDataBase() throws SQLException {
        db = SQLiteDatabase.openDatabase(DATABASE_PATH+DATABASE_NAME,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        return db != null;
    }

    @Override
    public synchronized void close() {
        if (db != null) {
            db.close();
        }
        super.close();
    }

    public Cursor makeSQLData(String table, String[] colums, String whereClause, String[] whereArgs, String groupBy, String having, String orderBy){
        DatabaseHandler sl = new DatabaseHandler(context);
        SQLiteDatabase db = sl.getReadableDatabase();

        /*String query = "SELECT * FROM "+table+";";

        if (selector != null){
            if (selector.equals("")){
                query = "SELECT * FROM "+table+";";
            }else {
                query = "SELECT * FROM "+table+" WHERE Vorname LIKE '%"+selector+"%' or Nachname LIKE '%"+selector+"%' or Anrede LIKE '%"+selector+"%';";
                System.out.println(query);
            }
        }*/
        //https://stackoverflow.com/questions/10600670/sqlitedatabase-query-

        //c = db.rawQuery(query,null);

        Cursor c = db.query(table,colums,whereClause,whereArgs,groupBy,having,orderBy);
        //System.out.println(table + "\n" + colums + "\n" + whereClause  + "\n" + (whereArgs!=null?whereArgs[0]:"NULL") + "\n" + groupBy + "\n" + having  + "\n" + orderBy);

        return c;

    }

    /*public ArrayList<String> tblLehrer(){
        ArrayList<String> ar = new ArrayList<String>();
        while (c.moveToNext() != false ){
            ar.add(c.getString(6)+ " "
                    +c.getString(1)+", "
                    +c.getString(0)+"\n"
                    +c.getString(2)+"\n"
                    +c.getString(3)+"\n"
                    +c.getString(4));
        }
        return ar;
    }

    public ArrayList<String> tblBildungsgang(){
        ArrayList<String> ar = new ArrayList<String>();
        while (c.moveToNext() != false ){
            ar.add(c.getString(7)+ " "
                    +c.getString(1)+", "
                    +c.getString(2)+"\n"
                    +c.getString(3)+"\n"
                    +c.getString(4)+"\n"
                    +c.getString(5)+"\n"
                    +c.getString(6));
        }
        return ar;
    }

    public ArrayList<String> tblAbschluss(){
        ArrayList<String> ar = new ArrayList<String>();
        while (c.moveToNext() != false ){
            ar.add(Integer.parseInt(c.getString(1)),c.getString(0));
        }
        return ar;
    }*/

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion){
            needUpdate = true;
        }
    }

}
