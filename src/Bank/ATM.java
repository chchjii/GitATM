package Bank;

import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        CardDatabase cardDatabase = new CardDatabase("data/cards.txt");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите номер карты (формат: XXXX-XXXX-XXXX-XXXX): ");
            String cardNumber = scanner.nextLine();

            if (!ATMUtils.isValidCardNumber(cardNumber)) {
                System.out.println("Неверный формат номера карты.");
                continue;
            }

            Card card = cardDatabase.getCard(cardNumber);

            if (card == null) {
                System.out.println("Карта не найдена.");
                continue;
            }

            if (card.isLocked()) {
                System.out.println("Карта заблокирована.");
                continue;
            }

            System.out.println("Введите ПИН-код: ");
            String pin = scanner.nextLine();

            if (!card.checkPin(pin)) {
                System.out.println("Неправильный ПИН-код.");
                card.incrementFailedAttempts();
                if (card.getFailedAttempts() >= 3) {
                    card.lock();
                    System.out.println("Карта заблокирована.");
                }
                cardDatabase.saveCards();
                continue;
            }

            card.resetFailedAttempts();

            while (true) {
                System.out.println("1. Проверить баланс");
                System.out.println("2. Снять средства");
                System.out.println("3. Пополнить баланс");
                System.out.println("4. Выход");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println("Баланс: " + card.getBalance());
                        break;
                    case 2:
                        System.out.println("Введите сумму для снятия: ");
                        double withdrawAmount = Double.parseDouble(scanner.nextLine());
                        if (card.withdraw(withdrawAmount)) {
                            System.out.println("Снятие успешно. Текущий баланс: " + card.getBalance());
                        } else {
                            System.out.println("Недостаточно средств.");
                        }
                        break;
                    case 3:
                        System.out.println("Введите сумму для пополнения: ");
                        double depositAmount = Double.parseDouble(scanner.nextLine());
                        if (card.deposit(depositAmount)) {
                            System.out.println("Пополнение успешно. Текущий баланс: " + card.getBalance());
                        } else {
                            System.out.println("Сумма пополнения превышает лимит.");
                        }
                        break;
                    case 4:
                        cardDatabase.saveCards();
                        return;
                    default:
                        System.out.println("Неверный выбор.");
                }
            }
        }
    }
}
