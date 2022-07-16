package com.example.ashana.dagger.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ashana.dagger.model.User;

public class NetworkResponseUtils<T> {

    public final T object;
    public ResponseStatus status;

    public NetworkResponseUtils(T object, ResponseStatus status) {
        this.object = object;
        this.status = status;
    }

    public static  <T> NetworkResponseUtils<T> getSuccessResponse ( T object) {
        return new NetworkResponseUtils<>(object,ResponseStatus.SUCCESS);
    }

    public static <T> NetworkResponseUtils<T> getLoading ( T object) {
        return new NetworkResponseUtils<>(object,ResponseStatus.LOADING);
    }

    public static <T> NetworkResponseUtils<T> getErrorResponse (T object) {
        return new NetworkResponseUtils<>(null,ResponseStatus.ERROR);
    }

    public static <T> NetworkResponseUtils<T> getExceptionResponse ( T object) {
        return new NetworkResponseUtils<>(object,ResponseStatus.EXCEPTION);
    }

    public static <T> NetworkResponseUtils<T> logout(T object) {
        return new NetworkResponseUtils<>(null, ResponseStatus.LOGOUT);
    }

    public enum ResponseStatus {
        LOADING,
        SUCCESS,
        ERROR,
        EXCEPTION,
        LOGOUT
    }
}
