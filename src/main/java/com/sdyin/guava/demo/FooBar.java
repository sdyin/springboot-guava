package com.sdyin.guava.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: liuye
 * @time: 2019/10/26$ 上午9:16$
 */
public class FooBar {
    private int n;
    private volatile Boolean flag = true;


    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                synchronized (this) {
                    while (!flag) {
                        this.wait();
                    }
                    // printFoo.run() outputs "foo". Do not change or remove this line.
                    printFoo.run();
                    flag = false;
                    this.notifyAll();
                }
            }
    }

    public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                synchronized(this) {
                    while (flag) {
                        this.wait();
                    }
                    // printBar.run() outputs "bar". Do not change or remove this line.
                    printBar.run();
                    flag = true;
                    this.notifyAll();
                }
            }

    }

    public static void main(String[] args) {
        FooBar fooBar = new FooBar(5);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(()-> {
            try {
                fooBar.foo(()-> System.out.println("foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(()-> {
            try {
                fooBar.bar(()-> System.out.println("bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();
    }
}
