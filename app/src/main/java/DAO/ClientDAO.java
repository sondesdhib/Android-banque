package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import DB.DatabaseHandler;
import model.Client;

/**
 * Created by florian on 05/08/2015.
 */
public class ClientDAO extends DAOBase {

public static final String TABLE_NAME = "Client";
    public static final String KEY ="idClient";
    public static final String NOM = "nomClient";
    public static final String PRENOM= "prenomClient";
    public static final String LOGIN = "loginClient";
    public static final String PASSWORD = "passClient";


    public ClientDAO(Context pContext) {

        super(pContext);

    }

    public void ajouter(Client c) {

        ContentValues value = new ContentValues();
        value.put(NOM , c.getNom());
        value.put(PRENOM, c.getPrenom());
        value.put(LOGIN, c.getLogin());
        value.put(PASSWORD,"123");

        mDb.insert(TABLE_NAME, null, value);



    }

    public void supprimer (long id) {

        mDb.delete(TABLE_NAME, KEY + " = ?", new String[]{String.valueOf(id)});

    }

    public void modifier (Client c) {

        ContentValues value = new ContentValues();
        value.put(NOM, c.getNom());
        value.put(PRENOM, c.getPrenom());
        mDb.update(TABLE_NAME, value, KEY + " = ?",
                new String[]{String.valueOf(c.getIdClient())});

    }

    public Client selectionner (long id) {

        Client client = new Client();

        String[] tableColumns = new String[]{KEY, NOM, PRENOM};
        Cursor cursor = mDb.query(TABLE_NAME, tableColumns,
                KEY + " = ?", new String[]{String.valueOf(id)}
                , null, null,null,null);

       if (cursor!=null){
           cursor.moveToFirst();
            client.setIdClient(cursor.getInt(cursor.getColumnIndex(KEY)));
            client.setNom(cursor.getString(cursor.getColumnIndex(NOM)));
            client.setPrenom(cursor.getString(cursor.getColumnIndex(PRENOM)));

           Log.d("index nom ", String.valueOf(cursor.getColumnIndex(NOM))  );

        }
        return client;

    }
    public boolean checkPassword(String login, String passwordIn){

        if (login!=null && passwordIn!=null){
            String pass= null;
            String [] tableColumns = new String[] {LOGIN,PASSWORD};
            Cursor cursor = mDb.query(TABLE_NAME, tableColumns, LOGIN + " = ?", new String[]{login},LOGIN,null,null,null);

            if (cursor!=null){
                cursor.moveToFirst();
                pass= cursor.getString(cursor.getColumnIndex(PASSWORD));

                if (pass == passwordIn)
                    return true;
            }
            cursor.close();
        }

        return false;
    }

    public List<Client> getALLClient(){
        List<Client> clients= new ArrayList<Client>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = mDb.rawQuery(selectQuery, null);

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

            cursor.close();

        }


        return clients;
    }



}
