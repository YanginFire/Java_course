package org.example.task4.menu;

import org.example.task4.objects.Book;
import org.example.task4.objects.User;

import java.util.Scanner;

/**
 * Меню библиотекаря
 */
public class WorkerMenu {

    public static void workerMenu(User worker) {
        Scanner in = new Scanner(System.in);
        int answer = -1;

        while (answer != 4) {
            System.out.print(
                    """
                                                
                            ================ Меню ================
                            1- найти книгу в библиотеке
                            2- выдать книгу читателю
                            3- список запросов на выдачу книг
                            4- выход
                            """);
            answer = in.nextInt();

            switch (answer) {
                case 1: {
                    System.out.println(workerFindBook(worker));
                    break;
                }
                case 2: {
                    if (!workerGiveBook(worker)) System.out.println("Операция не удалась.");
                    break;
                }
                case 3: {
                    System.out.println(worker.workerAcc.reservedBooksList());
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
     * Функция находит книгу в библиотеке
     * @param worker аккаунт библиотекаря
     * @return String
     */
    public static String workerFindBook(User worker) {
        Scanner in = new Scanner(System.in);
        String isbn;
        Book book;

        System.out.println("Введите isbn книги которую вы хотите найти:");
        isbn = in.nextLine();

        book = worker.workerAcc.findBook(isbn);

        if (book != null) return book.toString();

        return "Книга не найдена!";
    }

    /**
     * Функция выдает читателю книгу
     * @param worker аккаунт библиотекаря
     * @return boolean
     */
    public static boolean workerGiveBook(User worker) {
        Scanner in = new Scanner(System.in);
        String isbn, email;

        System.out.println("Введите email читателя:");
        email = in.nextLine();

        System.out.println("Введите isbn книги которую вы хотите выдать:");
        isbn = in.nextLine();

        if (worker.workerAcc.giveBook(isbn, email)) return true;

        return false;
    }


}
