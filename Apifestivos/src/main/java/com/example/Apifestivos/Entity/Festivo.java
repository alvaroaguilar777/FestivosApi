package com.example.Apifestivos.Entity;

import java.time.Month;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity

public class Festivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private int dia;
    private int mes;
    private int diasPascua;

    @ManyToOne
    @JoinColumn(name = "idTipo", nullable = false)
    private Tipo tipo;

    public Festivo(Object nombre2, int dayOfMonth, int monthValue, int idTipo, long diasPascua2) {

    }

    public int getIdTipo() {

        throw new UnsupportedOperationException("Unimplemented method 'getIdTipo'");
    }

    public long getDiasPascua() {

        throw new UnsupportedOperationException("Unimplemented method 'getDiasPascua'");
    }

    public Month getMes() {

        throw new UnsupportedOperationException("Unimplemented method 'getMes'");
    }

    public int getDia() {

        throw new UnsupportedOperationException("Unimplemented method 'getDia'");
    }

    public Object getNombre() {

        throw new UnsupportedOperationException("Unimplemented method 'getNombre'");
    }

}