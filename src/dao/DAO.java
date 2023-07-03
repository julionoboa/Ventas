package dao;

import java.util.List;

public interface DAO<T> {
    void insert(T entity);

    void update(T entity);

    int delete(T entity);

    T getByID(int id);

    List<T> showAll();

    void refresh(T entity, int t);
}
