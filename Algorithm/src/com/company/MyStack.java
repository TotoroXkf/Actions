package com.company;

import java.util.LinkedList;

public class MyStack {
    private LinkedList<Integer> dataStack = new LinkedList<>();
    private LinkedList<Integer> minStack = new LinkedList<>();

    public int pop() {
        if (dataStack.isEmpty()) {
            return Integer.MIN_VALUE;
        }
        int value = dataStack.getLast();
        dataStack.removeLast();
        if (minStack.isEmpty()) {
            return Integer.MIN_VALUE;
        }
        if (value == minStack.getLast()) {
            minStack.removeLast();
        }
        return value;

    }

    public int push() {

    }

    public int getMin() {

    }
}
