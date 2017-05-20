package soot.letsmeet.sqlite.impl;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

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

}
