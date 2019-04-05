package games;

class Drunkard {
    enum Suit {
        SPADES, // пики
        HEARTS, // червы
        CLUBS, // трефы
        DIAMONDS // бубны
    }

    static void main() {
        System.out.println("Hello World");
    }

    private static Suit getSuit(int cardNumber) {
        return Suit.values()[cardNumber / 9];
    }
}
