package com.edu.cedesistemas.oop.collections;

import java.util.Objects;

public class Employee {

    private String name;
    private String Id;

    public Employee(String name, String Id){
        this.name = name;
        this.Id = Id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setId(String Id){
        this.Id = Id;
    }

    public String getName(){
        return name;
    }

    public String getId(){
        return Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Id.equals(employee.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }
}
