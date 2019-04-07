package games;

import org.slf4j.Logger;

import java.io.IOException;

import static games.CardUtils.getShuffledCards;

class BlackJack {
    private static int[] cards; // Основная колода
    private static int cursor; // Счётчик карт основной колоды

    private static int[][] playersCards; // карты игроков. Первый индекс - номер игрока
    private static int[] playersCursors; // курсоры карт игроков. Индекс - номер игрока
    private static int[] playersMoney = {100, 100};

    private static final int MAX_VALUE = 21;
    private static final int MAX_CARDS_COUNT = 8;

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(BlackJack.class);

    static void main() throws IOException {
        while (endMoney(0) && endMoney(1)) {
            initRound();
            int humanPoints = givingCards(0);
            int computerPoints = givingCards(1);
            log.info("Сумма ваших очков -{}, компьютера - {}", humanPoints, computerPoints);
            if (humanPoints > computerPoints) {
                playersMoney[0] += 10;
                playersMoney[1] -= 10;
                log.info("Вы выиграли раунд! Получаете 10$");
            } else if (computerPoints > humanPoints) {
                playersMoney[0] -= 10;
                playersMoney[1] += 10;
                log.info("Вы проиграли раунд! Теряете 10$");
            } else {
                log.info("Ничья!");
            }
        }

        if (playersMoney[0] > 0)
            log.info("Вы выиграли! Поздравляем!");
        else
            log.info("Вы проиграли. Соболезнуем...");
    }

    private static int sum(int player) {
        int temp = 0;
        for (int i = 0; i < playersCursors[player]; i++) {
            temp += value(playersCards[player][i]);
        }
        return temp;
    }

    private static int getFinalSum(int player) {
        if (sum(player) <= MAX_VALUE) return sum(player);
        else return 0;
    }

    private static int givingCards(int player) throws IOException {
        if (player == 0) {
            for (int i = 0; i < 2; i++) {
                log.info("Вам выпала карта {}", CardUtils.toString(addCard2Player(player)));
            }
            while (confirm("Берём еще?") && sum(player) < MAX_VALUE - 1) {
                log.info("Вам выпала карта {}", CardUtils.toString(addCard2Player(player)));
            }
        } else {
            for (int i = 0; i < 2; i++) {
                log.info("Компьютеру выпала карта {}", CardUtils.toString(addCard2Player(player)));
            }
            while (sum(player) < MAX_VALUE - 4) {
                log.info("Компьютер решил взять ещё и ему выпала карта {}", CardUtils.toString(addCard2Player(player)));
            }
        }
        return getFinalSum(player);
    }

    private static boolean confirm(String message) throws IOException {
        log.info(message + " \"Y\" - Да, {любой другой символ} - нет (Что бы выйти из игры, нажмите Ctrl + C)");
        switch (Choice.getCharacterFromUser()) {
            case 'Y':
            case 'y':
                return true;
            default:
                return false;
        }
    }

    private static boolean endMoney(int player) {
        return playersMoney[player] > 0;
    }

    private static int addCard2Player(int player) {
        // todo: реализуйте!
        int card = cards[cursor];
        playersCards[player][playersCursors[player]] = card;
        cursor++;
        playersCursors[player]++;
        return card;
    }

    private static void initRound() {
        log.info("У Вас {}$, у компьютера - {}$. Начинаем новый раунд!", playersMoney[0], playersMoney[1]);
        cards = getShuffledCards();
        playersCards = new int[2][MAX_CARDS_COUNT];
        playersCursors = new int[]{0, 0};
        cursor = 0;
    }

    private static int value(int card) {
        switch (CardUtils.getPar(card)) {
            case JACK:
                return 2;
            case QUEEN:
                return 3;
            case KING:
                return 4;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case NINE:
                return 9;
            case TEN:
                return 10;
            case ACE:
            default:
                return 11;
        }
    }
}
