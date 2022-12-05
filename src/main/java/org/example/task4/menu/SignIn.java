package org.example.task4.menu;

import org.example.task4.objects.AccountTypes;
import org.example.task4.objects.User;

import java.util.Objects;
import java.util.Scanner;

import static org.example.task4.database.Accounts.*;
import static org.example.task4.menu.ManagerMenu.managerMenu;
import static org.example.task4.menu.ReaderMenu.readerMenu;
import static org.example.task4.menu.WorkerMenu.workerMenu;

/**
 * Меню регистрации и входа
 */
public class SignIn {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int choice = 0;
        String password;
        int checkResult;

        while (choice != -1) {
            System.out.print(
                    """
                    
                    ======================================================
                    Добро пожаловать в библиотеку! Чтобы выйти введите "-1"
                    У вас уже есть аккаунт?
                    1- Да
                    2- Нет
                    """);
            choice = in.nextInt();

            if (choice == 2) {
                if (!createAccount()) {
                    System.out.println("Не удалось создать аккаунт.");
                    continue;
                };
                System.out.println("Аккаунт успешно создан!");
            } else if (choice == -1) {
                System.out.println("До свидания!");
                continue;
            }

            signInMenu();
        }
    }


    /**
     * Функция входа в аккаунт
     */
    public static void signInMenu() {
        Scanner in = new Scanner(System.in);
        int answer;
        String email, password;
        User user;
        AccountTypes accountType;

        System.out.println("""
                            
                            Вход в аккаунт
                           
                            Введите email:""");
        email = in.nextLine();
        System.out.println("Введите пароль:");
        password = in.nextLine();

        // Проверка есть ли аккаунт в системе
        if (!checkEmail(email)) {
            user = findUserByEmail(email);
            if (Objects.equals(user.getPassword(), password)) {
                accountType = user.getAccountType();

                switch (accountType) {
                    case MANAGER: {
                        managerMenu(user);
                        break;
                    }
                    case WORKER: {
                        workerMenu(user);
                        break;
                    }
                    case READER: {
                        readerMenu(user);
                        break;
                    }
                    default: {
                        System.out.println("Неверный пароль");
                        break;
                    }
                }
            }
        } else {
            System.out.println("Аккаунта с таким email не существует");
        }

    }

    /**
     * Функция создает новый аккаунт и сохраняет его в базе
     * @return boolean
     */
    public static boolean createAccount() {
        Scanner in = new Scanner(System.in);
        int answer;
        String email, password;
        AccountTypes accountType;
        User account;

        System.out.println("""
                            
                            Создаем новый аккаунт.
                            
                            Какой аккаунт вы хотите создать?
                            1- Читатель
                            2- Работник библиотеки
                            3- Менеджер
                            """);
        answer = Integer.parseInt(in.nextLine());
        if (answer == 1) accountType = AccountTypes.READER;
        else if (answer == 2) accountType = AccountTypes.WORKER;
        else accountType = AccountTypes.MANAGER;

        System.out.println("Введите email (Внимание! Его нельзя будет изменить) :");
        email = in.nextLine();

        // проверка существует ли уже аккаунт с таким емейлом
        if (!checkEmail(email)) {
            System.out.println("Аккаунт с таким email уже существует");
            return false;
        }

        System.out.println("Придумайте пароль:");
        password = in.nextLine();

        account = new User(accountType, email, password);

        System.out.println("Введите имя:");
        account.setName(in.nextLine());
        System.out.println("Введите фамимлию:");
        account.setSurname(in.nextLine());
        System.out.println("Введите отчество:");
        account.setPatronymic(in.nextLine());
        System.out.println("Введите адрес проживания:");
        account.setAddress(in.nextLine());

        System.out.println(account.toString());
        System.out.println("""
                            Данные верны?
                            1- Да
                            2- Нет
                            """);
        answer = Integer.parseInt(in.nextLine());

        if (answer == 1) {
            addUser(account);
            return true;
        }

        return false;
    }


}
