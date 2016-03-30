package com.example.daniel.cartavirtual;

public class Producto
{
    String nombre;
    String categoria;
    String precio;

    public String getCategoria() {
        return categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public Producto(String nombre, String categoria, String precio) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }
}
