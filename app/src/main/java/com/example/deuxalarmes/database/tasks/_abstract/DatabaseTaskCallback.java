package com.example.deuxalarmes.database.tasks._abstract;

@FunctionalInterface
public interface DatabaseTaskCallback<T> {
    void onTaskDone(T result);
}
