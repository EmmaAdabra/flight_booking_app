package dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> get(int id) throws SQLExecutionException;
    List<T> getAll() throws SQLExecutionException;
    int save(T t) throws SQLExecutionException;
    int update(Object... params) throws SQLExecutionException;
    int delete(int id) throws SQLExecutionException;
}
