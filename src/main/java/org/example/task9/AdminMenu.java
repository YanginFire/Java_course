package org.example.task9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AdminMenu implements ActionListener {
    /*
     * Может добавлять других пользователей
     * и управлять всеми данными сотрудников
     */

//    Интерфейс для администратора

    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mydb";
    static final String USERNAME = "root";
    static final String PASSWORD = "root";
    private static JLabel userlabel;
    private static JTextField textField;
    private static JLabel passwordlabel;
    private static JPasswordField passwordField;
    private static JButton button;


    public static void main(String[] args) {

        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        /* Для форм заполнения логина и пароля */

        frame.setSize(400,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);

        panel.setLayout(null);

        userlabel = new JLabel("User");
        userlabel.setBounds(10,20,80,25);
        panel.add(userlabel);

        textField = new JTextField(20);
        textField.setBounds(100,20,165,25);
        panel.add(textField);

        passwordlabel = new JLabel("Password");
        passwordlabel.setBounds(10,80,80,25);
        panel.add(passwordlabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100,80,165,25);
        panel.add(passwordField);

        button = new JButton("Log In");
        button.setBounds(10,120,80,25);
        button.addActionListener(new AdminMenu());
        panel.add(button);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = textField.getText();
        String password = passwordField.getText();

        if (user.equals("root") && password.equals("root")){
            System.out.println("Log In Admin");

            connectToDatabase();

            frame_for_admin();


        }

        else if (user.equals("root1") && password.equals("root1")){
            System.out.println("Log In  Redactor");

        }

        else if (user.equals("root2") && password.equals("root2")) {
            System.out.println("Log In  Viewer");

        }

        else {
            System.out.println("Invalid password");
        }
    }

    /**
     * Функция для отрисовки интерфейса администратора
     * <p>
     * ADD - done
     * Update function not done yet!!!
     *
     * @return
     */
    public static Component frame_for_admin() {


        JPanel adminpanel = new JPanel();
        JFrame adminframe = new JFrame();
        JButton addButton = new JButton();
        JButton updateButton = new JButton();

        adminframe.setSize(600,600);
        adminframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminframe.setVisible(true);
        adminframe.add(adminpanel);

        addButton = new JButton("Add something");
        addButton.setBounds(10,320,350,300);


        /**
         * Функция которая спрашивает пользователя здесь ли он?
         */

        JOptionPane optionPane = new JOptionPane();

        Timer timer = new Timer(15000, e -> {
            optionPane.setVisible(false);
            optionPane.getRootFrame().dispose();
        });
        timer.setRepeats(false);
        timer.start();


        int n = optionPane.showConfirmDialog(null, "Are you here?", null, JOptionPane.YES_NO_OPTION);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Info added");
                addEmployee();

            }
        });

        adminpanel.add(addButton);

        updateButton = new JButton("Update something");
        updateButton.setBounds(10,300,350,300);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Info updated");
                updateEmployee();

            }
        });
        adminpanel.add(updateButton);
        return null;
    }

    /**
     * НЕ РАБОТАЕТ ОШИБКА В SQL????
     */
    public static void updateEmployee(){
        JPanel addpanel = new JPanel();

        JFrame addframe = new JFrame();
        addframe.setLayout(new BorderLayout());
        JButton addButUpdate = new JButton("Обновить");
        JLabel Idlabel = new JLabel("Введите id сотрудника которого хотите поменять:");
        JLabel namelabel = new JLabel("Введите имя сотрудника:");
        JLabel surnamelabel = new JLabel("Введите фамилию");
        JLabel birthdatelabel = new JLabel("Дата рождения сотрудника (формат DD.MM.YY):");
        JLabel birthplacelabel = new JLabel("Введите место рождения сотрудника:");
        JLabel salarylabel = new JLabel("Введите зарплату сотрудника:");
        JLabel statuslabel = new JLabel("Введите семейное положение сотрудника:");

        JLabel nothing = new JLabel(" ");
        JTextField Id = new JTextField(20);
        JTextField firstname = new JTextField(20);
        JTextField surname = new JTextField(20);
        JTextField birthdate = new JTextField(20);
        JTextField birthplace = new JTextField(20);
        JTextField salary = new JTextField(20);
        JTextField status = new JTextField(20);


        addframe.setSize(280,350);
        addframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addframe.add(addpanel, BorderLayout.CENTER);

        Idlabel.setBounds(300,600,80,25);
        addpanel.add(Idlabel, BorderLayout.CENTER);
        addpanel.add(nothing, BorderLayout.CENTER);
        Id.setBounds(300,540,400,450);
        addpanel.add(Id, BorderLayout.CENTER);

        namelabel.setBounds(300,600,80,25);
        addpanel.add(namelabel, BorderLayout.CENTER);
        addpanel.add(nothing, BorderLayout.CENTER);
        firstname.setBounds(300,540,400,450);
        addpanel.add(firstname, BorderLayout.CENTER);

        surnamelabel.setBounds(300,500,80,25);
        addpanel.add(surnamelabel, BorderLayout.CENTER);
        addpanel.add(nothing, BorderLayout.CENTER);
        surname.setBounds(300,420,165,25);
        addpanel.add(surname, BorderLayout.CENTER);


        birthdatelabel.setBounds(300,360,80,25);
        addpanel.add(birthdatelabel, BorderLayout.CENTER);
        addpanel.add(nothing, BorderLayout.CENTER);
        birthdate.setBounds(300,300,165,25);
        addpanel.add(birthdate, BorderLayout.CENTER);

        birthplacelabel.setBounds(300,240,80,25);
        addpanel.add(birthplacelabel, BorderLayout.CENTER);
        addpanel.add(nothing, BorderLayout.CENTER);
        birthplace.setBounds(300,180,165,25);
        addpanel.add(birthplace, BorderLayout.CENTER);

        salarylabel.setBounds(300,120,80,25);
        addpanel.add(salarylabel, BorderLayout.CENTER);
        addpanel.add(nothing, BorderLayout.CENTER);
        salary.setBounds(300,60,165,25);
        addpanel.add(salary, BorderLayout.CENTER);

        statuslabel.setBounds(300,120,80,25);
        addpanel.add(statuslabel, BorderLayout.CENTER);
        addpanel.add(nothing, BorderLayout.CENTER);
        status.setBounds(300,60,165,25);
        addpanel.add(status, BorderLayout.CENTER);

        addButUpdate.setBounds(300,0,350,300);
        addpanel.add(addButUpdate, BorderLayout.CENTER);



        addframe.setVisible(true);

        addButUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String DATABASE_URL = "jdbc:mysql://localhost:3306/mydb";
                final String USERNAME = "root";
                final String PASSWORD = "root";

                int id = Integer.parseInt(Id.getText());
                String firstName = firstname.getText();
                String surName = surname.getText();
                String birthDate = birthdate.getText();
                String birthPlace = birthplace.getText();
                Double salarys = Double.valueOf(salary.getText());
                String statuss = status.getText();


                String QUERY = "UPDATE people SET ";

                QUERY += "('" +
                        id + "','" +
                        firstName + "','" +
                        surName + "','" +
                        birthDate + "','" +
                        birthPlace + "'," +
                        salarys + ",'" +
                        statuss
                        + "')";

                Connection conn = null;
                try {
                    conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(QUERY);
                    System.out.println("Сотрудник успешно добавлен.");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }
    /**
     * Сделано
     */
    public static void addEmployee() {
        JPanel addpanel = new JPanel();

        JFrame addframe = new JFrame();
        addframe.setLayout(new BorderLayout());
        JButton addBut = new JButton("Добавить");
        JLabel namelabel = new JLabel("Введите имя сотрудника:");
        JLabel surnamelabel = new JLabel("Введите фамилию");
        JLabel birthdatelabel = new JLabel("Дата рождения сотрудника (формат DD.MM.YY):");
        JLabel birthplacelabel = new JLabel("Введите место рождения сотрудника:");
        JLabel salarylabel = new JLabel("Введите зарплату сотрудника:");
        JLabel statuslabel = new JLabel("Введите семейное положение сотрудника:");

        JLabel nothing = new JLabel(" ");
        JTextField firstname = new JTextField(20);
        JTextField surname = new JTextField(20);
        JTextField birthdate = new JTextField(20);
        JTextField birthplace = new JTextField(20);
        JTextField salary = new JTextField(20);
        JTextField status = new JTextField(20);


        addframe.setSize(280,350);
        addframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addframe.add(addpanel, BorderLayout.CENTER);

        namelabel.setBounds(300,600,80,25);
        addpanel.add(namelabel, BorderLayout.CENTER);
        addpanel.add(nothing, BorderLayout.CENTER);
        firstname.setBounds(300,540,400,450);
        addpanel.add(firstname, BorderLayout.CENTER);

        surnamelabel.setBounds(300,500,80,25);
        addpanel.add(surnamelabel, BorderLayout.CENTER);
        addpanel.add(nothing, BorderLayout.CENTER);
        surname.setBounds(300,420,165,25);
        addpanel.add(surname, BorderLayout.CENTER);


        birthdatelabel.setBounds(300,360,80,25);
        addpanel.add(birthdatelabel, BorderLayout.CENTER);
        addpanel.add(nothing, BorderLayout.CENTER);
        birthdate.setBounds(300,300,165,25);
        addpanel.add(birthdate, BorderLayout.CENTER);

        birthplacelabel.setBounds(300,240,80,25);
        addpanel.add(birthplacelabel, BorderLayout.CENTER);
        addpanel.add(nothing, BorderLayout.CENTER);
        birthplace.setBounds(300,180,165,25);
        addpanel.add(birthplace, BorderLayout.CENTER);

        salarylabel.setBounds(300,120,80,25);
        addpanel.add(salarylabel, BorderLayout.CENTER);
        addpanel.add(nothing, BorderLayout.CENTER);
        salary.setBounds(300,60,165,25);
        addpanel.add(salary, BorderLayout.CENTER);

        statuslabel.setBounds(300,120,80,25);
        addpanel.add(statuslabel, BorderLayout.CENTER);
        addpanel.add(nothing, BorderLayout.CENTER);
        status.setBounds(300,60,165,25);
        addpanel.add(status, BorderLayout.CENTER);

        addBut.setBounds(300,0,350,300);
        addpanel.add(addBut, BorderLayout.CENTER);

        addframe.setVisible(true);
        // Функция для кнопки добавления сотрудника в БД
        addBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String DATABASE_URL = "jdbc:mysql://localhost:3306/mydb";
                final String USERNAME = "root";
                final String PASSWORD = "root";

                String firstName = firstname.getText();
                String surName = surname.getText();
                String birthDate = birthdate.getText();
                String birthPlace = birthplace.getText();
                Double salarys = Double.valueOf(salary.getText());
                String statuss = status.getText();


                String QUERY = "INSERT INTO people(firstname,surname,birthdate,birthplace,salary,status) VALUES ";

                QUERY += "('" +
                        firstName + "','" +
                        surName + "','" +
                        birthDate + "','" +
                        birthPlace + "'," +
                        salarys + ",'" +
                        statuss
                        + "')";

                Connection conn = null;
                try {
                    conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(QUERY);
                    System.out.println("Сотрудник успешно добавлен.");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

    }
    /**
     * Сделано
     */
    public static boolean connectToDatabase() {
        String QUERY = "SELECT * FROM people";

        try (
                Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY);) {
            System.out.println("Подключение установлено!");
            while (rs.next()) {
                System.out.println("=========================== ID: " + rs.getInt("id") + " ==========================");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
