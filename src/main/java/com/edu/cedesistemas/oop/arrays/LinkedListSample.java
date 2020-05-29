package com.edu.cedesistemas.oop.arrays;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListSample {
    public static void sample1(){
        LinkedList<Integer> al=new LinkedList<Integer>();
        al.add(1);
        al.add(2);
        al.add(3);
        al.add(4);
        al.add(5);

        Iterator<Integer> itr=al.iterator();

        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }

    public static void sample2() {
        LinkedList<Integer> ll=new LinkedList<Integer>();

        System.out.println("Initial list of elements: " + ll);

        ll.add(4);
        ll.add(7);
        ll.add(3);

        System.out.println("After invoking add(E e) method: " + ll);

        //Adding an element at the specific position
        ll.add(1, 9);
        System.out.println("After invoking add(int index, E element) method: " + ll);

        LinkedList<Integer> ll2 = new LinkedList<Integer>();
        ll2.add(22);
        ll2.add(42);

        //Adding second list elements to the first list
        ll.addAll(ll2);

        System.out.println("After invoking addAll(Collection<? extends E> c) method: " + ll);

        LinkedList<Integer> ll3 = new LinkedList<Integer>();
        ll3.add(41);
        ll3.add(98);

        //Adding second list elements to the first list at specific position
        ll.addAll(1, ll3);
        System.out.println("After invoking addAll(int index, Collection<? extends E> c) method: " + ll);

        //Adding an element at the first position
        ll.addFirst(19);
        System.out.println("After invoking addFirst(E e) method: " + ll);

        //Adding an element at the last position
        ll.addLast(78);
        System.out.println("After invoking addLast(E e) method: " + ll);
    }

    public static void sample3() {
        LinkedList<Integer> ll=new LinkedList<Integer>();
        ll.add(14);
        ll.add(16);
        ll.add(75);
        ll.add(24);
        ll.add(16);
        ll.add(33);
        ll.add(18);
        ll.add(5);
        ll.add(97);
        ll.add(11);
        ll.add(18);
        ll.add(54);
        System.out.println("Initial list of elements: " + ll);

        //Removing specific element from arraylist
        ll.remove(75);
        System.out.println("After invoking remove(object) method: " + ll);

        //Removing element on the basis of specific position
        ll.remove(0);
        System.out.println("After invoking remove(index) method: " + ll);

        LinkedList<Integer> ll2 = new LinkedList<Integer>();
        ll2.add(36);
        ll2.add(12);

        // Adding new elements to arraylist
        ll.addAll(ll2);
        System.out.println("Updated list : " + ll);

        //Removing all the new elements from arraylist
        ll.removeAll(ll2);
        System.out.println("After invoking removeAll() method: " + ll);

        //Removing first element from the list
        ll.removeFirst();
        System.out.println("After invoking removeFirst() method: " + ll);

        //Removing first element from the list
        ll.removeLast();
        System.out.println("After invoking removeLast() method: " + ll);

        //Removing first occurrence of element from the list
        ll.removeFirstOccurrence(18);
        System.out.println("After invoking removeFirstOccurrence() method: " + ll);

        //Removing last occurrence of element from the list
        ll.removeLastOccurrence(16);
        System.out.println("After invoking removeLastOccurrence() method: " + ll);

        //Removing all the elements available in the list
        ll.clear();
        System.out.println("After invoking clear() method: " + ll);
    }

    public static void sample4() {
        LinkedList<Integer> ll=new LinkedList<Integer>();
        ll.add(1);
        ll.add(2);
        ll.add(3);

        //Traversing the list of elements in reverse order
        Iterator i = ll.descendingIterator();
        while(i.hasNext()) {
            System.out.println(i.next());
        }
    }
}
