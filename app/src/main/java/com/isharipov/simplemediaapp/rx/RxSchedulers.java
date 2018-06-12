package com.isharipov.simplemediaapp.rx;

import io.reactivex.Scheduler;

/**
 * 24.05.2018.
 */
public interface RxSchedulers {
    Scheduler runOnBackground();

    Scheduler io();

    Scheduler compute();

    Scheduler androidThread();

    Scheduler internet();
}
