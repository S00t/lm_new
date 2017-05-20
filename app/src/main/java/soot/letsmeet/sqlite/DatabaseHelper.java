package soot.letsmeet.sqlite;

/**
 * Created by Soot on 03/05/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import soot.letsmeet.models.Account;
import soot.letsmeet.models.Token;

/**
 * Klasa zarządzająca tabelami bazy danych
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    // name of the database file for your application -- change to something
    // appropriate for your app
    private static final String DATABASE_NAME = "LETSMEET.db";
    // any time you make changes to your database objects, you may have to
    // increase the database version
    private static final int DATABASE_VERSION = 1;

    // the DAO object we use to access the SimpleData table

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        setWriteAheadLoggingEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        Log.i(DatabaseHelper.class.getName(), "onCreate");
        try {
            TableUtils.createTable(connectionSource, Account.class);
            TableUtils.createTable(connectionSource, Token.class);

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        Log.i(DatabaseHelper.class.getName(), "onUpgrade");
        try {
            TableUtils.dropTable(connectionSource, Account.class, true);
            TableUtils.dropTable(connectionSource, Token.class, true);

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        onCreate(database, connectionSource);
    }
}

