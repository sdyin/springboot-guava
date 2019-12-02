package com.sdyin.guava.demo.test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @Description TODO 计算结果目测有问题
 * @Author liuye
 * @Date 2019/12/2 10:31
 **/
public class ForkJoinTest extends RecursiveTask<Long> {

    private long[] numbers;
    private int from;
    private int to;

    public ForkJoinTest(long[] numbers, int from, int to) {
        this.numbers = numbers;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Long compute() {

        if(to - from < 100){
            long total = 0L;
            for (int i = from ; i < to; i++) {
                total += numbers[i];
            }
            return total;
        }else{
            int middle = (from + to) / 2;
            ForkJoinTest left = new ForkJoinTest(numbers, from, middle);
            ForkJoinTest right = new ForkJoinTest(numbers, middle + 1, to);
//            left.fork();
//            right.fork();
            invokeAll(left,right);
            return left.join() + right.join();
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        long[] array = new long[100000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i+1;
        }
        long a = System.currentTimeMillis();
        ForkJoinTest forkJoin = new ForkJoinTest(array, 0, array.length);
        Long result = pool.invoke(forkJoin);
        long b = System.currentTimeMillis();
        System.out.println("result:" + result + "  time:" + (b - a) );
    }
}
