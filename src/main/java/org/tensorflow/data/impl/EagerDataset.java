package org.tensorflow.data.impl;

import org.tensorflow.EagerSession;

public abstract class EagerDataset<U> extends Dataset<U> implements Iterable<U> {
    public EagerDataset(EagerSession session) {
        super(session);
    }
}
