package DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.Client;

public class DatabaseHandler extends SQLiteOpenHelper {



    private static String DB_PATH = "/data/data/com.example.florian.android_banque/databases/";

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "banqueManager";

    public static final String TABLE_CLIENT_NAME = "Clients";

    public static final String CLIENT_ID = "idClient";
    public static final String CLIENT_NOM = "nomClient";
    public static final String CLIENT_PRENOM = "prenomClient";
    public static final String CLIENT_LOGIN = "loginClient";
    public static final String CLIENT_PASSWORD = "passClient";



    public static final String TABLE_CLIENT_CREATE = "CREATE TABLE "
            + TABLE_CLIENT_NAME + " (" +
            CLIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CLIENT_NOM+ " TEXT, " +
            CLIENT_PRENOM + " TEXT, " +
            CLIENT_LOGIN + " TEXT, " +
            CLIENT_PASSWORD + " TEXT " +
              ")";


    public static final String CLIENT_TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_CLIENT_NAME + ";";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CLIENT_CREATE);

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("on", "upgrade");
        db.execSQL(CLIENT_TABLE_DROP);
        onCreate(db);
    }



    public void ajouter(Client c) {

        if(clientExist(c)){
            modifier(c);
        }

        else {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues value = new ContentValues();
            value.put(CLIENT_NOM, c.getNom());
            value.put(CLIENT_PRENOM, c.getPrenom());
            value.put(CLIENT_LOGIN, c.getLogin());
            value.put(CLIENT_PASSWORD, "123");

            db.insert(TABLE_CLIENT_NAME, null, value);
            db.close();
        }



    }

    public void supprimer (long id) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_CLIENT_NAME, CLIENT_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();

    }

    public void modifier (Client c) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(CLIENT_NOM, c.getNom());
        value.put(CLIENT_PRENOM, c.getPrenom());
        value.put(CLIENT_LOGIN, c.getLogin());
        value.put(CLIENT_PASSWORD, "123");
        db.update(TABLE_CLIENT_NAME, value, CLIENT_ID + " = ?",
                new String[]{String.valueOf(c.getIdClient())});

    }

    public Client selectionner (long id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Client client = new Client();

        String[] tableColumns = new String[]{CLIENT_ID, CLIENT_NOM, CLIENT_PRENOM, CLIENT_LOGIN, CLIENT_PASSWORD};
        Cursor cursor = db.query(TABLE_CLIENT_NAME, tableColumns,
                CLIENT_ID + " = ?", new String[]{String.valueOf(id)}
                , null, null, null, null);

        if (cursor!=null){
            cursor.moveToFirst();
            client.setIdClient(cursor.getInt(cursor.getColumnIndex(CLIENT_ID)));
            client.setNom(cursor.getString(cursor.getColumnIndex(CLIENT_NOM)));
            client.setPrenom(cursor.getString(cursor.getColumnIndex(CLIENT_PRENOM)));
        }
        return client;

    }
    public boolean checkPassword(String login, String passwordIn){

        SQLiteDatabase db = this.getReadableDatabase();

        if (login!=null && passwordIn!=null){
            String pass;
            String [] tableColumns = new String[] {CLIENT_LOGIN, CLIENT_PASSWORD};
            Cursor cursor = db.query(TABLE_CLIENT_NAME, tableColumns, CLIENT_LOGIN + " = ?", new String[]{login},null,null,null,null);

            if (cursor.isFirst()){
                cursor.moveToFirst();
                pass= cursor.getString(cursor.getColumnIndex(CLIENT_PASSWORD));

                if (pass == passwordIn){
                    return true;
                }else{
                    return false;
                }
            }
            else{
                return false;
            }

        } else {
            return false;
        }
    }

    public List<Client> getALLClient(){

        SQLiteDatabase db = this.getReadableDatabase();
        List<Client> clients= new ArrayList<Client>();

        String selectQuery = "SELECT * FROM " + TABLE_CLIENT_NAME;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){

            do {
                Client client = new Client();
                client.setIdClient(Integer.parseInt(cursor.getString(0)));
                client.setNom(cursor.getString(1));
                client.setPrenom(cursor.getString(2));
                client.setLogin(cursor.getString(3));
                client.setPassword(cursor.getString(4));
                clients.add(client);

            }while (cursor.moveToNext());

        }
        return clients;
    }

    public boolean clientExist(Client c){
     SQLiteDatabase db = this.getReadableDatabase();
        String[] args = {c.getNom(), c.getPrenom()};
        String selectQuery = "SELECT * FROM " + TABLE_CLIENT_NAME + " WHERE " + CLIENT_NOM + " = ? AND " + CLIENT_PRENOM +" = ?" ;
        Cursor cursor = db.rawQuery(selectQuery, args);

        if (cursor.moveToFirst())
            return true;
        else
            return false;





}
}
