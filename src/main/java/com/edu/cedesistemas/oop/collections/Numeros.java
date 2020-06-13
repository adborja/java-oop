package com.edu.cedesistemas.oop.collections;

import java.util.Objects;

public class Numeros {

           private Integer numero;
           private String español;
           private String ingles;

           public Numeros(Integer number, String nameSpanish, String nameEnglish) {
               this.numero = numero;
               this.español = español;
               this.ingles = ingles;
           }

           public Integer getNumber () {
               return numero;
           }

           public String getNameSpanish () {
               return español;
           }

           public String getNameEnglish () {
               return ingles;
           }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Numeros numbers = (Numeros) o;
        return numero.equals(numbers.numero) &&
                Objects.equals(español, numbers.español) &&
                Objects.equals(ingles, numbers.ingles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

}
