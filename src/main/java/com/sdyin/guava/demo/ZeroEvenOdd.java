package com.sdyin.guava.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @Description 三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用odd()。正确的输出为 "0102"。
 * 输入：n = 5
 * 输出："0102030405"
 * @Author liuye
 * @Date 2019/10/29 9:48
 **/
public class ZeroEvenOdd {
    private int n;
    private Semaphore s,s1,s2;

    public ZeroEvenOdd(int n) {
        this.n = n;
        s = new Semaphore(1);
        s1 = new Semaphore(0);
        s2 = new Semaphore(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1;i <= n;i++) {
            s.acquire();
            //执行函数式接口,入参提供
            printNumber.accept(0);
            if((i&1) == 0) {
                s1.release();
            } else {
                s2.release();
            }
        }

    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i = 2;i <= n;i+=2) {
            s1.acquire();
            printNumber.accept(i);
            s.release();
        }

    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1;i <= n;i+=2) {
            s2.acquire();
            printNumber.accept(i);
            s.release();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(5);
        executorService.execute(()-> {
            try {
                zeroEvenOdd.zero((i)-> System.out.println(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(()-> {
            try {
                zeroEvenOdd.even((i)-> System.out.println(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(()-> {
            try {
                zeroEvenOdd.odd((i)-> System.out.println(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
    }

}
