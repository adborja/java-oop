package com.edu.cedesistemas.oop.collections;

public class Number {
    private final Integer number;
    private final String nameSpanish;
    private final String nameEnglish;
    public Number(Integer number, String nameSpanish, String nameEnglish){
        this.number = number;
        this.nameSpanish = nameSpanish;
        this.nameEnglish =nameEnglish;
    }

    public Integer getNumber() {
        return number;
    }

    public String getNameSpanish() {
        return nameSpanish;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }
}
