package soot.letsmeet.utils;

import org.joda.time.DateTimeZone;

public class TimeUtil {
    /*
    Metoda do pobierania aktualnego czasu w milisekundach z uwzglêdnieniem strefy czasowej i zmiany czasu
     */
    public static long getLocalMillis() {
        long utcMillis = System.currentTimeMillis();
        long offset = DateTimeZone.forID("Europe/Warsaw").getOffset(utcMillis);
        return utcMillis + offset;
    }
}
