package com.example.daniel.cartavirtual;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class gestionBD extends Application
{
    static SQLiteDatabase eDB;
    public SQLiteDatabase getDB(Context context)
    {
            SQLiteOpenHelper m_OpenHelper = new SQLiteOpenHelper(context,"BDPrueba",null,1) {
            String sql="CREATE TABLE productos (id int,nombre varchar(200),categoria varchar(50),precio int)";
            String sql2="INSERT INTO productos VALUES (1,'Carne de res','fuerte',15000),(2,'lassagna','fuerte',12000),(3,'Casuela','fuerte',9000),(4,'Frijoles Asoleados','fuerte',20000),(5,'Camarones','fuerte',12000)" +
                                                     ",(6,'Ensalada','entrada',2000),(7,'Postre Vainilla','entrada',3000),(8,'Helado de Caramelo','fuerte',20000),(9,'Brawnie','entrada',3500),(10,'Caramelo de Chocolate','entrada',5000),(11,'Cocktail','entrada',2000)" +
                                                     ",(12,'Cerveza','bebida',2000),(13,'Jugo de Mora','bebida',3000),(14,'Michelada','bebida',5000),(15,'Vino','bebida',7000),(16,'Coca-Cola','bebida',2000)";
            String sql3="CREATE TABLE usuarios (id int primary key,nombre varchar(200),contraseña varchar(10),cedula varchar(50),telefono varchar(20))";
            String sql4="CREATE TABLE reserva (id int primary key,nombre varchar(200),fecha datetime)";

            public void onCreate(SQLiteDatabase db)
            {
                db.execSQL(sql);
                db.execSQL(sql2);
                db.execSQL(sql3);
                db.execSQL(sql4);

            }


            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        };
        SQLiteDatabase GDB = m_OpenHelper.getWritableDatabase();
        eDB=GDB;

        return GDB;
    }

}
