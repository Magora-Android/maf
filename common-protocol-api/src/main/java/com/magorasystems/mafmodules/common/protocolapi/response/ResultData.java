package com.magorasystems.mafmodules.common.protocolapi.response;

/**
 * @author Valentin S. Bolkonsky
 *         Developed by Magora Team (magora-systems.com). 2015.
 */
public class ResultData<T> {

    private final T result;

    public ResultData(T result) {
        this.result = result;
    }

    public T getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "ResultData{" +
                "result=" + result +
                '}';
    }
}
