package games;

import org.apache.commons.math3.util.MathArrays;

class CardUtils {
    enum Suit {
        SPADES, // пики
        HEARTS, // червы
        CLUBS, // трефы
        DIAMONDS // бубны
    }

    enum Par {
        SIX, SEVEN, EIGHT, NINE, TEN, JACK, // Валет
        QUEEN, // Дама
        KING,// Король
        ACE// Туз
    }

    static final int PARS_TOTAL_COUNT = Par.values().length;
    static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length; //36

    private static Suit getSuit(int cardNumber) {
        return Suit.values()[cardNumber / PARS_TOTAL_COUNT];
    }

    static Par getPar(int cardNumber) {
        return Par.values()[cardNumber % PARS_TOTAL_COUNT];
    }

    static String toString(int cardNumber) {
        return getPar(cardNumber) + " " + getSuit(cardNumber);
    }

    static int[] getShuffledCards() {
        // колода подряд
        int[] cards = {
                0, 1, 2, 3, 4, 5, 6, 7, 8,  // бубны
                9, 10, 11, 12, 13, 14, 15, 16, 17,  // червы
                18, 19, 20, 21, 22, 23, 24, 25, 26,  // трефы
                27, 28, 29, 30, 31, 32, 33, 34, 35}; // пики

        MathArrays.shuffle(cards);
        return cards;
    }
}
