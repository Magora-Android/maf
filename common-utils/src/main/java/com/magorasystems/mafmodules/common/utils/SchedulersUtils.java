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

    /**
     * @param coreScheduler instance of CoreScheduler
     * @param <T>           first and result arguments of Observable for Transformer
     * @return Observable.Transformer that subscribeOn {@link CoreScheduler#backgroundThread()} and observeOn {@link CoreScheduler#mainThread()}
     */
    public static <T> Observable.Transformer<T, T> applySchedulers(final CoreScheduler coreScheduler) {
        return observable -> observable.subscribeOn(coreScheduler.backgroundThread())
                .observeOn(coreScheduler.mainThread());
    }

    /**
     * @param mapper a function to apply to all elements of {@code list}
     * @param <F>    first argument for Func1 (type of List)
     * @param <T>    result type (type of Collection)
     * @return a {@code Func1} that applies {@code mapper} to each element of {@code list}. <br>
     * If {@code list} is null, that collection be empty.
     */
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
