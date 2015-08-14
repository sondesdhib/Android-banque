package com.example.florian.android_banque;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import DAO.ClientDAO;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, MenuItem.OnMenuItemClickListener {

    private EditText loginIn, passIn;
    private Button logged, canceled;

    private ClientDAO clientDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        clientDao = new ClientDAO(this);

        loginIn = (EditText) findViewById(R.id.login);
        passIn = (EditText) findViewById(R.id.pass);

        logged = (Button) findViewById(R.id.buttonLog);
        logged.setOnClickListener(this);

        canceled = (Button) findViewById(R.id.buttonCancel);
        canceled.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {


        if (v==logged){
            Log.d("view : " , "logged");
            String login = loginIn.getText().toString();
            String pass = passIn.getText().toString();
            Log.d("login :  ", login);
            Log.d("login :  ", pass);
            boolean passOk = clientDao.checkPassword(login, pass);
            if (!login.isEmpty() || !pass.isEmpty()){
                if(passOk ){
                    Log.d("check", "ok");
                    Intent compte = new Intent(LoginActivity.this, Compte.class);
                    startActivity(compte);
                } else{
                    loginIn.clearComposingText();
                    passIn.clearComposingText();
                }
            }
            else {
                loginIn.clearComposingText();
                passIn.clearComposingText();
            }


        } else if (v==canceled){
            Intent main = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(main);
        }



    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
