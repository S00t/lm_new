package soot.letsmeet.sqlite.impl;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import soot.letsmeet.sqlite.repository.BaseRepository;

public class BaseSQLImpl<T, I> implements BaseRepository<T, I> {
    protected Dao<T,I> mDao;

    @Override
    public List<T> findAll() {
        try {
            return mDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public T findById(I id) {
        try {
            return id != null ? mDao.queryForId(id) : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean create(T r) {
        try {
            return mDao.create(r) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public void bathCreate(List<T> objectList) {
        try {
            mDao.callBatchTasks(() -> {
                for (T object : objectList){
                    mDao.create(object);
                }
                return null;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void bathUpdate(List<T> objectList) {
        try {
            mDao.callBatchTasks(() -> {
                for (T object : objectList){
                    mDao.update(object);
                }
                return null;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isEmpty() {
        try{
            return mDao.queryForAll().size() == 0;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    @Override
    public boolean update(T r) {
        try {
            return mDao.update(r) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteAll() {
        try {
            if (count() != 0) {
                TableUtils.dropTable(mDao, false);
                TableUtils.createTable(mDao);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(T r) {
        try {
            return mDao.delete(r) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public long count() {
        try {
            return mDao.countOf();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
