package soot.letsmeet.sqlite.impl;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

import soot.letsmeet.models.Token;
import soot.letsmeet.sqlite.repository.TokenRepository;
import timber.log.Timber;

/**
 * Created by ggla00 on 2017-05-15.
 */

public class TokenSQLImpl extends BaseSQLImpl<Token, Integer> implements TokenRepository{

    public TokenSQLImpl(ConnectionSource connectionSource){
        try {
            mDao = DaoManager.createDao(connectionSource, Token.class);
        } catch (SQLException e) {
            e.printStackTrace();
            Timber.e(e);
        }
    }


    @Override
    public Token findToken() {
        try {
            List<Token> tokens = mDao.queryForAll();
            return (tokens != null && !tokens.isEmpty()) ? tokens.get(0) : null;
        } catch (SQLException e) {
            e.printStackTrace();
            Timber.e(e);
        }
        return null;
    }

    @Override
    public boolean createOrUpdate(Token mToken) {
        try {
            Dao.CreateOrUpdateStatus status = mDao.createOrUpdate(mToken);
            return status.isCreated() | status.isUpdated();
        } catch (SQLException e) {
            e.printStackTrace();
            Timber.e(e);
        }
        return false;
    }
}
