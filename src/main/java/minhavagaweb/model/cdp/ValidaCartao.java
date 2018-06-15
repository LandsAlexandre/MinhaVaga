/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.model.cdp;

/**
 *
 * @author ISM
 */
public class ValidaCartao {

    public static final int INVALID = -1;
    public static final int VISA = 0;
    public static final int MASTERCARD = 1;
    public static final int AMERICAN_EXPRESS = 2;
    public static final int EN_ROUTE = 3;
    public static final int DINERS_CLUB = 4;

    private static final String[] CARD_NAMES = {"Visa", "Mastercard",
        "American Express", "En Route", "Diner's CLub/Carte Blanche",};

    private ValidaCartao() {
        //Construtor
    }

    /**
     * Valid a Credit Card number
     *
     * @param number
     * @return
     */
    public static boolean validCC(String number) {
        int cardID = getCardID(number);
        if (cardID != -1) {
            return validCCNumber(number);
        }
        return false;
    }

    /**
     * Get the Card type returns the credit card type INVALID = -1; VISA = 0;
     * MASTERCARD = 1; AMERICAN_EXPRESS = 2; EN_ROUTE = 3; DINERS_CLUB = 4;
     *
     * @param number
     * @return
     */
    public static int getCardID(String number) {
        int valid = INVALID;

        String digit1 = number.substring(0, 1);
        String digit2 = number.substring(0, 2);
        String digit3 = number.substring(0, 3);
        String digit4 = number.substring(0, 4);

        if (isNumber(number)) {
            /*
             * ----* VISA prefix=4* ---- length=13 or 16 (can be 15 too!?!
             * maybe)
             */
            if (digit1.equals("4") && compVisa(number)) {
                valid = VISA;
            }/*
             * ----------* MASTERCARD prefix= 51 ... 55* ---------- length= 16
             */ else if (compMaster(digit2) && (number.length() == 16)) {
                valid = MASTERCARD;
            } /*
             * ----* AMEX prefix=34 or 37* ---- length=15
             */ else if (compAmerican(digit2) && (number.length() == 15)) {
                valid = AMERICAN_EXPRESS;
            } /*
             * -----* ENROU prefix=2014 or 2149* ----- length=15
             */ else if (compEn(digit4) && (number.length() == 15)) {
                valid = EN_ROUTE;
            } /*
             * -----* DCLUB prefix=300 ... 305 or 36 or 38* ----- length=14
             */ else if (digit3.compareTo("305") <= 0 && isDigitEqual(digit2, digit3) && (number.length() == 14)) {
                valid = DINERS_CLUB;
            }
        }
        return valid;
    }

    public static boolean compEn(String digit4) {
        return digit4.equals("2014") || digit4.equals("2149");
    }

    public static boolean compAmerican(String digit2) {
        return digit2.equals("34") || digit2.equals("37");
    }

    public static boolean compMaster(String digit2) {
        return digit2.compareTo("51") >= 0 && digit2.compareTo("55") <= 0;
    }

    public static boolean compVisa(String number) {
        return number.length() == 13 || number.length() == 16;
    }

    public static boolean isDigitEqual(String digit2, String digit3) {
        return digit2.equals("36") || digit2.equals("38") || (digit3.compareTo("300") >= 0);
    }

    public static boolean isNumber(String n) {
        try {
            Double.valueOf(n);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String getCardName(int id) {
        return (id > -1 && id < CARD_NAMES.length ? CARD_NAMES[id] : "");
    }

    public static boolean validCCNumber(String n) {
        try {
            int j = n.length();
            String[] s1 = new String[j];
            for (int i = 0; i < n.length(); i++) {
                s1[i] = "" + n.charAt(i);
            }
            int checksum = 0;
            for (int i = s1.length - 1; i >= 0; i -= 2) {
                if (i > 0) {
                    int k;
                    k = Integer.parseInt(s1[i - 1]) * 2;
                    if (k > 9) {
                        String s = "" + k;
                        k = Integer.parseInt(s.substring(0, 1)) + Integer.valueOf(s.substring(1));
                    }
                    checksum += Integer.parseInt(s1[i]) + k;
                } else {
                    checksum += Integer.parseInt(s1[0]);
                }
            }
            return ((checksum % 10) == 0);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
