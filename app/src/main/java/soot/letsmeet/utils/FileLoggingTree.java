package soot.letsmeet.utils;

import android.os.Environment;
import android.util.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import timber.log.Timber;

public class FileLoggingTree extends Timber.DebugTree {

    static Logger mLogger = LoggerFactory.getLogger(FileLoggingTree.class);

    FileLoggingTree(){
        super();
        configureLogger();
    }


    public static void configureLogger() {
        final String LOG_DIR = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        final String LOGBACK_XML = "<configuration>" +
                "<property name='LOG_DIR' value='" + LOG_DIR + "'/>" +

                "<appender name='ROLLING' class='ch.qos.logback.core.rolling.RollingFileAppender'>" +
                "<file>" + LOG_DIR + "/log-latest.html" + "</file>" +

                "<encoder class='ch.qos.logback.core.encoder.LayoutWrappingEncoder'>" +
                "<charset>UTF-8</charset>" +
                "<layout class='ch.qos.logback.classic.html.HTMLLayout'>" +
                "<pattern>%d{HH:mm:ss.SSS}%thread%level%line%msg</pattern>" +
                "</layout>" +
                "</encoder>" +

                "<rollingPolicy class='ch.qos.logback.core.rolling.TimeBasedRollingPolicy'>" +
                "<fileNamePattern>" + LOG_DIR + "/log.%d{yyyy-MM-dd}.%i.html" + "</fileNamePattern>" +

                "<timeBasedFileNamingAndTriggeringPolicy class='ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP'>" +
                "<maxFileSize>5MB</maxFileSize>" +
                "</timeBasedFileNamingAndTriggeringPolicy>" +
                "<maxHistory>5</maxHistory>" +
                "</rollingPolicy>" +
                "</appender>" +

                "<root level='INFO'>" +
                "<appender-ref ref='ROLLING' />" +
                "</root>" +
                "</configuration>";

        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        lc.reset();

        JoranConfigurator config = new JoranConfigurator();
        config.setContext(lc);

        InputStream stream = new ByteArrayInputStream(LOGBACK_XML.getBytes());
        try {
            config.doConfigure(stream);
        } catch (JoranException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return;
        }

        String logMessage = tag + ": " + message;
        switch (priority) {
            case Log.INFO:
                mLogger.info(logMessage);
                break;
            case Log.WARN:
                mLogger.warn(logMessage);
                break;
            case Log.ERROR:
                if (t != null) {
                    mLogger.error(logMessage, t);
                } else {
                    mLogger.error(logMessage);
                }
                break;
        }
    }
}