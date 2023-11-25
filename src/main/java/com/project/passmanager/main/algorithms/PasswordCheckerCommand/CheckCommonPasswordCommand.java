package com.project.passmanager.main.algorithms.PasswordCheckerCommand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CheckCommonPasswordCommand implements IPasswordCheckCommand {

    /**
     * Проверяет, содержит ли пароль стандартные слова.
     *
     * @param password Пароль для проверки.
     * @return -40, если пароль содержит общие слова, иначе - 15.
     */
    @Override
    public int execute(String password) {
        var filePath = "resources/common_password.txt";

        try {
            // Список известных общих паролей
            var commonPasswords = Files.readAllLines(Paths.get(filePath));

            // Приведем пароль к нижнему регистру для сравнения без учета регистра
            password = password.toLowerCase();

            // Проверяем, содержится ли пароль в списке известных общих паролей
            for (var commonPassword : commonPasswords) {
                if (password.contains(commonPassword.toLowerCase())) {
                    return -40;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return 15;
    }
}
