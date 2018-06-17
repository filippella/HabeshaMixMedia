package org.dalol.habeshamixmedia.common.rx;

import java.util.concurrent.Executor;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 15:41.
 */
public class DefaultRXScheduler implements RXScheduler {

    @Override
    public Scheduler main() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler trampoline() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler newThread() {
        return Schedulers.newThread();
    }

    @Override
    public Scheduler single() {
        return Schedulers.single();
    }

    @Override
    public Scheduler from(Executor executor) {
        return Schedulers.from(executor);
    }

    @Override
    public void start() {
        Schedulers.start();
    }

    @Override
    public void shutdown() {
        Schedulers.shutdown();
    }
}
