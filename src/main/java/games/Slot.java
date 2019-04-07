package games;

import org.slf4j.Logger;

import static java.lang.Math.random;

class Slot {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Slot.class);

    static void main() {
        int initialRate = 100;
        int rate = 10;
        int prize = 1000;

        int firstCounter = 0;
        int secondCounter = 0;
        int thirdCounter = 0;
        int size = 7;

        while (initialRate > 0) {
            int temp = initialRate;
            initialRate -= rate;
            firstCounter = (firstCounter + (int) Math.round(random() * 100)) % size;
            secondCounter = (secondCounter + (int) Math.round(random() * 100)) % size;
            thirdCounter = (thirdCounter + (int) Math.round(random() * 100)) % size;
            if (firstCounter == secondCounter && firstCounter == thirdCounter) {
                initialRate += prize;
                log.info("У Вас {}$, ставка - {}$ \n" +
                                "Крутим барабаны !Розыгрыш принёс следующие результаты: \n" +
                                "первый барабан - {}, второй - {}, третий - {} \n" +
                                "Выигрыш {}$, ваш капитал теперь составляет: {}$",
                        temp, rate, firstCounter, secondCounter, thirdCounter, rate, initialRate);
            } else {
                log.info("У Вас {}$, ставка - {}$\n" +
                        "Крутим барабаны !Розыгрыш принёс следующие результаты:\n" +
                        "первый барабан - {}, второй - {}, третий - {}\n" +
                        "Проигрыш {}, ваш капитал теперь составляет: {}$",
                        temp, rate, firstCounter, secondCounter, thirdCounter, rate, initialRate);
            }
        }


    }
}
