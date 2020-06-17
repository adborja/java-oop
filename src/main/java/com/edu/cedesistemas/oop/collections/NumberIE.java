package com.edu.cedesistemas.oop.collections;

import java.util.Objects;

public class NumberIE {

    private int number;
    private String nameSpanish;
    private String nameEnglish;

    public NumberIE(int number, String nameSpanish, String nameEnglish){
        this.number = number;
        this.nameSpanish = nameSpanish;
        this.nameEnglish = nameEnglish;
    }

    public void setNumber(int number){
        this.number = number;
    }

    public void setNameSpanish(String nameSpanish) {
        this.nameSpanish = nameSpanish;
    }

    public void setNameEnglish(String nameEnglish) {
        this.nameEnglish = nameEnglish;
    }

    public int getNumber(){
        return number;
    }

    public String getNameSpanish() {
        return nameSpanish;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public boolean equals(Object obj){
        if( obj instanceof NumberIE){
            //Casting para convertir el obj del tipo Object a un obj del tipo NumberName
            NumberIE numberIE1 = (NumberIE) obj;
            if(this.number == numberIE1.number){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
