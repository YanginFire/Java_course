package org.example.task9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

class AdminMenu implements ActionListener {
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mydb";
    static final String USERNAME = "root";
    static final String PASSWORD = "root";
    private static JLabel userlabel;
    private static JTextField textField;
    private static JLabel passwordlabel;
    private static JPasswordField passwordField;
    private static JButton button;
    private static JButton regbutton;


    public static void main(String[] args) {

        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        /* Для форм заполнения логина и пароля */

        frame.setSize(500,250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);

        panel.setLayout(null);

        userlabel = new JLabel("Логин");
        userlabel.setBounds(10,20,80,25);
        panel.add(userlabel);

        textField = new JTextField(30);
        textField.setBounds(100,20,165,25);
        panel.add(textField);

        passwordlabel = new JLabel("Пароль");
        passwordlabel.setBounds(10,80,80,25);
        panel.add(passwordlabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100,80,165,25);
        panel.add(passwordField);

        button = new JButton("Войти");
        button.setBounds(10,120,80,25);
        button.addActionListener(new AdminMenu());
        panel.add(button);

        regbutton = new JButton("Регистрация");
        regbutton.setBounds(100,120,150,25);
        regbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Registration");
                JPanel regpanel = new JPanel();
                JFrame regframe = new JFrame();

                JLabel regLabel = new JLabel("Введите Логин");
                JLabel regLabel_pass = new JLabel("Введите Логин");

                JTextField log_in = new JTextField();
                JPasswordField reg_pass = new JPasswordField();

                JButton regbutton_n = new JButton();
                /* Для форм заполнения логина и пароля */

                regframe.setSize(200,200);
                regframe.setVisible(true);
                regframe.add(regpanel);

                regLabel = new JLabel("Введите Логин");
                regLabel.setBounds(10,20,80,25);
                regpanel.add(regLabel);

                log_in = new JTextField(20);
                log_in.setBounds(100,20,165,25);
                regpanel.add(log_in);

                regLabel_pass = new JLabel("Введите Пароль");
                regLabel_pass.setBounds(10,80,80,25);
                regpanel.add(regLabel_pass);

                reg_pass = new JPasswordField(20);
                reg_pass.setBounds(100,80,165,25);
                regpanel.add(reg_pass);

                regbutton_n = new JButton("Зарегистрироваться");
                regbutton_n.setBounds(100,80,165,25);
                regbutton_n.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        insert_into_login_and_passwords();
                    }
                });
                regpanel.add(regbutton_n);
            }
        });
        panel.add(regbutton);

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

            connectToDatabase();

            frame_for_editor();

        }

        else if (user.equals("root2") && password.equals("root2")) {
            System.out.println("Log In  Viewer");

            connectToDatabase();

            frame_for_viewer();

        }

        else {
            System.out.println("Invalid password");
        }
    }
    /**
     * Функция для отрисовки интерфейса администратора
     * <p>
     * delete and Update func - DONE
     *
     * @return
     */
    private static Component frame_for_editor() {
        JPanel editorpanel = new JPanel();
        JFrame editorframe = new JFrame("Editor interface");
        JButton updateButton = new JButton();
        JButton delButton = new JButton();

        editorframe.setSize(600,600);
        editorframe.setVisible(true);
        editorframe.add(editorpanel);


        updateButton = new JButton("Обновить данные");
        updateButton.setBounds(10,300,350,300);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Info updated");
                updateEmployee();

            }
        });
        editorpanel.add(updateButton);

        delButton = new JButton("Удалить");
        delButton.setBounds(10,300,350,300);
        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("The employee has been deleted");
                deleteEmployee();
            }
        });

        editorpanel.add(delButton);

        String QUERY = "SELECT * FROM people";
        String[] columnNames = {"id", "firstname", "surname", "birthdate", "birthplace", "salary", "status"};
        ArrayList<String[]> data = new ArrayList<>();


        try (
                Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY);) {
            System.out.println("Подключение установлено!");
            while (rs.next()) {
                String[] row = {rs.getString("id"),
                        rs.getString("firstname"),
                        rs.getString("surname"),
                        rs.getString("birthdate"),
                        rs.getString("birthplace"),
                        rs.getString("salary"),
                        rs.getString("status")};
                data.add(row);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Object[][] res = new Object[data.size()][];
        data.toArray(res);

        JTable table = new JTable(res, columnNames);
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        editorpanel.add(scrollPane);
        editorpanel.setVisible(true);

        return null;
    }

    /**
     * Функция для отрисовки интерфейса администратора
     * <p>
     * ADD, delete and Update func - DONE
     *
     * @return
     */
    public static Component frame_for_admin() {

        JPanel adminpanel = new JPanel();
        JFrame adminframe = new JFrame("Administrator interface");
        JButton addButton = new JButton();
        JButton updateButton = new JButton();


        JButton delete_Button = new JButton();

        JButton display_Button = new JButton();

        adminframe.setSize(600,600);
        adminframe.setVisible(true);
        adminframe.add(adminpanel);

        delete_Button = new JButton("Удалить");
        delete_Button.setBounds(10,320,350,300);

        delete_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Delete data");
                deleteEmployee();
            }
        });
        adminpanel.add(delete_Button);

        addButton = new JButton("Добавить");
        addButton.setBounds(10,320,350,300);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Info added");
                addEmployee();

            }
        });

        adminpanel.add(addButton);

        updateButton = new JButton("Обновить данные");
        updateButton.setBounds(10,300,350,300);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Info updated");
                updateEmployee();

            }
        });
        adminpanel.add(updateButton);


        String QUERY = "SELECT * FROM people";
        String[] columnNames = {"id", "firstname", "surname", "birthdate", "birthplace", "salary", "status"};
        ArrayList<String[]> data = new ArrayList<>();


        try (
                Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY);) {
            System.out.println("Подключение установлено!");
            while (rs.next()) {
                String[] row = {rs.getString("id"),
                        rs.getString("firstname"),
                        rs.getString("surname"),
                        rs.getString("birthdate"),
                        rs.getString("birthplace"),
                        rs.getString("salary"),
                        rs.getString("status")};
                data.add(row);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Object[][] res = new Object[data.size()][];
        data.toArray(res);

        JTable table = new JTable(res, columnNames);
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        adminpanel.add(scrollPane);
        adminframe.setVisible(true);


        return null;
    }

    /**
     *  Функция поиска по id, поиск по имени, Расчет зарплаты по базе данных, поиск по дате рождения - DONE
     *
     * @return
     */
    public static Component frame_for_viewer(){
        JPanel viewerpanel = new JPanel();
        JFrame viewerframe = new JFrame("Viewer interface");
        JButton addButton = new JButton();
        JButton updateButton = new JButton();

        JButton find_id_Button = new JButton();
        JButton find_by_name_Button = new JButton();
        JButton find_by_birthdate_But = new JButton();
        JButton sum_of_salary_But = new JButton();

        viewerframe.setSize(600,600);
        viewerframe.setVisible(true);
        viewerframe.add(viewerpanel);

        find_id_Button = new JButton("Найти сотрудника по ID");
        find_id_Button.setBounds(10,320,350,300);

        find_id_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("find by id");
                find_by_id();
            }
        });


        viewerpanel.add(find_id_Button);

        find_by_name_Button = new JButton("Найти сотрудника по имени");
        find_by_name_Button.setBounds(10,320,350,300);

        find_by_name_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("find by name");
                find_by_name();
            }
        });

        viewerpanel.add(find_by_name_Button);

        find_by_birthdate_But = new JButton("Найти сотрудника по дате рождения");
        find_by_birthdate_But.setBounds(10,320,350,300);

        find_by_birthdate_But.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("find by name");
                find_by_birthdate();
            }
        });

        viewerpanel.add(find_by_birthdate_But);

        sum_of_salary_But = new JButton("Расчет суммы зарплаты сотрудников");
        sum_of_salary_But.setBounds(10,320,350,300);

        sum_of_salary_But.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("find by name");
                sum_of_salary();
            }
        });

        viewerpanel.add(sum_of_salary_But);

        String QUERY = "SELECT * FROM people";
        String[] columnNames = {"id", "firstname", "surname", "birthdate", "birthplace", "salary", "status"};
        ArrayList<String[]> data = new ArrayList<>();


        try (
                Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY);) {
            System.out.println("Подключение установлено!");
            while (rs.next()) {
                String[] row = {rs.getString("id"),
                        rs.getString("firstname"),
                        rs.getString("surname"),
                        rs.getString("birthdate"),
                        rs.getString("birthplace"),
                        rs.getString("salary"),
                        rs.getString("status")};
                data.add(row);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Object[][] res = new Object[data.size()][];
        data.toArray(res);

        JTable table = new JTable(res, columnNames);
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        viewerpanel.add(scrollPane);
        viewerpanel.setVisible(true);

        return null;
    }

    /**
     * Сделан функционал
     * | find_by_id
     * | find_by_name
     * | find_by_birthdate
     * Вывод в отдельное окно или таблицу (????) - display_the_db
     */
    public static void find_by_id() {
        JPanel find_by_idpanel = new JPanel();

        JFrame find_by_idframe = new JFrame();
        find_by_idframe.setLayout(new BorderLayout());
        JButton find_by_idBut = new JButton("Найти");
        JLabel quest = new JLabel("Введите id сотрудника:");

        JLabel nothing = new JLabel(" ");
        JTextField id_quest = new JTextField(20);

        find_by_idframe.setSize(280,150);

        find_by_idframe.add(find_by_idpanel, BorderLayout.CENTER);

        quest.setBounds(300,600,80,25);
        find_by_idpanel.add(quest, BorderLayout.CENTER);

        find_by_idpanel.add(nothing, BorderLayout.CENTER);

        id_quest.setBounds(300,540,400,450);
        find_by_idpanel.add(id_quest, BorderLayout.CENTER);


        find_by_idBut.setBounds(300,0,350,300);
        find_by_idpanel.add(find_by_idBut, BorderLayout.CENTER);

        find_by_idBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                final String DATABASE_URL = "jdbc:mysql://localhost:3306/mydb";
                final String USERNAME = "root";
                final String PASSWORD = "root";

                String id_que = id_quest.getText();

                String QUERY = "SELECT * FROM people WHERE id=";

                QUERY += id_que;

                Connection conn = null;
                try {
                    conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                    Statement stmt = conn.createStatement();
//                    stmt.executeQuery(QUERY);
                    ResultSet rs = stmt.executeQuery(QUERY); {
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

                    }
                    System.out.println("Сотрудник найден");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        find_by_idframe.setVisible(true);

    }

    public static void find_by_name() {
        JPanel find_by_namepanel = new JPanel();

        JFrame find_by_nameframe = new JFrame();
        find_by_nameframe.setLayout(new BorderLayout());
        JButton find_by_nameBut = new JButton("Найти");
        JLabel quest = new JLabel("Введите id сотрудника:");

        JLabel nothing = new JLabel(" ");
        JTextField name_quest = new JTextField(20);

        find_by_nameframe.setSize(280,150);

        find_by_nameframe.add(find_by_namepanel, BorderLayout.CENTER);

        quest.setBounds(300,600,80,25);
        find_by_namepanel.add(quest, BorderLayout.CENTER);

        find_by_namepanel.add(nothing, BorderLayout.CENTER);

        name_quest.setBounds(300,540,400,450);
        find_by_namepanel.add(name_quest, BorderLayout.CENTER);


        find_by_nameBut.setBounds(300,0,350,300);
        find_by_namepanel.add(find_by_nameBut, BorderLayout.CENTER);

        find_by_nameBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                final String DATABASE_URL = "jdbc:mysql://localhost:3306/mydb";
                final String USERNAME = "root";
                final String PASSWORD = "root";

                String firstName = name_quest.getText();

                String QUERY = "SELECT * FROM people WHERE firstname=";

                QUERY += "'" + firstName + "'";

                Connection conn = null;
                try {
                    conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                    Statement stmt = conn.createStatement();
//                    stmt.executeQuery(QUERY);
                    ResultSet rs = stmt.executeQuery(QUERY); {
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

                    }
                    System.out.println("Сотрудник найден");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        find_by_nameframe.setVisible(true);

    }

    public static void find_by_birthdate(){
        JPanel find_by_birthdatepanel = new JPanel();

        JFrame find_by_birthdateframe = new JFrame();
        find_by_birthdateframe.setLayout(new BorderLayout());
        JButton find_by_birthdateBut = new JButton("Найти");
        JLabel quest = new JLabel("Введите дату рождения сотрудника:");

        JLabel nothing = new JLabel(" ");
        JTextField birthdate_quest = new JTextField(20);

        find_by_birthdateframe.setSize(280,150);

        find_by_birthdateframe.add(find_by_birthdatepanel, BorderLayout.CENTER);

        quest.setBounds(300,600,80,25);
        find_by_birthdatepanel.add(quest, BorderLayout.CENTER);

        find_by_birthdatepanel.add(nothing, BorderLayout.CENTER);

        birthdate_quest.setBounds(300,540,400,450);
        find_by_birthdatepanel.add(birthdate_quest, BorderLayout.CENTER);


        find_by_birthdateBut.setBounds(300,0,350,300);
        find_by_birthdatepanel.add(find_by_birthdateBut, BorderLayout.CENTER);

        find_by_birthdateBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                final String DATABASE_URL = "jdbc:mysql://localhost:3306/mydb";
                final String USERNAME = "root";
                final String PASSWORD = "root";

                String birthDate = birthdate_quest.getText();

                String QUERY = "SELECT * FROM people WHERE birthdate=";

                QUERY += "'" + birthDate + "'";

                Connection conn = null;
                try {
                    conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                    Statement stmt = conn.createStatement();
//                    stmt.executeQuery(QUERY);
                    ResultSet rs = stmt.executeQuery(QUERY); {
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

                    }
                    System.out.println("Сотрудник найден");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        find_by_birthdateframe.setVisible(true);


    }

    /**
     * Сделано - функционал и вывод в отдельное диалоговое окно
     */
    public static void sum_of_salary(){

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

        JOptionPane optionPane = new JOptionPane();

        int n = optionPane.showConfirmDialog(null, "Итоговая зарплата составляет:"+ salarySum, null, JOptionPane.CANCEL_OPTION);

    }

    /**
     * Сделано
     */
    public static void updateEmployee(){
        JPanel updatepanel = new JPanel();

        JFrame updateframe = new JFrame();
        updateframe.setLayout(new BorderLayout());
        JButton updateBut = new JButton("Изменить данные");
        JLabel question = new JLabel("Введите id сотрудника для изменения:");

        JLabel nothing = new JLabel(" ");
        JTextField id_question = new JTextField(20);

        updateframe.setSize(280,150);

        updateframe.add(updatepanel, BorderLayout.CENTER);

        question.setBounds(300,600,80,25);
        updatepanel.add(question, BorderLayout.CENTER);

        updatepanel.add(nothing, BorderLayout.CENTER);

        id_question.setBounds(300,540,400,450);
        updatepanel.add(id_question, BorderLayout.CENTER);


        updateBut.setBounds(300,0,350,300);
        updatepanel.add(updateBut, BorderLayout.CENTER);

        updateBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel addpanel = new JPanel();

                JFrame addframe = new JFrame();
                addframe.setLayout(new BorderLayout());
                JButton addBut = new JButton("Изменить");
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

                        String id_que = id_question.getText();
                        String firstName = firstname.getText();
                        String surName = surname.getText();
                        String birthDate = birthdate.getText();
                        String birthPlace = birthplace.getText();
                        Double salarys = Double.valueOf(salary.getText());
                        String statuss = status.getText();


                        String QUERY = "UPDATE people SET ";

                        QUERY += "firstname='" + firstName + "'," +
                                "surname='" + surName + "'," +
                                "birthdate='" + birthDate + "'," +
                                "birthplace='" + birthPlace + "'," +
                                "salary=" + salarys + "," +
                                "status='" + statuss + "'" +
                                " WHERE id=" + id_que;

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
        });

        updateframe.setVisible(true);

    }
    /**
     * Сделано
     */
    public static void deleteEmployee(){
        JPanel deletepanel = new JPanel();

        JFrame deleteframe = new JFrame();
        deleteframe.setLayout(new BorderLayout());
        JButton deleteBut = new JButton("Удалить");
        JLabel question = new JLabel("Введите id сотрудника для удаления из БД:");

        JLabel nothing = new JLabel(" ");
        JTextField id_question = new JTextField(20);

        deleteframe.setSize(280,150);

        deleteframe.add(deletepanel, BorderLayout.CENTER);

        question.setBounds(300,600,80,25);
        deletepanel.add(question, BorderLayout.CENTER);

        deletepanel.add(nothing, BorderLayout.CENTER);

        id_question.setBounds(300,540,400,450);
        deletepanel.add(id_question, BorderLayout.CENTER);


        deleteBut.setBounds(300,0,350,300);
        deletepanel.add(deleteBut, BorderLayout.CENTER);

        deleteBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel DELpanel = new JPanel();

                JFrame DELframe = new JFrame();
                DELframe.setLayout(new BorderLayout());
                JButton DELBut = new JButton("Удалить");
                JLabel question = new JLabel("Вы точно уверены?");

                DELframe.setSize(280,350);
                DELframe.add(DELpanel, BorderLayout.CENTER);

                question.setBounds(300,600,80,25);
                DELpanel.add(question, BorderLayout.CENTER);

                DELBut.setBounds(300,0,350,300);
                DELpanel.add(DELBut, BorderLayout.CENTER);

                DELframe.setVisible(true);
                // Функция для кнопки добавления сотрудника в БД
                DELBut.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        final String DATABASE_URL = "jdbc:mysql://localhost:3306/mydb";
                        final String USERNAME = "root";
                        final String PASSWORD = "root";

                        String id_que = id_question.getText();

                        String QUERY = "DELETE FROM people WHERE id=";

                        QUERY += id_que;

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
        });

        deleteframe.setVisible(true);


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


    public static void insert_into_login_and_passwords(){
        System.out.println("Registration");
        JPanel regpanel = new JPanel();
        JFrame regframe = new JFrame();

        JLabel regLabel = new JLabel("Введите Логин");
        JLabel regLabel_pass = new JLabel("Введите Логин");

        JTextField log_in = new JTextField();
        JPasswordField reg_pass = new JPasswordField();

        JButton regbutton_n = new JButton();
        /* Для форм заполнения логина и пароля */

        regframe.setSize(200,200);
        regframe.setVisible(true);
        regframe.add(regpanel);

        regLabel = new JLabel("Введите Логин");
        regLabel.setBounds(10,20,80,25);
        regpanel.add(regLabel);

        log_in = new JTextField(20);
        log_in.setBounds(100,20,165,25);
        regpanel.add(log_in);

        regLabel_pass = new JLabel("Введите Пароль");
        regLabel_pass.setBounds(10,80,80,25);
        regpanel.add(regLabel_pass);

        reg_pass = new JPasswordField(20);
        reg_pass.setBounds(100,80,165,25);
        regpanel.add(reg_pass);

        regbutton_n = new JButton("Зарегистрироваться");
        regbutton_n.setBounds(100,80,165,25);
        JTextField finalLog_in = log_in;
        JPasswordField finalReg_pass = reg_pass;
        regbutton_n.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String DATABASE_URL = "jdbc:mysql://localhost:3306/mydb";
                final String USERNAME = "root";
                final String PASSWORD = "root";

                String log_in_in = finalLog_in.getText();
                String passwrd = finalReg_pass.getText();



                String QUERY = "INSERT INTO log_pass(log_in, passwrd) VALUES ";

                QUERY += "('" +
                        log_in_in + "','" +
                        passwrd
                        + "')";

                Connection conn = null;
                try {
                    conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(QUERY);
                    System.out.println("Регистрация прошла успешно!");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        regpanel.add(regbutton_n);
    }




    public static boolean connectToDatabase_login_and_passwords() {
        String QUERY = "SELECT * FROM log_pass";

        try (
                Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY);) {
            System.out.println("Подключение установлено c базой паролей и логинов!");
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
