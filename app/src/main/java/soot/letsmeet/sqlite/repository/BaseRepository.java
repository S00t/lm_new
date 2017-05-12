package soot.letsmeet.sqlite.repository;

import java.util.List;

/**
 * Created by Soot on 03/05/2017.
 */

public interface BaseRepository<T,I> {
    List<T> findAll();
    T findById(I id);

    boolean create(T r);
    void bathCreate(List<T> objectList);
    void bathUpdate(List<T> objectList);

    boolean isEmpty();

    boolean update(T r);
    boolean delete(T r);
    boolean deleteAll();

    long count();
}
