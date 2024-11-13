package data;

import java.time.YearMonth;

public class Visa {
    public static boolean validate(String cardNumber, String cvv, int month, int year) {
        YearMonth currentYearMonth = YearMonth.now();
        int currentYear = currentYearMonth.getYear() % 100; // last two digits of the year
        int currentMonth = currentYearMonth.getMonthValue();

        if (cardNumber.startsWith("4") && cardNumber.length() == 16 && isNumeric(cardNumber)) {	// card number
            if (cvv.length() >= 3 && cvv.length() <= 4 && isNumeric(cvv)) {	// cvv number
                return year > currentYear || (year == currentYear && month >= currentMonth);	// date
            }
        }
        return false;
    }
    
    private static boolean isNumeric(String str) {	// check if it contains only numbers
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Long.parseLong(str);	//cant use Integer.parseInt because it's too long
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
