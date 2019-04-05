package games;

class Utils {
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

    static int incrementIndex(int i) {
        return (i + 1) % CARDS_TOTAL_COUNT;
    }
}
