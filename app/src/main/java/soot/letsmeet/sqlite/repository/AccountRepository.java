package soot.letsmeet.sqlite.repository;

import soot.letsmeet.models.Account;

/**
 * Created by Soot on 20/05/2017.
 */

public interface AccountRepository extends BaseRepository<Account,Integer> {

    Account findAccount();
    boolean createOrUpdate(Account mAccount);
}
