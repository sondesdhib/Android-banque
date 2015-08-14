package DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import DB.DatabaseHandler;

/**
 * Created by florian on 05/08/2015.
 */
public abstract class DAOBase {
    // Nous sommes à la première version de la base
    // Si je décide de la mettre à jour, il faudra changer cet attribut
    protected final static int VERSION = 11;
    // Le nom du fichier qui représente ma base
    protected final static String NOM = "Client.db";

    protected SQLiteDatabase mDb = null;
    protected DatabaseHandler mHandler = null;


    public DAOBase(Context pContext) {
        mHandler = new DatabaseHandler(pContext, NOM, null, VERSION);

        mDb =  mHandler.getWritableDatabase();
    }



    public void close() {
        mDb.close();
    }

    public SQLiteDatabase getDb() {
        return mDb;

    }


}
