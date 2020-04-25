package utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import struct.ListNode;

class Utils {
    static void println(int[] array) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
                stringBuilder.append(array[i]);
            } else {
                stringBuilder.append(array[i]).append(", ");
            }
        }
        stringBuilder.append("]");
        System.out.println(stringBuilder.toString());
    }

    static void println(int[][] matrix) {
        for (int[] array : matrix) {
            println(array);
        }
    }

    static <T> void println(List<T> list) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                stringBuilder.append(list.get(i).toString());
            } else {
                stringBuilder.append(list.get(i).toString()).append(", ");
            }
        }
        stringBuilder.append("]");
        System.out.println(stringBuilder.toString());
    }

    static LinkedList<Integer> newRandomStack(int length) {
        return newRandomStack(length, 0, 100);
    }

    private static LinkedList<Integer> newRandomStack(int length, int minValue, int maxValue) {
        int i = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        while (i < length) {
            double randomValue = Math.random();
            int value = (int) (randomValue * (maxValue - minValue) + minValue);
            stack.push(value);
            i++;
        }
        return stack;
    }

    public static ListNode createLinkedList(int[] array) {
        ListNode preHead = new ListNode(0);
        ListNode preNode = preHead;
        for (int value : array) {
            preNode.next = new ListNode(value);
            preNode = preNode.next;
        }
        return preHead.next;
    }

    public static int[][] createMatrix(String text) {
        List<int[]> list = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder(text);
        stringBuilder.deleteCharAt(0);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        int start = 0;
        int end = stringBuilder.indexOf("]");
        while (start != -1) {
            String subString = stringBuilder.substring(start + 1, end);
            String[] textArray = subString.split(",");
            int[] intArray = new int[textArray.length];
            for (int i = 0; i < textArray.length; i++) {
                textArray[i] = textArray[i].trim();
                intArray[i] = Integer.parseInt(textArray[i]);
            }
            list.add(intArray);
            stringBuilder.delete(start, end + 1);
            start = stringBuilder.indexOf("[");
            end = stringBuilder.indexOf("]");
        }
        int[][] result = new int[list.size()][];
        list.toArray(result);
        return result;
    }
}
