package com.edu.cedesistemas.oop.collections;

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

}
