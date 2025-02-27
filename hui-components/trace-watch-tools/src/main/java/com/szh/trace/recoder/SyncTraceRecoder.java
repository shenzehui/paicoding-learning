package com.szh.trace.recoder;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class SyncTraceRecoder implements ITraceRecoder {
    public static SyncTraceRecoder SYNC_RECODER = new SyncTraceRecoder();

    @Override
    public <T> T sync(Supplier<T> supplier, String name) {
        return supplier.get();
    }

    @Override
    public void sync(Runnable run, String name) {
        run.run();
    }

    @Override
    public <T> CompletableFuture<T> async(Supplier<T> supplier, String name) {
        return CompletableFuture.completedFuture(supplier.get());
    }

    @Override
    public CompletableFuture<Void> async(Runnable run, String name) {
        run.run();
        return CompletableFuture.completedFuture(null);
    }
}
