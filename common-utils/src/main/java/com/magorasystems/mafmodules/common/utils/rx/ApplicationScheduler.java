package com.magorasystems.mafmodules.common.utils.rx;


import com.magorasystems.mafmodules.common.utils.SchedulersUtils;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Valentin S. Bolkonsky
 *         Developed by Magora Team (magora-systems.com). 2015.
 */
public class ApplicationScheduler implements SchedulersUtils.CoreScheduler {

    @Override
    public Scheduler mainThread() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler backgroundThread() {
        return Schedulers.io();
    }
}
