package org.example;

import java.util.*;

public class LetsGuess {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        sayHi();
        boolean shouldPlay = true;
        while (shouldPlay) {
            gameLogic();
            shouldPlay = playAgain();
        }
    }

    public static void gameLogic() {
        int level = chooseLevel();
        int[] randomTable = getRandomDigits(level);
        boolean hasWon = false;
        int maxLives = 5;
        int lives = maxLives;

        while (lives > 0 && !hasWon) {
            printHowManyLives(lives, maxLives);
            int[] userTable = getPlayerArray(level);
            String[] showResult = checkNumbers(randomTable, userTable);
            printResult(showResult);
            hasWon = checkWin(showResult);
            lives--;
        }

        if (hasWon) {
            System.out.println("Wygrałeś!");
        } else {
            System.out.println("Przegrałeś!");
        }
    }

    private static boolean playAgain() {
        System.out.println("Czy chcesz zagrać ponownie?");
        String playAgain = scanner.next();
        while (!playAgain.equalsIgnoreCase("tak") && !playAgain.equalsIgnoreCase("nie")) {
            System.out.println("Nie rozumiem\nCzy chcesz zagrać ponownie?");
            playAgain = scanner.next();
        }
        return playAgain.equalsIgnoreCase("tak");
    }

    private static void printResult(String[] showResult) {
        System.out.print("\n" + showResult[0]);
        for (int i = 1; i < showResult.length; i++) {
            System.out.print(", " + showResult[i]);
        }
        System.out.println();
    }

    private static void printHowManyLives(int lives, int maxLives) {//Wyświetlenie liczby pozostałych żyć
        System.out.printf("Masz: %d żyć z %d", lives, maxLives);
    }

    private static boolean checkWin(String[] showResult) { //Sprawdza warunek zwycięstwa
        for (String s : showResult) {
            if (!s.equals("HOT")) {
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

    private static int chooseLevel() {//Wybór ilości zgadywanych cyfr
        System.out.println("Ile cyfr chcesz zgadywać?");
        System.out.println("Wpisz 3, 4 lub 5: ");
        int chosenNumber = scanner.nextInt();
        while (chosenNumber > 5 || chosenNumber < 3) {
            System.out.println("Wpisałeś zły zakres cyfr");
            chosenNumber = scanner.nextInt();
        }
        return chosenNumber;
    }

    private static int[] getRandomDigits(int amount) {//Uzupełnia zgadywaną tabelę losowymi cyframi
        ArrayList<Integer> digits = new ArrayList<>();
        Collections.addAll(digits,0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        Collections.shuffle(digits);// 2, 4, 6 ,7...
        int[] randomDigits = new int[amount];
        for (int i = 0; i < randomDigits.length; i++) {
            randomDigits[i] = digits.get(0);
            digits.remove(0);
        }
        return randomDigits;
    }

    private static int[] getPlayerArray(int level) { //Metoda do zgadywania cyfr przez gracza
        int[] playerArray = new int[level];
        System.out.printf("\nWpisz %d cyfr oddzielonych spacją: ", level);
        for (int i = 0; i < level; i++) {
            playerArray[i] = scanner.nextInt();
        }
        return playerArray;
    }

    private static String[] checkNumbers(int[] randomTable, int[] userTable) { //Sprawdza poprawność odgadniętych cyfr
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
