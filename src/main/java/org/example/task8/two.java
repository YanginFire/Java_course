package org.example.task8;

import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

public class two {
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mydb";
    static final String USERNAME = "root";
    static final String PASSWORD = "root";

    public static void main(String[] args) {
        int choice = -1;

        if (connectToDatabase()) {
            while (choice != 8) {
                Scanner in = new Scanner(System.in);

                System.out.println("""
                        ================ Меню ================
                        1- добавить клиента в базу
                        2- искать сотрудника в базе по id
                        3- искать сотрудника(ов) в базе по имени
                        4- искать сотрудника(ов) в базе по дате рождения
                        5- изменить информацию о сотруднике
                        6- удалить сотрудника из базы
                        7- Расчитать общую сумму зарплаты
                        8- Выход
                        """);
                choice = Integer.parseInt(in.nextLine());

                switch (choice) {
                    case 1 -> addEmployee();
                    case 2 -> findEmployeeById();
                    case 3 -> findEmployeeByName();
                    case 4 -> findEmployeeByBirthdate();
                    case 5 -> changeEmployeeInfo();
                    case 6 -> deleteEmployee();
                    case 7 -> countSalarySum();
                    default -> System.out.println("До свидания!");
                }
            }
        } else {
            System.out.println("Не удалось подключиться к базе данных.");
        }


    }

    /**
     * Функция тестирует подключение к драйверу базы данных и выводит данные на экран
     *
     * @return boolean
     */
    public static boolean connectToDatabase() {
        String QUERY = "SELECT * FROM people";

        try (
                Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY);) {
            System.out.println("Подключение удалось!");
            while (rs.next()) {
                System.out.println("=========================== ID: " + rs.getInt("id") + " ==========================");
                System.out.println("Имя: " + rs.getString("firstname"));
                System.out.println("Фамилия: " + rs.getString("surname"));
                System.out.println("Дата Рождения: " + rs.getString("birthdate"));
                System.out.println("Место Рождения: " + rs.getString("birthplace"));
                System.out.println("Зарплата: " + rs.getString("salary"));
                System.out.println("Статус: " + rs.getString("status"));
                System.out.println("************************************************************");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Функция добавляет сотрудника в базу
     */
    public static void addEmployee() {
        String firstName, surname, birthdate, birthplace, status;
        double salary;

        String QUERY = "INSERT INTO people(firstname,surname,birthdate,birthplace,salary,status) VALUES ";

        Scanner in = new Scanner(System.in);

        System.out.println("Введите имя сотрудника:");
        firstName = in.nextLine();
        System.out.println("Введите фамилию сотрудника:");
        surname = in.nextLine();
        System.out.println("Введите дату рождения сотрудника (формат DD.MM.YY) :");
        birthdate = in.nextLine();
        System.out.println("Введите место рождения сотрудника:");
        birthplace = in.nextLine();
        System.out.println("Введите зарплату сотрудника:");
        salary = Double.parseDouble(in.nextLine());
        System.out.println("""
                Выберите семейный статус сотрудника:
                1- Не женат/Не замужем
                2- Женат/Замужем
                3- Разведен(а)
                """);
        status = in.nextLine();
        if (Objects.equals(status, "1")) status = "SINGLE";
        else if (Objects.equals(status, "2")) status = "MARRIED";
        else status = "DIVORCED";

        QUERY += "('" +
                firstName + "','" +
                surname + "','" +
                birthdate + "','" +
                birthplace + "'," +
                salary + ",'" +
                status
                + "')";

        try {
            Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(QUERY);
            System.out.println("Сотрудник успешно добавлен.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка.");
        }
    }

    /**
     * Функция ищет сотрудника в базе по id
     */
    public static void findEmployeeById() {
        String QUERY = "SELECT * FROM people WHERE id=";
        int id;

        Scanner in = new Scanner(System.in);

        System.out.println("Введите id сотрудника:");
        id = Integer.parseInt(in.nextLine());

        QUERY += id;

        try (
                Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY);) {
            System.out.println("Сотрудник найден!");
            while (rs.next()) {
                System.out.println("=========================== ID: " + rs.getInt("id") + " ==========================");
                System.out.println("Имя: " + rs.getString("firstname"));
                System.out.println("Фамилия: " + rs.getString("surname"));
                System.out.println("Дата Рождения: " + rs.getString("birthdate"));
                System.out.println("Место Рождения: " + rs.getString("birthplace"));
                System.out.println("Зарплата: " + rs.getString("salary"));
                System.out.println("Статус: " + rs.getString("status"));
                System.out.println("************************************************************");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка.");
        }
    }

    /**
     * Функция ищет сотрудника(ов) в базе по имени
     */
    public static void findEmployeeByName() {
        String QUERY = "SELECT * FROM people WHERE firstname=";
        String firstName;

        Scanner in = new Scanner(System.in);

        System.out.println("Введите имя сотрудника:");
        firstName = in.nextLine();

        QUERY += "'" + firstName + "'";

        try (
                Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY);) {
            System.out.println("Сотрудник(и) найден(ы)!");
            while (rs.next()) {
                System.out.println("=========================== ID: " + rs.getInt("id") + " ==========================");
                System.out.println("Имя: " + rs.getString("firstname"));
                System.out.println("Фамилия: " + rs.getString("surname"));
                System.out.println("Дата Рождения: " + rs.getString("birthdate"));
                System.out.println("Место Рождения: " + rs.getString("birthplace"));
                System.out.println("Зарплата: " + rs.getString("salary"));
                System.out.println("Статус: " + rs.getString("status"));
                System.out.println("************************************************************");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка.");
        }
    }

    /**
     * Функция ищет сотрудника(ов) в базе по дате рождения
     */
    public static void findEmployeeByBirthdate() {
        String QUERY = "SELECT * FROM people WHERE birthdate=";
        String birthdate;

        Scanner in = new Scanner(System.in);

        System.out.println("Введите дату рождения сотрудника (формат DD.MM.YY) :");
        birthdate = in.nextLine();

        QUERY += "'" + birthdate + "'";

        try (
                Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY);) {
            System.out.println("Сотрудник(и) найден(ы)!");
            while (rs.next()) {
                System.out.println("=========================== ID: " + rs.getInt("id") + " ==========================");
                System.out.println("Имя: " + rs.getString("firstname"));
                System.out.println("Фамилия: " + rs.getString("surname"));
                System.out.println("Дата Рождения: " + rs.getString("birthdate"));
                System.out.println("Место Рождения: " + rs.getString("birthplace"));
                System.out.println("Зарплата: " + rs.getString("salary"));
                System.out.println("Статус: " + rs.getString("status"));
                System.out.println("************************************************************");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка.");
        }
    }

    /**
     * Функция изменяет данные о сотруднике
     */
    public static void changeEmployeeInfo() {
        String firstName, surname, birthdate, birthplace, status;
        double salary;
        int id;

        String QUERY = "UPDATE people SET ";

        Scanner in = new Scanner(System.in);

        System.out.println("Введите id сотрудника информацию которого хотите поменять:");
        id = Integer.parseInt(in.nextLine());
        System.out.println("Введите новое имя сотрудника:");
        firstName = in.nextLine();
        System.out.println("Введите новую фамилию сотрудника:");
        surname = in.nextLine();
        System.out.println("Введите новую дату рождения сотрудника (формат DD.MM.YY) :");
        birthdate = in.nextLine();
        System.out.println("Введите новое место рождения сотрудника:");
        birthplace = in.nextLine();
        System.out.println("Введите новую зарплату сотрудника:");
        salary = Double.parseDouble(in.nextLine());
        System.out.println("""
                Выберите новый семейный статус сотрудника:
                1- Не женат/Не замужем
                2- Женат/Замужем
                3- Разведен(а)
                """);
        status = in.nextLine();
        if (Objects.equals(status, "1")) status = "SINGLE";
        else if (Objects.equals(status, "2")) status = "MARRIED";
        else status = "DIVORCED";

        QUERY += "firstname='" + firstName + "'," +
                "surname='" + surname + "'," +
                "birthdate='" + birthdate + "'," +
                "birthplace='" + birthplace + "'," +
                "salary=" + salary + "," +
                "status='" + status + "'" +
                " WHERE id=" + id;
        System.out.println(QUERY);

        try {
            Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(QUERY);
            System.out.println("Информация о сотруднике успещно изменена!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка.");
        }
    }

    /**
     * Функция удаляет сотрудника
     */
    public static void deleteEmployee() {
        String QUERY = "DELETE FROM people WHERE id=";
        int id;

        Scanner in = new Scanner(System.in);

        System.out.println("Введите id сотрудника которого вы хотите удалить:");
        id = Integer.parseInt(in.nextLine());

        QUERY += id;

        try {
            Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(QUERY);
            System.out.println("Сотрудник успешно удален!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка.");
        }
    }

    /**
     * Функция считает общую сумму зарплат сотрудников
     */
    public static void countSalarySum() {
        double salarySum = 0;
        String QUERY = "SELECT * FROM people";

        try (
                Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY);) {
            while (rs.next()) {
                salarySum += rs.getDouble("salary");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка.");
        }
        if (salarySum != 0) System.out.println("Cумма зарплат сотрудников: " + salarySum + "руб.\n");
    }
}
