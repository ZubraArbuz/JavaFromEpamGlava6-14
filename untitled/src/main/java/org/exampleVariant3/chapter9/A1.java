package org.exampleVariant3.chapter9;

import java.io.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class A1 {
    static class InvalidNumberException extends Exception {
        public InvalidNumberException(String message) {
            super(message);
        }
    }

    public static void processFile(String filePath) {
        List<BigDecimal> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    numbers.add(parseLine(line));
                } catch (InvalidNumberException | ParseException e) {
                    System.err.println("Ошибка обработки строки: " + line + ". Причина: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + filePath);
        } catch (IOException e) {
            System.err.println("Ошибка ввода/вывода при работе с файлом: " + e.getMessage());
        } catch (OutOfMemoryError e) {
            System.err.println("Недостаточно памяти для обработки файла.");
        }

        if (!numbers.isEmpty()) {
            BigDecimal sum = numbers.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal average = sum.divide(BigDecimal.valueOf(numbers.size()), BigDecimal.ROUND_HALF_UP);
            System.out.println("Сумма: " + sum);
            System.out.println("Среднее значение: " + average);
        } else {
            System.out.println("Нет корректных чисел для обработки.");
        }
    }

    public static BigDecimal parseLine(String line) throws InvalidNumberException, ParseException {
        String[] parts = line.split(";");
        if (parts.length != 2) {
            throw new InvalidNumberException("Неверный формат строки. Ожидается два значения: число и локаль.");
        }

        String numberPart = parts[0].trim();
        String localePart = parts[1].trim();

        Locale locale = Locale.forLanguageTag(localePart);
        if (locale.getLanguage().isEmpty()) {
            throw new InvalidNumberException("Некорректная локаль: " + localePart);
        }

        NumberFormat numberFormat = NumberFormat.getInstance(locale);
        Number number = numberFormat.parse(numberPart);

        BigDecimal bigDecimal = new BigDecimal(number.toString());
        if (bigDecimal.abs().compareTo(new BigDecimal("1E308")) > 0) {
            throw new InvalidNumberException("Число выходит за пределы допустимых значений.");
        }

        return bigDecimal;
    }

    public static void main(String[] args) {
        String filePath = "D:\\3\\Java\\Glava6\\untitled\\src\\main\\java\\org\\example\\chapter9\\file.txt";
        processFile(filePath);
    }
}
