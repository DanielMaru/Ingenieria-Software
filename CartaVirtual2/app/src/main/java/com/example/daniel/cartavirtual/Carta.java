package com.example.daniel.cartavirtual;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Carta extends Activity {

    ListView lista;
    ArrayAdapter<String> itemsAdapter;
    ArrayList<Producto> bebidas= new ArrayList<Producto>();
    ArrayList<Producto> entradas= new ArrayList<Producto>();
    ArrayList<Producto> fuertes= new ArrayList<Producto>();
    SQLiteDatabase db;

    Cursor c;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carta);

        db = ((gestionBD)getApplication()).eDB;


        lista=(ListView)findViewById(R.id.listView);

        llenarVectores();

        rellenarListaProducto(bebidas);






    }
    public void llenarVectores()
    {

        c=db.rawQuery("SELECT * FROM productos ",null);
        if  ( c !=  null  && c . moveToFirst ())
        {

            do{

                if(c.getString(2).equals("bebida"))
                {
                    bebidas.add(new Producto(c.getString(1),c.getString(2),c.getString(3)));
                }
                else if(c.getString(2).equals("fuerte"))
                {
                    fuertes.add(new Producto(c.getString(1),c.getString(2),c.getString(3)));
                }
                else
                {
                    entradas.add(new Producto(c.getString(1),c.getString(2),c.getString(3)));
                }


            }while(c.moveToNext());



        }


    }
    public void clickBebidas(View v)
    {
        rellenarListaProducto(bebidas);
    }
    public void clickEntradas(View v)
    {
        rellenarListaProducto(entradas);
    }

    public void clickFuertes(View v)
    {
        rellenarListaProducto(fuertes);
    }

    public void reserva(View v) {
        Intent intent = new Intent(this, Reservar.class);
        startActivity(intent);
    }

    private void rellenarListaProducto( ArrayList<Producto> categoria)
    {
      ArrayAdapter<Producto> adaptador = new MiProductoAdaptador(categoria);
      lista.setAdapter(adaptador);
    }






    public class MiProductoAdaptador extends ArrayAdapter<Producto>
    {

        private ArrayList<Producto> categoria;
        public MiProductoAdaptador( ArrayList<Producto> categoria)
        {

            super(Carta.this,R.layout.plantilla,categoria);
            this.categoria=categoria;



        }


        public View getView(int position, View convertView, ViewGroup parent)
        {
            View vistaProducto= convertView;
            if (vistaProducto==null)
            {
                vistaProducto=getLayoutInflater().inflate(R.layout.plantilla,parent,false);
            }

            Producto currentProducto= categoria.get(position);

            /****** IMPORTANTE SI SE QUIERE CAMBIAR EL COLOR DE LA LISTA
             *
             *
             LinearLayout llLayout=(LinearLayout)vistafiesta.findViewById(R.id.llLayout);
             if(currentFiesta.getAsistentes()==1)
             {
             llLayout.setBackgroundColor(Color.parseColor("#818ADB"));
             }
             else
             {
             llLayout.setBackgroundColor(Color.parseColor("#81DBA2"));
             }
             *****/
            ImageView imagen=(ImageView)vistaProducto.findViewById(R.id.imagen);
            if(currentProducto.getCategoria().equals("fuerte"))
            {
                imagen.setBackgroundResource(R.mipmap.fuerte);
            }
            else if(currentProducto.getCategoria().equals("entrada"))
            {
                imagen.setBackgroundResource(R.mipmap.entrada);
            }
            else
            {
                imagen.setBackgroundResource(R.mipmap.bebida);
            }

            TextView Nombre= (TextView) vistaProducto.findViewById(R.id.nombre);
            Nombre.setText(currentProducto.getNombre());
            TextView Precio= (TextView) vistaProducto.findViewById(R.id.precio);
            Precio.setText(currentProducto.getPrecio());


            return vistaProducto;

        }
    }


}
