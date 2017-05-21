package soot.letsmeet.sqlite.impl;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

import soot.letsmeet.models.Account;
import soot.letsmeet.sqlite.repository.AccountRepository;
import timber.log.Timber;

/**
 * Created by Soot on 20/05/2017.
 */

public class AccountSQLImpl extends BaseSQLImpl<Account, Integer> implements AccountRepository {

    public AccountSQLImpl(ConnectionSource connectionSource){
        try {
            mDao = DaoManager.createDao(connectionSource, Account.class);
        } catch (SQLException e) {
            e.printStackTrace();
            Timber.e(e);
        }
    }

    @Override
    public Account findAccount() {
        try {
            List<Account> accounts = mDao.queryForAll();
            return (accounts != null && !accounts.isEmpty()) ? accounts.get(0) : null;
        } catch (SQLException e) {
            e.printStackTrace();
            Timber.e(e);
        }
        return null;
    }

    @Override
    public boolean createOrUpdate(Account mAccount) {
        try {
            Dao.CreateOrUpdateStatus status = mDao.createOrUpdate(mAccount);
            return status.isCreated() | status.isUpdated();
        } catch (SQLException e) {
            e.printStackTrace();
            Timber.e(e);
        }
        return false;
    }

}
