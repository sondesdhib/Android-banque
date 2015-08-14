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

import DAO.ClientDAO;
import DAO.DAOBase;
import DB.DatabaseHandler;
import model.Client;

public class MainActivity extends AppCompatActivity implements  View.OnTouchListener, View.OnClickListener {

    TextView test;
    private ClientDAO DAOClient;
    private List<Client> clientsList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test = (TextView) findViewById(R.id.text);

        DAOClient = new ClientDAO(this);

        //clientDao.open();
        Client client1 = new Client("florian", "labouysse");
        Client client2 = new Client("florian", "francky");

        Log.d("client1log", client1.getLogin());
        Log.d("client 1 : ", client1.toString());
        Log.d("client 2 : ", client2.toString());

        Log.d("Insert", "Inserting ...");
        //DAOClient.ajouter(client1);
        //DAOClient.ajouter(client2);

        clientsList = DAOClient.getALLClient();


        Log.d("taille", String.valueOf(clientsList.size()));


        //Log.d("client", clientDao.selectionner(1).getPrenom());


     //  Log.d("Client1", DAOClient.selectionner(1).toString());
       // test.setText(clientT.getNom());


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
}
