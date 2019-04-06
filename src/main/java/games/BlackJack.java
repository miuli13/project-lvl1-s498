package games;

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

    static void main() throws IOException {
        while (endMoney(0) && endMoney(1)) {
            initRound();
            int humanPoints = givingCards(0);
            int computerPoints = givingCards(1);
            System.out.printf("Сумма ваших очков -%d, компьютера - %d\n", humanPoints, computerPoints);
            if (humanPoints > computerPoints) {
                playersMoney[0] += 10;
                playersMoney[1] -= 10;
                System.out.println("Вы выиграли раунд! Получаете 10$\n");
            } else if (computerPoints > humanPoints) {
                playersMoney[0] -= 10;
                playersMoney[1] += 10;
                System.out.println("Вы проиграли раунд! Теряете 10$\n");
            } else {
                System.out.println("Ничья!\n");
            }
        }

        if (playersMoney[0] > 0)
            System.out.println("Вы выиграли! Поздравляем!");
        else
            System.out.println("Вы проиграли. Соболезнуем...");
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
                System.out.printf("Вам выпала карта %s \n", CardUtils.toString(addCard2Player(player)));
            }
            while (confirm("Берём еще?") && sum(player) < MAX_VALUE - 1) {
                System.out.printf("Вам выпала карта %s \n", CardUtils.toString(addCard2Player(player)));
            }
        } else {
            for (int i = 0; i < 2; i++) {
                System.out.printf("Компьютеру выпала карта %s \n", CardUtils.toString(addCard2Player(player)));
            }
            while (sum(player) < MAX_VALUE - 4) {
                System.out.printf("Компьютер решил взять ещё и ему выпала карта %s \n", CardUtils.toString(addCard2Player(player)));
            }
        }
        return getFinalSum(player);
    }

    private static boolean confirm(String message) throws IOException {
        System.out.println(message + " \"Y\" - Да, {любой другой символ} - нет (Что бы выйти из игры, нажмите Ctrl + C)");
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
        System.out.println("\nУ Вас " + playersMoney[0] + "$, у компьютера - " + playersMoney[1] + "$. Начинаем новый раунд!");
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
