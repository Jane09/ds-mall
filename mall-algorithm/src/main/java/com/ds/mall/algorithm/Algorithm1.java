package com.ds.mall.algorithm;

import java.time.Instant;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Algorithm1 {

    private static final int MAX = 10000000;

    public static void main(String[] args) {
        System.out.println(next(10));
        System.out.println(next(10,11));
        method1();
        method2();
    }


    private static void method1(){
        long start = Instant.now().toEpochMilli();
        for(int i=1;i<=MAX;i++){
//            System.out.println(next(i,i+1));
            next(i,i+1);
        }
        long end = Instant.now().toEpochMilli();
        System.out.println("耗时："+(end-start)+" 毫秒");
    }

    private static void method2(){
        long start = Instant.now().toEpochMilli();
        int value = 10000000;
        int[] list = new int[value];
        for (int j = 1; j <= value; ++j) {
            list[j-1] = j;
        }
        int index;
        int count = 0;
        int tmp;
        while (value > 0) {
            index = next(value);
            tmp = list[count + index];
            list[count + index] = list[count];
            list[count] = tmp;
            ++count;
            --value;
        }
        long end = Instant.now().toEpochMilli();
        System.out.println("耗时："+(end-start)+" 毫秒");
    }

    private static int next(int value) {
        return ThreadLocalRandom.current().nextInt(value);
    }

    /**
     * [origin,bound)
     */
    private static int next(int origin,int bound) {
        return ThreadLocalRandom.current().nextInt(origin,bound);
    }
}
