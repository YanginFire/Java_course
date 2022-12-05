package org.example.task4.database;

import org.example.task4.objects.Book;
import org.example.task4.objects.BookCategories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Library {

    private static ArrayList<Book> books = new ArrayList<>(Arrays.asList(
            new Book(
                    "978-5-17-092624-4",
                    "12 стульев",
                    "Илья Ильф, Евгений Петров",
                    1,
                    "АСТ",
                    2022,
                    BookCategories.COMEDY),
            new Book(
                    "978-5-9268-2814-3",
                    "Гордость и предубеждение",
                    "Джейн Остен",
                    4,
                    "Речь",
                    2018,
                    BookCategories.ROMANCE),
            new Book(
                    "978-5-17-102557-1",
                    "Под стеклянным колпаком",
                    "Сильвия Плат",
                    2,
                    "АСТ",
                    2017,
                    BookCategories.HISTORY)
    ));

    /************* Функции проверки правильности информации в базе *************/


    /**
     * Функция проверяет существует ли уже книга с таким isbn в базе
     *
     * @param isbn книги
     * @return boolean
     */
    public static boolean checkIsbn(String isbn) {
        return findBookByIsbn(isbn) == null;
    }


    /************* Функции поиска информации в базе *************/


    /**
     * Функция ищет книгу по isbn и возвращает ее
     *
     * @param isbn книги
     * @return Book
     */
    public static Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (Objects.equals(book.getIsbn(), isbn)) {
                return book;
            }
        }

        return null;
    }

    /**
     * Функция возвращает все книги с названием аналогичным введенному
     *
     * @param name книг(и)
     * @return ArrayList<Book>
     */
    public static ArrayList<Book> findBookByName(String name) {
        ArrayList<Book> bookList = new ArrayList<Book>();

        for (Book book : bookList) {
            if (Objects.equals(book.getName(), name)) {
                bookList.add(book);
            }
        }

        return bookList;
    }

    /************* Функции которые возвращают информацию о книгах в формате String *************/

    /**
     * Функция печататет информацию о всех книгах в базе
     *
     * @return String
     */
    public static String printBooks() {
        StringBuilder returnString = new StringBuilder();

        for (Book book : books) {
            returnString.append(book.toString());
            returnString.append("\n");
        }

        if (returnString.isEmpty()) {
            return "В базе нет книг!";
        } else {
            return returnString.toString();
        }
    }

    /**
     * Функция печатает все книги с названием аналогичным введенному
     *
     * @param name название книг(и)
     * @return String
     */
    public static String printBooksByName(String name) {
        StringBuilder returnString = new StringBuilder();
        ArrayList<Book> bookList = findBookByName(name);

        for (Book book : books) {
            if (Objects.equals(book.getName(), name)) {
                returnString.append(book.toString());
                returnString.append("\n");
            }
        }

        if (returnString.isEmpty()) {
            return "Книг с таким названием нет в базе";
        } else {
            return returnString.toString();
        }
    }

    /**
     * Функция печатает информацию о книге по введенному isbn
     *
     * @param isbn книги
     * @return String
     */
    public static String printBookById(String isbn) {
        Book book = findBookByIsbn(isbn);

        if (book == null) {
            return "Книги с таким isbn нет в базе";
        } else {
            return book.toString();
        }
    }

    /************* Функции добавления и удаления из базы *************/

    /**
     * Функция добавляет новую книгу в базу
     *
     * @param book объект
     * @return boolean
     */
    public static boolean addBook(Book book) {
        try {
            if (checkIsbn(book.getIsbn())) {
                books.add(book);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Функция удаляет книгу из базы по isbn
     *
     * @param isbn книги
     * @return boolean
     */
    public static boolean deleteBook(String isbn) {
        try {
            books.remove(findBookByIsbn(isbn));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /************* Статистика  *************/

    /**
     * Функция возвращает количество книг в базе
     *
     * @return int
     */
    public static int bookCount() {
        return books.size();
    }


}


