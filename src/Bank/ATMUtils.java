package Bank;

public class ATMUtils {
    public static boolean isValidCardNumber(String cardNumber) {
        return cardNumber.matches("\\d{4}-\\d{4}-\\d{4}-\\d{4}");
    }
}

