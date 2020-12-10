package com.example.allrecipes.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Recetas {
    @PrimaryKey (autoGenerate = true)
            int id;
    String titulo;
    String usuario;
    String imagen;
    int tiempo;
    int valoracion;

    public Recetas(String titulo, String usuario, String imagen, int tiempo, int valoracion) {
        this.titulo = titulo;
        this.usuario = usuario;
        this.imagen = imagen;
        this.tiempo = tiempo;
        this.valoracion = valoracion;
    }
}
