package soot.letsmeet.sqlite.repository;

import java.util.UUID;

import soot.letsmeet.activities.BaseActivity;
import soot.letsmeet.models.Token;

/**
 * Created by ggla00 on 2017-05-15.
 */

public interface TokenRepository extends BaseRepository<Token,Integer> {
    Token findToken();
    boolean createOrUpdate(Token mToken);
}
