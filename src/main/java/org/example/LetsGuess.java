package org.example;

import java.util.*;

public class LetsGuess {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        sayHi();
        int level = chooseLevel();
        int[] randomTable = getRandomDigits(level);
        boolean isGameOver = false;
        int maxLives = 5;
        int lives = maxLives;
        while (!isGameOver){
            isGameOver = gameLogic(level, randomTable, lives, maxLives); // oddzielne zmienne na toczenie sie rundy oraz wygrana
            lives--;
            if(lives == 0){
                System.out.println("Przegrałeś!");
                break;
            }
        }
        if(isGameOver){
            System.out.println("Wygrałeś!");
        }
    }

    public static boolean gameLogic(int level, int[] randomTable, int lives, int maxLives) { //Zbiorcza metoda funkcji gry
        int[] userTable = new int[level];
        printHowManyLives(lives, maxLives);
        fillPlayerArrey(userTable);//podobnie jak random
        String[] showResult = checkNumbers(randomTable, userTable);
        System.out.println(Arrays.toString(showResult));//metoda ktora ladnie wyswietla
        return checkWin(showResult);
    }

    public static void printHowManyLives(int lives, int maxLives){//Wyświetlenie liczby pozostałych żyć
        System.out.printf("Masz: %d żyć z %d",lives, maxLives);
    }

    public static boolean checkWin(String[] showResult) { //Sprawdza warunek zwycięstwa
        for (String s : showResult) {
            if (s.equals("Cold") || s.equals("Warm")) {
                return false;
            }
        }
            return true;
    }


    public static void sayHi() {//Rozpoczęcie gry
        System.out.println("Witaj w zgadywance liczbowej :)");
        System.out.println("Twoim zadaniem będzie odgadnięcie wylosowanych cyfr 0-9");
        System.out.println("Zaczynajmy!");
    }

    public static int chooseLevel() {//Wybór ilości zgadywanych cyfr
        System.out.println("Ile cyfr chcesz zgadywać?");
        System.out.println("Wpisz 3, 4 lub 5: ");
        int choseNumber = scanner.nextInt();//upewnij sie ze dobry zakres liczb
        return choseNumber;
    }

    public static int[] getRandomDigits(int amount) {//Uzupełnia zgadywaną tabelę losowymi cyframi
        int [] table= {0,1,2,3,4,5,6,7,8,9};
        int[] array = new int[amount];
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

    public static String[] checkNumbers(int[] randomTable, int[] userTable) { //Sprawdza poprawność odgadniętych cyfr
        String[] showResult = new String[randomTable.length];
        for (int i = 0; i < userTable.length; i++) {
            int userNumber = userTable[i];
            if (userNumber == randomTable[i]) {
                showResult[i] = "HOT";
            } else {
                for (int randomNumber : randomTable) {
                    if (userNumber == randomNumber) {
                        showResult[i] = "Warm";
                        break;
                    }
                    showResult[i] = "Cold";
                }
            }
        }
        return showResult;
    }
}
