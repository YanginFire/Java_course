package org.example.task1;



/**
 * Калькулятор 3D векторов "Спаси и Сохрани"
 */
public class VectorCalculator {

    /**
     * Скалярное произведение 3D векторов
     * @return double
     */
    public static double scalarMltpl(double x, double y, double z, double x1, double y1, double z1) {
        return module(x, y, z) * module(x1, y1, z1);
    }

    /**
     * Векторное произведение 3D векторов
     * @return double
     */
    public static double[] vectorMltpl(double x, double y, double z, double x1, double y1, double z1) {
        double [] answer = new double[3];

        answer[0] = (y * z - y1 * z1);
        answer[1] = -(x * z - z1 * x1);
        answer[2] = (x * y - x1 * y1);

        return answer;
    }

    /**
     * Вычитание 3D векторов
     * @return double
     */
    public static double[] sbstrct(double x, double y, double z, double x1, double y1, double z1) {
        double [] answer = new double[3];

        answer[0] = (x - x1);
        answer[1] = (y - y1);
        answer[2] = (z - z1);

        return answer;
    }

    /**
     * Сложение 3D векторов
     * @return double
     */
    public static double[] addition(double x, double y, double z, double x1, double y1, double z1) {
        double [] answer = new double[3];

        answer[0] = (x + x1);
        answer[1] = (y + y1);
        answer[2] = (z + z1);

        return answer;
    }

    /**
     * Модуль 3D вектора
     * @return double
     */
    public static double module(double x, double y, double z) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    /**
     * Угол между двумя 3D векторами
     * @return double
     */
    public static double angle(double x, double y, double z, double x1, double y1, double z1) {
        return Math.acos(scalarMltpl(x, y, z, x1, y1, z1) / (module(x, y, z) * module(x1, y1, z1)));
    }
}
