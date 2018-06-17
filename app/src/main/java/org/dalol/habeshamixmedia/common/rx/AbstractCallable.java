package org.dalol.habeshamixmedia.common.rx;

import java.util.concurrent.Callable;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Mon, 11/06/2018 at 10:45.
 */
public abstract class AbstractCallable<S, O> implements Callable<O> {

    private final S subject;

    protected AbstractCallable(S subject) {
        this.subject = subject;
    }

    @Override
    public O call() throws Exception {
        return call(this.subject);
    }

    protected abstract O call(S subject) throws Exception ;
}
