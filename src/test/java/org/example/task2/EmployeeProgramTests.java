package org.example.task2;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


public class EmployeeProgramTests {


    /************* Функции проверки правильности информации в базе *************/

    @Test
    @DisplayName("Тестируем проверку максимального размера базы (EmployeeProgram.checkEmployeeCounter)")
    void testCheckEmployeeCounter()
    {
        assertTrue(EmployeeProgram.checkEmployeeCounter());
    }

    @Test
    @DisplayName("Тестируем проверку того существует ли id в базе (EmployeeProgram.checkId)")
    void testCheckId()
    {
        assertTrue(EmployeeProgram.checkId(9));    // id нет в базе
        assertFalse(EmployeeProgram.checkId(1));   // id есть в базе
    }


    /************* Функции поиска информации в базе *************/

    @Test
    @DisplayName("Тестируем поиск сотрудника по Id (EmployeeProgram.findEmployeeById)")
    void testFindEmployeeById()
    {
        assertEquals(1, EmployeeProgram.findEmployeeById(1).getId());
    }

    @Test
    @DisplayName("Тестируем поиск сотрудника по имени (EmployeeProgram.findEmployeeByName)")
    void testFindEmployeesByName()
    {
        Employee []answerList = EmployeeProgram.findEmployeeByName("Никита").toArray(new Employee[2]);

        boolean condition = answerList.length == 2 &&
                Objects.equals(answerList[0].getName(), "Никита") &&
                Objects.equals(answerList[1].getName(), "Никита");

        assertTrue(condition);
    }

    @Test
    @DisplayName("Тестируем поиск сотрудника по году рождения (EmployeeProgram.findEmployeeByBirthYear)")
    void testFindEmployeeByBirthYear()
    {
        Employee []answerList = EmployeeProgram.findEmployeeByBirthYear(1998).toArray(new Employee[2]);

        boolean condition = answerList.length == 2 &&
                Objects.equals(answerList[0].getYearOfBirth(), 1998) &&
                Objects.equals(answerList[1].getYearOfBirth(), 1998);

        assertTrue(condition);
    }


    /************* Функции которые возвращают информацию о сотрудниках в формате String *************/

    @Test
    @DisplayName("Тестируем вывод всех сотрудников в виде строки (EmployeeProgram.printEmployees)")
    void testPrintEmployees()
    {
        String expected =
                "id: 1, Имя: Артем, Фамилия: Никифоров, Зарплата: 50000.0, Год рождения : 2002, Место рождения : Магадан, Семейное положение : Не женат\n" +
                "id: 2, Имя: Никита, Фамилия: Панфилов, Зарплата: 40000.0, Год рождения : 1998, Место рождения : Магадан, Семейное положение : Женат\n" +
                "id: 3, Имя: Анна, Фамилия: Лебедь, Зарплата: 70000.0, Год рождения : 2000, Место рождения : Магадан, Семейное положение : Замужем\n" +
                "id: 4, Имя: Никита, Фамилия: Склифософский, Зарплата: 50500.0, Год рождения : 2001, Место рождения : Магадан, Семейное положение : Не женат\n" +
                "id: 5, Имя: Аркадий, Фамилия: Дурачок, Зарплата: 35000.0, Год рождения : 1998, Место рождения : Магадан, Семейное положение : Женат\n";

        assertTrue(Objects.equals(EmployeeProgram.printEmployees(), expected));
    }

    @Test
    @DisplayName("Тестируем вывод сотрудников по году рождения в виде строки (EmployeeProgram.printEmployeesByBirthYear)")
    void testPrintEmployeesByBirthYear()
    {
        String expected =
                        "id: 2, Имя: Никита, Фамилия: Панфилов, Зарплата: 40000.0, Год рождения : 1998, Место рождения : Магадан, Семейное положение : Женат\n" +
                        "id: 5, Имя: Аркадий, Фамилия: Дурачок, Зарплата: 35000.0, Год рождения : 1998, Место рождения : Магадан, Семейное положение : Женат\n";

        assertTrue(Objects.equals(EmployeeProgram.printEmployeeByBirthYear(1998), expected));
    }

    @Test
    @DisplayName("Тестируем вывод сотрудников по имени в виде строки (EmployeeProgram.printEmployeesByName)")
    void testPrintEmployeesByName()
    {
        String expected =
                        "id: 2, Имя: Никита, Фамилия: Панфилов, Зарплата: 40000.0, Год рождения : 1998, Место рождения : Магадан, Семейное положение : Женат\n" +
                        "id: 4, Имя: Никита, Фамилия: Склифософский, Зарплата: 50500.0, Год рождения : 2001, Место рождения : Магадан, Семейное положение : Не женат\n";

        assertTrue(Objects.equals(EmployeeProgram.printEmployeeByName("Никита"), expected));
    }

    @Test
    @DisplayName("Тестируем вывод сотрудника по id (EmployeeProgram.printEmployeeById)")
    void testPrintEmployeeById()
    {
        String expected = "id: 3, Имя: Анна, Фамилия: Лебедь, Зарплата: 70000.0, Год рождения : 2000, Место рождения : Магадан, Семейное положение : Замужем";

        assertTrue(Objects.equals(EmployeeProgram.printEmployeeById(3), expected));
    }


    /************* Функции подсчета *************/

    @Test
    @DisplayName("Тестируем вывод суммы зарплат всех сотрудников (EmployeeProgram.totalSalary)")
    void testTotalSalary()
    {
       double expected = 245500.0;

        assertTrue(EmployeeProgram.totalSalary() == 245500.0);
    }

    /************* Функции изменения информации о сотруднике *************/

    @Test
    @DisplayName("Тестируем изменение id сотрудника (EmployeeProgram.changeEmployeeId)")
    void testChangeEmployeeId()
    {
        int newId = 23;

        EmployeeProgram.changeEmployeeId(2, 23);

        assertFalse(EmployeeProgram.checkId(23));  // Проверяем что сотрудник с таким id есть в базе

        EmployeeProgram.changeEmployeeId(23, 2); // Возвращаем индекс к старому значению
    }

    @Test
    @DisplayName("Тестируем изменение имени сотрудника (EmployeeProgram.changeEmployeeName)")
    void testChangeEmployeeName()
    {
        String newName = "Сергей";
        String oldName = EmployeeProgram.findEmployeeById(4).getName();

        EmployeeProgram.changeEmployeeName(4, newName);

        assertEquals(EmployeeProgram.findEmployeeById(4).getName(), newName);  // Проверяем поменялось ли имя сотрудника

        EmployeeProgram.changeEmployeeName(4, oldName); // Возвращаем имя к старому значению
    }

    @Test
    @DisplayName("Тестируем изменение фамилии сотрудника (EmployeeProgram.changeEmployeeSurname)")
    void testChangeEmployeeSurname()
    {
        String newSurname = "Пупкин";
        String oldSurname = EmployeeProgram.findEmployeeById(4).getFamilyName();

        EmployeeProgram.changeEmployeeSurname(4, newSurname);

        assertTrue(Objects.equals(EmployeeProgram.findEmployeeById(4).getFamilyName(), newSurname));  // Проверяем поменялась ли фамилия сотрудника

        EmployeeProgram.changeEmployeeName(4, oldSurname); // Возвращаем фамилию к старому значению
    }


    @Test
    @DisplayName("Тестируем изменение зарплаты сотрудника (EmployeeProgram.changeEmployeeSalary)")
    void testChangeEmployeeSalary()
    {
        double newSalary = 15000.95;
        double oldSalary = EmployeeProgram.findEmployeeById(1).getSalary();

        EmployeeProgram.changeEmployeeSalary(1, newSalary);

        assertTrue(Objects.equals(EmployeeProgram.findEmployeeById(1).getSalary(), newSalary));  // Проверяем поменялась ли зарплата сотрудника

        EmployeeProgram.changeEmployeeSalary(1, oldSalary); // Возвращаем зарплату к старому значению

    }

    @Test
    @DisplayName("Тестируем изменение года рождения сотрудника (EmployeeProgram.changeEmployeeYearOfBirth)")
    void testChangeEmployeeYearOfBirth()
    {
        int newYearOfBirth = 1997;
        int oldYearOfBirth = EmployeeProgram.findEmployeeById(5).getYearOfBirth();

        EmployeeProgram.changeEmployeeYearOfBirth(5, newYearOfBirth);

        assertTrue(Objects.equals(EmployeeProgram.findEmployeeById(5).getYearOfBirth(), newYearOfBirth));  // Проверяем поменялся ли год рождения сотрудника

        EmployeeProgram.changeEmployeeYearOfBirth(5, oldYearOfBirth); // Возвращаем год рождения к старому значению

    }

    @Test
    @DisplayName("Тестируем изменение места рождения сотрудника (EmployeeProgram.changeEmployeePlaceOfBirth)")
    void testChangeEmployeePlaceOfBirth()
    {
        String newPlaceOfBirth = "Ессентуки";
        String oldPlaceOfBirth = EmployeeProgram.findEmployeeById(2).getPlaceOfBirth();

        EmployeeProgram.changeEmployeePlaceOfBirth(2, newPlaceOfBirth);

        assertTrue(Objects.equals(EmployeeProgram.findEmployeeById(2).getPlaceOfBirth(), newPlaceOfBirth));  // Проверяем поменялось ли место рождения сотрудника

        EmployeeProgram.changeEmployeePlaceOfBirth(2, oldPlaceOfBirth); // Возвращаем место рождения к старому значению

    }

    @Test
    @DisplayName("Тестируем изменение семейного положения сотрудника (EmployeeProgram.changeEmployeeMaritalStatus)")
    void testChangeEmployeeMaritalStatus()
    {
        String newMaritalStatus = "Ессентуки";
        String oldMaritalStatus = EmployeeProgram.findEmployeeById(1).getMaritaLStatus();

        EmployeeProgram.changeEmployeeMaritalStatus(1, newMaritalStatus);

        assertTrue(Objects.equals(EmployeeProgram.findEmployeeById(1).getMaritaLStatus(), newMaritalStatus));  // Проверяем поменялось ли семейное положение сотрудника

        EmployeeProgram.changeEmployeeMaritalStatus(1, oldMaritalStatus); // Возвращаем семейное положение к старому значению

    }



}
