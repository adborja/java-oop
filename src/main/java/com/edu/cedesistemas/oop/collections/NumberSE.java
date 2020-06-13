package com.edu.cedesistemas.oop.collections;

import java.util.Objects;

public class NumberSE {
    private final Integer number;
    private final String nameSpanish;
    private final String nameEnglish;
    public NumberSE(Integer number, String nameSpanish, String nameEnglish){
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberSE number1 = (NumberSE) o;
        return number.equals(number1.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
