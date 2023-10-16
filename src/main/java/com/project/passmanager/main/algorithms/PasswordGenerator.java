package com.project.passmanager.main.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.CharUtils;


/**
 * Singleton класс `PasswordGenerator` предоставляет возможность генерировать пароли с разными параметрами.
 * Пользователь может настроить включение/выключение использования нижнего регистра, верхнего регистра,
 * цифр и специальных символов при генерации пароля.
 */
//TODO синглтон с помощью DI
public class PasswordGenerator {
    private static PasswordGenerator instance;
    //TODO встроееная библиотек букв
    private static final String LOWERCASE = IntStream.rangeClosed('a', 'z').mapToObj(c -> String.valueOf((char) c)).collect(Collectors.joining());
    private static final String UPPERCASE = IntStream.rangeClosed('A', 'Z').mapToObj(c -> String.valueOf((char) c)).collect(Collectors.joining());
    private static final String DIGITS = IntStream.rangeClosed('0', '9').mapToObj(c -> String.valueOf((char) c)).collect(Collectors.joining());
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?";


    /**
     * Приватный конструктор, чтобы предотвратить создание экземпляров извне.
     */
    private PasswordGenerator() {}

    public static PasswordGenerator getInstance() {
        if (instance == null) {
            instance = new PasswordGenerator();
        }
        return instance;
    }



    /**
     * Генерирует пароль заданной длины с учетом настроек.
     *
     * @param passwordSettings настройки пароля
     * @return Сгенерированный пароль.
     */
    public String build(PasswordSettings passwordSettings) {
        if (passwordSettings == null) {
            return "";
        }

        StringBuilder password = new StringBuilder(passwordSettings.getPasswordLength());
        Random random = new Random(System.nanoTime());

        List<String> charCategories = new ArrayList<>(4);
        if (passwordSettings.isUseLower()) {
            charCategories.add(LOWERCASE);
        }
        if (passwordSettings.isUseUpper()) {
            charCategories.add(UPPERCASE);
        }
        if (passwordSettings.isUseDigits()) {
            charCategories.add(DIGITS);
        }
        if (passwordSettings.isUseSpecialCharacters()) {
            charCategories.add(SPECIAL_CHARACTERS);
        }

        for (int i = 0; i < passwordSettings.getPasswordLength(); i++) {
            String charCategory = charCategories.get(random.nextInt(charCategories.size()));
            int position = random.nextInt(charCategory.length());
            password.append(charCategory.charAt(position));
        }

        return new String(password);
    }
}


