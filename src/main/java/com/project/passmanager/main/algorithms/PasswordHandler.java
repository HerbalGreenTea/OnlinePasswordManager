package com.project.passmanager.main.algorithms;

import org.apache.commons.lang3.RandomStringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

/**
 * Класс `PasswordHandler` представляет собой реализацию интерфейса `IPasswordHandler` и предназначен для работы с паролями.
 * Он предоставляет методы для шифрования паролей и проверки соответствия хешированных паролей открытому.
 */
@Component
public class PasswordHandler implements IPasswordHandler {
    /**
     * Шифрует переданный пароль с использованием хеширования BCrypt и добавляет к нему случайно сгенерированные "перец" (pepper).
     *
     * @param password пароль, который нужно зашифровать
     * @return хешированный пароль с добавленным "перцем"
     */
    @Override
    public String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt() + getPepper());
    }

    /**
     * Генерирует и возвращает случайно сгенерированные "перец" (pepper), которое может быть добавлено к хешированному паролю.
     *
     * @return случайно сгенерированные "перец"
     */
    private String getPepper() {
        return RandomStringUtils.random((int) (Math.random() * 100), true, true);
    }

    /**
     * Проверяет, соответствует ли заданный кандидат хешированному паролю.
     *
     * @param candidate кандидат на соответствие
     * @param hashed    хешированный пароль, с которым нужно сравнить кандидата
     * @return true, если кандидат соответствует хешированному паролю, иначе - false
     */
    @Override
    public Boolean isMatch(String candidate, String hashed) {
        return BCrypt.checkpw(candidate, hashed);
    }
}
