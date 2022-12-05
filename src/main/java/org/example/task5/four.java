package org.example.task5;

import java.io.FileWriter;
import java.util.TreeSet;

import static org.example.task5.two.readFile;

public class four {
    public static void main(String[] args) {
        TreeSet ts = new TreeSet();
        String filename = "words.txt", data = "";
        StringBuilder words_new = new StringBuilder();
        data = readFile(filename);
        data = data.replaceAll("[\n\r;,!.?:]", "");
        String[] fileEntries = data.split(" ");
        for (String entry : fileEntries) {
            ts.add(entry.toLowerCase());
        }
        for (Object word : ts) {
            words_new.append(word);
            words_new.append(" ");
        }
        System.out.println(String.format("Сохраняем слова в файл words_new.txt..."));
        try {
            FileWriter myWriter = new FileWriter("words_new.txt");
            myWriter.write(String.valueOf(words_new));
            myWriter.close();
            System.out.println("Сохранено успешно.");
        } catch (Exception e) {
            System.out.println("Создание файла провалилось.");
            e.printStackTrace();
        }
    }
}
