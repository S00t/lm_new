package soot.letsmeet.di;

import android.app.Application;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import soot.letsmeet.sqlite.DatabaseHelper;
import soot.letsmeet.sqlite.impl.LoggerSQLImpl;
import soot.letsmeet.sqlite.impl.TokenSQLImpl;
import soot.letsmeet.sqlite.repository.LoggerRepository;
import soot.letsmeet.sqlite.repository.TokenRepository;

@Module
public class RepositoryModule {
    @Provides
    @Singleton
    ConnectionSource providesConnectionSource(Application app) {
        return new AndroidConnectionSource(new DatabaseHelper(app));
    }

    @Provides
    @Singleton
    TokenRepository providesTokenRepository(ConnectionSource connectionSource) {
        return new TokenSQLImpl(connectionSource);

    }

//    @Provides
//    @Singleton
//    MessagesRepository providesMessagesRepository(ConnectionSource connectionSource) {
//        return new MessagesSQLImpl(connectionSource);
//    }

//
//    @Provides
//    @Singleton
//    ZlecenieRepository providesZlecenieRepostiroy(ConnectionSource connectionSource,
//                                                  UmowaRepository umowaRepository,
//                                                  WplataRepository wplataRepository,
//                                                  KampaniaRepository kampaniaRepository,
//                                                  KredytobiorcaRepository kredytobiorcaRepository,
//                                                  ReportResultRepository reportResultRepository,
//                                                  RaportXWynikRepository raportXWynikRepository,
//                                                  MessagesRepository messagesRepository,
//                                                  PlannedRepository plannedRepository,
//                                                  WarunkiRepository warunkiRepository) {
//        return new ZlecenieSQLImpl(connectionSource, umowaRepository, wplataRepository,
//                kampaniaRepository, kredytobiorcaRepository, reportResultRepository, raportXWynikRepository,messagesRepository, plannedRepository,warunkiRepository);
//    }


    @Provides
    @Singleton
    LoggerRepository providesLoggerRepository(ConnectionSource connectionSource){
        return new LoggerSQLImpl(connectionSource);
    }
}
