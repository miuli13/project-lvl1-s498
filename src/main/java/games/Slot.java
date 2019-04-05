package games;

import static java.lang.Math.random;
import static java.lang.StrictMath.round;

public class Slot {

    static void main() {
        int initialRate = 100;
        int rate = 10;
        int prize = 1000;

        int firstCounter = 0;
        int secondCounter = 0;
        int thirdCounter = 0;
        int size = 7;

        int gain = (int) Math.round(random() * 100);

        while (initialRate > 0) {
            int temp = initialRate;
            initialRate -= rate;
            firstCounter = (firstCounter + gain) % size;
            secondCounter = (secondCounter + gain) % size;
            thirdCounter = (thirdCounter + gain) % size;
            if (firstCounter == secondCounter && firstCounter == thirdCounter) {
                temp += prize;
                System.out.printf("У Вас %d$, ставка - %d$\n"+
                                "Крутим барабаны !Розыгрыш принёс следующие результаты:\n"+
                                "первый барабан - %d, второй - %d, третий - %d\n"+
                                "Выигрыш % d$, ваш капитал теперь составляет: %d$\n ",
                        initialRate, rate, firstCounter, secondCounter, thirdCounter, rate, temp);
            } else {
                temp -= rate;
                System.out.printf("У Вас %d$, ставка - %d$\n"+
                        "Крутим барабаны !Розыгрыш принёс следующие результаты:\n"+
                        "первый барабан - %d, второй - %d, третий - %d\n"+
                        "Проигрыш % d$, ваш капитал теперь составляет: %d$\n ",
                        initialRate, rate, firstCounter, secondCounter, thirdCounter, rate, temp);
            }
            initialRate = temp;
            firstCounter = secondCounter = thirdCounter = 0;
        }


    }
}