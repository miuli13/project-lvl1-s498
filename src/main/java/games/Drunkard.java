package games;

import org.slf4j.Logger;

import static games.CardUtils.*;

class Drunkard {

    private static int[][] playersCards = new int[2][CARDS_TOTAL_COUNT + 1];

    private static int[] playersCardTails = new int[2];
    private static int[] playersCardHeads = new int[2];

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Drunkard.class);

    static void main() {
        int[] cards = getShuffledCards();
        System.arraycopy(cards, 0, playersCards[0], 0, 18);
        System.arraycopy(cards, 18, playersCards[1], 0, 18);
        boolean result = true;
        int winner = 0;
        int sumIterations = 0;
        while (result) {
            sumIterations++;
            if (sumIterations == 1) for (int i = 0; i < 2; i++) playersCardHeads[i] = CARDS_TOTAL_COUNT / 2;
            log.info("Итерация {}", sumIterations);
            winner += resolveWinner();
            if (playerCardsIsEmpty(0) || playerCardsIsEmpty(1)) {
                result = false;
                continue;
            }
            log.info("У игрока №1 {} карт, у игрока №2 {} карт", countCards(0), countCards(1));
        }
        if (winner > 0) {
            log.info("У игрока №1 36 карт, у игрока №2 {} карт", countCards(1));
            log.info("Выиграл первый игрок! Количество произведённых итераций: {}.", sumIterations);
        } else {
            log.info("У игрока №1 " + countCards(0) + " карт, у игрока №2 36 карт");
            log.info("Выиграл второй игрок! Количество произведённых итераций: {}.", sumIterations);
        }

    }

    private static int resolveWinner() {
        int num = getCard(0) % PARS_TOTAL_COUNT;
        int num2 = getCard(1) % PARS_TOTAL_COUNT;
        log.info("Игрок №1 карта: {}; игрок №2 карта: {}.", CardUtils.toString(num), CardUtils.toString(num2));
        if (num2 > num) {
            if ((num == 0) && (num2 == 8)) {
                addCard(0, num);
                addCard(0, num2);
                log.info("Выиграл игрок 1!");
                return 1;
            } else {
                addCard(1, num);
                addCard(1, num2);
                log.info("Выиграл игрок 2!");
                return -1;
            }
        } else if (num2 < num) {
            if ((num == 8) && (num2 == 0)) {
                addCard(1, num);
                addCard(1, num2);
                log.info("Выиграл игрок 2!");
                return -1;
            } else {
                addCard(0, num);
                addCard(0, num2);
                log.info("Выиграл игрок 1!");
                return 1;
            }
        } else {
            addCard(0, num);
            addCard(1, num2);
            log.info("Спор - каждый остаётся при своих!");
            return 0;
        }
    }

    private static int countCards(int playerIndex) {
        if (playersCardHeads[playerIndex] < playersCardTails[playerIndex]) {
            return playersCardHeads[playerIndex] + CARDS_TOTAL_COUNT - playersCardTails[playerIndex];
        } else {
            return playersCardHeads[playerIndex] - playersCardTails[playerIndex];
        }
    }

    private static int getCard(int playerIndex) {
        int temp = playersCards[playerIndex][playersCardTails[playerIndex]];
        playersCardTails[playerIndex] = incrementIndex(playersCardTails[playerIndex]);
        return temp;
    }

    private static void addCard(int playerIndex, int card) {
        playersCards[playerIndex][playersCardHeads[playerIndex]] = card;
        playersCardHeads[playerIndex] = incrementIndex(playersCardHeads[playerIndex]);
    }

    private static boolean playerCardsIsEmpty(int playerIndex) {
        int tail = playersCardTails[playerIndex];
        int head = playersCardHeads[playerIndex];
        return tail == head;
    }

    private static int incrementIndex(int i) {
        return (i + 1) % CARDS_TOTAL_COUNT;
    }
}
