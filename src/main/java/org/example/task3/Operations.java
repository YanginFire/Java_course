package org.example.task3;

import java.io.*;
import java.util.Scanner;

public class Operations {
    public static String filename;

    public static void main(String[] args) {

        createFile(); // Создаем новый файл

        int choice = -1;

        while (choice != 5) {
            Scanner in = new Scanner(System.in);
            System.out.print(
                    """
                    ================ Меню ================
                    1- записать текст в файл
                    2- подсчитать количество слов в тексте
                    3- подсчитать количество знаков препинания
                    4- поиск в файле
                    5- выход
                    """);
            choice = in.nextInt();
            switch (choice) {
                case 1: {
                    writeToFile();
                    break;
                }
                case 2: {
                    countWords();
                    break;
                }
                case 5: {
                    System.out.println("До свидания!");
                    break;
                }
                default:
                {
                    System.out.println("Некорректный ввод!");
                }
            }
        }
    }

    /**
     * Функция создания файла
     */
    public static void createFile() {
        System.out.println("Введите название файла: ");
        Scanner in = new Scanner(System.in);
        filename = in.nextLine();

        System.out.println(String.format("Создаю пустой файл [%s]....", filename));
        File file = new File(filename);

        try {
            if (file.createNewFile()) {
                System.out.println("Файл создан успешно!");
            } else {
                System.out.println("Создание файла провалилось!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Функция записи текста в файл
     */
    public static void writeToFile() {
        try {
            System.out.println("Введите текст: ");
            Scanner in = new Scanner(System.in);
            String text = in.nextLine();

            FileWriter fileWriter = new FileWriter(filename);
            fileWriter.write(text);
            fileWriter.close();

        }catch (Exception e) {
            System.out.println("Добавление текста в файл провалилось.");
            e.printStackTrace();
        }
    }

    /**
     * Функция подсчета слов в файле
     */
    public static void countWords() {
        String[] words;
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                words = nextLine.split("\\s");
                count += words.length;
            }
            System.out.println("Количество слов в тексте: " + count);
//            System.out.println("Вы хотите сохранить результат? y/n");
//
//            Scanner in = new Scanner(System.in);
//            String answer = in.nextLine();
//
//            if(Objects.equals(answer, 'y')) {
//                saveResult(String.format("Количество слов в файле %s: %d", filename, count));
//            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

//    public static void saveResult(String text) {
//        try {
//            FileWriter fileWriter = new FileWriter("Results.txt");
//            fileWriter.write("ЛОЛ КЕК");
//            fileWriter.close();
//
//        }catch (Exception e) {
//            System.out.println("Сохранение результата провалилось.");
//            e.printStackTrace();
//        }
//    }


}
