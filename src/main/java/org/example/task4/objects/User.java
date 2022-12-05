package org.example.task4.objects;

import java.util.ArrayList;
import java.util.Objects;

import static org.example.task4.database.Accounts.*;
import static org.example.task4.database.Library.*;

/**
 *
 */
public class User {
    private String email;
    private String name;
    private String surname;
    private String patronymic;
    private String address;
    private String password;
    private AccountTypes accountType;

    public Manager managerAcc;
    public Reader readerAcc;
    public Worker workerAcc;


    public User(AccountTypes accountType, String email, String password) {
        this.accountType = accountType;
        this.email = email;
        this.password = password;

        if (accountType == AccountTypes.MANAGER) this.managerAcc = new Manager();
        else if (accountType == AccountTypes.WORKER) this.workerAcc = new Worker();
        else this.readerAcc = new Reader();
    }

    public User(String email, String password, String name, String surname, String patronymic, String address, AccountTypes accountType) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.address = address;
        this.accountType = accountType;

        if (accountType == AccountTypes.MANAGER) this.managerAcc = new Manager();
        else if (accountType == AccountTypes.WORKER) this.workerAcc = new Worker();
        else this.readerAcc = new Reader();
    }

    public class Manager {

        /**
         * Функция позволяет добавить книгу в базу
         *
         * @param book объект
         * @return boolean
         */
        public boolean addBookToLibrary(Book book) {
            try {
                addBook(book);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        /**
         * Функция позволяет менеджеру удалить книгу из базы
         *
         * @param isbn книги
         * @return boolean
         */
        public boolean deleteBookFromLibrary(String isbn) {
            try {
                deleteBook(isbn);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        /**
         * Функция возвращает статистику базы
         *
         * @return String
         */
        public String statistics() {
            StringBuilder returnString = new StringBuilder();
            ArrayList<User> users = getAllUsers();
            ArrayList<Book> reservedBooks, receivedBooks;
            int countReservations = 0;
            int countRecievedBoooks = 0;

            returnString.append("Всего книг в базе: " + bookCount() + "\n");
            returnString.append("Всего пользователей в базе: " + userCount() + "\n");

            for (User user : users) {
                if (user.getAccountType() == AccountTypes.READER) {
                    reservedBooks = user.readerAcc.getReservedBooks();
                    receivedBooks = user.readerAcc.getReceivedBooks();

                    countReservations += reservedBooks.size();
                    countRecievedBoooks += receivedBooks.size();
                }
            }

            returnString.append("Всего запросов на книги: " + countReservations + "\n");
            returnString.append("Всего выданных книг: " + countRecievedBoooks);

            return returnString.toString();
        }
    }


    public class Worker {

        /**
         * Функция позволяет библиотекарю найти книгу в базе
         *
         * @param isbn книги
         * @return Book
         */
        public Book findBook(String isbn) {
            try {
                return findBookByIsbn(isbn);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * Функция позволяет библиотекарю выдать книгу читателю
         *
         * @param isbn  книги
         * @param email читателя
         * @return boolean
         */
        public boolean giveBook(String isbn, String email) {
            User user;
            Book book;
            ArrayList<Book> receivedBooks;
            ArrayList<Book> reservedBooks;

            user = findUserByEmail(email);
            if (user == null) return false;

            book = findBookByIsbn(isbn);
            if (book == null) return false;

            receivedBooks = user.readerAcc.getReceivedBooks();
            reservedBooks = user.readerAcc.getReservedBooks();

            for (Book receivedBook : receivedBooks) {
                if (Objects.equals(receivedBook.getIsbn(), isbn)) return false;
            }

            try {
                receivedBooks.add(book);
                reservedBooks.remove(book);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        /**
         * Функция выводит список читателей и книг которые они запросили
         *
         * @return String
         */
        public String reservedBooksList() {
            StringBuilder returnString = new StringBuilder();
            ArrayList<User> users = getAllUsers();
            ArrayList<Book> reservedBooks;

            for (User user : users) {
                if (user.getAccountType() == AccountTypes.READER) {
                    reservedBooks = user.readerAcc.getReservedBooks();
                    for (Book book : reservedBooks) {
                        returnString.append(
                                " " + book.getIsbn() +
                                        " " + book.getName() +
                                        " " + user.getEmail() +
                                        " " + user.getName() +
                                        " " + user.getSurname());
                        returnString.append("\n");
                    }
                }
            }


            if (returnString.isEmpty()) {
                return "Нет запрошенных книг";
            } else {
                return returnString.toString();
            }
        }

    }

    public class Reader {
        private ArrayList<Book> reservedBooks = new ArrayList<Book>();
        private ArrayList<Book> receivedBooks = new ArrayList<Book>();

        public ArrayList<Book> getReservedBooks() {
            return reservedBooks;
        }

        public void setReservedBooks(ArrayList<Book> reservedBooks) {
            this.reservedBooks = reservedBooks;
        }

        public ArrayList<Book> getReceivedBooks() {
            return receivedBooks;
        }

        public void setReceivedBooks(ArrayList<Book> receivedBooks) {
            this.receivedBooks = receivedBooks;
        }

        /**
         * Функция позволяет читателю вернуть книгу в библиотеку
         *
         * @param isbn
         * @return
         */
        public boolean returnBook(String isbn) {
            for (Book book : receivedBooks) {
                if (Objects.equals(book.getIsbn(), isbn)) {
                    receivedBooks.remove(book);
                    return true;
                }
            }
            return false;
        }

        /**
         * Функция позволяет читателю запросить книгу у библиотекаря
         *
         * @param isbn
         * @return boolean
         */
        public boolean reserveBook(String isbn) {
            for (Book book : receivedBooks) {
                if (Objects.equals(book.getIsbn(), isbn)) {
                    return false;
                }
            }
            for (Book book : reservedBooks) {
                if (Objects.equals(book.getIsbn(), isbn)) {
                    return false;
                }
            }
            try {
                reservedBooks.add(findBookByIsbn(isbn));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AccountTypes getAccountType() {
        return accountType;
    }

    @Override
    public String toString() {
        return "Email: " + email +
                ", Имя: " + name +
                ", Фамилия: " + surname +
                ", Отчество: " + patronymic +
                ", Адрес: " + address +
                ", Тип аккаунта: " + accountType;
    }
}
