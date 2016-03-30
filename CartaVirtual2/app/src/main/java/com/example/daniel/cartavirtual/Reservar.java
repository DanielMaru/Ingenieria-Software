package com.example.daniel.cartavirtual;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Reservar extends AppCompatActivity {

    SQLiteDatabase db;
    EditText editUsuario;
    EditText editContraseña;


    Spinner spiDIa;
    Spinner spiMes;
    Spinner spiHora;

    ArrayList<String> adapSpiDia=new ArrayList<String>();
    ArrayList<String> adapSpiMes=new ArrayList<String>();
    ArrayList<String> adapSpiHora=new ArrayList<String>();

    Cursor c;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar);
        db = ((gestionBD)getApplication()).eDB;
        editUsuario= (EditText)findViewById(R.id.editTextUsuario);
        editContraseña= (EditText)findViewById(R.id.editTextContraseña);


        spiDIa=(Spinner)findViewById(R.id.spiDia);
        spiMes=(Spinner)findViewById(R.id.spiMes);
        spiHora=(Spinner)findViewById(R.id.spHora);

        for (int i=1;i<=31;i++)
        {
            adapSpiDia.add(""+i);
        }
        for (int i=1;i<=12;i++)
        {
            adapSpiMes.add(""+i);
        }
        for (int i=1;i<=24;i++)
        {
            adapSpiHora.add(""+i);
        }
        ArrayAdapter<String> adaptadorDia = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, adapSpiDia);
        ArrayAdapter<String> adaptadorMes = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, adapSpiMes);
        ArrayAdapter<String> adaptadorHora =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, adapSpiHora);

        spiDIa.setAdapter(adaptadorDia);
        spiMes.setAdapter(adaptadorMes);
        spiHora.setAdapter(adaptadorHora);

        Intent intent = getIntent();
        if(getIntent().getExtras() != null)
        {
            Bundle bundle = getIntent().getExtras();
            String usuario = bundle.getString("Usuario");
            String contraseña = bundle.getString("Contraseña");

            editUsuario.setText(usuario);
            editContraseña.setText(contraseña);
        }
    }
    public void botonReservar(View v)
    {
        c=db.rawQuery("Select count(*) from usuarios where nombre='"+editUsuario.getText().toString()+"' and contraseña='"+editContraseña.getText().toString()+"'",null);
        if  ( c !=  null  && c . moveToFirst ())
        {
            if(c.getString(0).equals("0"))
            {
                new AlertDialog.Builder(this)
                        .setTitle("No estas registrado")
                        .setMessage("¿Quieres Registrarse?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(Reservar.this,Registrarse.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
            else
            {
                insertarReserva(editUsuario.getText().toString(), spiDIa.getSelectedItem().toString(), spiMes.getSelectedItem().toString(), spiHora.getSelectedItem().toString());
                new AlertDialog.Builder(this)
                        .setTitle("Reserva")
                        .setMessage(editUsuario.getText().toString()+"\nhaz reservado Correctamente\npara el dia: "+spiDIa.getSelectedItem().toString()+"\ndel mes: "+spiMes.getSelectedItem().toString()+" del año 2016\na las: "+spiHora.getSelectedItem().toString()+" Horas")
                        .setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(Reservar.this,Home.class);
                                startActivity(intent);
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


            }
        }
    }
    public void botonRegistrar(View v)
    {
        Intent intent = new Intent(this, Registrarse.class);
        startActivity(intent);
    }
    public void insertarReserva(String usuario, String dia,String mes,String hora)
    {
        String finalFecha="2016-"+mes+"-"+dia+" "+hora+":00:00";
        db.execSQL("insert into reserva values(null,'"+usuario+"','"+finalFecha+"')");
    }
}
