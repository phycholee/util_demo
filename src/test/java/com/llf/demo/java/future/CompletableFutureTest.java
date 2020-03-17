package com.llf.demo.java.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2019/4/10 17:23
 */
public class CompletableFutureTest {


    @Test
    public void test1() throws Exception {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");

        System.out.println(future.get());
    }

    public static void main(String[] args) throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
        String join = CompletableFuture.supplyAsync(() -> "Hello").thenApplyAsync(v -> v + "World").join();
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 100).thenApplyAsync(v -> v + 200);

        System.out.println(future.get());
        System.out.println(join);
        System.out.println(future1.get());

    }

}
