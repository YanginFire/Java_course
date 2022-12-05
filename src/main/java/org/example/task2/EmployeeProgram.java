package org.example.task2;

import org.example.task2.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class EmployeeProgram {

    private static ArrayList<Employee> employees = new ArrayList<>(Arrays.asList(
            new Employee(1,
                    "Артем",
                    "Никифоров",
                    2002,
                    "Магадан",
                    "Не женат",
                    50000.0),
            new Employee(
                    2,"Никита",
                    "Панфилов",
                    1998,
                    "Магадан",
                    "Женат",
                    40000.0),
            new Employee(
                    3,
                    "Анна",
                    "Лебедь",
                    2000,
                    "Магадан",
                    "Замужем",
                    70000.0),
            new Employee(
                    4,
                    "Никита",
                    "Склифософский",
                    2001,
                    "Магадан",
                    "Не женат",
                    50500.0),
            new Employee(
                    5,
                    "Аркадий",
                    "Дурачок",
                    1998,
                    "Магадан",
                    "Женат",
                    35000.0))
    );

    /************* Функции проверки правильности информации в базе *************/

    /**
     * Функция проверяет не превысило ли количество сотрудников 100 человек
     * @return boolean
     */
    public static boolean checkEmployeeCounter() {
        return employees.size() <= 100;
    }

    /**
     * Функция проверяет существует ли уже сотрудник с таким id в базе
     * @param id сотрудника
     * @return boolean
     */
    public static boolean checkId(int id) {
        return findEmployeeById(id) == null;
    }


    /************* Функции поиска информации в базе *************/

    /**
     * Функция ищет сотрудника по id и возвращает его информацию
     * @param id сотрудника
     * @return Employee
     */
    public static Employee findEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }

        System.out.println("Сотрудника с таким id не существует");
        return null;
    }

    /**
     * Функция возвращает всех сотрудников с именем аналогичным введенному
     * @param name имя сотрудника(ов)
     * @return ArrayList<Employee>
     */
    public static ArrayList<Employee> findEmployeeByName(String name) {
        ArrayList<Employee> employeeList = new ArrayList<Employee>();

        for (Employee employee : employees) {
            if (Objects.equals(employee.getName(), name)) {
                employeeList.add(employee);
            }
        }

        return employeeList;
    }

    /**
     * Функция возвращает всех сотрудников с годом рождения аналогичным введенному
     * @param yearOfBirth год рождения сотруднка(ов)
     * @return ArrayList<Employee>
     */
    public static ArrayList<Employee> findEmployeeByBirthYear(int yearOfBirth) {
        ArrayList<Employee> employeeList = new ArrayList<Employee>();

        for (Employee employee : employees) {
            if (employee.getYearOfBirth() == yearOfBirth) {
                employeeList.add(employee);
            }
        }

        return employeeList;
    }

    /************* Функции которые возвращают информацию о сотрудниках в формате String *************/

    /**
     * Функция печататет информацию о всех сотрудниках в базе
     * @return String
     */
    public static String printEmployees() {
        StringBuilder returnString = new StringBuilder();

        for (Employee employee : employees) {
            returnString.append(employee.toString());
            returnString.append("\n");
        }

        System.out.println(employees);

        if (returnString.isEmpty()) {
            return "В базе нет сотрудников!";
        } else {
            return returnString.toString();
        }
    }

    /**
     * Функция печатает всех сотрудников с годом рождения аналогичным введенному
     * @param yearOfBirth год рождения сотруднка(ов)
     * @return Date
     */
    public static String printEmployeeByBirthYear(int yearOfBirth) {
        StringBuilder returnString = new StringBuilder();
        ArrayList<Employee> employeeList = findEmployeeByBirthYear(yearOfBirth);

        for (Employee employee : employeeList) {
            if (employee.getYearOfBirth() == yearOfBirth) {
                returnString.append(employee.toString());
                returnString.append("\n");
            }
        }

        if (returnString.isEmpty()) {
            return "Сотрудника родившегося в этот год не существует!";
        } else {
            return returnString.toString();
        }
    }

    /**
     * Функция печатает всех сотрудников с именем аналогичным введенному
     * @param name имя сотрудника(ов)
     * @return String
     */
    public static String printEmployeeByName(String name) {
        StringBuilder returnString = new StringBuilder();
        ArrayList<Employee> employeeList = findEmployeeByName(name);

        for (Employee employee : employeeList) {
            if (Objects.equals(employee.getName(), name)) {
                returnString.append(employee.toString());
                returnString.append("\n");
            }
        }

        if (returnString.isEmpty()) {
            return "Сотрудника с таким именем не существует!";
        } else {
            return returnString.toString();
        }
    }

    /**
     * Функция печатает информацию сотрудника с введенным id
     * @param id сотрудника
     * @return String
     */
    public static String printEmployeeById(int id) {
        StringBuilder returnString = new StringBuilder();
        Employee employee = findEmployeeById(id);

        if (employee == null) {
            return "Сотрудника с таким id не существует!";
        } else {
            return employee.toString();
        }
    }


    /************* Функции подсчета *************/

    /**
     * Функция возвращает общую сумму денег которую сотрудники получают в качестве зарплаты
     * @return double
     */
    public static double totalSalary() {
        double salarySum = 0;

        for (Employee employee : employees) {
            salarySum += employee.getSalary();
        }

        return salarySum;
    }


    /************* Функции изменения информации о сотруднике *************/

    /**
     * Функция для изменения id сотрудника, возвращает результат операции
     * @param id сотрудника
     * @param newId новый id
     * @return String
     */
    public static String changeEmployeeId(int id, int newId) {
        if (checkId(newId)) {
            findEmployeeById(id).setId(newId);
            return "Id успешно изменен";
        }
        return "Сотрудник с таким Id уже сущестсвует!";
    }

    /**
     * Функция для изменения имени сотрудника
     * @param id сотрудника
     * @param newName новое имя
     */
    public static void changeEmployeeName(int id, String newName) {
        findEmployeeById(id).setName(newName);
    }

    /**
     * Функция для изменения фамилии сотрудника
     * @param id сотрудника
     * @param newSurname новая фамилия
     */
    public static void changeEmployeeSurname(int id, String newSurname) {
        findEmployeeById(id).setFamilyName(newSurname);
    }

    /**
     * Функция для изменения зарплаты сотрудника
     * @param id сотрудника
     * @param newSalary новая зарплата
     */
    public static void changeEmployeeSalary(int id, double newSalary) {
        findEmployeeById(id).setSalary(newSalary);
    }

    /**
     * Функция для изменения года рождения сотрудника
     * @param id сотрудника
     * @param newYearOfBirth новый год рождения
     */
    public static void changeEmployeeYearOfBirth(int id, int newYearOfBirth) {
        findEmployeeById(id).setYearOfBirth(newYearOfBirth);
    }

    /**
     * Функция для изменения места рождения сотрудника
     * @param id сотрудника
     * @param newPlaceOfBirth новое место рождения
     */
    public static void changeEmployeePlaceOfBirth(int id, String newPlaceOfBirth) {
        findEmployeeById(id).setPlaceOfBirth(newPlaceOfBirth);
    }

    /**
     * Функция для изменения семейного положения сотрудника
     * @param id сотрудника
     * @param newMaritalStatus новое семейное положение
     */
    public static void changeEmployeeMaritalStatus(int id, String newMaritalStatus) {
        findEmployeeById(id).setMaritaLStatus(newMaritalStatus);
    }


}
