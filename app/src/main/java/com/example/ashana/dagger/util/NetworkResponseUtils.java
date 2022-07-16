package com.example.ashana.dagger.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NetworkResponseUtils<T> {

    public final T object;
    public ResponseStatus status;

    public NetworkResponseUtils(T object, ResponseStatus status) {
        this.object = object;
        this.status = status;
    }

    public static  <T> NetworkResponseUtils<T> getSuccessResponse ( T object) {
        return new NetworkResponseUtils<T>(object,ResponseStatus.SUCCESS);
    }

    public static <T> NetworkResponseUtils<T> getLoading ( T object) {
        return new NetworkResponseUtils<T>(object,ResponseStatus.LOADING);
    }

    public static <T> NetworkResponseUtils<T> getErrorResponse (T object) {
        return new NetworkResponseUtils<T>(null,ResponseStatus.ERROR);
    }

    public static <T> NetworkResponseUtils<T> getExceptionResponse ( T object) {
        return new NetworkResponseUtils<T>(object,ResponseStatus.EXCEPTION);
    }

    public enum ResponseStatus {
        LOADING,
        SUCCESS,
        ERROR,
        EXCEPTION
    }
}
