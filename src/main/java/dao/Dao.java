package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> get(int id) throws SQLException;
    List<T> getAll() throws SQLException;
    int save(T t) throws SQLException;
    int update(Object... params) throws SQLException;
    int delete(int id) throws SQLException;
}
