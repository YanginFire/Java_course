package org.example.task5;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class two {

    public static void main(String[] args) {
        HashMap dictionary;
        String choice, wordMeaning;

        while (true) {
            dictionary = createHashMap();
            Scanner in = new Scanner(System.in);

            System.out.println("==== Словарь ====");
            for (Object word : dictionary.keySet()) {
                System.out.println(word);
            }
            System.out.println("Введите слово которое хотите найти (quit чтобы выйти):");
            choice = in.nextLine();

            if (choice == "quit") {
                System.out.println("До свидания!");
                break;
            }

            wordMeaning = (String) dictionary.get(choice);

            if (wordMeaning == null) System.out.println("Такого слова нет в словаре.");
            else {
                System.out.println("Значение: " + wordMeaning + "\n");
            }

        }

    }

    public static HashMap createHashMap() {
        HashMap<String, String> dictionary = new HashMap<>();
        String filename = "dict.txt", data = "";
        data = readFile(filename);
        data = data.replace("\n", "").replace("\r", "");
        String[] dictEntries = data.split(";");

        for (String entry : dictEntries) {
            String[] pair = entry.split(":");
            dictionary.put(pair[0], pair[1]);
        }
        return dictionary;
    }

    public static String readFile(String filename) {
        System.out.println("Считывание информации из файла...");
        StringBuilder sb = new StringBuilder();
        try {
            FileReader myReader = new FileReader(filename);
            int character = myReader.read();
            while (character != -1) {
                sb.append((char) character);
                character = myReader.read();
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("Чтение провалилось");
            e.printStackTrace();
        }
        return sb.toString();
    }

}
