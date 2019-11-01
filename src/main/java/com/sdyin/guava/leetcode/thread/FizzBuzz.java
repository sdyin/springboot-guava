package com.sdyin.guava.leetcode.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * @Description
 * 请你实现一个有四个线程的多线程版  FizzBuzz， 同一个 FizzBuzz 实例会被如下四个线程使用：
 *
 * 线程A将调用 fizz() 来判断是否能被 3 整除，如果可以，则输出 fizz。
 * 线程B将调用 buzz() 来判断是否能被 5 整除，如果可以，则输出 buzz。
 * 线程C将调用 fizzbuzz() 来判断是否同时能被 3 和 5 整除，如果可以，则输出 fizzbuzz。
 * 线程D将调用 number() 来实现输出既不能被 3 整除也不能被 5 整除的数字。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fizz-buzz-multithreaded
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author liuye
 * @Date 2019/10/30 22:30
 **/
public class FizzBuzz {

    private int n;

    public FizzBuzz(int n) {
        this.n = n;
    }

    private ReentrantLock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    private volatile  int state = 0;

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 3; i <= n; i = i+3) {
            lock.lock();
            while (state != 3){
                condition.await();
            }
            printFizz.run();
            state = 0;
            condition.signalAll();
            lock.unlock();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i = i+5) {
            lock.lock();
            while (state != 5){
                condition.await();
            }
            printBuzz.run();
            state = 0;
            condition.signalAll();
            lock.unlock();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 15; i <= n; i = i+15) {
            lock.lock();
            while (state != 15){
                condition.await();
            }
            printFizzBuzz.run();
            state = 0;
            condition.signalAll();
            lock.unlock();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i<= n;++i){
            lock.lock();
            while(state != 0){
                condition.await();
            }
            if(i % 3 == 0 && i % 5 == 0){
                state = 15;
            }else if( i % 3 == 0 && i % 5 != 0){
                state = 3;
            }else if( i % 3 != 0 && i % 5 == 0){
                state = 5;
            }else{
                printNumber.accept(i);
            }
            condition.signalAll();
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        FizzBuzz fizzBuzz = new FizzBuzz(12);
        ExecutorService es = Executors.newFixedThreadPool(4);
        es.execute(()-> {
            try {
                fizzBuzz.fizz(()-> System.out.println("fizz"));
            } catch (InterruptedException e) {

            }
        });
        es.execute(()-> {
            try {
                fizzBuzz.buzz(()-> System.out.println("buzz"));
            } catch (InterruptedException e) {

            }
        });
        es.execute(()-> {
            try {
                fizzBuzz.fizzbuzz(()-> System.out.println("fizzbuzz"));
            } catch (InterruptedException e) {

            }
        });
        es.execute(()-> {
            try {
                fizzBuzz.number((i)-> System.out.println(i));
            } catch (InterruptedException e) {

            }
        });
        es.shutdown();
    }
}
