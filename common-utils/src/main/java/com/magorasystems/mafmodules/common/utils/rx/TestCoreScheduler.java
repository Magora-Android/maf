package com.magorasystems.mafmodules.common.utils.rx;


import com.magorasystems.mafmodules.common.utils.SchedulersUtils;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * @author Valentin S. Bolkonsky
 *         Developed by Magora Team (magora-systems.com). 2015.
 */
public class TestCoreScheduler implements SchedulersUtils.CoreScheduler {

    @Override
    public Scheduler mainThread() {
        return Schedulers.immediate();
    }

    @Override
    public Scheduler backgroundThread() {
        return Schedulers.immediate();
    }
}
