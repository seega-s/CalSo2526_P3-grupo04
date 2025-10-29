package com.formandera.domain.models;

public class Curso {
    private String nombre;
    private String tematica;   // p.ej. "Informática", "Salud", "Sociales"
    private int nivel;         // 1 = básico, 2 = intermedio, 3 = avanzado
    private int valoracion;    // 1 a 5

    public Curso(String nombre, String tematica, int nivel, int valoracion) {
        this.nombre = nombre;
        this.tematica = tematica;
        this.nivel = nivel;
        this.valoracion = valoracion;
    }

    // --- Getters ---
    public String getNombre() {
        return nombre;
    }

    public String getTematica() {
        return tematica;
    }

    public int getNivel() {
        return nivel;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    @Override
    public String toString() {
        return nombre + " (" + tematica + ", nivel " + nivel + ", valoración " + valoracion + ")";
    }
}

