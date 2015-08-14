package DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {



    private static String DB_PATH = "/data/data/com.example.florian.android_banque/databases/";



    public static final String CLIENT_ID = "idClient";
    public static final String CLIENT_NOM = "nomClient";
    public static final String CLIENT_PRENOM = "prenomClient";
    public static final String CLIENT_LOGIN = "loginClient";
    public static final String CLIENT_PASSWORD = "passClient";

    public static final String TABLE_CLIENT_NAME = "Client";



    public static final String TABLE_CLIENT_CREATE = "CREATE TABLE "
            + TABLE_CLIENT_NAME + " (" +
            CLIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CLIENT_NOM+ " TEXT, " +
            CLIENT_PRENOM + " TEXT " +
            CLIENT_LOGIN + " TEXT " +
            CLIENT_PASSWORD + " TEXT " +
              ");";


    public static final String CLIENT_TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_CLIENT_NAME + ";";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("on", "create");
        db.execSQL(TABLE_CLIENT_CREATE);


    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("on", "upgrade");
        if (newVersion==oldVersion){
            db.execSQL(CLIENT_TABLE_DROP);
            onCreate(db);
        }



        if (newVersion > oldVersion){
           db.execSQL("ALTER TABLE " + TABLE_CLIENT_NAME + " ADD COLUMN " + CLIENT_LOGIN + " TEXT ");
            db.execSQL("ALTER TABLE " + TABLE_CLIENT_NAME + " ADD COLUMN " + CLIENT_PASSWORD + " TEXT ");
       }
    }






}
