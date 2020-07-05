package com.edu.cedesistemas.oop.tareascollections;

import java.util.Objects;

public class Employee {
    private String ID;
    private String nombre;

    public Employee(String ID, String nombre) {
        this.ID = ID;
        this.nombre = nombre;
    }

    public String getID() {
        return ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return ID.equals(employee.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
