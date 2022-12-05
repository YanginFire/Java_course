package org.example.task5;

import java.util.*;

public class one {


    /* ошибки: вычисления работают только с положительными числами
     например -2 * 4 выдаст ошибку
     (1 - 3) * 4 тоже выдаст ошибку
     !НО! (1+2)-10 выдаст -7 !
     также не учитывается порядок вычислений
     например 2 + 3*4 выдаст 20 вместо 14
     !НО! 2 + (3*4) выдаст 14 !
     */


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String equation;

        while (true) {
            System.out.println("Введите математическое выражение для проверки(quit для выхода):");
            equation = in.nextLine();

            if (Objects.equals(equation, "quit")) break;

            if (equationCheck(equation)) {
                System.out.println("Выражение верное");
                System.out.println("Ответ: " + calculation(simplifyEquation(equation)));
            } else {
                System.out.println("Выражение неверное");
            }
            ;

        }
    }

    public static boolean equationCheck(String equation) {
        ArrayDeque<Character> stack = new ArrayDeque<Character>();

        for (char c : equation.toCharArray()) {
            if (c == '{' || c == '(' || c == '[')
                stack.addLast(c); // если символ это открывающая скобка -> добавляем в стек
            else if (c == '}') {                                    // если символ закрывающая кобка -> удаляем последний элемент стека
                if (stack.getLast() == '{') {
                    stack.removeLast();
                } else return false;
            } else if (c == ')') {
                if (stack.getLast() == '(') {
                    stack.removeLast();
                } else return false;
            } else if (c == ']') {
                if (stack.getLast() == '[') {
                    stack.removeLast();
                } else return false;
            }
        }

        if (stack.size() != 0) return false;                          // если стек не пустой значит выражение неверное
        return true;
    }

    public static String simplifyEquation(String equation) {
        String branch = "", smplEquation = "", mode = "n";

        equation = equation.replace(")(", ")*(");

        ArrayDeque<Character> stack = new ArrayDeque<Character>();

        for (char c : equation.toCharArray()) {
            if (c == '(') {
                branch = "";
                mode = "b";
            } else if (c == ')') {
                smplEquation += calculation(branch);
                mode = "n";
            } else if (Character.isDigit(c) || c == '+' || c == '-' || c == '*') {
                if (mode == "b") branch += c;
                else smplEquation += c;
            }
        }

        if (smplEquation.length() == 0) smplEquation = branch;

        return smplEquation;
    }

    public static int calculation(String equation) {
        int ans, temp;
        String operation = "";
        String[] values = equation.split("[+*-]");
        ArrayDeque<Integer> stack = new ArrayDeque<Integer>();

        for (String val : values) {
            stack.addLast(Integer.parseInt(val));
        }

        ans = stack.pollFirst();

        for (char c : equation.toCharArray()) {
            if (c == '+' || c == '-' || c == '*') {
                if (c == '+') ans += stack.pollFirst();
                else if (c == '-') ans -= stack.pollFirst();
                else ans *= stack.pollFirst();
            }
        }

        return ans;
    }
}