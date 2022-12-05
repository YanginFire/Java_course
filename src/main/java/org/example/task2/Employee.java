package org.example.task2;

/**
 * Класс для хранения информации о сотрудниках
 */
public class Employee
{
    private String name = "";                              // Имя сотрудника
    private String familyName = "";                        // Фамилия сотрудника
    private int yearOfBirth;                               // Год рождения сотрудника
    private int id;                                        // id сотрудника
    private double salary;                                 // Зарплата сотрудника
    private String placeOfBirth = "";                      // Место рождения сотрудника
    private String maritaLStatus = "";                      // Семейное положение сотрудника

    public Employee() {
    }

    public Employee(int id, String name, String familyName, int yearOfBirth, String placeOfBirth, String maritaLStatus, double salary) {
        this.name = name;
        this.familyName = familyName;
        this.yearOfBirth = yearOfBirth;
        this.id = id;
        this.placeOfBirth = placeOfBirth;
        this.maritaLStatus = maritaLStatus;
        this.salary = salary;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getMaritaLStatus() {
        return maritaLStatus;
    }

    public void setMaritaLStatus(String maritaLStatus) {
        this.maritaLStatus = maritaLStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "id: " + id +
                ", Имя: " + name +
                ", Фамилия: " + familyName +
                ", Зарплата: " + salary +
                ", Год рождения : " + yearOfBirth +
                ", Место рождения : " + placeOfBirth +
                ", Семейное положение : " + maritaLStatus;
    }
}
