package com.example;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

@QuarkusMain
public class Main {
    public static void main(String... args) {
        Quarkus.run(MyApp.class, args);
    }

    public static class MyApp implements QuarkusApplication {

        @Inject
        TransactionService transactionService;

        @Override
        public int run(String... args) throws Exception {
            System.out.println("Do startup logic here");
            test();
            Quarkus.waitForExit();
            return 0;
        }

        private void test() {
            int n = 100;
            AtomicInteger atomicInteger = new AtomicInteger(0);
            AtomicLong sum = new AtomicLong(0);
            ExecutorService executorService = Executors.newFixedThreadPool(n);
            List<Future<Long>> results = IntStream.rangeClosed(1, n)
                    .boxed()
                    .map(i -> executorService.submit(() -> {
                        var a = System.currentTimeMillis();
                        var res = transactionService.callUnpaidTransactionsFunction();
                        var end = (System.currentTimeMillis() - a);
                        System.out.println("execution " + atomicInteger.incrementAndGet() + ": " + end);
                        sum.addAndGet(end);
                        return res;
                    }))
                    .toList();
            ;
            waitForAllFutures(results, sum, n);
            executorService.shutdown();

        }

        public static void waitForAllFutures(List<Future<Long>> futures, AtomicLong sum, int n) {
            for (Future<Long> future : futures) {
                try {
                    // Wait for the result and print it
                    var res = future.get();
//                    System.out.println("Result: " + res);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("AVERAGE: "+ sum.get()/n);
        }
    }
}
