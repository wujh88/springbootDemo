package com.sinotech.settle.demo.pool;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class ForkJoinPoolTask {
    public static void main(String[] args) throws Exception {
        int[] arr = new int[1000000];
        Random random = new Random();
        int total =0;
        //初始化100个数组元素
        for(int i=0,len = arr.length;i<len;i++){
            int temp = random.nextInt(200);
            //对数组元素赋值，并将数组元素的值添加到sum总和中
            arr[i]=temp;
        }
        long start = System.currentTimeMillis();
        for(int j=0, len1 = arr.length; j<len1;j++){
            total += arr[j];
        }
        long end = System.currentTimeMillis();
        System.out.println("初始化数组总和："+total+"; 耗时/ms："+(end-start));
        long start1 = System.currentTimeMillis();
        SumTask task = new SumTask(arr, 0, arr.length);
//        创建一个通用池，这个是jdk1.8提供的功能
        ForkJoinPool pool = ForkJoinPool.commonPool();
//        提交分解的SumTask 任务
        Future<Integer> future = pool.submit(task);
        long end1 = System.currentTimeMillis();
        System.out.println("多线程执行结果："+future.get()+"; 耗时/ms："+(end1-start1));
        //关闭线程池
        pool.shutdown();
    }
}
