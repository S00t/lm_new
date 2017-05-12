package soot.letsmeet.sqlite.impl;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.UUID;

import soot.letsmeet.models.Logger;
import soot.letsmeet.sqlite.repository.LoggerRepository;

public class LoggerSQLImpl extends BaseSQLImpl<Logger,UUID> implements LoggerRepository {
    public LoggerSQLImpl(ConnectionSource connectionSource){
        try {
            mDao = DaoManager.createDao(connectionSource, Logger.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
