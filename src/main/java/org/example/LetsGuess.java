package org.example;

import java.util.*;

public class LetsGuess {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        sayHi();
        gameLogic();
        while (playAgain()) {
            gameLogic();
        }
    }

    public static void gameLogic() {
        int level = chooseLevel();
        int[] randomTable = getRandomDigits(level);
        boolean isGameOver = false;
        int maxLives = 5;
        int lives = maxLives;
        while (!isGameOver) {
            printHowManyLives(lives, maxLives);
            int[] userTable = fillPlayerArray(level);
            String[] showResult = checkNumbers(randomTable, userTable);
            printResult(showResult);
            isGameOver = checkWin(showResult);
            lives--;
            if (lives == 0) {
                System.out.println("Przegrałeś!");
                break;
            }
        }
        if (isGameOver) {
            System.out.println("Wygrałeś!");
        }
    }

    private static boolean playAgain() {
        System.out.println("Czy chcesz zagrać ponownie?");
        String playAgain = scanner.next();
        while (!playAgain.equalsIgnoreCase("tak") && !playAgain.equalsIgnoreCase("nie")) {
            System.out.println("Nie rozumiem\nCzy chcesz zagrać ponownie?");
            playAgain = scanner.next();
        }
        if (playAgain.equalsIgnoreCase("tak")) {
            return true;
        } else {
            return false;
        }
    }

    public static void printResult(String[] showResult) {
        System.out.print("\n" + showResult[0]);
        for (int i = 1; i < showResult.length; i++) {
            System.out.print(", " + showResult[i]);
        }
        System.out.println();
    }

    public static void printHowManyLives(int lives, int maxLives) {//Wyświetlenie liczby pozostałych żyć
        System.out.printf("Masz: %d żyć z %d", lives, maxLives);
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
        int choseNumber = scanner.nextInt();
        while (choseNumber > 5 || choseNumber < 3) {
            System.out.println("Wpisałeś zły zakres cyfr");
            choseNumber = scanner.nextInt();
        }
        return choseNumber;
    }

    public static int[] getRandomDigits(int amount) {//Uzupełnia zgadywaną tabelę losowymi cyframi
        int[] table = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] array = new int[amount];
        for (int i = 0; i < array.length; i++) {
            int index = numberRandom();
            while (table[index] == -1) {
                index = numberRandom();
            }
            array[i] = table[index];
            table[index] = -1;
        }
        return array;
    }

    public static int numberRandom() {//Losowanie kolejnych liczb do zgadywanej tabeli
        return (int) (Math.random() * 10);
    }

    public static int[] fillPlayerArray(int level) { //Metoda do zgadywania cyfr przez gracza
        int[] playerArray = new int[level];
        System.out.printf("\nWpisz %d cyfr oddzielonych spacją: ", level);
        for (int i = 0; i < level; i++) {
            playerArray[i] = scanner.nextInt();
        }
        return playerArray;
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
