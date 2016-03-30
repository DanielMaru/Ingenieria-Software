package com.example.daniel.cartavirtual;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends Activity {

    static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        gestionBD gestion=new gestionBD();
        db=gestion.getDB(getApplicationContext());
    }

    public void carta(View v) {
        Intent intent = new Intent(this, Carta.class);
        startActivity(intent);
    }

    public void reserva(View v) {
        Intent intent = new Intent(this, Reservar.class);
        startActivity(intent);
    }


    public void mapa(View v) {
        Intent intent = new Intent(this, Mapa.class);
        startActivity(intent);
    }
    
}
