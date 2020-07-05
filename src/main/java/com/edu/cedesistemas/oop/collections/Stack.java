package com.edu.cedesistemas.oop.collections;

// Lesson 3 -- Generics
public class Stack<E> implements Comparable<E> {
    private final int size;
    private int top;

    private final E[] elements;

    public Stack() {
        this(10);
    }

    @SuppressWarnings("unchecked")
    public Stack(int capacity) {
        size = capacity > 0 ? capacity : 10;
        top = -1;
        elements = (E[]) new Object[size];
    }

    public int size() {
        return size;
    }

    public void push(E value) {
        if (top == size - 1) {
            throw new RuntimeException("stack is full");
        }
        elements[++top] = value;
    }

    public E pop() {
        if (top == -1) {
            throw new RuntimeException("stack is empty");
        }
        return elements[top--];
    }

    @Override
    public int compareTo(E e) {
        return 0;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(10);

        System.out.println("pop: " + stack.pop());
        System.out.println("pop: " + stack.pop());

        Stack<String> stack2 = new Stack<>(15);
        stack2.push("hola");
        stack2.push("mundo");
    }
}