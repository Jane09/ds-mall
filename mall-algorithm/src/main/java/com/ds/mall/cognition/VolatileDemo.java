package com.ds.mall.cognition;

public class VolatileDemo {
    private static /*volatile*/ int n3 = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("change");
            n3 = 1;
        }).start();
        while (n3 == 0) {
//            System.out.println("hello"); //加了之后能够答应end
        }
        System.out.println("end");
    }
}
