package org.example;

import java.util.*;

public class LetsGess {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        sayHi();
        int level = choseLevel();
        int[] randomTable = new int[level];
        fillArray(randomTable);
        System.out.println(Arrays.toString(randomTable));
        boolean b = false;
        int maxLives = 5;
        int lives = maxLives;
        while (!b){
            b= game(level, randomTable, lives, maxLives);
            lives--;
            if(lives == 0){
                System.out.println("Przegrałeś!");
                break;
            }
        }
        if(b){
            System.out.println("Wygrałeś!");
        }
    }

    public static boolean game(int level, int[] randomTable, int lives, int maxLives) { //Zbiorcza metoda funkcji gry
        int[] userTable = new int[level];
        printHowManyLives(lives, maxLives);
        fillPlayerArrey(userTable);
        System.out.println(Arrays.toString(userTable));
        String[] showResult = checkNumber(randomTable, userTable);
        System.out.println(Arrays.toString(showResult));
        return checkWin(level, showResult, randomTable);
    }

    public static void printHowManyLives(int lives, int maxLives){//Wyświetlenie liczby pozostałych żyć
        System.out.printf("Masz: %d żyć z %d",lives, maxLives);
    }

    public static boolean checkWin(int level, String[] showResult, int[] randomTable) { //Sprawdza warunek zwycięstwa
        int countscore = 0;
        for (int i = 0; i < showResult.length; i++) {
            if (showResult[i].equals("Cold") || showResult[i].equals("Warm")) {
                return false;
            } else {
                countscore++;
            }
        }
        if(countscore == showResult.length){
            return true;
        }
        return false;
    }


    public static void sayHi() {//Rozpoczęcie gry
        System.out.println("Witaj w zgadywance liczbowej :)");
        System.out.println("Twoim zadaniem będzie odgadnięcie wylosowanych cyfr 0-9");
        System.out.println("Zaczynajmy!");
    }

    public static int choseLevel() {//Wybór ilości zgadywanych cyfr
        System.out.println("Ile cyfr chcesz zgadywać?");
        System.out.println("Wpisz 3, 4 lub 5: ");
        int choseNumber = scanner.nextInt();
        return choseNumber;
    }

    public static int[] fillArray(int[] array) {//Uzupełnia zgadywaną tabelę losowymi cyframi
        int [] table= {0,1,2,3,4,5,6,7,8,9};
        for (int i = 0; i < array.length; i++) {
            int index= numberRandom();
            while (table[index]==-1){
                index=numberRandom();
            }
            array[i]=table[index];
            table[index]=-1;
        }
        return array;
    }

    public static int numberRandom() {//Losowanie kolejnych liczb do zgadywanej tabeli
        int random = (int) (Math.random() * 10);
        return random;
    }

    public static int[] fillPlayerArrey(int[] userTable) { //Metoda do zgadywania cyfr przez gracza
        System.out.printf("\nWpisz cyfry: ");
        for (int i = 0; i < userTable.length; i++) {

            userTable[i] = scanner.nextInt();
        }
        return userTable;
    }

    public static String[] checkNumber(int[] randomTable, int[] userTable) { //Sprawdza poprawność odgadniętych cyfr
        String[] showResult = new String[randomTable.length];
        for (int i = 0; i < userTable.length; i++) {
            int userNumber = userTable[i];
            if (userNumber == randomTable[i]) {
                showResult[i] = "HOT";
            } else {
                for (int j = 0; j < randomTable.length; j++) {
                    int rabdomNumber = randomTable[j];
                    if (userNumber == rabdomNumber) {
                        showResult[i] = "Warm";
                        break;
                    }
                    showResult[i]="Cold";
                }
            }
        }
        return showResult;
    }
}
