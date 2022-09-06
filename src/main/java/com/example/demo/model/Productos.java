package com.example.demo.model;

import com.opencsv.bean.CsvBindByName;

public class Productos {
    @CsvBindByName(column = "codigo")
    public String codigo;
    @CsvBindByName(column = "descripcion")
    public String descripcion;
    public String formato;

    public Productos(String codigo, String descripcion, String formato) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.formato = formato;
    }

    public Productos() {
    }


    @Override
    public String toString() {
        return "Productos{" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
