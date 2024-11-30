package Bank;

import java.io.*;
import java.util.*;

public class CardDatabase {
    private String filePath;
    private Map<String, Card> cards;

    public CardDatabase(String filePath) {
        this.filePath = filePath;
        this.cards = new HashMap<>();
        loadCards();
    }

    public void loadCards() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Card card = Card.fromDataString(line);
                cards.put(card.getCardNumber(), card);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCards() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Card card : cards.values()) {
                writer.write(card.toDataString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Card getCard(String cardNumber) {
        return cards.get(cardNumber);
    }
}

