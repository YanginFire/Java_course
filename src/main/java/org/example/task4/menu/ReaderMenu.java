package org.example.task4.menu;

import org.example.task4.objects.User;

import java.util.Scanner;

/**
 * Меню читателя
 */
public class ReaderMenu {
    public static void readerMenu(User reader) {
        Scanner in = new Scanner(System.in);
        int answer = -1;

        while (answer != 3) {
            System.out.print(
                    """
                    
                    ================ Меню ================
                    1- вернуть книгу
                    2- попросить сотрудника выдать книгу
                    3- выход
                    """);
            answer = in.nextInt();

            switch (answer) {
                case 1: {
                    if(!readerReturnBook(reader)) System.out.println("Операция провалилась!");
                    break;
                }
                case 2: {
                    if(!readerReserveBook(reader)) System.out.println("Операция провалилась!");
                    break;
                }
                default: {
                    System.out.println("До свидания!");
                    break;
                }
            }
        }
    }

    /**
     * Функция возвращения книги в библиотеку
     * @param reader аккаунт читателя
     * @return boolean
     */
    public static boolean readerReturnBook(User reader) {
        Scanner in = new Scanner(System.in);
        String isbn;

        System.out.println("Введите isbn книги которую вы хотите вернуть:");
        isbn = in.nextLine();

        if(reader.readerAcc.returnBook(isbn)) return true;

        return false;
    }

    /**
     * Функция запроса на книгу у библиотекаря
     * @param reader аккаунт читателя
     * @return boolean
     */
    public static boolean readerReserveBook(User reader) {
        Scanner in = new Scanner(System.in);
        String isbn;

        System.out.println("Введите isbn книги которую вы хотите забронировать:");
        isbn = in.nextLine();

        if(reader.readerAcc.reserveBook(isbn)) return true;

        return false;
    }
}
