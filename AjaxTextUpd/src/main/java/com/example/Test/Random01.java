package com.example.Test;

public class Random01 {
    static int count=0;
    static int data[]= new int[100];

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public Random01() {
        count++;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Random01.count = count;
    }
}
