package soot.letsmeet.utils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Util do wyœwietlania jako String sformatowanej wartoœci pieniê¿nych przedstawionych jako BigDecimal
 */
public final class MoneyUtils implements Serializable {
    private static DecimalFormat df = new DecimalFormat("#,##0.00");

    public static String format(BigDecimal value) {
        return value != null ? df.format(value) : null;
    }

    public static String format(Float value) {
        return value != null ? df.format(value) : null;
    }

    public static String formatWithPln(BigDecimal value) {
        return format(value) != null ? format(value).isEmpty() ? null : format(value) + " PLN" : null;
    }

    public static String formatWithWaluta(BigDecimal value, String waluta) {
        return format(value) != null ? format(value).isEmpty() ? null : format(value) + " " + waluta : null;
    }


}
