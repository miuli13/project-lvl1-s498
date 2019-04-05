package games;

import static java.lang.Math.random;

class Slot {

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
                System.out.printf("У Вас %d$, ставка - %d$\n"+
                                "Крутим барабаны !Розыгрыш принёс следующие результаты:\n"+
                                "первый барабан - %d, второй - %d, третий - %d\n"+
                                "Выигрыш % d$, ваш капитал теперь составляет: %d$\n ",
                        temp, rate, firstCounter, secondCounter, thirdCounter, rate, initialRate);
            } else {
                System.out.printf("У Вас %d$, ставка - %d$\n"+
                        "Крутим барабаны !Розыгрыш принёс следующие результаты:\n"+
                        "первый барабан - %d, второй - %d, третий - %d\n"+
                        "Проигрыш % d$, ваш капитал теперь составляет: %d$\n ",
                        temp, rate, firstCounter, secondCounter, thirdCounter, rate, initialRate);
            }
        }


    }
}
