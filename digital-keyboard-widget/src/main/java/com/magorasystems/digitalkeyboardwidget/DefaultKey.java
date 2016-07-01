package com.magorasystems.digitalkeyboardwidget;

import android.support.annotation.ColorInt;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

/**
 * Created by Serega on 24.08.2015.
 */
public class DefaultKey {
    public static final int EMPTY = Integer.MAX_VALUE;
    public final int primaryKeyCode;
    public final int secondaryKeyCode;
    public final String primaryOne;
    public final String primaryTwo;
    public final String secondaryOne;
    public final String secondaryTwo;
    private int drawableRes = EMPTY;
    private int primaryColor = EMPTY;
    private int secondaryColor = EMPTY;
    private int layoutResId = EMPTY;
    private int keyBackgroundColor = EMPTY;
    private boolean activeInPrimaryState = true;
    private boolean activeInSecondaryState;
    private State currentState = State.PRIMARY;

    public enum State {
        PRIMARY,
        SECONDARY
    }

    public DefaultKey(int primaryKeyCode, int secondaryKeyCode,
                      @Nullable String primaryOne, @Nullable String primaryTwo,
                      @Nullable String secondaryOne, @Nullable String secondaryTwo) {
        this.primaryKeyCode = primaryKeyCode;
        this.secondaryKeyCode = secondaryKeyCode;
        this.primaryOne = primaryOne;
        this.secondaryOne = secondaryOne;
        this.primaryTwo = primaryTwo;
        this.secondaryTwo = secondaryTwo;
    }

    public int getDrawableRes() {
        return drawableRes;
    }

    public DefaultKey setDrawableRes(int drawableRes) {
        this.drawableRes = drawableRes;
        return this;
    }

    public int getPrimaryColor() {
        return primaryColor;
    }

    public DefaultKey setPrimaryColor(int primaryColor) {
        this.primaryColor = primaryColor;
        return this;
    }

    public int getSecondaryColor() {
        return secondaryColor;
    }

    public DefaultKey setSecondaryColor(int secondaryColor) {
        this.secondaryColor = secondaryColor;
        return this;
    }

    public int getLayoutResId() {
        return layoutResId;
    }

    public DefaultKey setLayoutResId(@LayoutRes int layoutResId) {
        this.layoutResId = layoutResId;
        return this;
    }

    public void switchState() {
        currentState = (currentState == State.PRIMARY) ? State.SECONDARY : State.PRIMARY;
    }

    public String getActualSymbol() {
        return currentState == State.PRIMARY ? primaryOne : secondaryOne;
    }

    public int getActualKeyCode(){
        return currentState == State.PRIMARY ? primaryKeyCode : secondaryKeyCode;
    }

    public boolean isActiveInPrimaryState() {
        return activeInPrimaryState;
    }

    public DefaultKey setActiveInPrimaryState(boolean value) {
        activeInPrimaryState = value;
        return this;
    }

    public boolean isActiveInSecondaryState() {
        return activeInSecondaryState;
    }

    public DefaultKey setActiveInSecondaryState(boolean value) {
        activeInSecondaryState = value;
        return this;
    }

    public boolean enabledInCurrentState(){
        return (currentState == State.PRIMARY) ? activeInPrimaryState : activeInSecondaryState;
    }

    public int getKeyBackgroundColor() {
        return keyBackgroundColor;
    }

    public DefaultKey setKeyBackgroundColor(@ColorInt int keyBackgroundColor) {
        this.keyBackgroundColor = keyBackgroundColor;
        return this;
    }
}
