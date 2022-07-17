package com.aworldoffear;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    private static String finalResult = null;
    private static byte finalByteResult = 0;
    private static byte firstNumber = 1;
    private static byte secondNumber = 1;

    public static void main(String[] args) throws Exception {
        asciiArt(); // Рисование обязательного ASCII арта перед использованием калькулятора.
        Scanner in = new Scanner(System.in);
        System.out.print("Введите желаемую операцию: ");
        String numberOperation = in.nextLine();
        String[] dataArray = numberOperation.split(" ");
        if (dataArray.length == 1) {
            System.out.println("Выброшено исключение! Строка не является математической операцией.");
            throw new Exception();
        } else if (dataArray.length > 3) {
            System.out.println("Выброшено исключение! Операция не удовлетворяет заданию. Нужно 2 операнда и 1 оператор.");
            throw new Exception();
        } else if (dataArray.length <= 2) {
            System.out.println("Выброшено исключение! Нужно вводить 2 операнда и 1 оператор через пробел.");
            throw new Exception();
        }
        boolean testResult = testForRomanNumerals(dataArray);
        if (testResult == true) {
            finalResult = calc(dataArray);
            System.out.println(finalResult);
        } else {
            finalByteResult = calc(firstNumber, secondNumber, dataArray);
            System.out.println(finalByteResult);
        }
        in.close();
    }

    public static boolean testForRomanNumerals(String dataArray[]) throws Exception {
        boolean testForRomanNumerals = false;
        boolean testForFirstNumber = false, testForSecondNumber = false;
        for (RomanNumeral x : RomanNumeral.values()) {
            if (x.name().equals(dataArray[0])) {
                testForFirstNumber = true;
            }
        }
        for (RomanNumeral y : RomanNumeral.values()) {
            if (y.name().equals(dataArray[2])) {
                testForSecondNumber = true;
            }
        }
        if (testForFirstNumber == true && testForSecondNumber == true) {
            testForRomanNumerals = true;
        } else if (testForFirstNumber == false && testForSecondNumber == false) {
            testForRomanNumerals = false;
        } else {
            System.out.println("Выброшено исключение! Можно вводить только корректные римские цифры в одной строке или только арабские цифры в одной строке.");
            throw new Exception();
        }
        return testForRomanNumerals;
    }

    public static String calc(String dataArray[]) throws Exception {
        byte result = 0;
        for (RomanNumeral x : RomanNumeral.values()) { // Цикл для римских цифр.
                if (x.name().equals(dataArray[0])) {
                    firstNumber = (byte) Integer.parseInt(x.getRomanNumeral());
                    if (firstNumber > 10) {
                        System.out.println("Выброшено исключение! Римские цифры не должны превышать при вводе X.");
                        throw new Exception();
                    }
                }

                if (x.name().equals(dataArray[2])) {
                    secondNumber = (byte) Integer.parseInt(x.getRomanNumeral());
                    if (secondNumber > 10) {
                        System.out.println("Выброшено исключение! Римские цифры не должны превышать при вводе X.");
                        throw new Exception();
                    }
            }
        }
        switch (dataArray[1]) {
            case "+": result = (byte) (firstNumber + secondNumber); break;
            case "-": result = (byte) (firstNumber - secondNumber); break;
            case "/": result = (byte) (firstNumber / secondNumber); break;
            case "*": result = (byte) (firstNumber * secondNumber); break;
            default:
                System.out.println("Выброшено исключение! Разрешено вводить следующие операторы: -, +, *, /.");
                throw new Exception();
        }
        if (result <= 0) {
            System.out.println("Выброшено исключение! В системе римских цифр отсутствует цифра 0 или цифры меньше нуля.");
            throw new Exception();
        }
        finalResult = Byte.toString(result);
        for (RomanNumeral x : RomanNumeral.values()) {
            if (x.getRomanNumeral().equals(finalResult)) {
                finalResult = x.name();
                break;
            }
        }
        return finalResult;
    }

    public static byte calc (byte firstNumber, byte secondNumber, String dataArray[]) throws Exception {
        byte result = 0;
        try {
            firstNumber = (byte) Integer.parseInt(dataArray[0]);
        }catch (Exception e) {
            System.out.println("Выброшено исключение! Некорректное первое число.");
            throw new Exception();
        }
        try {
            secondNumber = (byte) Integer.parseInt(dataArray[2]);
        }catch (Exception e) {
            System.out.println("Выброшено исключение! Некорректное второе число.");
            throw new Exception();
        }
        if (firstNumber < 1 || firstNumber > 10) {
            System.out.println("Выброшено исключение! Число при вводе не должно быть меньше 1 или больше 10.");
            throw new Exception();
        }
        if (secondNumber < 1 || secondNumber > 10) {
            System.out.println("Выброшено исключение! Число при вводе не должно быть меньше 1 или больше 10.");
            throw new Exception();
        }
        switch (dataArray[1]) {
            case "+": result = (byte) (firstNumber + secondNumber); break;
            case "-": result = (byte) (firstNumber - secondNumber); break;
            case "/": result = (byte) (firstNumber / secondNumber); break;
            case "*": result = (byte) (firstNumber * secondNumber); break;
            default:
                System.out.println("Выброшено исключение! Разрешено вводить следующие операторы: -, +, *, /.");
                throw new Exception();
        }
        return result;
    }

    private static void asciiArt() {
        File asciiArt = new File("ASCII_Art.txt");
        try {
            Scanner reader = new Scanner(asciiArt);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл " + asciiArt.getName() + " не был найден.");
        }
    }
}