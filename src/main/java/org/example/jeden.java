package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class jeden {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        sayHi();
        int level = choseLevel();
        //System.out.println(level);
        int[] randomTable = new int[level];
        int[] userTable = new int[level];
        fillArray(randomTable);
        System.out.println(Arrays.toString(randomTable));
        fillPlayerArrey(userTable);
        System.out.println(Arrays.toString(userTable));
        String [] showResult= checkNumber(randomTable, userTable);
        System.out.println(Arrays.toString(showResult));

    }

    public static void sayHi() {
        System.out.println("Witaj w zgadywance liczbowej :)");
        System.out.println("Twoim zadaniem będzie odgadnięcie wylosowanych cyfr 0-9");
        System.out.println("Zaczynajmy!");
    }

    public static int choseLevel() {
        System.out.println("Ile cyfr chcesz zgadywać?");
        System.out.println("Wpisz 3, 4 lub 5: ");
        int choseNumber = scanner.nextInt();
        return choseNumber;
    }

    public static int[] fillArray(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            array[i] = numberRandom();
        }
        return array;
    }

    public static int numberRandom() {
        int random = (int) (Math.random() * 10);
        return random;
    }

    public static int[] fillPlayerArrey(int[] userTable) {
        for (int i = 0; i < userTable.length; i++) {
            System.out.printf("\nWpisz liczbę %d z %d: ", i + 1, userTable.length);
            userTable[i] = scanner.nextInt();

        }
        return userTable;
    }

    public static String[] checkNumber(int[] randomTable, int[] userTable) {
        String [] showResult= new String[randomTable.length];
        for (int i=0;i<userTable.length;i++){
            int userNumber= userTable[i];
            if (userNumber==randomTable[i]){
                showResult[i]="HOT";
            }
            else {for(int j=0;j<randomTable.length;j++){
                int rabdomNumber=randomTable[j];
                if (userNumber!=rabdomNumber){ showResult[i]="Cold";}
                else if(userNumber==rabdomNumber){
                showResult[i]="Warm";
            }
            }
            }
        }
        return showResult;
    }
}

