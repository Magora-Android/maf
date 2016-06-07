package com.magorasystems.mafmodules.common.utils;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;

/**
 * @author Valentin S. Bolkonsky.
 *         Proud to Code for Magora Systems/magora-systems.com/magora-systems.ru
 */
public class SchedulersUtils {

    private SchedulersUtils() {

    }

    public static <T> Observable.Transformer<T, T> applySchedulers(final CoreScheduler coreScheduler) {
        return observable -> observable.subscribeOn(coreScheduler.backgroundThread())
                .observeOn(coreScheduler.mainThread());
    }

    public static <F, T> Func1<List<F>, Collection<T>> applyMapper(final Func1<F, T> mapper) {
        return list -> {
            if (null == list) {
                return Collections.emptyList();
            }
            return Lists.newArrayList(Collections2.transform(list, mapper::call));
        };
    }


    /**
     * We use a scheduler to make testing our presenters easier
     */
    public interface CoreScheduler {

        Scheduler mainThread();

        Scheduler backgroundThread();


    }
}
