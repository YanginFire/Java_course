package org.example.task4.database;

import org.example.task4.objects.AccountTypes;
import org.example.task4.objects.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Accounts {

    private static ArrayList<User> users = new ArrayList<>(Arrays.asList(
            new User("lololoshka2006@gmail.com",
                    "lol",
                    "Вася",
                    "Пупкин",
                    "Пупкович",
                    "ул.Приколов д.1",
                    AccountTypes.READER),
            new User(
                    "sveta220797@gmail.com",
                    "220797",
                    "Светлана",
                    "Коробкова",
                    "Васильевна",
                    "ул.Лошков д.12",
                    AccountTypes.WORKER),
            new User(
                    "kpopgirl@gmail.com",
                    "btslove",
                    "Наргиз",
                    "Пак",
                    "Петровна",
                    "ул.Корейская д.111",
                    AccountTypes.MANAGER)
    ));

    /************* Функции проверки правильности информации в базе *************/

    /**
     * Функция проверяет существует ли уже аккаунт с таким email в базе
     * @param email пользователя
     * @return boolean
     */
    public static boolean checkEmail(String email) {
        return findUserByEmail(email) == null;
    }

    /************* Функции поиска информации в базе *************/

    /**
     * Функция ищет аккаунт по email
     * @param email пользователя
     * @return User
     */
    public static User findUserByEmail(String email) {
        for (User user : users) {
            if (Objects.equals(user.getEmail(), email)) {
                return user;
            }
        }

        return null;
    }

    /**
     * Функция возвращает всех пользлвателей в базе
     * @return ArrayList<User>
     */
    public static ArrayList<User> getAllUsers() {
        return users;
    }

    /************* Функции которые возвращают информацию о книгах в формате String *************/

    /**
     * Функция печататет информацию о всех пользователях в базе
     * @return String
     */
    public static String printUsers() {
        StringBuilder returnString = new StringBuilder();

        for (User user : users) {
            returnString.append(user.toString());
            returnString.append("\n");
        }

        if (returnString.isEmpty()) {
            return "В базе нет аккаунтов!";
        } else {
            return returnString.toString();
        }
    }


    /**
     * Функция печатает информацию об аккаунте по введенному email
     * @param email пользователя
     * @return String
     */
    public static String printUserByEmail(String email) {
        User user = findUserByEmail(email);

        if (user == null) {
            return "Сотрудника с таким email нет в базе";
        } else {
            return user.toString();
        }
    }

    /************* Функции добавления и удаления из базы *************/

    /**
     * Функция добавляет новый аккаунт в базу
     * @param user объект
     * @return boolean
     */
    public static boolean addUser(User user) {
        try {
            if(checkEmail(user.getEmail())) {
                users.add(user);
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Функция удаляет аккаунт из базы по email
     * @param email пользователя
     * @return boolean
     */
    public static boolean deleteUser(String email) {
        try {
            users.remove(findUserByEmail(email));
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Функция возвращает количество пользователей в базе
     * @return int
     */
    public static int userCount() {
        return users.size();
    }

}
