package org.example.task1;

import org.example.task1.VectorCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class VectorCalculatorTests {


    @Test
    @DisplayName("Тестируем операции по вычилению модуля 3D вектора (VectorCalculator.module)")
    void testModule()
    {
        assertTrue(VectorCalculator.module(1.0,2.0,2.0) == 3.0);
    }

    @Test
    @DisplayName("Тестируем операции по вычитанию 3D векторов (VectorCalculator.sbstrct)")
    void testSbstrct()
    {
        double [] expectedResult = new double[] {0, 0, 0};
        assertTrue(Arrays.equals(VectorCalculator.sbstrct(1.0,2.0,2.0, 1.0, 2.0, 2.0), expectedResult));
    }

    @Test
    @DisplayName("Тестируем операции по сложению 3D векторов (VectorCalculator.addition)")
    void testAddition()
    {
        double [] expectedResult = new double[] {2, 4, 4};
        assertTrue(Arrays.equals(VectorCalculator.addition(1.0,2.0,2.0, 1.0, 2.0, 2.0), expectedResult));
    }

    @Test
    @DisplayName("Тестируем операции по скалярному умножению 3D векторов (VectorCalculator.scalarMltpl)")
    void testScalarMltpl()
    {
        assertTrue(VectorCalculator.scalarMltpl(1.0, 2.0, 2.0, 1.0, 2.0, 2.0) == 9);
    }

    @Test
    @DisplayName("Тестируем операции по векторному умножению 3D векторов (VectorCalculator.vectorMltpl)")
    void testVectorMltpl()
    {
        double [] expectedResult = new double[] {0.0, -0.0, 0.0};
        assertTrue(Arrays.equals(VectorCalculator.vectorMltpl(1.0,2.0,2.0, 1.0, 2.0, 2.0), expectedResult));
    }

    @Test
    @DisplayName("Тестируем операции по нахождению угла между 3D векторами (VectorCalculator.angle)")
    void testAngle()
    {
        assertTrue(VectorCalculator.angle(1.0, 2.0, 2.0, 1.0, 2.0, 2.0) == 0);
    }
}
