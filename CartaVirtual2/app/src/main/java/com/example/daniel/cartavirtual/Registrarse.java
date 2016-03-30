package com.example.daniel.cartavirtual;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registrarse extends AppCompatActivity {
    SQLiteDatabase db;
    EditText editUsuario;
    EditText editContraseña;
    EditText editCedula;
    EditText editTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        db = ((gestionBD)getApplication()).eDB;
        editUsuario= (EditText)findViewById(R.id.editTextNombre);
        editContraseña= (EditText)findViewById(R.id.editTextContraseña);
        editCedula= (EditText)findViewById(R.id.editTextCedula);
        editTelefono= (EditText)findViewById(R.id.editTextTelefono);

    }
    public void botonInsertar(View v)
    {
        insertar(editUsuario.getText().toString(),editContraseña.getText().toString(),editCedula.getText().toString(),editTelefono.getText().toString());
    }
    public void insertar(final String nombre, final String contraseña, String Cedula, String Telefono)
    {
        db.execSQL("insert into usuarios values(NULL,'" + nombre + "','" + contraseña + "','" + Cedula + "','" + Telefono + "')");


        new AlertDialog.Builder(this)
                .setTitle("Registro")
                .setMessage("Te haz registrado Correctamente")
                .setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Registrarse.this,Reservar.class);
                        intent.putExtra("Usuario",nombre);
                        intent.putExtra("Contraseña",contraseña);
                        startActivity(intent);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }
}
