package org.example.task5;

import java.util.*;

class Customer {
    private String name;
    private String city;
    private int kmFromCity;

    public Customer() {
    }

    public Customer(String name, String city, int kmFromCity) {
        this.name = name;
        this.city = city;
        this.kmFromCity = kmFromCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getKmFromCity() {
        return kmFromCity;
    }

    public void setKmFromCity(int kmFromCity) {
        this.kmFromCity = kmFromCity;
    }

    @Override
    public String toString() {
        return "Имя: " + name + ", Город: " + city + ", Км до города: " + kmFromCity;
    }
}


public class three {
    public static void main(String[] args) {
        int choice = 0;
        Comparator<Customer> comparator = new Comparator<Customer>() {
            @Override
            public int compare(Customer c1, Customer c2) {
                if (c1.getKmFromCity() < c2.getKmFromCity()) {
                    return 1;
                }
                if (c1.getKmFromCity() > c2.getKmFromCity()) {
                    return -1;
                }
                return 0;
            }
        };
        PriorityQueue<Customer> queue = new PriorityQueue<>(20, comparator);

        while (choice != 3) {
            Scanner in = new Scanner(System.in);

            System.out.println("""
                    ================ Меню ================
                    1- добавить клиента(ов) в очередь
                    2- показать текущую очередь
                    3- выход
                    """);
            choice = Integer.parseInt(in.nextLine());

            switch (choice) {
                case 1: {
                    addClients(queue);
                    break;
                }
                case 2: {
                    int size = queue.size();
                    for (int i = 0; i < size; i++) {
                        System.out.println((i + 1) + ". " + queue.poll().toString());
                    }
                    break;
                }
                default: {
                    System.out.println("До свидания!");
                    break;
                }
            }
        }
    }

    public static PriorityQueue addClients(PriorityQueue queue) {
        String choice;

        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.println("=== Введите данные клиента ===");
            Customer customer = new Customer();
            System.out.println("Введите имя:");
            customer.setName(in.nextLine());
            System.out.println("Введите город из которого прибыл клиент:");
            customer.setCity(in.nextLine());
            System.out.println("Сколько километров отсюда до города откуда прибыл клиент?");
            customer.setKmFromCity(Integer.parseInt(in.nextLine()));
            queue.add(customer);

            System.out.println("В очереди осталось " + (20 - queue.size()) + " мест");
            System.out.println("Закончить добавление клиентов?(Да/Нет)");
            choice = in.nextLine();

            if (Objects.equals(choice, "Да")) break;
        }

        return queue;
    }
}
