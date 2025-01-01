package dao;

import dao.exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> get(int id) throws DAOException;
    List<T> getAll() throws DAOException;
    int save(T t) throws DAOException;
    int update(Object... params) throws DAOException;
    int delete(int id) throws DAOException;
}
