package com.edu.cedesistemas.oop.model;

import java.util.Objects;

public class Employee {
    private final String id;
    private final String name;

<<<<<<< HEAD
    public Employee(final String id, final String name)
    {
        this.id=id;
        this.name=name;
    }
    public String getName(){
        return name;
    }

    public String getId(){
=======
    public Employee(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
>>>>>>> 362d7d74e76413dced2ba44ba6dae5d4c7f9ab4b
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
<<<<<<< HEAD
        return Objects.equals(id, employee.id);
=======
        return id.equals(employee.id);
>>>>>>> 362d7d74e76413dced2ba44ba6dae5d4c7f9ab4b
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
