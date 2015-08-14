package com.example.florian.android_banque;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import DB.DatabaseHandler;
import model.Client;

public class MainActivity extends AppCompatActivity implements  View.OnTouchListener, View.OnClickListener {

    TextView test;

    private List<Client> clientsList = null;

    DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test = (TextView) findViewById(R.id.text);

       dbHandler = new DatabaseHandler(this);

        dbHandler.ajouter(new Client("Xavier","Charles"));
       dbHandler.ajouter(new Client("Dupont", "Caroline"));
        dbHandler.ajouter(new Client("eponge", "bob"));

      //  dbHandler.close();

       List<Client> clients = dbHandler.getALLClient();
        Log.d("Taille de la base ", String.valueOf(clients.size()));
        Log.d("Client 1", clients.get(0).toString());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.connection) {

            Intent connect = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(connect);
            return true;
        }
        if (id == R.id.compte) {

            Intent compte = new Intent(MainActivity.this, Compte.class);
            startActivity(compte);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event){
        return true;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onPause() {
        dbHandler.close();
        super.onPause();
    }
    @Override
    protected void onResume(){
        dbHandler.getWritableDatabase();
        super.onResume();
    }
}
