package com.example.dsproyect_p1.data.api;

public interface Callback<T> {

  void onSuccess(T result);

  void onError(Exception e);
}
