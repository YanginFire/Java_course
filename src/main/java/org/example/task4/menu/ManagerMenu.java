package org.example.task4.menu;

import org.example.task4.objects.Book;
import org.example.task4.objects.BookCategories;
import org.example.task4.objects.User;

import java.util.Scanner;

/**
 * Меню менеджера
 */
public class ManagerMenu {

    public static void managerMenu(User manager) {
        Scanner in = new Scanner(System.in);
        int answer = -1;

        while (answer != 4) {
            System.out.print(
                    """
                                                
                            ================ Меню ================
                            1- добавить книгу в библиотеку
                            2- удалить книгу из библиотеки
                            3- статистика
                            4- выход
                            """);
            answer = in.nextInt();

            switch (answer) {
                case 1: {
                    if (!managerAddBook(manager)) System.out.println("Операция провалилась!");
                    break;
                }
                case 2: {
                    if (!managerDeleteBook(manager)) System.out.println("Операция провалилась!");
                    break;
                }
                case 3: {
                    System.out.println(manager.managerAcc.statistics());
                    break;
                }
                default:
                    System.out.println("До свидания!");
                    break;

            }
        }
    }

    /**
     * Функция добавления книги в библиотеку
     * @param manager аккаунт менеджера
     * @return boolean
     */
    public static boolean managerAddBook(User manager) {
        Scanner in = new Scanner(System.in);
        String isbn;
        int answer;
        Book newBook = new Book();

        System.out.println("Введите информацию о книге.");
        System.out.println("Введите isbn книги:");
        newBook.setIsbn(in.nextLine());
        System.out.println("Введите название книги:");
        newBook.setName(in.nextLine());
        System.out.println("Введите автора книги:");
        newBook.setAuthor(in.nextLine());
        System.out.println("Введите номер издания:");
        newBook.setEdition(Integer.parseInt(in.nextLine()));
        System.out.println("Введите издателя:");
        newBook.setPublisher(in.nextLine());
        System.out.println("""
                Выберите категорию книги:
                                
                1- ужасы
                2- романтика
                3- комедия
                4- история
                """);
        answer = Integer.parseInt(in.nextLine());
        switch (answer) {
            case 1:
                newBook.setCategory(BookCategories.HORROR);
                break;
            case 2:
                newBook.setCategory(BookCategories.ROMANCE);
                break;
            case 3:
                newBook.setCategory(BookCategories.COMEDY);
                break;
            case 4:
                newBook.setCategory(BookCategories.HISTORY);
                break;
            default: {
                System.out.println("Неверная категория.");
                break;
            }
        }

        System.out.println(newBook.toString());

        System.out.println("""
                Данные верны?
                1- Да
                2- Нет
                """);
        answer = Integer.parseInt(in.nextLine());

        if (answer == 1) {
            if (manager.managerAcc.addBookToLibrary(newBook)) return true;
        }

        return false;
    }


    /**
     * Функция удаления книги из библиотеки
     * @param manager аккаунт менеджера
     * @return boolean
     */
    public static boolean managerDeleteBook(User manager) {
        Scanner in = new Scanner(System.in);
        String isbn;

        System.out.println("Введите isbn книги которую вы хотите удалить:");
        isbn = in.nextLine();

        if (manager.managerAcc.deleteBookFromLibrary(isbn)) return true;

        return false;
    }
}
