package com.edu.cedesistemas.oop.collections;

import java.util.Objects;

public class Employee {

    private String id;
    private String nombre;

    public Employee(String ID, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getID() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setID(String ID) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id.equals(employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }
}
