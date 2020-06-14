package com.edu.cedesistemas.oop.arrays;

import com.sun.javafx.css.StyleCacheEntry;

//lista de parametros tipo que pueden ser genericos
public class OrderedPair<K, V> implements Pair<K, V>{
private K key;
private V value;
public OrderedPair(K key, V value){
   this.key = key;
   this.value = value;

    System.out.println("Prueba Oscar el valor de K es: " + key + " y el valor de V es: " + value);

}

  @Override
  public K getKey() {
      return key;
  }

  @Override
  public V getValue() {
      return value;
  }
}
