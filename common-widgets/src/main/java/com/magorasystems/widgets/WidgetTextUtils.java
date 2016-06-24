package com.magorasystems.widgets;

import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import java.util.regex.Pattern;

import rx.Observable;
import rx.functions.Func1;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class WidgetTextUtils {

    private WidgetTextUtils() {

    }

    public static Observable<Boolean> validationTextView(final TextView view, final Pattern pattern, final int count) {
        return RxTextView.textChangeEvents(view)
                .compose(validationTextViewTransformer(pattern, count));
    }

    public static Observable.Transformer<TextViewTextChangeEvent, Boolean> validationTextViewTransformer(final Pattern pattern, final int count) {
        return observable -> observable
                .filter(filterByEmpty)
                .map(TextViewTextChangeEvent::text)
                .map(t -> t.toString().trim())
                .map(t -> t.length() >= count && (pattern == null || pattern.matcher(t).matches()));
    }

    public static Observable.Transformer<TextViewTextChangeEvent, Boolean> validationChangeTextViewTransformer(final Pattern pattern, final int count) {
        return observable -> observable
                .compose(validationTextViewTransformer(pattern, count))
                .distinctUntilChanged();
    }


    private static Func1<TextViewTextChangeEvent, Boolean> filterByEmpty = e -> !(e.before() == 0 && e.count() == 0);
}
