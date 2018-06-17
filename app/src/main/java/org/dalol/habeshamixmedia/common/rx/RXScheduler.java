package org.dalol.habeshamixmedia.common.rx;

import java.util.concurrent.Executor;

import io.reactivex.Scheduler;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 15:39.
 */
public interface RXScheduler {

    Scheduler main();

    Scheduler io();

    Scheduler computation();

    Scheduler trampoline();

    Scheduler newThread();

    Scheduler single();

    Scheduler from(Executor executor);

    void start();

    void shutdown();
}
