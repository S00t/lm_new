package soot.letsmeet.utils;

/**
 * Klasa to formatowania tekstu
 */
public class TextFormatUtils {
    public static final String KWOTA_REGEX = "^[0-9]*\\.?[0-9]+$";
    public static final String PREFIKS_REGEX ="[+]\\d{2}";

    /**
     * Metoda do formatowania numeru konta
     *
     * @param number numer konta
     * @return Sformatowany numer konta w postaci z przerwami
     */
    public static String bankAccountNymberFormater(String number) {
        if (number == null) {
            return null;
        }
        String pattern = "## #### #### #### #### #### ####";
        number = number.trim();
        number = number.replace(" ", "");
        int index = 0;
        StringBuilder formated = new StringBuilder();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (c == '#') {
                formated.append(number.charAt(index));
                index++;
            } else {
                formated.append(c);
            }
        }
        return formated.toString();
    }

    public static String formatTimeCounter(long minutes, long seconds) {
        return String.format("%02d", minutes) + " : " + String.format("%02d", seconds);
    }

//    public static String formatAdres(ReportAddres adres) {
//        if (adres == null) {
//            return null;
//        }
//        return adres.getCharakter() + "\n" + adres.getKodPocztowy() + " " + adres.getMiejscowosc() + "\n" + adres.getUlica() + " " + (adres.getNrDomu() != null ? (adres.getNrDomu() + " ") : "") + (adres.getNrMieszkania() != null ? (adres.getNrMieszkania() + " ") : "") + (adres.getTelefon() != null ? "\n" + adres.getTelefon() : "") + (adres.getDochodNetto() != null ? "\n" + MoneyUtils.formatWithPln(adres.getDochodNetto()) : "");
//    }
//
//    public static String formatPhoneForm(ReportPhone phone) {
//        if (phone == null) {
//            return null;
//        }
//        return (phone.getDomowy() != null ? "Domowy: " + phone.getDomowy() + "\n" : "") + (phone.getKomorkowy() != null ? "Komórkowy: " + phone.getKomorkowy() + "\n" : "") + (phone.getDoPracy() != null ? "Do pracy: " + phone.getDoPracy() : "");
//    }
//
//    public static String formatAddressUlicaDisplayValue(AdresKredytobiorcy adresKredytobiorcy) {
//        if (adresKredytobiorcy == null) {
//            return null;
//        }
//        return adresKredytobiorcy.getUlica() + " " + (adresKredytobiorcy.getNumer() != null ? adresKredytobiorcy.getNumer() : "");
//    }
//
//    public static String formatAddressMiejscowoscDisplayValue(AdresKredytobiorcy adresKredytobiorcy) {
//        if (adresKredytobiorcy == null) {
//            return null;
//        }
//        return adresKredytobiorcy.getKodPocztowy() + " " + adresKredytobiorcy.getMiejscowosc();
//    }

    public static String valueOf(Number number) {
        if (number == null) {
            return null;
        }
        return String.valueOf(number);
    }

    public static String emptyIfNull(String value) {
        return value != null ? value : "";
    }
}
